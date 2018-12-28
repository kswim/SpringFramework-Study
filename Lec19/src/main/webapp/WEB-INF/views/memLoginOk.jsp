<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<h1> memLoginOk </h1>
	ID : ${member.memId}<br />
	PW : ${member.memPw}<br />
	
	<a href="/lec19/resources/html/index.html"> Go Main </a>
</body>
</html>
