<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Save Customer</title>
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet">

<link
	href="${pageContext.request.contextPath}/resources/css/add-customer-style.css"
	rel="stylesheet">
	
	<style>
		.error{color:red}
	</style>
</head>
<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>

		<div id="container">
			<h3>Save Customer</h3>

			<form:form action="saveCustomer" modelAttribute="customer"
				method="POST">

				<!-- need to associate this data with the customer Id -->
				<form:hidden path="id" />

				<table>

					<tbody>
						<tr>
							<td><label>First Name:</label></td>
							<td><form:input path="firstName" /> <form:errors
									path="firstName" cssClass="error" /></td>
						</tr>

						<tr>
							<td><label>Last Name:</label></td>
							<td><form:input path="lastName" /> <form:errors
									path="lastName" cssClass="error" /></td>
						</tr>

						<tr>
							<td><label>Email:</label></td>
							<td><form:input path="email" /></td>
						</tr>

						<tr>
							<td><label></label></td>
							<td><input type="submit" value="Save" class="save" /></td>
						</tr>
					</tbody>

				</table>


			</form:form>

			<div style="">
				<p>
					<a href="${pageContext.request.contextPath}/customer/list" /> Back
					to List
				</p>
			</div>

		</div>

	</div>

</body>
</html>