<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>传输服务配置</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
  </head>

<body>
<div class="main" align="center">
	<h1 class="page_title" align="center" >≡≡≡ 传输服务配置 ≡≡≡</h1>  
	<div class="add" style="width: 70%;" align="center" style="padding-left: 0px;">
	<table width="60%" border="0" cellspacing="0" cellpadding="0">
		<colgroup>
            <col width="10%" />
            <col width="25%" />
            <col width="65%" />
        </colgroup>
  		<tr>
  			<td colspan="3"><font style="font-weight: bold;">传输服务配置：</font></td>
  		</tr>
	  	<tr>
	    	<td></td>
	    	<td>传输方向：</td>
	   		<td><input name="Input" type="text" disabled="disabled" style="width: 150px;" value="${transferResult.transferDirection}"></td>
	    </tr>
	    <tr>
	    	<td></td>
	    	<td>服务类型：</td>
	   		<td><input name="Input" type="text" disabled="disabled" style="width: 150px;" value="${transferResult.serviceType}"></td>
	    </tr>
	    <tr>
	    	<td></td>
	    	<td>传输次数：</td>
	   		<td><input name="Input" type="text" disabled="disabled" style="width: 150px;" value="${transferResult.transferCount}"></td>
	    </tr>
	    <tr><td></td></tr>
	    <tr>
	    	<td colspan="3"><font style="font-weight: bold;">内网服务器通讯参数：</font></td>
	    </tr>
	    <tr>
	    	<td></td>
	    	<td>IP地址：</td>
	   		<td><input name="Input" type="text" disabled="disabled" style="width: 150px;" value="${transferResult.inNetIp}"></td>
	    </tr>
	    <tr>
	    	<td></td>
	    	<td>MAC地址：</td>
	   		<td><input name="Input" type="text" disabled="disabled" style="width: 150px;" value="${transferResult.inNetMac}"></td>
	    </tr>
	    <tr><td></td></tr>
	    <tr>
	    	<td colspan="3"><font style="font-weight: bold;">外网服务器通讯参数：</font></td>
	    </tr>
	  	<tr>
	    	<td></td>
	    	<td>IP地址：</td>
	   		<td><input name="Input" type="text" disabled="disabled" style="width: 150px;" value="${transferResult.outNetIp}"></td>
	    </tr>
	    <tr>
	        <td></td>
	    	<td>MAC地址：</td>
	   		<td><input name="Input" type="text" disabled="disabled" style="width: 150px;" value="${transferResult.outNetMac}"></td>
	    </tr>
	    <tr><td></td></tr>
  		<tr>
  			<td colspan="3" >
  				<font style="font-weight: bold;">配置说明：</font>
    			<textarea name="" cols="3" rows="3" disabled="disabled"  style="width: 300px;vertical-align:middle;">${transferResult.remarks}</textarea>
    		</td>
  		</tr> 
    </table>
</div>
</div>    
</body>
</html>
