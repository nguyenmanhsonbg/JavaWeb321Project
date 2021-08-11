<%-- 
    Document   : order
    Created on : Mar 24, 2021, 2:38:20 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/common/css/style.css">
        <title>JSP Page</title>
    </head>
    <body> 
        <div class="menuhoadonchitiet">       
            <a href="#" class="button" id ="themchuyen">THÊM CHUYẾN</a>
        </div>
        <div class="formnhap"> 
            <div class="formnhap_noidung">
                <img src="common/img/close_icon.png" alt="Close" class="close">
                <form action="AddOrderDetail" method="GET">
                    LOẠI VẬT LIỆU:
                    <select name="loaivatlieu">
                    </select>
                    <br>
                    SỐ LƯỢNG:<input class="inputform" name="soluong" />
                    <br>
                    ĐƠN GIÁ:<input class="inputform" name="dongia"/>
                    <br>
                    ĐỊA ĐIỂM: <input class="inputform" name="diadiem"/>
                    <br>
                    <input type="submit" value="THÊM" class="btThem">                        
                </form>
            </div>
        </div>
        <div class="tuychonhoadon">
            <h3>KHÁCH HÀNG:</h3>
            <input type="Text" readonly value="${requestScope.Order.customer.companyName}">
            <br>
            <h3>THỜI GIAN:</h3><input type="date" name="thoigian" readonly value="${requestScope.Order.date}"/>
            <br>
            <h3>TỔNG GIÁ TRỊ ĐƠN HÀNG:</h3><input type="text" name="total" readonly value="${requestScope.Order.value}" />
            <br>
            <form action="AddOrderDetail" method="POST">
                <input type="submit" class="btThem" value="CẬP NHẬT"/>
            </form>
        </div>
        <script src="<%=request.getContextPath()%>/common/js/popUp.js"></script>
    </body>
</html>
