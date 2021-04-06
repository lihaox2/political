<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

    <form action="http://localhost:8080/dataumb/import/train/physical?name=测试&place=分局&registrationStartDate=2021-03-24 13:54&scorer=052805&trainContent=测试" enctype="multipart/form-data" method="post" >  

    <table>  
        <tr>  
            <td width="100" align="right">选择Excel</td>  
            <td><input type="file" name="import"/> 
        </tr> 
        <tr>  
            <td width="100" align="right">name属性</td>  
            <td><input type="input" name="name"/>   <input type="submit"></td>  
        </tr>   
    </table>  
</form>  
</body>
</html>




