<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK" 
	import="java.sql.*"
	import="cn.casnuc.entity.*"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>

<script type="text/javascript" src="js/ddaccordion.js"></script>

<script type="text/javascript">

ddaccordion.init({
	headerclass: "expandable", //Shared CSS class name of headers group that are expandable
	contentclass: "categoryitems", //Shared CSS class name of contents group
	revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
	mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
	collapseprev: true, //Collapse previous content (so only one open at any time)? true/false 
	defaultexpanded: [0], //index of content(s) open by default [index1, index2, etc]. [] denotes no content
	onemustopen: false, //Specify whether at least one header should be open always (so never all headers closed)
	animatedefault: false, //Should contents open by default be animated into view?
	persiststate: true, //persist state of opened contents within browser session?
	toggleclass: ["", "openheader"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
	togglehtml: ["prefix", "", ""], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
	animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
	oninit:function(headers, expandedindices){ //custom code to run when headers have initalized
		//do nothing
	},
	onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
		//do nothing
	}
})

function $(_sId){
	return document.getElementById(_sId);
}

function hide(_sId){
	$(_sId).style.display = $(_sId).style.display == "none" ? "" : "none";
}
</script>

<style type="text/css">
.arrowlistmenu{
width: 150px; /*width of accordion menu*/
}

.arrowlistmenu .menuheader{ /*CSS class for menu headers in general (expanding or not!)*/
font: normal 14px Arial;
color: white;
background: black url(titlebar.png) repeat-x center left;
margin-bottom: 10px; /*bottom spacing between header and rest of content*/
text-transform: uppercase;
padding: 4px 0 4px 10px; /*header text is indented 10px*/
cursor: hand;
cursor: pointer;
font-size:12px
}

.arrowlistmenu .openheader{ /*CSS class to apply to expandable header when it's expanded*/
background-image: url(titlebar-active.png);
}

.arrowlistmenu ul{ /*CSS for UL of each sub menu*/
list-style-type: none;
margin: 0;
padding: 0;
margin-bottom: 8px; /*bottom spacing between each UL and rest of content*/
}

.arrowlistmenu ul li{
padding-bottom: 2px; /*bottom spacing between menu items*/
}

.arrowlistmenu ul li a{
color: white;
background: url(arrowbullet.png) no-repeat center left; /*custom bullet list image*/
display: block;
padding: 2px 0;
padding-left: 19px; /*link text is indented 19px*/
text-decoration: none;
font-weight: normal;
border-bottom: 1px solid #dadada;
font-size:12px;
font-family:Verdana, Arial, Helvetica, sans-serif
}

.arrowlistmenu ul li a:visited{
color: red;
}

.arrowlistmenu ul li a:hover{ /*hover state CSS*/
color: blue;
background-color: #F3F3F3;
}

</style>
<script>
 function doOnclick () {
 	window.open('showBulletin.html','','width=800, height=425, top=100,left=300, toolbar=no, status=yes, menubar=no, resizable=no, scrollbars=no');

}
 </script>
</head>

<%
	User user = (User)session.getAttribute("userinfo");
	String role = user.getRole();
	boolean protectLog = user.getPermission().getProtectLog();
	boolean attackLog = user.getPermission().getAttackLog();
	boolean operateLog = user.getPermission().getOperateLog();
	boolean gatewayConfig = user.getPermission().getGatewayConfig();
	boolean transferConfig = user.getPermission().getTransferConfig();
	boolean keywordConfig = user.getPermission().getKeywordConfig();
	boolean protectConfig = user.getPermission().getProtectConfig();
%>	

<body style="background-image:url(images/bg.gif)">

