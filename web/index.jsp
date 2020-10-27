<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Application</title>
</head>
<body>

<h2>Specialties</h2>
<table>
  <tr>
    <th>id</th>
    <th>specialty</th>
  </tr>
  <c:forEach var="specialties" items="${specialtiesList}">
    <tr>
      <td>${specialties.id}</td>
      <td>${specialties.specialty}</td>
      <td>
        <a href="/edit/${specialties.id}">edit</a>
        <a href="/delete/${specialties.id}">delete</a>
      </td>
    </tr>
  </c:forEach>
</table>

<h2>Add</h2>
<c:url value="/specialties/save" var="save"/>
<a href="${save}">Add new specialty</a>

<h2>Accounts</h2>
<table>
  <tr>
    <th>id</th>
    <th>account_status</th>
  </tr>
  <c:forEach var="accounts" items="${accountsList}">
    <tr>
      <td>${accounts.id}</td>
      <td>${accounts.account_status}</td>
      <td>
        <a href="/edit/${accounts.id}">edit</a>
        <a href="/delete/${accounts.id}">delete</a>
      </td>
    </tr>
  </c:forEach>
</table>

<h2>Add</h2>
<c:url value="/accounts/save" var="save"/>
<a href="${save}">Add new account</a>
</body>
</html>
