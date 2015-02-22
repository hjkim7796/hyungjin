<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>SnmpIfEntry List page</title>
</head>
<body>
<h1>SnmpIfEntry List page</h1>
<br/>
<a href="${pageContext.request.contextPath}/">HyungJing Home</a>
<br/><br/>
<table style="text-align: center;" border="1px" cellpadding="0" cellspacing="0" >
<thead>
<tr>
<th width="25px"><a href="${pageContext.request.contextPath}/snmpIfEntry/sort/ifIndex">ifIndex</a></th>
<th width="60px"><a href="${pageContext.request.contextPath}/snmpIfEntry/sort/ifDescr">ifDescr</a></th>
<th width="25px"><a href="${pageContext.request.contextPath}/snmpIfEntry/sort/ifSpeed">ifSpeed</a></th>
<th width="50px"><a href="${pageContext.request.contextPath}/snmpIfEntry/sort/ifPhysAddress">ifPhysAddress</a></th>
<th width="25px"><a href="${pageContext.request.contextPath}/snmpIfEntry/sort/ifAdminStatus">ifAdminStatus</a></th>
<th width="25px"><a href="${pageContext.request.contextPath}/snmpIfEntry/sort/ifOperStatus">ifOperStatus</a></th>
<th width="25px"><a href="${pageContext.request.contextPath}/snmpIfEntry/sort/ifInOctets">ifInOctets</a></th>
<th width="50px"><a href="${pageContext.request.contextPath}/snmpIfEntry/sort/ifInDiscards">ifInDiscards</a></th>
<th width="25px"><a href="${pageContext.request.contextPath}/snmpIfEntry/sort/ifInErrors">ifInErrors</a></th>
<th width="25px"><a href="${pageContext.request.contextPath}/snmpIfEntry/sort/ifOutOctets">ifOutOctets</a></th>
<th width="50px"><a href="${pageContext.request.contextPath}/snmpIfEntry/sort/ifOutDiscards">ifOutDiscards</a></th>
<th width="25px"><a href="${pageContext.request.contextPath}/snmpIfEntry/sort/ifOutErrors">ifOutErrors</a></th>
<th width="25px"><a href="${pageContext.request.contextPath}/snmpIfEntry/delete" onClick="return confirm('Are you sure?')">clear</a></th>
</tr>
</thead>
<tbody>
<c:forEach var="snmpIfEntry" items="${snmpIfEntryList}">
<tr>
<td>${snmpIfEntry.ifIndex}</td>
<td>${snmpIfEntry.ifDescr}</td>
<td>${snmpIfEntry.ifSpeed}</td>
<td>${snmpIfEntry.ifPhysAddress}</td>
<td>${snmpIfEntry.ifAdminStatus}</td>
<td>${snmpIfEntry.ifOperStatus}</td>
<td>${snmpIfEntry.ifInOctets}</td>
<td>${snmpIfEntry.ifInDiscards}</td>
<td>${snmpIfEntry.ifInErrors}</td>
<td>${snmpIfEntry.ifOutOctets}</td>
<td>${snmpIfEntry.ifOutDiscards}</td>
<td>${snmpIfEntry.ifOutErrors}</td>

<td>
<a href="${pageContext.request.contextPath}/snmpIfEntry/${snmpIfEntry.id}">Edit</a><br/>
<%-- <a href="${pageContext.request.contextPath}/snmpIfEntry/${snmpIfEntry.id}.html">Detail</a><br/> --%>
<a href="${pageContext.request.contextPath}/snmpIfEntry/delete/${snmpIfEntry.id}">Delete</a><br/>
</td>
</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>