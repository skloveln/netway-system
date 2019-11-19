<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

 <head>    
    <title>攻击业务日志</title>
    
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
	  	<form action="attacklog" method="post" style="margin-left: 20px;">
	  		攻击对象：<select id="attackTarget" name="attackQuery.attackTarget" style="width:150px;">
	  					<option selected="selected">全部</option>	
	  					<option>业务端</option>	
	  					<option>管理端</option>			
	  					</select>&nbsp;&nbsp;
	  		攻击类型：<select id="attackType" name="attackQuery.attackType" style="width:150px;">
	  					<option selected="selected">全部</option>	
	  					<option>端口扫描</option>
	  					<option>ICMP攻击</option>	
	  					<option>SYN攻击</option>	
	  					<option>UDP攻击</option>	
	  					</select>&nbsp;&nbsp;
			时间：<input type="text" id="startTime" name="attackQuery.startDate" onfocus="calendar(this)" value="2016-01-01 00:00:00"/>
			至 <input type="text" id="endTime" name="attackQuery.endDate" onfocus="calendar(this)" value="2018-01-01 00:00:00"/>
			<br/>
			<br/>
			攻击源MAC：<input type="text" id="sourceMac" name="attackQuery.sourceMac" value="全部" style="width: 200px;"/>&nbsp;&nbsp;	
			目标IP：<input type="text" id="destinationIp" name="attackQuery.targetIp" value="全部" style="width: 200px;"/>&nbsp;&nbsp;	
			目标端口：<input id="destinationPort" type="text" name="attackQuery.targetPort" value="全部" style="width: 200px;" 
				onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9]+/,'');}).call(this)" onblur="this.v();"/>&nbsp;&nbsp;		
	  		<input id="bt" type="button" value="查询">
	  	</form>
  	</div>	
    <div style="height: 80%;">
	    <table id="dg" ></table>
  	</div>
  	
    <script type="text/javascript">	   			
  		$("#bt").click(function () {
  			
  			// 提交记录日志的信号
  			$.ajax({
				type : "post",
				url : "writelog",
				data : {
					event : "查看了攻击信息日志"
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
             	url : '${pageContext.request.contextPath}/attacklogjson.action',			
 				method : 'post',
 				columns : [[    
			        {field:'time',title:'时间',width:150,sortable:true},   
			        {field:'sourceMac',title:'攻击源MAC地址',width:100,sortable:true},    
			        {field:'attackTarget',title:'攻击对象',width:100,sortable:true,
			        	formatter: function(value,row,index){
							if (value == 1){
								return "业务端";
							} else {
								return "管理端";
							}
						}
			        },    
			        {field:'attackType',title:'攻击类型',width:100,sortable:true,
						formatter: function(value,row,index){
							if (value == 1){
								return "端口扫描";
							} else if (value == 2){
								return "ICMP攻击";
							} else if (value == 3){
								return "SYN攻击";
							} else {
								return "UDP攻击";
							}
						}
			        },    
			        {field:'targetIp',title:'目标IP',width:100,sortable:true},    
			        {field:'targetPort',title:'目标端口号',width:100,sortable:true},    
			        {field:'remarks',title:'备注',width:150,sortable:true}    		        
 				]], 				
 				queryParams: {
 					attackTarget: $("#attackTarget").val(),
 					attackType: $("#attackType").val(),
 					startTime:$("#startTime").val(),
 					endTime:$("#endTime").val(),
 					sourceMac:$("#sourceMac").val(),
 					destinationIp:$("#destinationIp").val(),
 					destinationPort:$("#destinationPort").val()
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
