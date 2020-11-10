<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>admin home</title>
</head>
<body>
<div align=center>
<jsp:include page="header.jsp"/>
 <div align=center>
		
		<h2>eLoan Admin Dash Board</h2>

		<hr />
		<nav>
			<a href="adminhome1.jsp">Home</a> <span>|</span> 
			<a href="listall.jsp">List All</a> <span>|</span> <a
			href="process.jsp">Process Loan</a> <span>|</span> 
			<a href="index.jsp">Logout</a>
		</nav>
		<hr />
		<jsp:include page="footer.jsp" />
	</div>
</div>
</body>
</html>