<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>质量管理系统</title>
<meta http-equiv=Content-Type content="text/html; charset=GBK">
<style type=text/css>
* {
	font-size: 12px;
	color: white
}

#logo {
	color: white
}

#logo a {
	color: white
}

form {
	margin: 0px
}
</style>
<script src="Top.files/Clock.js" type=text/javascript></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<meta content="MSHTML 6.00.2900.5848" name=GENERATOR>
</head>

<BODY style="BACKGROUND-IMAGE: url(images/bg.gif); MARGIN: 0px; BACKGROUND-REPEAT: repeat-x">
<form id="form1">
<%
	/* String Name = "";
	Name = session.getAttribute("name").toString(); */
%>
<div id=logo style="margin-left:20px; background-image: url(images/logo.jpg); background-repeat: no-repeat; background-position: left;">
	<div style="padding-right: 5%; background-position: right 50%; display: block; padding-left: 0px; background-image: url(images/bg_banner_menu.gif); padding-bottom: 0px; padding-top: 3px; background-repeat: no-repeat; height: 30px; text-align: right">
		<a id="HyperLink2" href="right" target="mainFrame">返回首页</a> <img src="Top.files/menu_seprator.gif" align="middle">
		<a id="HyperLink3" href="exit" target="_parent">退出系统</a>
	</div>
	<div align="center"	style="font-size: 24px; font: '宋体'; font-style: italic; font-weight: bold; display: block; height: 54px">网关日志信息管理系统
	</div>
	</div>
	<div>	
			<DIV
				style="BACKGROUND-IMAGE: url(images/bg_nav.gif); BACKGROUND-REPEAT: repeat-x; HEIGHT: 30px">
				<TABLE cellSpacing=0 cellPadding=0 width="100%">
					<TBODY>
						<TR>
							<TD>
								<DIV>
									<IMG src="Top.files/nav_pre.gif" align="middle">&nbsp;&nbsp;&nbsp;&nbsp;登录用户：${userinfo.account}&nbsp;&nbsp;姓名：${userinfo.name}&nbsp;&nbsp;角色：${userinfo.role}
								</DIV>
							</TD>
							<TD align=right width="70%"><SPAN
								style="PADDING-RIGHT: 50px"> 
								<A href="exit" target="_parent"><IMG src="Top.files/nav_changePassword.gif"
										align="middle" border=0>重新登录</A> 
								<A href="user_modifyPassword" target=mainFrame><IMG src="Top.files/nav_resetPassword.gif"
										align="middle" border=0>修改密码</A> <IMG
									src="Top.files/menu_seprator.gif" align="middle"> <SPAN
									id="clock"></SPAN></SPAN></TD>
						</TR>
					</TBODY>
				</TABLE>
			</DIV>
		</DIV>
	<script type=text/javascript>
		var clock = new Clock();
		clock.display(document.getElementById("clock"));
		
		setInterval(function(){
			// 轮询查看用户登录状态
			$.ajax({
				type : "post",
				url : "userstate",
				data : {
					
				},
				dataType : "json",
				success : function(data) {
					
					if(data.status=="relogin"){
						var object =  confirm("该账号在其他地方登录，请重新登录");
					   	if(object == true) {	
					   		window.open("exit","_parent");
					   	}
					}else if(data.status =="null"){
						var object =  confirm("登录失效，请重新登录");
					   	if(object == true) {	
					   		window.open("exit","_parent");
					   	}
					}	
				},
				error : function(ajax, b, c) {
					alert("数据出错，请稍后再试");
				}
			});
			
		},3000);
		
	</script> 
</form>
</BODY>
</HTML>
