<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Edit SnmpIfEntry page</title>
</head>
<body>
<h1>Edit SnmpIfEntry page</h1>
<br/>
<a href="${pageContext.request.contextPath}/">Home page</a>
<br/><br/>
<form:form method="POST" commandName="snmpIfEntry" action="${pageContext.request.contextPath}/snmpIfEntry/${snmpIfEntry.id}" >
<table>
<tbody>
<tr>
<td>SnmpIfEntry AdminStatus:</td>
<td><form:input path="ifAdminStatus" /></td>
<td><form:errors path="ifAdminStatus" cssStyle="color: red;"/></td>
</tr>
<tr>
<td><input type="submit" value="Update" /></td>
<td></td>
<td></td>
</tr>
</tbody>
</table>
</form:form>
</body>
</html>