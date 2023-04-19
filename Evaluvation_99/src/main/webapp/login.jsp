<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>

</head>
<body>
	<h1>Login Page</h1>
    	<form action="login" method="get">

           <label>User:</label>
           <input type="radio" id="user" name="user-type" value="user" required><br><br>
           <label>Admin:</label>
           <input type="radio" id="admin" name="user-type" value="admin" required><br><br>


    		<label for="username">Username:</label>
    		<input type="text" id="username" name="username" required><br><br>
    		<label for="password">Password:</label>
    		<input type="password" id="password" name="password" required><br><br>
             <input type="submit" value="login">



    	</form>
</body>
</html>