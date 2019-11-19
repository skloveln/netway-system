
/**
 * 增加用户或管理员检查用户名
 */

$(document).ready(function() {
	$("#account").focus(function() {
		$("#tip").hide();
	});
	
	$("#password").focus(function() {
		$("#pwdtip").hide();
	});
	
	$("#password").blur(function(){
		
		if($("#password").val().length < 8){
			$("#pwdtip").html("密码长度不能小于8位");
			$("#pwdtip").show();
			return;
		}
		if($("#password").val().length > 20){
			$("#pwdtip").html("密码长度不能大于20位");
			$("#pwdtip").show();
			return;
		}
		if(($("#password").val().match(/([a-z])/) && $("#password").val().match(/[0-9]/))
			|| ($("#password").val().match(/([A-Z])/) && $("#password").val().match(/[0-9]/))
			){		
		}else{
			$("#pwdtip").html("密码必须包含字母与数字");
			$("#pwdtip").show();
		}
	});
	
	$("#account").blur(function() {
		if ($("#account").val().trim() != "") {
			$.ajax({
				type : "post",
				url : "ajaxadduser",
				data : {
					account : $("#account").val().trim()
				},
				dataType : "json",
	//			contentType:"application/json;charset=utf-8",
				success : function(data) {
					if (data.result == true) {
						$("#tip").html("用户名可用");
						$("#tip").css('color', 'green');
					} else {
						$("#tip").html("用户名已存在");
						$("#tip").css('color', 'red');
					}
					$("#tip").show();
				},
				error : function(ajax, b, c) {
					alert("数据出错，请稍后再试");
				}
			});
		} else {
			$("#tip").html("用户名不能为空");
			$("#tip").css('color', 'red');
			$("#tip").show();
		}
	});
});