<div class="arrowlistmenu">
<%
	if(role.equals("用户")){
		
		out.println("<h3 class=\"menuheader expandable\" onclick=\"hide('hideMenuFunc0')\">网关日志</h3>");
		out.println("<ul class=\"categoryitems\" id=\"hideMenuFunc0\" style=\"display:none\">");		
		if(protectLog)
		{
			out.println("<li><a href=\"protectLogInfo\" target=\"mainFrame\">业务防护日志</a></li>");			
		}
		if(attackLog)
		{
			out.println("<li><a href=\"attackLogInfo\" target=\"mainFrame\">攻击信息日志</a></li>");			
		}
/*		if(operateLog)
		{
			out.println("<li><a href=\"getIndent.jsp\" target=\"mainFrame\">操作日志</a></li>");			
		}
*/		out.println("</ul>");
		out.println("<h3 class=\"menuheader expandable\" onclick=\"hide('hideMenuFunc1')\">网关配置</h3>");
		out.println("<ul class=\"categoryitems\" id=\"hideMenuFunc1\" style=\"display:none\">");
		if(transferConfig)
		{
			out.println("<li><a href=\"transferconfig\" target=\"mainFrame\">传输服务配置</a></li>");			
		}
		if(protectConfig)
		{
			out.println("<li><a href=\"protectconfig\" target=\"mainFrame\">防护策略配置</a></li>");			
		}
/*		if(gatewayConfig)
		{
			out.println("<li><a href=\"gatewayConfig.jsp\" target=\"mainFrame\">网关参数设置</a></li>");			
		}
		if(keywordConfig)
		{
			out.println("<li><a href=\"getIndent.jsp\" target=\"mainFrame\">关键字配置</a></li>");			
		}
*/		out.println("</ul>");
	} else if(role.equals("系统管理员")){
		
		out.println("<h3 class=\"menuheader expandable\" onclick=\"hide('hideMenuFunc2')\">用户管理</h3>");
		out.println("<ul class=\"categoryitems\" id=\"hideMenuFunc2\" style=\"display:none\">");
		if(user.getAccount().equals("sysadmin")){
			out.println("<li><a href=\"user_add\" target=\"mainFrame\">添加管理员或用户</a></li>");
			out.println("<li><a href=\"all_admin\" target=\"mainFrame\">管理系统管理员</a></li>");		
		}else{
			out.println("<li><a href=\"user_add_sysadmin\" target=\"mainFrame\">添加用户</a></li>");	
		}
		out.println("<li><a href=\"all_user\" target=\"mainFrame\">管理用户</a></li>");	
		out.println("</ul>");
		
		out.println("<h3 class=\"menuheader expandable\" onclick=\"hide('hideMenuFunc3')\">操作日志</h3>");
		out.println("<ul class=\"categoryitems\" id=\"hideMenuFunc3\" style=\"display:none\">");
		out.println("<li><a href=\"systemOperate_sysadmin\" target=\"mainFrame\">操作日志</a></li>");		
		out.println("</ul>");	
		
	}else if(role.equals("审计管理员")){
		
		if(user.getAccount().equals("auditadmin")){
			out.println("<h3 class=\"menuheader expandable\" onclick=\"hide('hideMenuFunc2')\">审计员管理</h3>");
			out.println("<ul class=\"categoryitems\" id=\"hideMenuFunc2\" style=\"display:none\">");
			out.println("<li><a href=\"user_add_audit\" target=\"mainFrame\">添加审计管理员</a></li>");	
			out.println("<li><a href=\"all_admin\" target=\"mainFrame\">管理审计管理员</a></li>");
			out.println("</ul>");
		}
		
		out.println("<h3 class=\"menuheader expandable\" onclick=\"hide('hideMenuFunc3')\">操作日志</h3>");
		out.println("<ul class=\"categoryitems\" id=\"hideMenuFunc3\" style=\"display:none\">");
		out.println("<li><a href=\"systemOperate_audit\" target=\"mainFrame\">操作日志</a></li>");		
		out.println("</ul>");
		
	}else if(role.equals("安全管理员")){
		if(user.getAccount().equals("safeadmin")){
			out.println("<h3 class=\"menuheader expandable\" onclick=\"hide('hideMenuFunc2')\">安全员管理</h3>");
			out.println("<ul class=\"categoryitems\" id=\"hideMenuFunc2\" style=\"display:none\">");
			out.println("<li><a href=\"user_add_safe\" target=\"mainFrame\">添加安全管理员</a></li>");
			out.println("<li><a href=\"all_admin\" target=\"mainFrame\">管理安全管理员</a></li>");	
			out.println("</ul>");
		}
		out.println("<h3 class=\"menuheader expandable\" onclick=\"hide('hideMenuFunc3')\">操作日志</h3>");
		out.println("<ul class=\"categoryitems\" id=\"hideMenuFunc3\" style=\"display:none\">");
		out.println("<li><a href=\"systemOperate_safe\" target=\"mainFrame\">操作日志</a></li>");		
		out.println("</ul>");
	}
%>

<h3 class="menuheader expandable" onclick="hide('hideMenuFunc4')">个人设置</h3>
<ul class="categoryitems" id="hideMenuFunc4" style="display:none">
<li><a href="user_modifyPassword" target="mainFrame">修改个人密码</a></li>
<li><a href="user_modifyInfo" target="mainFrame">修改个人信息</a></li>
</ul>

</div>

<p>&nbsp;</p>
</body>
</html>