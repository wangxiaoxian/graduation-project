<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style/oa.css" rel="stylesheet" type="text/css">
<script language="javascript" src="script/public.js"></script>
<title>请选择一个步骤进行提交！</title>
</head>
<body>
<center>
<form action="document!submit.do" method="post">
<TABLE class="tableEdit" border="0" cellspacing="1" cellpadding="0" style="width:580px;">
	<TBODY>
		<TR>
			<!-- 这里是添加、编辑界面的标题 -->
			<td align="center" class="tdEditTitle">请选择一个步骤进行提交！</TD>
		</TR>
		<TR>
			<td>
			<!-- 主输入域开始 -->

<input type="hidden" name="documentId" value="${documentId }">
<table class="tableEdit" style="width:580px;" cellspacing="0" border="0" cellpadding="0">
    <c:forEach items="${steps }" var="step">
	<tr>
		<td  style="width:100%;align:left" ><input type="radio" name="transitionName" value="${step }">${step }</td>
	</tr>
	</c:forEach>
</table>

			<!-- 主输入域结束 -->
			</td>
		</TR>
	</TBODY>
</TABLE>

<TABLE>
		<TR align="center">
			<TD colspan="3" bgcolor="#EFF3F7">
			<input type="submit" name="saveButton"
				class="MyButton" value="提交"> 
			<input type="button" class="MyButton"
				value="关闭窗口" onclick="window.close()">
			</TD>
		</TR>
</TABLE>
</form>
</center>
</body>
</html>