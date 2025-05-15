<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOANGNAM
  Date: 5/16/2025
  Time: 2:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Đặt vé cho lịch chiếu: ${schedule.showTime}</h2>
<form action="submitBooking" method="post">
    <input type="hidden" name="scheduleId" value="${schedule.id}"/>
    <input type="hidden" name="customerId" value="1"/>
    <c:forEach var="seat" items="${seats}">
        <input type="checkbox" name="seatIds" value="${seat.id}"
               <c:if test="${seat.status == 'booked'}">disabled</c:if>/>
        Ghế ${seat.id} - ${seat.price}đ - ${seat.status}<br/>
    </c:forEach>
    <button type="submit">Đặt vé</button>
</form>
</body>
</html>
