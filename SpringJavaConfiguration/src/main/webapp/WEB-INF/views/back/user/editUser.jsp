<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户信息</title>
</head>
<body>
用户信息：
<table width="100%" border=1>
    <tr>
        <td>账号</td>
        <td><input type="text" name="name" value="${user.account }"/></td>
    </tr>
    <tr>
        <td>密码</td>
        <td><input type="text" name="price" value="${user.password }"/></td>
    </tr>
    <tr>
        <td>创建日期</td>
        <td><input type="text" name="createtime"
                   value="<fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/></td>
    </tr>
</table>

</body>
</html>