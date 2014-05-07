<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="/common/common.jsp" %>
<HTML>
<!--
 ---------------------------------------------------------------------------
 this script is copyright (c) 2001 by Michael Wallner!
 http://www.wallner-software.com
 mailto:dhtml@wallner-software.com

 you may use this script on web pages of your own
 you must not remove this copyright note!

 This script featured on Dynamic Drive (http://www.dynamicdrive.com)
 Visit http://www.dynamicdrive.com for full source to this script and more
 ---------------------------------------------------------------------------
-->
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html;CHARSET=UTF-8">
<TITLE>Outlook Like Bar</TITLE>

<!--
  you need this style or you will get an error in ns4 on first page load!
-->
<STYLE>
  div    {
         position:absolute;
         }
</STYLE>

<script src="crossbrowser.js" type="text/javascript">
</script>
<script src="outlook.js" type="text/javascript">
</script>

<SCRIPT>

// ---------------------------------------------------------------------------
// Example of howto: use OutlookBar
// ---------------------------------------------------------------------------


  //create OutlookBar
  var o = new createOutlookBar('Bar',0,0,screenSize.width,screenSize.height,'#606060','white');//'#000099') // OutlookBar
  
//根据授权列表创建父模块
  <c:forEach items="${modules}" var="m">
  <c:if test="${empty m.parent}">
  var p${m.id} = new createPanel('id_${m.id}','${m.name}');
  o.addPanel(p${m.id});
  </c:if>
  </c:forEach>

  //根据授权列表创建子模块
  <c:forEach items="${modules}" var="s">
  <c:if test="${!empty s.parent}">
  try{
  	p${s.parent.id}.addButton('netm.gif','${s.name}','parent.main.location="${s.url}"');
  }catch(ignore){}
  </c:if>
  </c:forEach>
  //create first panel
  o.draw();         //draw the OutlookBar

//-----------------------------------------------------------------------------
//functions to manage window resize
//-----------------------------------------------------------------------------
//resize OP5 (test screenSize every 100ms)
function resize_op5() {
  if (bt.op5) {
    o.showPanel(o.aktPanel);
    var s = new createPageSize();
    if ((screenSize.width!=s.width) || (screenSize.height!=s.height)) {
      screenSize=new createPageSize();
      //need setTimeout or resize on window-maximize will not work correct!
      //ben�tige das setTimeout oder das Maximieren funktioniert nicht richtig
      setTimeout("o.resize(0,0,screenSize.width,screenSize.height)",100);
    }
    setTimeout("resize_op5()",100);
  }
}

//resize IE & NS (onResize event!)
function myOnResize() {
  if (bt.ie4 || bt.ie5 || bt.ns5) {
    var s=new createPageSize();
    o.resize(0,0,s.width,s.height);
  }
  else
    if (bt.ns4) location.reload();
}

</SCRIPT>

</head>
<!-- need an onResize event to redraw outlookbar after pagesize changes! -->
<!-- OP5 does not support onResize event! use setTimeout every 100ms -->
<body onLoad="resize_op5();" onResize="myOnResize();">
</body>
</html>


