$(function(){
	$("#btn-save").click(function(){
		let param = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		console.log(param);
		$.ajax({
			type: "post",
			url: "/api/user",
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
	});
	
	$("#btn-login").click(function(){
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
	});
});