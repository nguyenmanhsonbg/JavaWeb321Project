<%-- 
    Document   : CongNo
    Created on : Mar 28, 2021, 6:41:56 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/common/css/style.css">
        <title>JSP Page</title>
    </head>
    <body>             
        <div class="mainFrame">
            <h2>Khách hàng: ${requestScope.Customer.companyName}</h2> 
            <h3>Tổng giá trị hóa đơn:${requestScope.TotalOrderValue}</h3>
            <h3>Tổng thanh toán:${requestScope.TotalPaid}</h3>
            <h3 style="color:red;">Còn nợ lại:${requestScope.Unpaid}</h3>
            <br>
            <form action="CongNo" method="Post">

                <h4> Thời gian </h4>
                <input class="txtInput" type="date" name="date" require/>
                <input type="hidden" name="cusid" value="${requestScope.Customer.id}">
                <br>
                <h4> Số tiền</h4>
                <input class="txtInput" type="number" name="paid" require/>   
                <br>
                <br>
                <input class="btThanhToan"type="submit" Value="Thanh Toán"/>
            </form>
            <form action="Customer" method="get">
                <input class="btQuayLai" type="submit" Value="Quay Lại"/>
            </form>             
            <br>
            <br>
            <br>
            <table class="tbCongNo" border="1">
                <thead>
                    <tr>
                        <th>Thời gian</th>
                        <th>Số tiền thanh toán</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.paids}" var="p">
                        <tr>
                            <td>${p.formatDate}</td>
                            <td>${p.money}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
