<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<link href="style/oa.css" rel="stylesheet" type="text/css">
<script src="script/public.js"></script>
<title>模块管理</title>
</head>
<BODY bgColor=#dee7ff leftMargin=0 background="" topMargin=0 marginheight="0" marginwidth="0">
<center>
      <table width="778" border=0 cellPadding=0 cellSpacing=0 borderColor=#ffffff bgColor=#dee7ff style="FONT-SIZE: 10pt">
        <tbody>
          <tr height=35>
            <td align=middle width=20 background="images/title_left.gif" bgColor=#dee7ff></td>
            <td align=middle width=120 background="images/title_left.gif" bgColor=#dee7ff><font color=#f7f7f7>机构管理<font color="#FFFFFF">&nbsp;</font></font></td>
            <td align=middle width=11 background="images/title_middle.gif" bgColor=#dee7ff><font color=#f7f7f7>&nbsp;</font></td>
            <td align=middle background="images/title_right.gif" bgColor=#dee7ff><font color=#f7f7f7>&nbsp;</font></td>
          </tr>
        </tbody>
      </table>
      <table width="778" border=0 align=center cellPadding=0 cellSpacing=0 borderColor=#ffffff style="FONT-SIZE: 10pt">
        <tbody>
          <tr>
            <td width="82%" height=14 align=right vAlign=center noWrap>
            </td>
            <td width="18%" align=right vAlign=center noWrap>　</TD>
          </tr>
          <tr>
            <td height=14 align=right vAlign=center noWrap>
            	<!-- 在这里插入查询表单 -->
            </td>
            <td height=14 align="left" vAlign=center noWrap>
            <% 
            /**
            * 在这里定义“添加”，“查询”等按钮
            * <input type="image" name="find" value="find" src="images/cz.gif">
            * &nbsp;&nbsp;&nbsp;&nbsp; 
            * <a href="#" onClick="BeginOut('document.do?method=addInput','470')">
            * <img src="images/addpic.gif" border=0 align=absMiddle style="CURSOR: hand"></a>
            */
            %>
            
            <a onMouseover="this.style.cursor='pointer';" 
            	onclick="openWin('module!addInput.do?parentId=${parentId }','addOrg',600,200);">
            	添加模块信息</a>
            <a href="module.action?parentId=0">返回</a>
            </td>
          </tr>
          <tr>
            <td height=28 colspan="2" align=right vAlign=center noWrap background="images/list_middle.jpg">&nbsp;&nbsp;
            <!-- 可以在这里插入分页导航条 -->
            <pg:pager url="module.do" items="${pager.total }" export="currentPageNumber=pageNumber">
            	<pg:param name="parentId"/>
		  		<pg:first>
		  			<a href="${pageUrl }">首页</a>
		  		</pg:first>
		  		<pg:prev>
		  			<a href="${pageUrl }">上一页</a>
		  		</pg:prev>
		  		<pg:pages >
		  			<c:choose>
		  				<c:when test="${currentPageNumber eq pageNumber }">
		  					<font color="red">${pageNumber }</font>
		  				</c:when>
		  				<c:otherwise>
				  			<a href="${pageUrl }">${pageNumber }</a>
		  				</c:otherwise>
		  			</c:choose>
		  		</pg:pages>
		  		<pg:next>
		  			<a href="${pageUrl }">下一页</a>
		  		</pg:next>
		  		<pg:last>
		  			<a href="${pageUrl }">末页</a>
		  		</pg:last>
		  	</pg:pager>
            </td>
          </tr>
        </tbody>
      </table>
      <table width="778" border="0" cellPadding="0" cellSpacing="1" bgcolor="#6386d6">
        <!-- 列表标题栏 -->
	    <tr bgcolor="#EFF3F7" class="TableBody1">
		      <td width="5%" height="37" align="center"><b>序号</b></td>
		      <td width="18%" height="37" align="center"><b>模块名称</b></td>
		      <td width="18%" height="37" align="center"><b>模块编号</b></td>
		      <td width="18%" height="37" align="center"><b>父模块名称</b></td>
              <td width="5%" height="37" align="center"><b>相关操作</b></td>
        </tr>
          
        <!-- 列表数据栏 -->
        <s:if test="!pager.datas.isEmpty()">
        <s:iterator value="pager.datas" var="module">
	    <tr bgcolor="#EFF3F7" class="TableBody1" onmouseover="this.bgColor = '#DEE7FF';" onmouseout="this.bgColor='#EFF3F7';">
		   <td align="center" vAlign="center">${module.id }</td>
	       <td align="center" vAlign="center"><a href="module.action?pager.offset=0&parentId=${module.id }">${module.name }</a></td>
	       <td align="center" vAlign="center">${module.sn }</td>
	       <td align="center" vAlign="center">${module.parent.name }</td>
	       <td align="center" vAlign="center">
	          <a onMouseover="this.style.cursor='pointer';" onclick="del('module!del?moduleId=${module.id }')">删除</a>
	       </td>
        </tr>
        </s:iterator>
        </s:if>
        <!-- 在列表数据为空的时候，要显示的提示信息 -->
        <s:if test="pager.datas.isEmpty()">
	    <tr>
	    	<td colspan="7" align="center" bgcolor="#EFF3F7" class="TableBody1" onmouseover="this.bgColor = '#DEE7FF';" onmouseout="this.bgColor='#EFF3F7';">
	    	没有找到相应的记录
	    	</td>
	    </tr>
	    </s:if>
      </table>
      <table width="778" border=0 align=center cellPadding=0 cellSpacing=0 borderColor=#ffffff style="FONT-SIZE: 10pt">
        <tbody>
          <tr>
            <td height=28 align=right valign=center noWrap background="images/list_middle.jpg">&nbsp;&nbsp;
            <!-- 可以在这里插入分页导航条 -->
            <%
            /*
            <s:iterator begin="0" end="pager.totalPage-1" var="i">
            	<a href="org.action?pager.offset=${i * 4 }&orgForm.parent.id=${orgForm.parent.id }" > | ${i+1 } |  </a>
            </s:iterator>
            */
            %>
            <pg:pager url="module.action" items="${pager.total }" export="currentPageNumber=pageNumber">
            	<pg:param name="parentId"/>
		  		<pg:first>
		  			<a href="${pageUrl }">首页</a>
		  		</pg:first>
		  		<pg:prev>
		  			<a href="${pageUrl }">上一页</a>
		  		</pg:prev>
		  		<pg:pages >
		  			<c:choose>
		  				<c:when test="${currentPageNumber eq pageNumber }">
		  					<font color="red">${pageNumber }</font>
		  				</c:when>
		  				<c:otherwise>
				  			<a href="${pageUrl }">${pageNumber }</a>
		  				</c:otherwise>
		  			</c:choose>
		  		</pg:pages>
		  		<pg:next>
		  			<a href="${pageUrl }">下一页</a>
		  		</pg:next>
		  		<pg:last>
		  			<a href="${pageUrl }">末页</a>
		  		</pg:last>
		  	</pg:pager>
    		</td>
          </tr>
        </tbody>
      </table>
</center>
<s:debug></s:debug>
</body>
</html>
