/**********************************/
/***	登录表单的验证   ***********/
/**********************************/

$(document).ready(function(){	
	// 检测用户是否输入用户名
	$("#txtusername").blur(function() {
		if ($("#txtusername").val() == "") {
			$("#usernameTd").html("<span style=\"color:red;\">请输入用户名</span>");
		}else{
			$("#usernameTd").hide();
			$("#tip").hide();
		}
	});
	// 检测用户是否输入密码
	$("#txtuserpassword").blur(function() {
		if ($("#txtuserpassword").val() == "") {
			$("#passwordTd").html("<span style=\"color:red;\">请输入密码</span>");
		}else{
			$("#passwordTd").hide();
			$("#tip").hide();
		}
	});	
	// 登录提交验证
	$("#btnlogin").click(function(){
 		var userName = $("#txtusername").val();
 		var userPassword = $("#txtuserpassword").val();
		if(userName == ""){
			alert("用户名不能为空");
		}else if(userPassword == ""){
			alert("密码不能为空");
		}
		else{									
			document.forms[0].submit();
		}
	});
});	

function keyLogin() {
	if (event.keyCode == 13) // 回车键的键值为13
	{
		var userName = $("#txtusername").val();
		var userPassword = $("#txtuserpassword").val();
		if (userName == "") {
			alert("用户名不能为空");
		} else if (userPassword == "") {
			alert("密码不能为空");
		} else {
			document.forms[0].submit();
		}
	}
}
