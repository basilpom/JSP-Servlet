<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<th>이메일</th> <td>${board.email}</td>
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
</div>
</body>
</html>