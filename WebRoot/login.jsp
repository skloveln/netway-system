<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="struts"%>
<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>欢迎登录网关管理系统</title>
<link href="./login/style.css" rel="stylesheet" type="text/css">
<script src="./login/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="./login/cloud.js" type="text/javascript"></script>
<link href="./login/validationEngine.jquery.css" rel="stylesheet" type="text/css">
<script src="./login/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="./login/jquery.validationEngine.js" type="text/javascript"></script>
<script src="./login/jquery.validationEngine-zh_CN.js"	type="text/javascript"></script>

<script language="javascript" type="text/javascript">
    	
    	$(function () {
            $('.loginbox').css({ 'position': 'absolute', 'left': ($(window).width() - 692) / 2 });
            $(window).resize(function () {
                $('.loginbox').css({ 'position': 'absolute', 'left': ($(window).width() - 692) / 2 });
            });
        });

        $(document).ready(function () {
            $("#txtName").focus();
            
            $("#txtName").blur(function() {
        		if ($("#txtName").val() != "") {        			
        			$("#tip").hide();
        		}
        	});
            
            $("#txtPwd").blur(function() {
        		if ($("#txtPwd").val() != "") {
        			$("#tip").hide();
        		}
        	});
            
        });

        function login() {
            var bvalidate = $("#loginform").validationEngine("validate");
            if (bvalidate) {
                    
             $("#loginform").submit();
            }
        }
        
        function keyLogin() {
        	if (event.keyCode == 13) // 回车键的键值为13
        	{
        		login();
        	}
        }
        
</script>

<style type="text/css">
#loginbox form {
	width: 100%;
	position: relative;
	top: 0;
	left: 0;
}

#loginbox .form-actions {
	padding: 14px 20px 15px;
}

#loginbox .form-actions .pull-left {
	margin-top: 0px;
}

#loginbox form#loginform {
	z-index: 200;
	display: block;
}

#loginbox form#recoverform {
	z-index: 100;
	display: none;
	background: #fff;
	text-align: center;
	height: 100%;
	width: 66.4%;
	left: 33%;
	margin-top: 7px;
	height: 79.8%;
	overflow: hidden;
	padding-top: 40px;
}

#loginbox form#recoverform .form-actions {
	margin-top: 10px;
}
</style>
</head>
<body onkeydown="keyLogin()" style="overflow: hidden; background-image: url(&quot;images/light.png&quot;); background-color: rgb(28, 119, 172); background-position: -279.9px 0px; background-repeat: no-repeat;">
	<!------状态BOX--------->
	<div id="p9" class="tipsBox"></div>
	<div id="mainBody">
		<div id="cloud1" class="cloud"	style="background-position: 884.5px 100px;"></div>
		<div id="cloud2" class="cloud"	style="background-position: 395px 460px;"></div>
	</div>
	<div class="logintop">
		<span>欢迎登录网关日志管理系统</span>
		<ul>
			<li></li>
			<li><a href="#">帮助</a></li>
			<li><a href="#">关于</a></li>
		</ul>
	</div>
	<div class="loginbody">
		<span class="systemlogo"></span>
		<div class="loginbox" id="loginbox"	style="position: absolute; left: 422px;">
			<form method="post" action="login" id="loginform" style="display: block;">
				<div class="aspNetHidden">
					<input type="hidden" name="__EVENTTARGET" id="__EVENTTARGET" value="">
					<input type="hidden" name="__EVENTARGUMENT"	id="__EVENTARGUMENT" value="">
					<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="/wEPDwUKLTc4MzU3MjA2NWRkV3WOxnw1uqfeFBxiegoJjCoBrebQOZTs1JnFPDgI5j4=">
				</div>

				<script src="./login/WebResource.axd" type="text/javascript"></script>

				<div class="aspNetHidden">
					<input type="hidden" name="__VIEWSTATEGENERATOR" id="__VIEWSTATEGENERATOR" value="B8B84CAE">
					<input type="hidden" name="__EVENTVALIDATION" id="__EVENTVALIDATION" value="/wEWAwLoztzjAgLEhISFCwKd+7qdDnC5T35FNTEp5M2kdmrmJANBuRSVaF+/6d2Vk2cmW/Q3">
				</div>

				<ul>
					<span id="tip"><font color="red" id="errMsg">${loginResult}</font></span>
					<li><input name="loginUser.account" type="text" id="txtName"
						class="validate[required] loginuser" placeholder="用户名"
						data-validation-engine="validate[required]"
						style="color: #90A2BC; font-weight: normal"
						data-errormessage="错误提示信息:用户名不能为空!"></li>
					<li><input name="loginUser.password" type="password"
						id="txtPwd" class="loginpwd" placeholder="密码"
						data-validation-engine="validate[required]"
						data-errormessage="错误提示信息:密码不能为空!"></li>
					<li style="margin-left: 0px;"><input type="button"
						id="btnLogin" class="loginbtn" onclick="login()" value="登录">
						<label> <input type="reset" class="loginbtn" id=""	value="重置"></label></li>
				</ul>

				<script type="text/javascript">
//<![CDATA[

WebForm_InitCallback();function CallPageServer(arg,context){WebForm_DoCallback('__Page',arg,CallBackServe,context,null,false);}//]]>
</script>

			</form>

		</div>
	</div>
	<div class="loginbm">
		技术支持 <a href="#">航天数控系统有限公司 版权所有 All Right Reversed 2017</a>
	</div>
	<script type="text/javascript">

            $(document).ready(function () {
                $("#loginform").validationEngine({
                    validationEventTriggers: "blur",
                    inlineValidation: true,
                    sucess: false,
                    promptPosition: "topRight"
                });
            });

    </script>

</body>
</html>