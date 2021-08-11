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
        <title>BÁN HÀNG</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
        <div class="topnav">
            <a class="active" href="Order">BÁN HÀNG</a>
            <a href="Customer">KHÁCH HÀNG</a>
            <a href="Report">BÁO CÁO</a>
        </div>
        <div class="formnhap"> 
            <div class="formnhap_noidung">
                <img src="common/img/close_icon.png" alt="Close" class="close">
                <form action="OrderDetail" method="GET">
                    KHÁCH HÀNG:
                    <select name="cuId">
                        <c:forEach items="${requestScope.listCustomer}" var='cu'>
                            <option name="${cu.companyName}" value ="${cu.id}">${cu.companyName}</option>
                        </c:forEach>
                    </select>
                    <br>
                    THỜI GIAN:<input class="inputform" name="date" type="date" required/>
                    <br>          
                    <input type="submit" value="THÊM" class="btThem">
                </form>
            </div>
        </div>
        <div class="search">

            <div class="timkiem"> 
                <form action="searchOrder" method="GET">
                    <h3> Tìm kiếm </h3> 
                    <br>
                    <input class="txtInput"  type="Text" placeholder="Theo tên khách hàng" name="customerName" value="${requestScope.name}">
                    <br>
                    <h3> Thời gian </h3>
                    <h4>Từ:</h4> <input class="txtInput"  type="date" name="from" placeholder="Từ" value="${requestScope.from}" />
                    <br>
                    <h4>Đến:</h4><input class="txtInput"  type="date" name="to" placeholder="Đến" value="${requestScope.to}" />
                    <br>
                    <br>
                    <input class="btThem" type="submit" value="Tìm Kiếm">
                </form>
            </div> 
        </div>
        <div class="display">
            <div class="themmoidonhang">                                     
                <input type="submit" class="danhsachkhachhang" id ="themchuyen" value="THÊM MỚI ĐƠN HÀNG"/>
            </div>
            <div class="Order">
                <div class="tieude">  
                    <h1>BÁN HÀNG</h1>
                </div>
                <table border="1" class="tbOrder">
                    <thead>
                        <tr>
                            <th>MÃ ĐƠN HÀNG</th>
                            <th>THỜI GIAN</th>
                            <th>TÊN KHÁCH HÀNG</th>
                            <th>TỔNG GIÁ TRỊ ĐƠN HÀNG</th>
                        </tr>
                    </thead>
                    <tbody>                          
                        <c:forEach items="${requestScope.listOrder}" var="o">
                            <tr>
                                <td>${o.id}</td>
                                <td>${o.formatDate}</td>
                                <td>${o.customer.companyName}</td>
                                <td>${o.formatValue}</td>                  
                                <td> <div class="edit">
                                        <a class="dieuchinhkhachhang" href="EditOrder?id=${o.id}">CHỈNH SỬA</a>                                       
                                        <a class="dieuchinhkhachhang" href="DeleteOrder?id=${o.id}">XÓA</a>
                                    </div></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <br>
            <div id="bottomPagger" class="pagger" style=""></div>
            <script>
            renderPager("bottomPagger",${requestScope.pageindex},${requestScope.totalpage}, 2);
            </script>
            <script src="<%=request.getContextPath()%>/common/js/popUp.js"></script>
    </body>

</html>
