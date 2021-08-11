<%-- 
    Document   : customer
    Created on : Mar 21, 2021, 4:27:47 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/common/css/style.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Khách Hàng</title>

    </head>
    <body>
        <div class="topnav">            
            <a href="Order">BÁN HÀNG</a>
            <a class="active" href="Customer">KHÁCH HÀNG</a>     
            <a href="Report">BÁO CÁO</a>
        </div>
        <div class="search">
            <div class="timkiem"> 
                <form action="searchCustomer" method="GET">                 
                    <h3> Chế độ lọc </h3> 
                    <h3><select class="txtInput" name="chedoloc">
                            <option value="1">Nợ nhiều nhất</option>
                            <option value="2">Nợ ít nhất</option>
                            <option value="3">Lấy nhiều hàng nhất</option>
                            <option value="4">Lấy ít hàng nhất</option>
                        </select> </h3>
                    <br>
                    <br>
                    <input class="btThem" type="submit" value="Tìm Kiếm">
                </form>
            </div> 
        </div>
        <div class="display">
            <div class="khachhang">                                     
                <form action="AddCustomer" method="GET">
                    <input type="submit" class="danhsachkhachhang" value="THÊM MỚI KHÁCH HÀNG"/>
                </form>

                <div class="Order">
                    <h1>KHÁCH HÀNG</h1>
                    <div class="thoigian">                 
                    </div>
                    <table border="1" class="tbOrder">
                        <thead>
                            <tr>
                                <th>MÃ KHÁCH HÀNG</th>
                                <th>TÊN KHÁCH HÀNG</th>
                                <th>ĐIỆN THOẠI</th>
                                <th>TỔNG GIÁ TRỊ ĐƠN HÀNG</th>
                                <th>ĐÃ THANH TOÁN</th>
                                <th>NỢ CÒN LẠI</th>
                            </tr>
                        </thead>
                        <tbody>    
                            <c:forEach items="${requestScope.listCustomer}" var="cu">
                                <tr>
                                    <td>${cu.id}</td>
                                    <td>${cu.companyName}</td>
                                    <td>${cu.phone}</td>
                                    <td>${cu.formatUnpaid}</td>
                                    <td>${cu.formatPaid}</td>  
                                    <td>${cu.formatNoConLai}</td>  
                                    <td> 
                                        <div class="edit">
                                            <a id ="chinhsua" href="EditCustomer?id=${cu.id}">CHỈNH SỬA</a> 
                                            <a class="dieuchinhkhachhang"  href="CongNo?id=${cu.id}">CÔNG NỢ</a>

                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                </body>
                </html>