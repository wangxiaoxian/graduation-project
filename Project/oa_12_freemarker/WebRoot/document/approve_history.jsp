<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公文审批历史</title>
</head>
<body>
<%-- 
<logic:empty name="historys">
尚未有审批历史记录！
</logic:empty>
<logic:notEmpty name="historys">
<logic:iterate id="history" name="historys">
<hr>
审批人：<c:out value="${history.approver.person.name }"/><br>
审批时间：<c:out value="${history.approveTime }"/><br>
审批意见：<c:out value="${history.comment }"/><br>
<br>
</logic:iterate>
</logic:notEmpty>
 --%>
<c:if test="${empty historys }">
尚未有审批历史记录！
</c:if>
<c:if test="${!empty historys }">
<c:forEach items="${historys }" var="history">
<hr>
审批人：<c:out value="${history.approver.person.name }"/><br>
审批时间：<c:out value="${history.approveTime }"/><br>
审批意见：<c:out value="${history.comment }"/><br>
<br>
</c:forEach>
</c:if>
</body>
</html>