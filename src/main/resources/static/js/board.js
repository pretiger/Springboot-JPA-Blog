$(function(){
	$("#btn-save").click(function(){
		let param = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		console.log(param);
		$.ajax({
			type: "post",
			url: "/api/board",
			data: JSON.stringify(param),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(result){
				console.log("success 결과값 : ", result);
				alert("글쓰기가 완료되었습니다.");
				location.href = "/";
			},
			error: function(err){
				console.log("error 결과값 : ", err);
			}
		});
	});

});