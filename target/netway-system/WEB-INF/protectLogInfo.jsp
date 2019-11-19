<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  
 <head>    
    <title>业务日志</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
 </head>

 <body > 
 <br>
  	<div style="height: 15%"> 	
	  	<form style="margin-left: 20px;">
	  		文件名：<input type="text" id="fileName" name="dataQuery.fileName" value="全部" style="width: 180px;"/>&nbsp;&nbsp;  		
	  		传输方向：<select id="transferDrection" name="dataQuery.transferDrection" style="width:100px;">
	  					<option selected="selected">全部</option>	
	  					<option>单向导入</option>	
	  					<option>单向导出</option>			
	  					</select>&nbsp;&nbsp;
	  		传输结果：<select id="transferResult" name="dataQuery.transferResult" style="width:100px;">
	  					<option selected="selected">全部</option>	
	  					<option>成功</option>	
	  					<option>失败</option>			
	  					</select>&nbsp;&nbsp;
			时间：<input type="text" id="start" name="dataQuery.start" onfocus="calendar(this)" value="2016-01-01 00:00:00"/>
			至 <input type="text" id="end" name="dataQuery.end" onfocus="calendar(this)" value="2018-01-01 00:00:00"/>
			<br/>
			<br/>
			源IP：&nbsp;&nbsp;&nbsp;<input id="sourceIp" type="text" name="dataQuery.sourceIp" value="全部" style="width: 150px;"/>&nbsp;&nbsp;	
			源MAC：<input type="text" id="sourceMac" name="dataQuery.sourceMac" value="全部" style="width: 150px;"/>	&nbsp;&nbsp;
			目的IP：<input type="text" id="destinationIp" name="dataQuery.destinationIp" value="全部" style="width: 150px;"/>&nbsp;&nbsp;	
			目的MAC：<input type="text" id="destinationMac" name="dataQuery.destinationMac" value="全部" style="width: 150px;"/>	&nbsp;&nbsp;	
	  		<input id="bt" type="button" value="查询">
	  	</form>
  	</div>	 	
   	
   	<div style="height: 80%;">
	    <table id="dg" ></table>
  	</div>
  	
  	<div id="window" class="easyui-window" title="查看文件" data-options="closed:true,iconCls:'icon-save'" style="width:800px;height:400px;padding:10px;"></div>
  	
  	<script type="text/javascript">	   			

  		function openfile(fileName,time){  			
  			var xmlhttp;
  			if (window.XMLHttpRequest) { 
  				// code for IE7+, Firefox, Chrome, Opera, Safari
  			 	xmlhttp=new XMLHttpRequest();
  			} else	{ 
  				// code for IE6, IE5
  			 	xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  			}
  			xmlhttp.onreadystatechange = function(){
  			  if (xmlhttp.readyState==4 && xmlhttp.status==200){	
  			//    document.getElementById("window").innerHTML = xmlhttp.responseText;
  				 document.getElementById("window").innerHTML = "该功能暂未实现，敬请期待！<br><br><br>你点击的文件名是"+fileName+"  "+time;
  			  }
  			};
  			xmlhttp.open("POST","#",true);
  			xmlhttp.send();
  			$('#window').window('open');
  		}	
  	
  		$("#bt").click(function () {			
        
  			// 提交记录日志的信号
  			$.ajax({
				type : "post",
				url : "writelog",
				data : {
					event : "查看了业务防护日志"
				},
				dataType : "json",
				success : function(data) {
					if(!data.status){
						window.location.href("relogin");
					}	
				},
				error : function(ajax, b, c) {
					alert("数据出错，请稍后再试");
				}
			});
  			
  			$("#dg").datagrid({           	 
             	url : '${pageContext.request.contextPath}/protectlogjson.action',			
 				method : 'post',
 				columns : [[    
		        {field:'time',title:'时间',width:180,sortable:true},   
		        {field:'fileName',title:'文件名',width:100,sortable:true,	        	
		        	formatter:function(value,row,index){
	 					return "<a href='javascript:void(0)' class='easyui-linkbutton' onclick=\"openfile('"+value+"','"+row.time+"')\">"+row.fileName+"</a>";
	 				}
		        },    
		        {field:'transferDrection',title:'传输方向',width:100,sortable:true,
		        	formatter: function(value,row,index){
						if (value == 1){
							return "单向导出";
						} else {
							return "单向导入";
						}
					}
		        },    
		        {field:'serviceType',title:'服务类型',width:100,sortable:true,
					formatter: function(value,row,index){
						if (value == 1){
							return "DNC服务";
						} else {
							return "MDC服务";
						}
					}
		        },    
		        {field:'sourceIp',title:'源IP',width:100,sortable:true},    
		        {field:'destinationIp',title:'目的IP',width:100,sortable:true},    
		        {field:'sourceMac',title:'源MAC',width:150,sortable:true},    
		        {field:'destinationMac',title:'目的MAC',width:150,sortable:true},
		        {field:'sourcePort',title:'源端口',width:100,sortable:true},    
		        {field:'destinationPort',title:'目的端口',width:100,sortable:true},    
		        {field:'transferResult',title:'传输结果',width:100,sortable:true,
		        	formatter: function(value,row,index){
						if (value == 0){
							return "成功";
						} else {
							return "失败";
						}
					}
		        },    
		        {field:'fileFormCheck',title:'格式检查',width:100,sortable:true,
					formatter: function(value,row,index){
						if (value == 0){
							return "合法";
						} else {
							return "不合法";
						}
					}
		        },    
		        {field:'fileTypeCheck',title:'类型检查',width:100,sortable:true,
					formatter: function(value,row,index){
						if (value == 0){
							return "合法";
						} else {
							return "不合法";
						}
					}
		        },    
		        {field:'keyWordCheck',title:'关键字检查',width:100,sortable:true,
					formatter: function(value,row,index){
						if (value == 0){
							return "合法";
						} else {
							return "不合法";
						}
					}
		        },    
		        {field:'remarks',title:'备注',width:100,sortable:true}  
 				]], 				
 				queryParams: {
 					fileName: $("#fileName").val(),
 					transferDrection: $("#transferDrection").val(),
 					transferResult:$("#transferResult").val(),
 					start:$("#start").val(),
 					end:$("#end").val(),
 					sourceIp:$("#sourceIp").val(),
 					sourceMac:$("#sourceMac").val(),
 					destinationIp:$("#destinationIp").val(),
 					destinationMac:$("#destinationMac").val()
 				},
 				striped:true,
 				rownumbers:true,
 				fitColumns : true,
 				singleSelect : true,
 				pagination : true,
 				pageSize : 10,
 				pageList : [5,10,15,20],
 				pageNumber : 1,
 				fit:true,
 				multiSort:false,
 				remoteSort:false,
 				loadFilter: function(data){
 					if (data.status == true){
 						return data.result;
 					} else if (data.status == false){
 						window.location.href("relogin");
 					}else{
 						return data;
 					}
 				}

 	  		});
         }); 		
	</script>		
 </body>
</html>
