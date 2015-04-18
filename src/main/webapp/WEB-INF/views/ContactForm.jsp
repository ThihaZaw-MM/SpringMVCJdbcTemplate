<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Contact</title>

<script src="<c:url value="/resources/core/jquery.1.10.2.min.js" />"></script>
<script src="<c:url value="/resources/core/jquery.autocomplete.min.js" />"></script>
<link rel="stylesheet" href="<c:url value="/resources/core/jquery-ui.css" />">

</head>
<body>
	<div align="center">
		<h1>New/Edit Contact</h1>
		<form:form action="saveContact" method="post" modelAttribute="contact">
			<table>
				<form:hidden path="id"/>
				<tr>
					<td>Name:</td>
					<td><form:input path="name" /></td>
				</tr>
				<tr>
					<td>Date of Birth:</td>
					<td><form:input path="dateofbirth" id="date" /></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><form:input path="email" /></td>
				</tr>
				<tr>
					<td>Address:</td>
					<td><form:input path="address" /></td>
				</tr>
				<tr>
					<td>Country:</td>
					<td>
						<input type="text" id="w-input-search" value="">
					</td>
				</tr>
				<tr>
					<td>Telephone:</td>
					<td><form:input path="telephone" /></td>
				</tr>
				<tr>
					<td>User:</td>
					<td>
						<select>
							<c:forEach var="user" items="${userList}">
								<option>${user.login}</option>
							</c:forEach>
						</select>
						
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Save"></td>
				</tr>
			</table>
		</form:form>
	</div>
	
	<script src="<c:url value="/resources/core/jquery.js" />"></script>
	<script src="<c:url value="/resources/core/jquery-ui.js" />"></script>
	<script>
	$(document).ready(function() {
		
		$('#w-input-search').autocomplete({
			serviceUrl: '${pageContext.request.contextPath}/getTags',
			paramName: "tagName",
			delimiter: ",",
		    transformResult: function(response) {
		    	
		        return {
		        	
		            suggestions: $.map($.parseJSON(response), function(item) {
		            	
		                return { value: item.country, data: item.id };
		            })  
		        };
		    }
		});
	});
	
	$(document).ready(function(){
		$("#date").datepicker({ dateFormat: 'dd/mm/yy' });
	});
	</script>
</body>
</html>