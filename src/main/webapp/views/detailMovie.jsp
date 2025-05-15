
<%--
  Created by IntelliJ IDEA.
  User: HOANGNAM
  Date: 5/15/2025
  Time: 5:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Chi tiết phim</title>
</head>
<body>
<h2>Chi tiết phim</h2>
<p><strong>Tiêu đề:</strong> ${movie.title}</p>
<p><strong>Đạo diễn:</strong> ${movie.director}</p>
<p><strong>Thể loại:</strong> ${movie.genre}</p>
<p><strong>Mô tả:</strong> ${movie.description}</p>
<p><strong>Thời lượng:</strong> ${movie.duration} phút</p>
<p><strong>Ngôn ngữ:</strong> ${movie.language}</p>

<h3>Lịch chiếu</h3>
<table border="1">
    <tr>
        <th>Thời gian</th>
        <th>Phòng chiếu</th>
        <th>Ghế trống</th>
        <th>Định dạng</th>
    </tr>
    <c:forEach var="schedule" items="${schedules}">
        <tr>
            <td>${schedule.showTime}</td>
            <td>${schedule.screenRoomId}</td>
            <td>${schedule.availableSeats}</td>
            <td>${schedule.format}</td>
        </tr>
    </c:forEach>
</table>

<p><a href="movie">Quay lại danh sách</a></p>
</body>
</html>

