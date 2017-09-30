<%@ page language="java" import="java.util.*" import="cn.casnuc.entity.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<link rel="stylesheet" type="text/css" href="css/common.css" />
	<script type="text/javascript" src="js/jquery-1.12.4.js" ></script>
<title>修改密码</title>

<script type="text/javascript">
	
	function check(){
		
		var a = $("#newpassword").val();
		var b = $("#affirmpassword").val();
		var c = $("#oldpassword").val();
		
		if(a == "" || b == "" || c == ""){
			alert("密码不能为空");
			return false;
		}
		
		if(a == b){
			return true;
		}else{
			alert("两次输入的密码不一样");
			return false;
		}
		
	}
	
</script>

</head>

<body>
<form action="updatepassword" method="post" onsubmit="return check()">
<div class="main" align="center">
	<h1 class="page_title" align="center" >≡≡≡ 修改密码 ≡≡≡</h1>
	
    <div class="add" align="center" style="width: 65%">
   	  <table align="center" width="100%" border="0" cellspacing="0" cellpadding="0">
       	  <colgroup>
              <col width="20%" />
              <col width="auto" />
          </colgroup>
          <tr>
              <td style="padding-left: 180px">旧密码&nbsp;&nbsp;<font color="red">*</font></td>
           	  <td style="padding-left: 100px"><input type="password" name="oldpassword" id="oldpassword"/></td>
          </tr>
          <tr>
              <td style="padding-left: 180px">新密码&nbsp;&nbsp;<font color="red">*</font></td>
              <td style="padding-left: 100px"><input type="password" name="newpassword" id="newpassword"/></td>
          </tr>
		  <tr>
              <td style="padding-left: 180px">确认新密码&nbsp;<font color="red">*</font></td>
              <td style="padding-left: 100px"><input type="password" name="affirmpassword" id="affirmpassword" /></td>
          </tr>        
      </table>
    </div>
    <div align="center" style="margin-top:20px">		
		<input type="submit" value="提交"/>&nbsp;&nbsp;&nbsp;&nbsp;<input  type="button" value="取消"/>
	</div>
</div>
</form>
</body>
</html>
