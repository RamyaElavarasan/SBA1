<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<jsp:include page="header.jsp" />
	<hr />
	<div align=center>
		<h2>eLoan Get Loan Status by Application No</h2>
		<hr />
		<a href="userhome1.jsp">Home</a>
		<hr />
		<form action="displaystatus" method="post">
			<div>
				<label for="applno">Application Number:</label> <input
					type="text" id="applno" name="applno" required>
			</div>
			<br />
			
			<div>
				<button>Get Status</button>
			</div>
		</form>
	</div>
	<hr />
	<jsp:include page="footer.jsp" />

</body>
</html>