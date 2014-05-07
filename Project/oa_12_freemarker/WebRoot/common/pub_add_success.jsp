<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加记录成功</title>
<script type="text/javascript">
function closewindow(){
	// window.opener表示打开本窗口的那个窗口
	if(window.opener){
		// 刷新的方式之一，还有很多种，要取得opener，必须是模态窗口，即父窗口不能被锁死
		window.opener.location.reload(true);
		window.close();
	}
}
function clock(){
	i = i - 1;
	if(document.getElementById("info")){
		document.getElementById("info").innerHTML = "本窗口将在 "+i+" 秒后自动关闭";
	}
	if(i > 0)
		// 1000毫秒之后再调用clock();
		setTimeout("clock();",1000);
	else
		closewindow();
}
// 上面是方法的定义，下面是方法的调用
var i = 4;
clock();
</script>
</head>
<body>
<center>
	<p>添加记录成功！</p>
	<div id="info">本窗口将在3秒后自动关闭</div>
	<input type="button" value="关闭窗口" onclick="closewindow();">
</center>
</body>
</html>