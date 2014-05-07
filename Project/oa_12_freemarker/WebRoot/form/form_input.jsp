<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style/oa.css" rel="stylesheet" type="text/css">
<script language="javascript" src="script/public.js"></script>
<title>设置流程表单</title>
</head>
<body>
<center>
<form action="flowForm!addForm.do" method="post">
<input type="hidden" name="flowFormId" value="${flowForm.id }">
<input type="hidden" name="workflowId" value="${workflow.id}">
<TABLE class="tableEdit" border="0" cellspacing="1" cellpadding="0" style="width:580px;">
	<TBODY>
		<TR>
			<!-- 这里是添加、编辑界面的标题 -->
			<td align="center" class="tdEditTitle">请设置流程【${workflow.name }】的表单</TD>
		</TR>
		<TR>
			<td>
			<!-- 主输入域开始 -->
<table class="tableEdit" style="width:580px;" cellspacing="0" border="0" cellpadding="0">
<c:if test="${empty flowForm.template }">
<c:set var="tmp" value="document_form.ftl"/>
</c:if>
<c:if test="${!empty flowForm.template }">
<c:set var="tmp" value="${flowForm.template}"/>
</c:if>
	<tr>
		<td class="tdEditLabel" >表单模板</td>			
		<td class="tdEditContent"><input type="text" name="flowForm.template" value="${tmp}">
		</td>
		<td class="tdEditLabel" ></td>			
		<td class="tdEditContent"></td>
	</tr>
</table>

<c:if test="${!empty flowForm }">
<hr>
<table class="tableEdit" style="width:580px;" cellspacing="0" border="0" cellpadding="0">
	<tr bgcolor="#EFF3F7" class="TableBody1">
		<td width="20%"><B>标签</B></td>			
		<td width="20%"><B>名称</B></td>
		<td width="20%" ><B>类型</B></td>			
		<td width="20%"><B>输入形式</B></td>
		<td width="20%"><B>操作</B></td>
	</tr>
	<c:forEach items="${flowForm.fields }" var="field">
	<tr>
		<td >${field.fieldLabel }</td>			
		<td >${field.fieldName }</td>
		<td >${field.fieldType.name }</td>			
		<td >${field.fieldInput.name }</td>
		<td>
		<a href="#" onclick="del('flowForm!delField.do?formFieldId=${field.id}')">删除</a>
		<a href="#" onclick="openWin('flowForm!addItemInput.do?formFieldId=${field.id }','additem',700,600)">条目</a>
		</td>
	</tr>
	</c:forEach>
</table>
</c:if>
			<!-- 主输入域结束 -->
			</td>
		</TR>
	</TBODY>
</TABLE>

<TABLE>
		<TR align="center">
			<TD colspan="3" bgcolor="#EFF3F7">
			<input type="submit" name="saveButton"
				class="MyButton" value="保存表单信息">
			<c:if test="${!empty flowForm }">
			<input type="button" class="MyButton"
				value="添加表单域" onclick="openWin('flowForm!formFieldInput.do?flowFormId=${flowForm.id }','addformfield',600,200)">
			</c:if>
			<input type="button" class="MyButton"
				value="关闭窗口" onclick="window.close()">
			</TD>
		</TR>
</TABLE>
</form>
</center>
</body>
</html>