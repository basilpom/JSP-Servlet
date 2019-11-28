<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글</title>
<link rel="stylesheet" href="css/board.css">
<script src="script/board.js"></script>
</head>
<body>
<div id="wrap" align="center">
	<h1>게시글 상세보기</h1>
	<table>
		<tr>
			<th>작성자</th> <td>${board.name}</td>
			<th>이메일</th> <td><%-- ${board.email} --%></td>
		</tr>
		<tr>
			<th>작성일</th> <td>${board.writedate}</td>
			<th>조회수</th> <td>${board.readcount}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan="3">${board.title}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="3" height="300" valign="top"><pre>${board.content}</pre></td>
		</tr>
		<!-- <tr>
			<th>내용</th>
			<td colspan="3"><textarea rows="20" cols="80" readonly>${board.content}</textarea></td>
		</tr> -->
	</table>
	<br />
	<input type="button" value="게시글 수정" onClick="open_win('BoardServlet?command=board_check_pass_form&num=${board.num}', 'update')">
	<input type="button" value="게시글 삭제" onClick="open_win('BoardServlet?command=board_check_pass_form&num=${board.num}', 'delete')">
	<input type="button" value="게시글 리스트" onClick="location.href='BoardServlet?command=board_list'">
	<input type="button" value="게시글 등록" onClick="location.href='BoardServlet?command=board_write_form'">
	<br />
	<!-- 댓글목록 ------------------------------------------------------------------------->
	<form name="frmUpdate" action="BoardServlet" method="post">
	<input type="hidden" name="command" value="reply_update">
	<table class="list">
		<c:forEach var="reply" items="${replyList}">
		<c:choose>
		<c:when test="${reply.no == param.no}">
		<input type="hidden" name="pNum" value="${board.num}">
		<input type="hidden" name="no" value="${reply.no}">
		<tr class="update">
			<td style="border: none;">
				<input type="text" name="name" placeholder="name" value="${reply.name}"><br />
				<input type="password" name="password" placeholder="password"><br />
				<textarea name="content" rows="5" cols="100" placeholder="content">${reply.content}</textarea>
			</td>
			<td width="20%" style="border: none;">
				<input type="submit" value="수정" onClick="return replyUpdateCheck()">
				<input type="button" value="취소" onClick="history.go(-1)">
		</tr>
		</c:when>
		<c:otherwise>
		<tr class="record">
			<td style="border: none;">
				${reply.name}<br />
				<fmt:formatDate value="${reply.writedate}"/><br />
				<pre>${reply.content}</pre>
			</td>
			<td width="20%" style="border: none;">
				<input type="button" value="수정" onClick="open_win2('BoardServlet?command=reply_check_pass_form&pNum=${board.num}&no=${reply.no}', 'update')">
				<input type="button" value="삭제" onClick="open_win2('BoardServlet?command=reply_check_pass_form&pNum=${board.num}&no=${reply.no}', 'delete')">
			</td>
		</tr>
		<tr>
			<td style="border: none;" colspan="2"><hr></td>
		</tr>
		</c:otherwise>
		</c:choose>
		</c:forEach>
	</table>
	</form>
	<!-- 댓글목록 -->
	<!-- 댓글 입력 폼 ----------------------------------------------------------------------->
	<form name="frm" action="BoardServlet" method="post">
	<input type="hidden" name="pNum" value="${board.num}">
	<input type="hidden" name="command" value="reply_write">
		<table>
			<tr>
				<td><input type="text" name="name" placeholder="name"></td>
			</tr>
			<tr>
				<td><input type="password" name="password" placeholder="password"></td>
			</tr>
			<tr>
				<td>
					<textarea name="content" rows="5" cols="100" placeholder="content"></textarea>
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="댓글등록" onClick="return replyCheck()"></td>
			</tr>
		</table>
	</form>
	<!-- 댓글 입력 폼 -->
</div>
</body>
</html>