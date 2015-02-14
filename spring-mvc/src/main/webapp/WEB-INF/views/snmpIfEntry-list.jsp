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
<table style="text-align: center;" border="1px" cellpadding="0" cellspacing="0" >
<thead>
<tr>
<th width="25px"><a href="${pageContext.request.contextPath}/snmpIfEntry/list/ifIndex.html">ifIndex</a></th>
<th width="60px"><a href="${pageContext.request.contextPath}/snmpIfEntry/list/ifDescr.html">ifDescr</a></th>
<th width="25px"><a href="${pageContext.request.contextPath}/snmpIfEntry/list/ifSpeed.html">ifSpeed</a></th>
<th width="50px"><a href="${pageContext.request.contextPath}/snmpIfEntry/list/ifPhysAddress.html">ifPhysAddress</a></th>
<th width="25px"><a href="${pageContext.request.contextPath}/snmpIfEntry/list/ifAdminStatus.html">ifAdminStatus</a></th>
<th width="25px"><a href="${pageContext.request.contextPath}/snmpIfEntry/list/ifOperStatus.html">ifOperStatus</a></th>
<th width="25px"><a href="${pageContext.request.contextPath}/snmpIfEntry/list/ifInOctets.html">ifInOctets</a></th>
<th width="50px"><a href="${pageContext.request.contextPath}/snmpIfEntry/list/ifInDiscards.html">ifInDiscards</a></th>
<th width="25px"><a href="${pageContext.request.contextPath}/snmpIfEntry/list/ifInErrors.html">ifInErrors</a></th>
<th width="25px"><a href="${pageContext.request.contextPath}/snmpIfEntry/list/ifOutOctets.html">ifOutOctets</a></th>
<th width="50px"><a href="${pageContext.request.contextPath}/snmpIfEntry/list/ifOutDiscards.html">ifOutDiscards</a></th>
<th width="25px"><a href="${pageContext.request.contextPath}/snmpIfEntry/list/ifOutErrors.html">ifOutErrors</a></th>
<th width="25px"><a href="${pageContext.request.contextPath}/snmpIfEntry/delete.htm" onClick="return confirm('Are you sure?')">clear</a></th>
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
<a href="${pageContext.request.contextPath}/snmpIfEntry/edit/${snmpIfEntry.id}.html">Edit</a><br/>
<a href="${pageContext.request.contextPath}/snmpIfEntry/delete/${snmpIfEntry.id}.html">Delete</a><br/>
</td>
</tr>
</c:forEach>
</tbody>
</table>
<a href="${pageContext.request.contextPath}/">HyungJing Home</a>
</body>
</html>