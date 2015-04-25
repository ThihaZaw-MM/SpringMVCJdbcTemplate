<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Township</title>
</head>
<body>
	<div align="center">
		<h1>New/Edit Township</h1>
		<form:form action="saveTownship" method="post" modelAttribute="township">
			<table>
				<form:hidden path="id"/>
				<tr>
					<td>Township Name:</td>
					<td><form:input path="townshipname" /></td>
				</tr>
				<tr>
					<td>Division:</td>
					<td><form:input path="division"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Save"></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>