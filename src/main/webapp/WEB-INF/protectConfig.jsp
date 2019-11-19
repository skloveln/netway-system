<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>防护策略配置</title>
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
	<h1 class="page_title" align="center" >≡≡≡ 防护策略配置 ≡≡≡</h1>  
	<div class="add" style="width: 70%;" align="center" style="padding-left: 0px;">
	<table width="60%" border="0" cellspacing="0" cellpadding="0">
		<colgroup>
            <col width="10%" />
            <col width="13%" />
            <col width="12%" />
            <col width="65%" />
        </colgroup>
  		<tr>
  			<td colspan="4"><font style="font-weight: bold;">防护策略配置：</font></td>
  		</tr>
	  	<tr>
	    	<td></td>
	    	<td colspan="2">MDC传输文件审查：</td>
	   		<td><input style="background:#f4f4f6;width: auto;" align="left" type="checkbox" onclick="return false" ${protectResult.mdccheck} ></td>
	    </tr>
	    <tr>
	    	<td></td>
	    	<td colspan="2">NC代码注释行检查：</td>
	   		<td><input name="Input" type="text" disabled="disabled" style="width: 150px;" value="${protectResult.nccheck}"></td>
	    </tr>
	    <tr><td></td></tr>
	    <tr>
	    	<td colspan="4"><font style="font-weight: bold;">传输文件格式审查规则：</font></td>
	    </tr>
	    <tr>
	    	<td></td>
	    	<td>系统型号</td>
	    	<td>G代码格式</td>
	   		<td>加工文件类型</td>
	    </tr>
	    <tr>
	    	<td></td>
	    	<td>SIEMENS</td>
	    	<td>专用</td>
	   		<td><input name="Input" type="text" disabled="disabled" style="width: 150px;" value="${protectResult.siemens}"></td>
	    </tr>
	    <tr>
	    	<td></td>
	    	<td>FANUC</td>
	    	<td>ISO标准</td>
	   		<td><input name="Input" type="text" disabled="disabled" style="width: 150px;" value="${protectResult.fanuc}"></td>
	    </tr>
	    <tr>
	    	<td></td>
	    	<td>HEIDENHAIN</td>
	    	<td>专用</td>
	   		<td><input name="Input" type="text" disabled="disabled" style="width: 150px;" value="${protectResult.heidenhain}"></td>
	    </tr>
	    <tr>
	    	<td></td>
	    	<td>OTHER</td>
	    	<td>ISO标准</td>
	   		<td><input name="Input" type="text" disabled="disabled" style="width: 150px;" value="${protectResult.other}"></td>
	    </tr>
	    <tr><td></td></tr>
	    <tr>
	    	<td colspan="4"><font style="font-weight: bold;">关键词过滤：</font></td>
	    </tr>
	  	
	  	<tr>
	    	<td></td>
	    	<td colspan="2">关键词</td>
	   		<td>词频</td>
	    </tr>    
	    <s:iterator var="R" value="#request.key" status="status">		
  			<tr>
  				<td></td>
   				<td colspan="2"><s:property value="#R.keyword"/></td>  
				<td><s:property value="#R.rate"/></td>
 			</tr>
  		</s:iterator>
	        
	    <tr><td></td></tr>
  		<tr>
  			<td colspan="4" >
  				<font style="font-weight: bold;">配置说明：</font>
    			<textarea name="" cols="3" rows="3" disabled="disabled"  style="width: 300px;vertical-align:middle;">${protectResult.remarks}</textarea>
    		</td>
  		</tr> 
    </table>
</div>
</div>    
</body>
</html>
