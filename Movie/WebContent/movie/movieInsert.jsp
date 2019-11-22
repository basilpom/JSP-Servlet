<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 등록 페이지</title>
<link rel="stylesheet" type="text/css" href="css/movie.css">
<script src="script/movie.js"></script>
</head>
<body>
<div id="wrap" align="center">
	<h1>영화 등록 - 관리자 페이지</h1>
	<form method="post" enctype="multipart/form-data" name="frm">
		<table>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" size="80"></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input type="text" name="price"></td>
			</tr>
			<tr>
				<th>감독</th>
				<td><input type="text" name="director"></td>
			</tr>
			<tr>
				<th>배우</th>
				<td><input type="text" name="actor"></td>
			</tr>
			<tr>
				<th>설명</th>
				<td><textarea cols="80" rows="10" name="synopsis"></textarea></td>
			</tr>
			<tr>
				<th>사진</th>
				<td>
					<input type="file" name="poster"><br />
				</td>
			</tr>
			
		</table>
		<br />
		<input type="submit" value="등록" onClick="return movieCheck()">
		<input type="reset" value="다시작성">
		<input type="button" value="목록" onClick="location.href='movieList.do'">
	</form>
</div>
</body>
</html>