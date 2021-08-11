<%-- 
    Document   : order
    Created on : Mar 24, 2021, 2:38:20 PM
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
        <div class="menuhoadonchitiet">
            <table border="1" class="tbOrderDetail">
                <thead>
                    <tr>
                        <th>MÃ SẢN PHẨM</th>
                        <th>TÊN SẢN PHẨM</th>
                        <th>SỐ LƯỢNG</th>
                        <th>ĐƠN VỊ</th>
                        <th>ĐƠN GIÁ</th>
                        <th>ĐỊA ĐIỂM</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.curOrder.listOrderDetail}" var="od">
                    <form action="DeleteOrderDetailInCurrentOrder" method="GET">
                        <tr>
                            <td>${od.product.id}<input type="hidden" name="pid" value="${od.product.id}"/></td>
                            <td>${od.product.name}</td> 
                            <td>${od.quantity}</td>
                            <td>${od.product.measureUnit}</td>
                            <td>${od.unitPrice}</td>
                            <td>${od.place}   <input type="hidden" name="place" value="${od.place}"/></td>
                            <td> <div class="edit">                                     
                                    <input type="submit" value="Xóa"/>                                                              
                                </div></td>
                        </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
            <a href="#" class="button" id ="themchuyen">THÊM CHUYẾN</a>
        </div>
        <div class="formnhap"> 
            <div class="formnhap_noidung">
                <img src="common/img/close_icon.png" alt="Close" class="close">
                <form action="AddOrderDetail" method="GET">
                    <div class ="luachonvatlieu">
                        LOẠI VẬT LIỆU:
                        <select name="pid">
                            <c:forEach items="${requestScope.listProduct}" var="p">
                                <option name="${p.name}" value ="${p.id}">${p.name}</option>
                            </c:forEach>

                        </select>
                    </div>

                    <br>
                    SỐ LƯỢNG:<input class="inputform" type="number" name="soluong" required />
                    <br>

                    ĐƠN GIÁ:<input class="inputform" type="number" name="dongia" required/>
                    <br>

                    ĐỊA ĐIỂM: <input class="inputform"  type="Text" name="diadiem" required/>
                    <br>
                    <input type="submit" value="THÊM" class="btThem">
                </form>
            </div>
        </div>

        <div class="tuychonhoadon">
            <form action="AddOrder" method="POST">
                <h3>KHÁCH HÀNG:</h3>
                <input style="padding: 10px; font-size: 100%;" type="Text" name="customer" value="${requestScope.curOrder.customer.companyName}"readonly/>
                <br>
                   <h3>THỜI GIAN:</h3>
                <input style="padding: 10px; font-size: 100%;" type="date" name="date" value="${requestScope.curOrder.date}" readonly/>
                <br>
                <h3>TỔNG GIÁ TRỊ ĐƠN HÀNG:</h3><br>
                <input style="padding: 10px; font-size: 100%;" type="text" name="total" value="${requestScope.curOrder.formatValue}" readonly/>
                <br>
                <br>
                <br>               
                <input  class="btThem" type="submit" value="Thêm Đơn"/>
                <input type="hidden" name="refresh" value="0">
            </form>
            <form action="AddOrder" method="POST">               
                <input type ="submit" class="btQuaylai" value="Quay lại"/>
                 <input type="hidden" name="refresh" value="1">
            </form>
        </div>
        <script src="<%=request.getContextPath()%>/common/js/popUp.js"></script>
    </body>
</html>
