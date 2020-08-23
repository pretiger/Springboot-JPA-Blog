$(function(){
	$("#btn-save").click(function(){
		let param = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		$.ajax({
			type: "post",
			url: "/auth/joinProc",
			data: JSON.stringify(param),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(result){
				alert("회원가입이 완료되었습니다.");
				console.log("success 결과값 : ", result);
				location.href = "/";
			},
			error: function(err){
				console.log("error 결과값 : ", err);
			}
		});
	});
	
	$("#btn-update").click(function(){
		let param = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		console.log("user정보:",param);
		$.ajax({
			type: "put",
			url: "/user",
			data: JSON.stringify(param),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(result){
				alert("회원수정이 완료되었습니다.");
				console.log("success 결과값 : ", result);
				location.href = "/";
			},
			error: function(err){
				console.log("error 결과값 : ", err);
			}
		});
	});
	
	/*$("#btn-login").click(function(){
		let param = {
			username: $("#username").val(),
			password: $("#password").val()
		};
		console.log(param);
		$.ajax({
			type: "post",
			url: "/api/login",
			data: JSON.stringify(param),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(result){
				console.log("success 결과값 : ", result);
				location.href = "/";
			},
			error: function(err){
				console.log("error 결과값 : ", err);
			}
		});
	});*/
});