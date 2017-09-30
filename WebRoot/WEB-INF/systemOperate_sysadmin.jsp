<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
	<title>操作日志</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>

	<script type="text/javascript">
		
		function deleteInfo(arg)
		{
			var b = confirm("确定删除吗？");
			if(b == true){
				$.ajax({
					type : "post",
					url : "deleteoperatejson",
					data : {
						account : arg
					},
					dataType : "json",
					success : function(data) {
						if(data.status){
							$("#dg").datagrid('reload');
						}else{
							window.location.href("relogin");
						}	
					},
					error : function(ajax, b, c) {
						alert("数据出错，请稍后再试");
					}
				});	
			}else{
				
			}		
		}
			
	</script>
</head>

<body>
<div class="main">
	
	<h1 class="page_title" align="center" >≡≡≡ 操作日志 ≡≡≡</h1>   
    <div style="padding-left: 50px;height: 10%">
    	<br>
	    <form>
			用户名：<input type="text" id="account" value="全部" style="width: 100px;"/>&nbsp;&nbsp; 
			姓名：<input type="text" id="name" value="全部" style="width: 100px;"/>&nbsp;&nbsp; 			
	  		时间：<input type="text" id="startTime" onfocus="calendar(this)" value="2016-01-01 00:00:00"/>
			至 <input type="text" id="endTime" onfocus="calendar(this)" value="2018-01-01 00:00:00"/> &nbsp;&nbsp; 
			<input id="bt" type="button" value="查询"/>
			 
		</form>		
    </div>    
    <div style="height: 80%; padding-left: 50px;">
    	<table id="dg"></table>
	</div>
	
	<script type="text/javascript">	
		$("#bt").click(function () {
			
			// 提交记录日志的信号
  			$.ajax({
				type : "post",
				url : "writelog",
				data : {
					event : "查看了操作日志"
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
	             	url : '${pageContext.request.contextPath}/operatejson.action',			
	 				method : 'post',
	 				columns : [[
						{field:'time',title:'时间',width:150,sortable:true},
				        {field:'account',title:'用户名',width:100,sortable:true},   
				        {field:'name',title:'姓名',width:100,sortable:true,
				        	editor:{
				        		type:'textbox'
				        	}
				        },    
				        {field:'role',title:'角色',width:100,sortable:true},
				        {field:'ip',title:'IP',width:100,sortable:true},
				        {field:'event',title:'行为',width:250,sortable:true},
				        {field:'remarks',title:'备注',width:100,sortable:true}
/*				        {field:'edit',title:'操作',width:100,align:'center',sortable:true,
				        	formatter:function(value, row, index) {	            		
			            		return "<a style='text-decoration:none;' href=\"javascript:deleteInfo('"
			            			+ row.account +"')\" ><img align=\"middle\" src=\"themes/icons/cancel.png\"> 删除</a>";
			            	}
				        }  		        
*/	 				]], 				
	 				queryParams: {
	 					account: $("#account").val().trim(),
	 					name: $("#name").val().trim(),
	 					startTime: $("#startTime").val(),
	 					endTime: $("#endTime").val()
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
	
</div>

</body>
</html>