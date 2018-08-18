<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="false" %>

<%@ include file="../header.jsp"%>

<section class="content">
	<div class="row">
		<div class="col-12 text-right">
			<a href="/board/register" class="btn btn-success">글쓰기</a>
		</div>
	</div>

	<div class="row">
		<div class="col-12">
			<table class="table table-bordered">
			
				<tr>
					<th style="width: 10px">#</th>
					<th>제목</th>
					<th>작성자</th>
					<th>등록일</th>
					<th>조회수</th>
				</tr>
				
			<c:forEach items="${list}" var="board">
				<tr>
					<td>${ board.bno }</td>
					<td><a href="/board/read?bno=${board.bno}">${ board.title }</a></td>
					<td><strong>${ board.writer }</strong></td>
					<td><fmt:formatDate pattern="YYYY-MM-dd HH:mm:ss" value="${ board.regdate }"/></td>
					<td><span class="badge bg-red">${ board.viewcnt }</span></td>
				</tr>
			</c:forEach>
				
			</table>
		</div>
	</div>

</section>

<script>
	var result = '${msg}';
	if (result === 'success') {
		alert("OK");
	}
</script>

<%@ include file="../footer.jsp"%>
</html>