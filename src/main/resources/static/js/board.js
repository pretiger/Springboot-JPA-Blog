$(function(){
	$("#btn-update").click(function(){
		let id = $("#id").val();
		let param = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		console.log(param);
		$.ajax({
			type: "put",
			url: "/api/board/"+id,
			data: JSON.stringify(param),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(result){
				console.log("success 결과값 : ", result);
				alert("글수정이 완료되었습니다.");
				location.href = "/";
			},
			error: function(err){
				console.log("error 결과값 : ", err);
			}
		});
	});
	
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
	
	$("#btn-delete").click(function(){
//		$("#id").val() 값을 id에 저장하면 controller에서 id값을 읽지못함
		const id = $("#id").text();
		$.ajax({
			type: "delete",
			url: "/api/board/"+id,
			success: function(result){
				console.log("success 결과값 : ", result);
				alert("삭제가 완료되었습니다.");
				location.href = "/";
			},
			error: function(err){
				console.log("error 결과값 : ", err);
			}
		});
	});
	
	$("#btn-reply2-save").click(function(){
		let param = {
			userId: $("#userId").val(),
			boardId: $("#boardId").val(),
			content: $("#reply2-content").val()
		};
		console.log(param);
		$.ajax({
			type: "post",
			url: `/api/board/${param.boardId}/reply2`,
			data: JSON.stringify(param),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(result){
				console.log("success 결과값 : ", result);
				alert("댓글 작성이 완료되었습니다.");
				location.href = `/board/${param.boardId}`;
			},
			error: function(err){
				console.log("error 결과값 : ", err);
			}
		});
	});
});

function reply2Delete(boardId, reply2Id){
	$.ajax({
		type: "delete",
		url: `/api/board/${boardId}/reply2/${reply2Id}`,
		dataType: "json",
		success: function(result){
			alert("댓글삭제 성공!");
			location.href = `/board/${boardId}`;
		},
		error: function(err){
			alert(JSON.stringify(err));
		}
	});
}