<%@ page language="java" import="java.util.*" import="cn.casnuc.entity.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
	<link rel="stylesheet" type="text/css" href="css/common.css" />
	<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="js/addUserCheck.js"></script>
<title>添加审计员</title>

<script type="text/javascript">
/*
 * 数据校验
 */
function check(){
	if($("#account").val().trim() == ""){
		alert("用户名不能为空");
		return false;
	}
	if($("#account").val().trim() == "全部"){
		alert("用户名不合法");
		return false;
	}
	if($("#name").val().trim() == "全部"){
		alert("姓名不合法");
		return false;
	}
	if($("#password").val().trim() == ""){
		alert("密码不能为空");
		return false;
	}
	if($("#confirmpassword").val().trim() == ""){
		alert("请再次确认密码");
		return false;
	}
	if($("#password").val() != $("#confirmpassword").val()){
		alert("两次密码不一样");
		return false;
	}
	if($("#password").val().length < 8 || $("#password").val().length > 20){
		alert("密码长度不符合要求");
		return false;
	}
	if(($("#password").val().match(/([a-z])/) && $("#password").val().match(/[0-9]/))
			|| ($("#password").val().match(/([A-Z])/) && $("#password").val().match(/[0-9]/))
			){		
		}else{
			alert("密码必须包含字母与数字");
			return false;
		}
	
	return true;
}
</script>

</head>

<body>
<form action="adduser" method="post" onsubmit="return check()">
<div class="main" align="center">
	<h1 class="page_title" align="center" >≡≡≡ 添加审计员 ≡≡≡</h1>
	
    <div class="add" style="width: 65%;" align="center">
   	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
       	  <colgroup>
             	<col width="20%" />
            	<col width="auto" />
          </colgroup>
          <tr>
            	<td style="padding-left: 150px">用户名&nbsp;&nbsp;<font color="red">*</font></td>            	
           	    <td style="padding-left: 100px"><input type="text" id="account" name="user.account" />&nbsp;&nbsp;<span id="tip"></span></td>
          </tr>
          <tr><td></td></tr>
          <tr>
            	<td style="padding-left: 150px">姓&nbsp;名&nbsp;&nbsp;</td>
           	    <td style="padding-left: 100px"><input type="text" id=name name="user.name" /></td>
          </tr>
          <tr><td></td></tr>
          <tr>
            	<td style="padding-left: 150px">密&nbsp;码&nbsp;&nbsp;<font color="red">*</font></td>
            	<td style="padding-left: 100px"><input id=password type="password" name="user.password"  />&nbsp;&nbsp;<span id="pwdtip" style="color:red;"></span></td>
          </tr>
          <tr><td></td></tr>
          <tr>
            	<td style="padding-left: 150px">密&nbsp;码&nbsp;确&nbsp;认&nbsp;<font color="red">*</font></td>
            	<td style="padding-left: 100px"><input id=confirmpassword type="password"/></td>
          </tr>
          <tr><td></td></tr> 		
        </table>
    </div>
    <div align="center" style="margin-top:20px">		
		<input type="submit" value="提交"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="取消"/>
	</div>
</div>
</form>
</body>
</html>
