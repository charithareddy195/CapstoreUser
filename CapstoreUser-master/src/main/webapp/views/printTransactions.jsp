<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Printing All Transactions</title>
</head>
<body>
	<div align="left">
		<table border="1">
			<tr>
				<th>Phone Number</th>
				<th>Transaction Type</th>
				<th>Amount</th>
				<th>Transaction Status</th>
				<th>Date</th>

			</tr>

			<c:forEach items="${transactions}" var="transactions">
				<tr>
					<td>${transactions.phoneNumber}</td>
					<td>${transactions.transactionType}</td>
					<td>${transactions.amount}</td>
					<td>${transactions.transactionStatus}</td>
					<td>${transactions.transactionDate}</td>
				</tr>
			</c:forEach>

		</table>

	</div>
</body>
</html>