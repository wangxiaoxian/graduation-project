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
  <TITLE>HowTo:</TITLE>
</HEAD>

<BODY leftmargin=10 topmargin=0 marginwidth=10 marginheight=0>
<BR>
<B>HowTo:</B> Build Outlook Like Bar seen on left hand...<HR><BR><BR>
<PRE>
&lt;script language="JavaScript" src="crossbrowser.js" type="text/javascript"&gt;
&lt;/script&gt;
&lt;script language="JavaScript" src="outlook.js" type="text/javascript"&gt;
&lt;/script&gt;

&lt;SCRIPT&gt;
<SPAN style="color:darkgreen">
// ---------------------------------------------------------------------------
// Example of howto: use Outlook Like Bar
// ---------------------------------------------------------------------------


  //create OutlookBar-Object:
  //              Name
  //              x-position
  //              y-Position
  //              width
  //              height
  //              background-color
  //              page background-color (needed for OP5)
  //(screenSize object is created by 'crossbrowser.js')
  //
</SPAN>
  var o = new createOutlookBar('Bar',0,0,screenSize.width,screenSize.height,'#606060','white') // OutlookBar
  var p
<SPAN style="color:darkgreen">
  //create first panel..</SPAN>
  p = new createPanel('al','Anwendungen');
<SPAN style="color:darkgreen">
  //add buttons:
  //             image name
  //             label text
  //             onClick JavaScript code
  //
</SPAN>
  p.addButton('netm.gif','Netmanage','alert("NetM")');
  p.addButton('news.gif','News','alert("News")');
  p.addButton('word.gif','Word','alert("Word")');
  p.addButton('peditor.gif','Editor','alert("Editor")');
  p.addButton('mail.gif','Mail','alert("Mail")');
  o.addPanel(p);
<SPAN style="color:darkgreen">
  //create second panel...</SPAN>
  p = new createPanel('p','Postf�cher');
  p.addButton('mail.gif','Mail 2','alert("Mail2")');
  p.addButton('peditor.gif','Personal&lt;BR&gt;Editor','alert("Personal Editor")');
  p.addButton('word.gif','Projekte','alert("Projekte")');
  p.addButton('news.gif','Adresse','alert("Adresse")');
  p.addButton('mail.gif','Postausgang','alert("Postausgang")');
  p.addButton('mail.gif','Posteingang','alert("Posteingang")');
  p.addButton('news.gif','Information aus&lt;BR&gt;erster Hand','alert("Infos")');
  p.addButton('peditor.gif','Gelbe Post','alert("Gelbe Post")');
  p.addButton('word.gif','Schreiben','alert("Schreiben")');
  o.addPanel(p);
<SPAN style="color:darkgreen">
  //create two empty panels...</SPAN>
  p = new createPanel('l','Leeres Panel');
  o.addPanel(p);

  p = new createPanel('l2','Leeres Panel 2');
  o.addPanel(p);

  o.draw();         <SPAN style="color:darkgreen">//draw the Outlook Like Bar!</SPAN>

<SPAN style="color:darkgreen">
//-----------------------------------------------------------------------------
//functions to manage window resize
//-----------------------------------------------------------------------------
//resize OP5 (test screenSize every 100ms)</SPAN>
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

<SPAN style="color:darkgreen">//resize IE & NS (onResize event!)</SPAN>
function myOnResize() {
  if (bt.ie4 || bt.ie5 || bt.ns5) {
    var s=new createPageSize();
    o.resize(0,0,s.width,s.height);
  }
  else
    if (bt.ns4) location.reload();
}

&lt;/SCRIPT&gt;

&lt;/head&gt;
<SPAN style="color:darkgreen">&lt;!-- need an onResize event to redraw outlookbar after pagesize changes! --&gt;
&lt;!-- OP5 does not support onResize event! use setTimeout every 100ms --&gt;</SPAN>
&lt;body onLoad="resize_op5();" onResize="myOnResize();"&gt;
</PRE>
<BR><BR><BR>

</BODY>
</HTML>
