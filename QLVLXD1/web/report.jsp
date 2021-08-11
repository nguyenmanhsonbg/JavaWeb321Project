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
        <title>Báo Cáo</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>

        <div class="topnav">
            <a href="Order">BÁN HÀNG</a>
            <a href="Customer">KHÁCH HÀNG</a>    
            <a class="active" href="Report">BÁO CÁO</a>
        </div>


        <div class="search">
            <div class="timkiem"> 
                <form action="Report" method="POST">    


                    <h3>Chọn Khách Hàng </h3> 
                    <br>
                    <select name="customerName">
                        <c:forEach items="${listCustomer}" var="cu">
                            <option value="${cu.companyName}">${cu.companyName}</option>
                        </c:forEach>
                    </select>
                    <br>

                    <h3> Thời gian </h3>
                    TỪ: <input type="date" name="from" placeholder="Từ" required/>
                    <br>
                    ĐẾN:<input type="date" name="to" placeholder="Đến" required/>
                    <br>
                    <br>
                    <br>

                    <input type="submit" value="Xuất báo cáo"/>
                </form>

            </div>

        </div>

        <div class="display">
            <h1> BÁO CÁO </h1>
            <table class="tbReport" border="1">
                <thead>
                    <tr>
                        <th>Thời gian</th>
                        <th>Địa điểm</th>
                        <th>Loại vật liệu</th>
                        <th>Đơn vị</th>
                        <th>Đơn giá</th>
                        <th>Số lượng</th>
                        <th>Thành tiền</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.listOrder}" var='o'>
                        <tr>
                            <td rowspan="${o.size}">${o.date}
                                <c:forEach items="${o.listOrderDetail}" var='odd' begin="0" end="0">
                                <td>${odd.place}</td>
                                <td>${odd.product.name}</td>
                                <td>${odd.product.measureUnit}</td>
                                <td>${odd.formatUnitPrice}</td>
                                <td>${odd.quantity}</td>

                                <td>${odd.formatThanhTien}</td>
                            </c:forEach>
                            </td>
                            <c:forEach items="${o.listOrderDetail}" var='odd' begin="1" end="${o.size}">
                            <tr>
                                <td>${odd.place}</td>
                                <td>${odd.product.name}</td>
                                <td>${odd.product.measureUnit}</td>
                                <td>${odd.formatUnitPrice}</td>
                                <td>${odd.quantity}</td>

                                <td>${odd.formatThanhTien}</td>
                            </tr>
                        </c:forEach>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>


            <div class="khachhang">                                     



            </div>

    </body>

</html>
