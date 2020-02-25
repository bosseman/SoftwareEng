<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Yahoo!!</title>
</head>
<body>
    <p><font color="red">${errorMessage}</font></p>
    <form action="/users.jsp" method="POST">
        Name : <input name="name" type="text" /> Password : <input name="password" type="password" /> <input type="submit" />
    </form>
    <p><form action = "/users/form" method = "GET"><input type = "submit" value = "Register an Account"/></form></p>
</body>
</html>