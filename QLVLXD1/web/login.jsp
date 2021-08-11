<%-- 
    Document   : login
    Created on : Mar 21, 2021, 4:06:42 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/common/css/style.css">
        <title>Đăng Nhập</title>    
    </head>
    <body>   
        <form class="modal-content animate" action="LoginController" method="post">
            <div class="imgcontainer">
                <img src="common/img/avatar1.jpg" alt="Avatar" class="avatar">
            </div>

            <div class="container">
                <label for="uname"><b>Đăng Nhập</b></label>
                <input type="text" style="margin-left: 1px;" placeholder="Điền tên đăng nhập" name="username" value="${requestScope.rememberUsername}" required>
                <br>
                <label for="psw"><b>Mật Khẩu</b></label>
                <input type="password"  style="margin-left: 10px;" placeholder="Điền mật khẩu" name="password" value="${requestScope.rememberPassword}" required>
                <br>
                <div class="checkbox">
                    <input type="checkbox" checked="checked" name="remember"> Remember me
                </div>
                <br>
                <label>
                    <button type="submit" style="width:10%; margin-left: 40px;">Đăng nhập</button>
                </label>
            </div>

        </form>

    </body>
</html>
