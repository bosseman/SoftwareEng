

This folder will house the interfaces used to access services.

	UserService --> Provide functions for communication from server to database
					boolean CreateUser(User user)
					Card[] GetFavorites(Long ID)
					Card GetFavorite(Long userId, long cardId)
	
	CardService --> Provide functions for communication from server to database. 
					GetCard(Long cardID)
					GetTopCards(int n)
	
	ScrapService -->  Provide functions for communication from server to database. 
					  This can be automated for every x hours
					  
					  Document ScrapWeb() 
					  String[][] CleanData(Document doc)
					  String[][] ComputeNewProjections()
					  boolean SaveData(Card card)