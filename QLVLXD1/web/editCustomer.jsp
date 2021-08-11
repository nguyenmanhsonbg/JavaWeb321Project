


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/common/css/style.css">
        <title>Chỉnh sửa thông tin</title>
    </head>
    <body>      
        <div class="addNewCustomer">
            <h1>Chỉnh sửa thông tin</h1> 

            <form action="EditCustomer" method="POST">
                <h3> Tên khách hàng:</h3>
                <input class="txtInput" type="Text" name="companyName" value="${requestScope.editCustomer.companyName}" required/>
                <br>
                <h3>SỐ ĐIỆN THOẠI:</h3> 
                <input  class="txtInput" type="number" name="phone" value="${requestScope.editCustomer.phone}"required />
                <br>
                <h3>ĐỊA CHỈ:</h3>
                <input  class="txtInput" type="Text" name="address" value="${requestScope.editCustomer.address}" required/>
                <br>
                <br>
                <input type="hidden" name="id" value="${requestScope.editCustomer.id}"/>
                <input class="btThem" type="submit" value="Cập nhật"/>
            </form> 
            <form action="Customer" method="GET">
                <input type="submit"  class="btQuayLai" value="Quay lại"/>
                <input type="hidden" name="id" value="${requestScope.editCustomer.id}">   
            </form>
        </div>

    </body>
</html>
