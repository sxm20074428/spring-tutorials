<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>查询用户列表</title>
    <script type="text/javascript">
        function queryItems() {
            document.userForm.action = "${pageContext.request.contextPath }/user/userList";
            document.userForm.submit();
        }
    </script>
</head>
<body>
<form name="userForm" method="post">
    查询条件：
    <table width="100%" border=1>
        <tr>
            <td>
                账号：<input name="account"/>
            </td>
            <td>
                <input type="button" value="查询" onclick="queryItems()"/>
            </td>
        </tr>
    </table>
    用户列表：
    <table width="100%" border=1>
        <tr>
            <td>账号</td>
            <td>密码</td>
            <td>创建日期</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${userList }" var="user">
            <tr>
                <td>${user.account }</td>
                <td>${user.password }</td>
                <td><fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td><a href="${pageContext.request.contextPath }/user/editUser/${user.id}">修改</a></td>
            </tr>
        </c:forEach>
    </table>
</form>

</body>
</body>

</html>