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
		
		var a = $("#name").val();
		
		if(a == ""){
			alert("姓名不能为空");
			return false;
		}
		if(a == "全部"){
			alert("姓名不合法，请重填");
			return false;
		}
		return true;
	
	}
	
</script>

</head>

<body>
<form action="updateuser" method="post" onsubmit="return check()">
<div class="main" align="center">
	<h1 class="page_title" align="center" >≡≡≡ 修改信息 ≡≡≡</h1>
	
    <div class="add" align="center" style="width: 65%">
   	  <table align="center" width="100%" border="0" cellspacing="0" cellpadding="0">
       	  <colgroup>
              <col width="20%" />
              <col width="auto" />
          </colgroup>
          <tr>
              <td style="padding-left: 180px">姓名&nbsp;&nbsp;<font color="red">*</font></td>
           	  <td style="padding-left: 100px"><input type="text" name="name" id="name"/></td>
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
