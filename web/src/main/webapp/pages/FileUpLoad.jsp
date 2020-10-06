<%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2020/9/5
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>上传文件</title>
</head>
<body>
<%--传统上传，直接存到当前服务器--%>
<form action="/fileUpload" method="post" enctype="multipart/form-data">
    名称：<input type="text" name="picname"/><br/>
    图片：<input type="file" name="uploadFile"/><br/>
    <input type="submit" value=" 上传 "/>
</form>
</body>
</html>
