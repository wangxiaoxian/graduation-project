<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style/oa.css" rel="stylesheet" type="text/css">
<script language="javascript" src="script/public.js"></script>
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="dwr/interface/aclManager.js"></script>
<script type="text/javascript">
function addOrUpdatePermission(field){
	dwr.engine.setAsync(false);
	
	// 如果被选择上，则同时选择其“不继承”和“启用”checkbox
	if(field.checked){
		$(field.getAttribute("moduleId")+"_USE").checked = true;
		<c:if test="${principalType eq 'User' }">
		$(field.getAttribute("moduleId")+"_EXT").checked = true;
		addOrUpdateExtends(field);
		</c:if>
	}
	
	aclManager.addOrUpdatePermission(
		"${principalType }",
		${principalId },
		field.getAttribute("moduleId"),
		field.getAttribute("permission"),
		field.checked
	);
}

//设置用户的继承特性
function addOrUpdateExtends(field){
	aclManager.addOrUpdateUserExtends(
		${principalId},
		field.moduleId,
		!field.checked
	);
}

// 点击启用checkbox
function usePermission(field) {
	// 如果checkbox被选中，意味着需要更新ACL的状态
	// 更新C/R/U/D以及Extends状态
	
	//设置为同步方式，以便DWR依次发出下列请求
	dwr.engine.setAsync(false);
	
	if(field.checked) {
		addOrUpdatePermission($(field.getAttribute("moduleId")+"_C"));
		addOrUpdatePermission($(field.getAttribute("moduleId")+"_R"));
		addOrUpdatePermission($(field.getAttribute("moduleId")+"_U"));
		addOrUpdatePermission($(field.getAttribute("moduleId")+"_D"));
		<c:if test="${principalType eq 'User'}">
		addOrUpdatePermission($(field.getAttribute("moduleId"+"_EXT")));
		</c:if>
	} else {
		aclManager.delPermission(
			"${principalType }",
			${principalId },
			field.getAttribute("moduleId")
		);
		$(field.getAttribute("moduleId")+"_C").checked = false;
		$(field.getAttribute("moduleId")+"_R").checked = false;
		$(field.getAttribute("moduleId")+"_U").checked = false;
		$(field.getAttribute("moduleId")+"_D").checked = false;
		<c:if test="${principalType eq 'User' }">
		$(field.getAttribute("moduleId")+"_EXT").checked = false;
		</c:if>
	}
}

function initTable(){
	aclManager.searchAclRecord(
		"${principalType}",
		${principalId},
		function(data){
			for(var i=0; i<data.length; i++){
				var moduleId = data[i][0];
				var cState = data[i][1];
				var rState = data[i][2];
				var uState = data[i][3];
				var dState = data[i][4];
				var extState = data[i][5];
				$(moduleId+"_C").checked = cState == 0 ? false : true;
				$(moduleId+"_R").checked = rState == 0 ? false : true;
				$(moduleId+"_U").checked = uState == 0 ? false : true;
				$(moduleId+"_D").checked = dState == 0 ? false : true;
				<c:if test="${principalType eq 'User' }">
				$(moduleId+"_EXT").checked = extState == 0 ? true : false;
				</c:if>
				$(moduleId+"_USE").checked = true;
			}
		}
	);
}


</script>
<c:choose>
	<c:when test="${principalType eq 'User'}">
		<c:set var="title" value="请给用户【${user.person.name}】授权"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="title" value="请给角色【${role.name}】授权"></c:set>
	</c:otherwise>
</c:choose>
<title>${title }</title>
</head>
<body onload="initTable()">
<center>
<TABLE class="tableEdit" border="0" cellspacing="1" cellpadding="0" style="width:580px;">
	<TBODY>
		<TR>
			<!-- 这里是添加、编辑界面的标题 -->
			<td align="center" class="tdEditTitle">${title}</TD>
		</TR>
		<TR>
			<td>
			<!-- 主输入域开始 -->
<table class="tableEdit" style="width:580px;" cellspacing="0" border="1" cellpadding="0" >
	<tr>
		<td class="tdEditLabel" style="text-align: center;">顶级模块</td>			
		<td class="tdEditLabel" style="text-align: center;">二级模块</td>
		<td class="tdEditLabel" style="text-align: center;">权限</td>
		<c:if test="${principalType eq 'User'}">
		<td class="tdEditLabel" style="text-align: center;">不继承</td>
		</c:if>
		<td class="tdEditLabel" style="text-align: center;">启用</td>
	</tr>
	<c:forEach items="${modules}" var="module">
	<tr>
		<td>${module.name}</td>
		<td></td>
		<td>
			<input type="checkbox" id="${module.id }_C" moduleId="${module.id }" permission="0" onclick="addOrUpdatePermission(this)">C
			<input type="checkbox" id="${module.id }_R" moduleId="${module.id }" permission="1" onclick="addOrUpdatePermission(this)">R
			<input type="checkbox" id="${module.id }_U" moduleId="${module.id }" permission="2" onclick="addOrUpdatePermission(this)">U
			<input type="checkbox" id="${module.id }_D" moduleId="${module.id }" permission="3" onclick="addOrUpdatePermission(this)">D
		</td>
		<c:if test="${principalType eq 'User'}">
		<td>
			<input type="checkbox" id="${module.id }_EXT">
		</td>
		</c:if>
		<td>
			<input type="checkbox" id="${module.id }_USE">
		</td>
	</tr>
	<c:forEach items="${module.children}" var="child">
	<tr>
		<td></td>
		<td>${child.name}</td>
		<td>
			<input type="checkbox" id="${child.id }_C" moduleId="${child.id }" permission="0" onclick="addOrUpdatePermission(this)">C
			<input type="checkbox" id="${child.id }_R" moduleId="${child.id }" permission="1" onclick="addOrUpdatePermission(this)">R
			<input type="checkbox" id="${child.id }_U" moduleId="${child.id }" permission="2" onclick="addOrUpdatePermission(this)">U
			<input type="checkbox" id="${child.id }_D" moduleId="${child.id }" permission="3" onclick="addOrUpdatePermission(this)">D
		</td>
		<c:if test="${principalType eq 'User'}">
		<td>
			<input type="checkbox" id="${child.id }_EXT">
		</td>
		</c:if>
		<td>
			<input type="checkbox" id="${child.id }_USE">
		</td>
	</tr>
	</c:forEach>
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
			<input type="button" class="MyButton"
				value="关闭窗口" onclick="window.close()">
			</TD>
		</TR>
</TABLE>
</center>
</body>
</html>