<%@page import="com.iiht.evaluation.eloan.model.LoanInfo"%> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- write the code to display the loan status information 
	     received from usercontrollers' displaystatus method
	-->
        
		<jsp:include page="header.jsp"/>
		
		<h2>Loan Details</h2>
		
		
			 	
		<table>
			<tr><td><strong>Loan Application number</strong></td>
				<td><em>${loan.applno}</em></td></tr>
			<tr><td><strong>Loan Purpose</strong></td>
				<td><em>${loan.purpose}</em></td></tr>	
			<tr><td><strong>Amount Requested</strong></td>
				<td><em>${loan.amtrequest}</em></td></tr>
			<tr><td><strong>Date of Application</strong></td>
				<td><em>${loan.doa}</em></td></tr>
			<tr><td><strong>Business Structure</strong></td>
				<td><em>${loan.bstructure}</em></td></tr>
			<tr><td><strong>Business Indicator</strong></td>
				<td><em>${loan.bindicator}</em></td></tr>
			<tr><td><strong>Address</strong></td>
				<td><em>${loan.address}</em></td></tr>
			<tr><td><strong>Email</strong></td>
				<td><em>${loan.email}</em></td></tr>
			<tr><td><strong>Mobile</strong></td>
				<td><em>${loan.mobile}</em></td></tr>
			<tr><td><strong>Status</strong></td>
				<td><em>${loan.status}</em></td></tr>		
		</table> 
		

</html>