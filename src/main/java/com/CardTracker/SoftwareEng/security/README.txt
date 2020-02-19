

Security folder will include:

	Authenticating users (We can also generate tokens to pass back) --> AuthenticationFilter.java
	Defining any constants needed for security such as token expiration time, token secret etc --> SecurityConstants
	Restricting request from unauthorized users (i.e a client cannot send a HTTP request with DELETE operation) -->WebSecurity
	