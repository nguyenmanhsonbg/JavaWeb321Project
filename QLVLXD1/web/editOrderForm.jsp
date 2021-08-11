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
                    <c:forEach items="${requestScope.listOrderDetail}" var="od">
                        <tr>
                            <td>${od.product.id}</td>
                            <td>${od.product.name}</td>
                            <td>${od.quantity}</td>
                            <td>${od.product.measureUnit}</td>
                            <td>${od.unitPrice}</td>
                            <td>${od.place}</td>
                            <td> <div class="edit">
                                    <a class="dieuchinhkhachhang" href="DeleteOrderDetail?oid=${requestScope.order.id}&pid=${od.product.id}&place=${od.place}">Xóa</a>
                                </div></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="#" class="button" id ="themchuyen">THÊM CHUYẾN</a>
        </div>
        <div class="formnhap"> 
            <div class="formnhap_noidung">
                <img src="common/img/close_icon.png" alt="Close" class="close">
                <form action="AddOrderDetail2" method="GET">
                    <div class ="luachonvatlieu">
                        LOẠI VẬT LIỆU:
                        <select class="txtInput" name="pid">
                            <c:forEach items="${requestScope.listProduct}" var="p">
                                <option name="${p.name}" value ="${p.id}">${p.name}</option>
                            </c:forEach>

                        </select>
                    </div>

                    <br>
                    SỐ LƯỢNG:<input  class="inputform txtInput" type="number" name="soluong" />
                    <br>
                    ĐƠN GIÁ:<input  class="inputform txtInput" type="number" name="dongia"/>
                    <br>
                    ĐỊA ĐIỂM: <input  class="inputform txtInput"  type="Text" name="diadiem"/>
                    <br>

                    <input type="submit" value="THÊM" class="btThem">

                </form>
            </div>
        </div>

        <div class="tuychonhoadon">
            <form action="AddOrderDetail2" method="POST">
                <h3>KHÁCH HÀNG:</h3>  <select class="txtInput" name="cuId" readonly>
                    <c:forEach items="${requestScope.listCustomer}" var="cu">
                        <option 
                            <c:if test="${requestScope.order.customer.id eq cu.id}">
                                selected="selected"
                            </c:if> value ="${cu.id}">${cu.companyName}</option>
                    </c:forEach>
                </select>
                <br>
                <h3>THỜI GIAN:</h3> <input class="txtInput" type="text" name="date"  readonly value="${requestScope.order.formatDate}"/>
                <br>
                <h3>TỔNG GIÁ TRỊ ĐƠN HÀNG:</h3> <input class="txtInput" type="text" name="total" value="${requestScope.order.formatValue}" readonly/>
                <br>
                <br>
                <br>
                <input class="btThem" type="submit" value="Cập nhật"/>
            </form>

            <form action="Order" method="GET">
                <input class="btQuayLai" type="submit" value="Quay lại"/>
            </form>
        </div>
        <script src="<%=request.getContextPath()%>/common/js/popUp.js"></script>

    </body>
</html>
