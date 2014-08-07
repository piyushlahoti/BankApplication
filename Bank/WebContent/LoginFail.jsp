<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script>
	function display()
	{
		window.location = "Home.html";
	}
</script>


</head>

<body onload="setTimeout(display(),5000)">
<%
	out.println("Login failed");	
%>
<a href="Home.html">Go Back</a>
</body>
</html>