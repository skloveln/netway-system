<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>请重新登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script language="javascript" type="text/javascript">
	   	var object =  confirm("登录失效，请重新登录");
	   	if(object == true) {	
	   		window.open("exit","_parent");
	   	}
   	</script>

  </head>
  
  <body>
	登录失效,请重新登录  	
  </body>
</html>
