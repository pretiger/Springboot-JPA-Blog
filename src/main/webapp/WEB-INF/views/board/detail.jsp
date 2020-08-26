<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	<c:if test="${board.user.id == principal.user.id}">
		<a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
		<button id="btn-delete" class="btn btn-danger">삭제</button>
	</c:if>
	<br /> <br />
	<div>
		글번호 : <span id="id"><i>${board.id} </i></span> 작성자 : <span><i>${board.user.username} </i></span>
	</div>
	<div>
		<h3>${board.title}</h3>
	</div>
	<hr />
	<div>
		<div>${board.content}</div>
	</div>
	<hr />
	<div class="card">
		<form>
			<input type="hidden" id="userId" value="${principal.user.id}" />
			<input type="hidden" id="boardId" value="${board.id}" />
			<div class="card-body">
				<textarea id="reply2-content" class="form-control" rows="1"></textarea>
			</div>
			<div class="card-footer">
				<button type="button" id="btn-reply2-save" class="btn btn-primary">등록</button>
			</div>
		</form>
		<!-- 		<br /> -->
		<div class="card">
			<div class="card-header">댓글 리스트</div>
			<ul id="reply2-box" class="list-group">
				<c:forEach var="reply2" items="${board.reply2s}">
					<li id="reply2-${reply2.id}" class="list-group-item d-flex justify-content-between">
						<div>${reply2.content}</div>
						<div class="d-flex">
							<div class="font-italic">작성자 : ${reply2.user.username} &nbsp;</div>
							<button onclick="reply2Delete(${board.id},${reply2.id})" class="badge">삭제</button>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>