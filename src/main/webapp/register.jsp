<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<div align=center>
	<h2>eLoan Login</h2>
	<form action="registernewuser" method="POST">
		<div>
			<div><label for="username">Enter Username</label> </div>
			<div><input type="text" id="username" name="username"> </div>
		</div>
		<div>
			<div><label for="password">Enter password</label> </div>
			<div><input type="password" id="password" name="password"> </div>
		</div>
		<div>
			<div><input type="submit" value="Login"> </div>
		</div>
		<a action="user?action=registerNewUser">New User? register here</a>
	</form>
	</div>
	
<hr/>
<jsp:include page="footer.jsp"/>
</body>
</html>