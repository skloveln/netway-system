<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
	<title>管理员信息</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
	
	function deleteInfo(arg)
	{
		var b = confirm("确定删除吗？");
		if(b == true){
			$.ajax({
				type : "post",
				url : "deleteuserjson",
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
	
	var editIndex = undefined;
	function endEditing(){
		if (editIndex == undefined){return true;}
		if ($('#dg').datagrid('validateRow', editIndex)){			
			$('#dg').datagrid('endEdit', editIndex);
			$("#dg").datagrid('rejectChanges');		// 回退数据
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	
	function updateInfo(index){
		if (editIndex != index){
			if (endEditing()){
				$('#dg').datagrid('selectRow', index).datagrid('beginEdit', index);
				var ed = $('#dg').datagrid('getEditor', {index:index, field:'name'});
				if (ed){
					($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
				}
				editIndex = index;
			} else {
				$('#dg').datagrid('selectRow', editIndex); 	// 选中要编辑的行
			}
		}
	}
	
</script>
</head>

<body>
<div class="main">
	
	<h1 class="page_title" align="center" >≡≡≡ 管理员管理 ≡≡≡</h1>   
    
    <div style="padding-left: 100px;height: 10%">
    	<br>
	    <form>
			用户名：<input type="text" id="account" value="全部" style="width: 180px;"/>&nbsp;&nbsp;&nbsp;&nbsp;  
			姓名：<input type="text" id="name" value="全部" style="width: 180px;"/>
			<input id="bt" type="button" value="查询"/> 
		</form>		
    </div>
        
    <div style="height: 80%; padding-left: 50px;">
    	<table id="dg"></table>
	</div>
	
	<script type="text/javascript">
	
		$("#bt").click(function () {
			
	  			$("#dg").datagrid({           	 
	             	url : '${pageContext.request.contextPath}/queryadminjson.action',			
	 				method : 'post',
	 				columns : [[    
				        {field:'account',title:'用户名',width:100,sortable:true},   
				        {field:'name',title:'姓名',width:100,sortable:true,
				        	editor:{
				        		type:'textbox'
				        	}
				        },    
				        {field:'role',title:'角色',width:100,sortable:true},    
				        {field:'edit',title:'操作',width:100,sortable:true,
				        	formatter:function(value, row, index) {	            		
			            		return "<a style='text-decoration:none;' href=\"javascript:updateInfo('"+ 
			            		index +"')\" ><img align=\"middle\" src=\"themes/icons/edit_remove.png\"> 编辑</a>"
			            			+"&nbsp;&nbsp; | &nbsp;&nbsp;<a style='text-decoration:none;' href=\"javascript:deleteInfo('"
			            			+ row.account +"')\" ><img align=\"middle\" src=\"themes/icons/cancel.png\"> 删除</a>";
			            	}}  		        
	 				]], 				
	 				queryParams: {
	 					account: $("#account").val(),
	 					name: $("#name").val(),
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
	 				},
	 				toolbar: [{
	 					iconCls: 'icon-save',
	 					text:'保存',
	 					handler: function(){
	 						$("#dg").datagrid('acceptChanges');		// 提交改变的数据 						
	 						var rows = $('#dg').datagrid('getRows');   // 获取所有行
	 						var row = rows[editIndex];		// 获取正在编辑的行
 							var name = row.name;	
 							var account = row.account;								
 							endEditing();	// 结束编辑
 							
 							//  数据校验
 							name.trim();
 							
 							//ajax异步提交数据
 							$.ajax({
 								type : "post",
 								url : "updateadminjson",
 								data : {
 									account : account,
 									name : name
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
	 					}
	 				},{
	 					iconCls: 'icon-cancel',
	 					text:'取消',
	 					handler: function(){
	 						$("#dg").datagrid('rejectChanges');		// 回退数据
	 						editIndex = undefined; 						
	 					}
	 				}]
	 	  		});
	         }); 
		
	</script>
	
</div>

</body>
</html>