<%-- 
    Document   : addNewCustomer
    Created on : Mar 24, 2021, 12:55:38 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/common/css/style.css">
        <title>Thêm khách hàng</title>
    </head>
    <body>        
        <div class="addNewCustomerForm">
            <h1> Thêm khách hàng</h1> 
            <form action="AddCustomer" method="POST">
                <h3>Mã khách hàng:</h3>
                <input class="txtInput" type="Text" value="Mặc định"/> 
                <br>
                <h3>Tên khách hàng:</h3>
                <input class="txtInput" type="Text" name="companyName" placeholder="Nhập tên khách hàng hoặc tên công ty"/>
                <br>
                <h3>SỐ ĐIỆN THOẠI:</h3>
                <input class="txtInput" type="Text" name="phone" placeholder="Nhập số điện thoại"/>
                <br>
                <h3>ĐỊA CHỈ:</h3>
                <input class="txtInput"  type="Text" name="address" placeholder="Nhập địa chỉ chính"/>
                <br>
                <br>                                  
                <input class="btThem"type="submit" value="Thêm"/>                   
            </form>
            <br>
            <form  action="Customer" method ="get">
                <input class="btQuayLai" type="submit" value="Quay lại"/>
            </form>
        </div>

    </body>
</html>
