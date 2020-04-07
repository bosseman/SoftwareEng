package com.CardTracker.SoftwareEng.Scraping;

import java.io.*;
import java.lang.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.CardTracker.SoftwareEng.entity.CardEntity;
import com.CardTracker.SoftwareEng.entity.CardPriceHistory;
import com.CardTracker.SoftwareEng.io.repository.CardRepository;

public class PSA10SilverPrizmRookies_Mark7 {
	
	@Autowired
	CardRepository cardRepo;
	
	public static ArrayList<String> listingNameList;
	public static ArrayList<BigDecimal> listingPriceList;
	
	public  void scrapCards(String[] args) throws IOException, ParseException {
		listingNameList = new ArrayList();
		listingPriceList = new ArrayList();
		String[] listingName_extract = null;
		String[] listingPrice_extract = null;
		StringBuilder builder = new StringBuilder();
		
		/*
		String directory = System.getProperty("user.dir");
		File file = new File(directory + "/src/main/java/com/CardTracker/SoftwareEng/Scraping/mark11.csv");
		FileWriter fw = new FileWriter(file, false); // true to not over ride
	*/
		/* Get the web site to scrape, in our case - eBay */
		Document document = Jsoup.connect(
				"https://www.ebay.com/sch/i.html?_from=R40&_nkw=PSA+10+silver+prizm+rookies&_sacat=0&LH_TitleDesc=0&rt=nc&LH_Sold=1&LH_Complete=1&_pgn=1")
				.get();

		/* To get the number of results and to get the page number of that */
		Elements listingCount = document.select("h1.srp-controls__count-heading > span:first-of-type"); // This is the
																										// number of
																										// search
																										// results
		String[] listingCount_split = listingCount.text().split(",");
		for (String character : listingCount_split) {
			builder.append(character);
		}
		int resultsNum = Integer.parseInt(builder.toString());
		int numPages = (resultsNum / 50) + 1;

		int page = 1;

		/* To get the listings' name */
		Elements listingName = document.select("a.s-item__link > h3").append("/split/"); // I append % in order to split
																							// them later
		String listingName_temp = listingName.text().replace(",", " ");

		/* To get the listings' prices */
		Elements listingsPrice = document.select("span.s-item__price").append("/split/"); // I append % in order to
																							// split them later
		String listingPrice_temp = listingsPrice.text().replace(",", " ");

		while (page < numPages) {
			if (page > 1) {
				document = Jsoup.connect(
						"https://www.ebay.com/sch/i.html?_from=R40&_nkw=PSA+10+silver+prizm+rookies&_sacat=0&LH_TitleDesc=0&rt=nc&LH_Sold=1&LH_Complete=1&_pgn="
								+ page)
						.get();
				/* To get the listings' name */
				listingName = document.select("a.s-item__link > h3").append("/split/"); // I append % in order to split
																						// them later
				listingName_temp = listingName.text().replace(",", " ");

				/* To get the listings' prices */
				listingsPrice = document.select("span.s-item__price").append("/split/"); // I append % in order to split
																							// them later
				listingPrice_temp = listingsPrice.text().replace(",", " ");
				System.out.println(page);
			}
			page++;

			int counter = 1;
			listingName_extract = listingName_temp.split("/split/");
			listingPrice_extract = listingPrice_temp.split("/split/");

			while (counter < listingName_extract.length - 1) {
				listingName_extract = listingName_temp.split("/split/");
				listingPrice_extract = listingPrice_temp.split("/split/");
				counter++;
			}
			for (String element : listingName_extract) {
				listingNameList.add(element);
			}

			for (String element : listingPrice_extract) {
				listingPriceList.add(parse(element, Locale.US));
				// System.out.println(listingPriceList);
			}
		}
		for (int k = 0; k < listingNameList.size(); k++) {
			CardEntity ce = new CardEntity();
			if(cardRepo.findByName(listingNameList.get(k)) == null) {
				ce.setCardName(listingNameList.get(k));
				ce.setCardType("PSA10SilverPrizmRookies_Mark7");
			}else {
				ce = cardRepo.findByName(listingNameList.get(k));
			}
			//Will need to add date
			CardPriceHistory cph = new CardPriceHistory();
			
			cph.setPrice(listingPriceList.get(k).intValue());
			
			ce.setPrices(cph);
			
			cardRepo.save(ce);
		}
		
	}

	public static BigDecimal parse(final String amount, final Locale locale) throws ParseException {
		final NumberFormat format = NumberFormat.getNumberInstance(locale);
		if (format instanceof DecimalFormat) {
			((DecimalFormat) format).setParseBigDecimal(true);
		}
		return (BigDecimal) format.parse(amount.replaceAll("[^\\d.,]", ""));
	}
}
