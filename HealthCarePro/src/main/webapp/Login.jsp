<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login page</title>
</head>
<body>
<h3>Login page..</h3>
<%
 String value=(String)request.getAttribute("msg");
 if(value!=null)
	out.print(value);
%>
<form method="post" action="Login">
<table>
	<tr>
	<td>UserName:</td>
	<td><input type="text" name="uname" required placeholder="enter username"></td>
	</tr>
	<tr>
	<td>Password:</td>
	<td><input type="password" name="pwd" required placeholder="enter pwd"></td>
	</tr>
	<tr>
	<td> <input type="submit" value="login"></td>
	</tr>
</table>
</form>
</body>
</html>