<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style/oa.css" rel="stylesheet" type="text/css">
<script language="javascript" src="script/public.js"></script>
<title>公文管理</title>
</head>
<BODY bgColor=#dee7ff leftMargin=0 background="" topMargin=0 marginheight="0" marginwidth="0">
<center>
      <TABLE width="778" border=0 cellPadding=0 cellSpacing=0 borderColor=#ffffff bgColor=#dee7ff style="FONT-SIZE: 10pt">
        <TBODY>
          <TR height=35>
            <TD align=middle width=20 background=images/title_left.gif 
          bgColor=#dee7ff></TD>
            <TD align=middle width=120 background=images/title_left.gif 
          bgColor=#dee7ff><FONT color=#f7f7f7> 待审公文管理<font color="#FFFFFF">&nbsp;</font></FONT> </TD>
            <TD align=middle width=11 background=images/title_middle.gif 
          bgColor=#dee7ff><FONT color=#f7f7f7>&nbsp;</FONT> </TD>
            <TD align=middle background=images/title_right.gif 
          bgColor=#dee7ff><FONT color=#f7f7f7>&nbsp;</FONT> </TD>
          </TR>
        </TBODY>
      </TABLE>
      <TABLE width="778" border=0 align=center cellPadding=0 cellSpacing=0 borderColor=#ffffff style="FONT-SIZE: 10pt">
        <TBODY>
          <TR>
            <TD width="82%" height=14 align=right vAlign=center noWrap>
            </TD>
            <TD width="18%" align=right vAlign=center noWrap>　</TD>
          </TR>
          <TR>
            <TD height=14 align=right vAlign=center noWrap>
            	<!-- 在这里插入查询表单 -->
            	<a href="document.do">我的公文</a>
            	【待审公文】
            	<a href="document!approvedList.do">已审公文</a>
            </TD>
            <TD height=14 align="left" vAlign=center noWrap>
            <% 
            /**
            * 在这里定义“添加”，“查询”等按钮
            * <input type="image" name="find" value="find" src="images/cz.gif">
            * &nbsp;&nbsp;&nbsp;&nbsp; 
            * <a href="#" onClick="openWin('document.do?method=addInput','470')">
            * <img src="images/addpic.gif" border=0 align=absMiddle style="CURSOR: hand"></a>
            */
            %>
            </TD>
          </TR>
          <TR>
            <TD height=28 colspan="2" align=right vAlign=center noWrap background=images/list_middle.jpg>&nbsp;&nbsp;
            <!-- 可以在这里插入分页导航条 -->
            </TD>
          </TR>
        </TBODY>
      </TABLE>
      <table width="778" border="0" cellPadding="0" cellSpacing="1" bgcolor="#6386d6">
          <!-- 列表标题栏 -->
	      <tr bgcolor="#EFF3F7" class="TableBody1">
		      <td width="15%" height="37" align="center"><b>公文名称</b></td>
		      <td width="15%" height="37" align="center"><B>公文描述</B></td>
		      <td width="15%" height="37" align="center"><B>创建时间</B></td>
		      <td width="15%" height="37" align="center"><B>公文状态</B></td>
		      <td width="8%" height="37" align="center"><B>流程名称</B></td>
		      <td width="7%" height="37" align="center"><B>附件</B></td>
		      <td width="15%" height="37" align="center"><B>审批历史</B></td>
              <td width="10%" align="center"><b>相关操作</b></td>
          </tr>
          <!-- 列表数据栏 -->
          <c:if test="${!empty documents}">
          <c:forEach items="${documents }" var="document">
	      <tr bgcolor="#EFF3F7" class="TableBody1" onmouseover="this.bgColor = '#DEE7FF';" onmouseout="this.bgColor='#EFF3F7';">
		      <td align="center" vAlign="center">${document.title}</td>
	          <td align="center" vAlign="center">${document.description }</td>
	          <td align="center" vAlign="center">${document.createTime }</td>
	          <td align="center" vAlign="center">${document.status }</td>
	          <td align="center" vAlign="center">${document.workflow.name}</td>
	          <td align="center" vAlign="center">
	          <c:if test="${!empty document.content }">
	          <a href="document!download.do?documentId=${document.id}">下载</a>
	          </c:if>
	          </td>
	          <td align="center" vAlign="center"><a href="#" onclick="openWin('document!approvedHistory.do?documentId=${document.id}')">查看</a></td>

	          <td align="center" vAlign="center">
	          <a href="#" onclick="openWin('document!approveInput.do?documentId=${document.id}')">审批</a>
	          <a href="#" onclick="openWin('document!submitInput.do?documentId=${document.id}')">提交</a>
	          </td>
        </tr>
        </c:forEach>
		</c:if>
        <!-- 在列表数据为空的时候，要显示的提示信息 -->
	    <c:if test="${empty documents}">
	    <tr>
	    	<td colspan="7" align="center" bgcolor="#EFF3F7" class="TableBody1" onmouseover="this.bgColor = '#DEE7FF';" onmouseout="this.bgColor='#EFF3F7';">
	    	没有找到相应的记录
	    	</td>
	    </tr>
	    </c:if>
      </table>
      <TABLE width="778" border=0 align=center cellPadding=0 cellSpacing=0 borderColor=#ffffff style="FONT-SIZE: 10pt">
        <TBODY>
          <TR>
            <TD height=28 align=right vAlign=center noWrap background=images/list_middle.jpg>&nbsp;&nbsp;
            <!-- 可以在这里插入分页导航条 -->
    		</TD>
          </TR>
        </TBODY>
      </TABLE>
</center>

</body>

</html>
