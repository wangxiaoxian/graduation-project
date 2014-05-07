<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style/oa.css" rel="stylesheet" type="text/css">
<script language="javascript" src="script/public.js"></script>
<title>添加模块信息</title>
</head>
<body>
<center>
<form action="module!add.do" method="post">
<input type="hidden" name="parentId" value="${parentId }">
<TABLE class="tableEdit" border="0" cellspacing="1" cellpadding="0" style="width:580px;">
	<TBODY>
		<TR>
			<!-- 这里是添加、编辑界面的标题 -->
			<td align="center" class="tdEditTitle">添加模块信息</TD>
		</TR>
		<TR>
			<td>
			<!-- 主输入域开始 -->
			<table class="tableEdit" style="width:580px;" cellspacing="0" border="0" cellpadding="0">
				<tr>
					<td class="tdEditLabel" >模块名称</td>			
					<td class="tdEditContent"><input type="text" name="module.name">
					</td>
					<td class="tdEditLabel" >模块编号</td>			
					<td class="tdEditContent"><input type="text" name="module.sn"></td>
				</tr>
				<tr>
					<td class="tdEditLabel" >排序号</td>			
					<td class="tdEditContent"><input type="text" name="module.orderNo">
					</td>
					<td class="tdEditLabel" >模块地址</td>			
					<td class="tdEditContent"><input type="text" name="module.url"></td>
				</tr>
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
				class="MyButton" value="保存模块信息"> 
			<input type="button" class="MyButton"
				value="关闭窗口" onclick="window.close()">
			</TD>
		</TR>
</TABLE>
</form>
</center>
</body>
</html>