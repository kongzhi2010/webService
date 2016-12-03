function login() {
	var username = $("#username").val();
	var password = $("#password").val();
	var check = checkUserNameAndPassword(username, password);
	if (check.succ == false) {
		bootbox.alert(check.msg);
		return;
	}
	var toUrl = $("#toUrl").val();
	$.ajax({
		url : "/login/login",
		type : "post",
		async : false,
		dataType : "json",
		data : {
			"username" : username,
			"password" : password
		},
		success : function(data) {
			bootbox.alert(data.msg);
			if (data.succ) {
				// 2秒之后转到首页
				setTimeout(function() {
					redirectToUrl(toUrl);
				}, 1000);
			}
		},
		error : function(data) {

		}
	});
}

function redirectToUrl(toUrl) {
	if (!toUrl || toUrl.length == 0) {
		location.href = "/";
		return true;
	}
	location.href = toUrl;
}

function checkUserNameAndPassword(username, password) {
	var check = new Object();
	if (!username || username.length == 0) {
		check.succ = false;
		check.msg = "用户名不能为空，请重新输入！";
		return check;
	}
	if (!password || password.length == 0) {
		check.succ = false;
		check.msg = "密码不能为空，请重新输入！";
		return check;
	}
	check.succ = true;
	check.msg = "success";
	return check;
}