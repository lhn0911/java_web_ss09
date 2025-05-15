<%--
  Created by IntelliJ IDEA.
  User: HOANGNAM
  Date: 5/15/2025
  Time: 5:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Danh sách phim</title>
</head>
<body>
<h2>Danh sách phim đang chiếu</h2>
<table border="1">
    <tr>
        <th>Tiêu đề</th>
        <th>Đạo diễn</th>
        <th>Thể loại</th>
        <th>Chi tiết</th>
    </tr>
    <c:forEach var="movie" items="${movies}">
        <tr>
            <td>${movie.title}</td>
            <td>${movie.director}</td>
            <td>${movie.genre}</td>
            <td><a href="detail?id=${movie.id}">Xem chi tiết</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

