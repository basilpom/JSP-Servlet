<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Coupang Join Demo</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<script>
	$(document).ready(function(){
		// email validation
		var emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		$("#email").on("focusout", function(){
			var u_email = $("#email").val();
			if(u_email == "")
			{
				$("#emailAlert").text("이메일을 입력하세요.");
				$(this).parent().addClass("on");
			}
			else if(!emailRule.test(u_email))
			{
				$("#emailAlert").text("이메일 형식이 올바르지 않습니다.");
				$(this).addClass("on");
			}
			else
			{
				$("#emailAlert").text("");
				$(this).removeClass("on");
			}
		})
		
		//pw validation : epthffh.tistory.com/entry/비밀번호-정규식 [물고기 개발자의 블로그]
		//var pwRule = /^.*(?=^.{6,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
		var pwRule_en = /^.*(?=^.{6,15}$)(?=.*[a-zA-Z]).*$/;
		var pwRule_num = /^.*(?=^.{6,15}$)(?=.*\d).*$/;
		var pwRule1 = /^.*(?=^.{6,15}$)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
		var pwRule2 = /^.*(?=^.{6,15}$)(?=.*\d)(?=.*[a-zA-Z]).*$/;
		var pwRule3 = /^.*(?=^.{6,15}$)(?=.*\d)(?=.*[!@#$%^&+=]).*$/;
		$("#pw").on("focusout", function(){
			var u_pw = $("#pw").val();
			
			if(u_pw.length < 6)
			{
				$("#pwAlert").text("비밀번호는 6~15자 이내로 입력하셔야 합니다.")
				$(this).addClass("on");
			}
			else if(pwRule1.test(u_pw) || pwRule2.test(u_pw) || pwRule3.test(u_pw))
			{
				$("#pwAlert").text("");
				$(this).removeClass("on");
			}
			else if(pwRule_en.test(u_pw))
			{
				$("#pwAlert").text("비밀번호는 영자로만 입력할 수 없습니다.");
				$(this).addClass("on");
			}
			else if(pwRule_num.test(u_pw))
			{
				$("#pwAlert").text("비밀번호는 숫자로만 입력할 수 없습니다.");
				$(this).addClass("on");
			}
		});
		
		//password check
		$("#pwCheck").on("focusout", function(){
			var u_pwCheck = $("#pwCheck").val();
			if(u_pwCheck != $("#pw").val())
			{
				$("#pwCheckAlert").text("비밀번호가 일치하지 않습니다.");
				$(this).addClass("on");
			}
			else
			{
				$("#pwCheckAlert").text("");
				$(this).removeClass("on");
			}
		});
		
		// name validation : sadtear.tistory.com/106 [[ Be Happy...]]
		var nameRule = /^[가-힣]{2,4}|[a-zA-Z]{2,10}$/;


		$("#name").on("focusout", function(){
			var u_name = $("#name").val();
			if(!nameRule.test(u_name))
			{
				$("#nameAlert").text("이름을 정확히 입력하세요.");
				$(this).addClass("on");
			}
			else
			{
				$("#nameAlert").text("");
				$(this).removeClass("on");
			}
		});
		
		// phone validation : suyou.tistory.com/150 [수유산장]
		var phoneRule = /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}|01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/;
	
		$("#phone").on("focusout", function(){
			var u_phone = $("#phone").val();
			if(!phoneRule.test(u_phone))
			{
				$("#phoneAlert").text("휴대폰 번호를 정확하게 입력하세요.");
			}
			else
			{
				$("#phoneAlert").text("");
			}
		});
	});
	

</script>
<style>
	* {padding: 0; margin: 0;}
	a {color: black;}
	div {
		width: 450px;
		margin: auto;
		font-size: 12px;
	}
	input {
		display: flex;
		width: 400px;
		height: 50px;
		border: none;
		padding-left: 10px;
	}
	input:focus {
		border: solid 1px #004890;
	}
	.inputBox {
		display: flex;
		width: 450px;
		heigth: 50px;
		margin: 10px auto;
		overflow: hidden;
		border: solid 1px #CCCCCC;
	}
	.inputBox>i {
		padding: 10px;
		width: 32px;
		height: 32px;
		text-align: center;
		line-height: 32px;
		font-size: 25px;
		border-right: solid 1px #CCCCCC;
		background: #FAFAFA;
		color: #CCCCCC;
	}
	#CoupangJoin>div {color: #E52528;}
	
	#btnSubmit {
		width: 450px;
		height: 50px;
		margin: 10px auto;
		background: #0074E9;
		color: white;
		border-bottom: solid 2px #004890;
		border-right: solid 2px #004890;
		font-size: 16px;
		
		/*padding-left: 160px;*/
	}
	#btnDiv{
	text-align: center;
	}
	.on {border: solid 1px #E52528}

	footer {
		width: 450px;
		margin: 20px auto;
		text-align: center;
		color: #555555;
	}
</style>
</head>
<body>
	<header style="width: 194px; margin: 50px auto 10px;">
		<img src="https://img1a.coupangcdn.com/image/static/login/logo-coupang.x1.20160902.png" />
	</header>
	<form name="CoupangJoin" id="CoupangJoin" action="Join" method="post">
		<div class="inputBox">
			<i class="far fa-envelope"></i>
			<input type="text" name="email" id="email" placeholder="아이디(이메일)">
		</div>
		<div id="emailAlert"></div>
		
		<div class="inputBox">
			<i class="fas fa-unlock"></i>
			<input type="password" name="pw" id="pw" maxlength="15" placeholder="비밀번호(영문 숫자 특수문자 2가지 이상 6~15자 이내)">
		</div>
		<div id="pwAlert"></div>
		
		<div class="inputBox">
			<i class="fas fa-unlock-alt"></i>
			<input type="password" name="pwCheck" id="pwCheck" placeholder="비밀번호 확인">
		</div>
		<div id="pwCheckAlert"></div>
		
		<div class="inputBox">
			<i class="far fa-user"></i>
			<input type="text" name="name" id="name" placeholder="이름">
		</div>
		<div id="nameAlert"></div>
		
		<div class="inputBox">
			<i class="fas fa-mobile-alt"></i>
			<input type="text" name="phone" id="phone" placeholder="휴대폰 번호">
		</div>
		<div id="phoneAlert"></div>
		<div id="btnDiv">
			<!-- <input type="submit" id="btnSubmit" value="동의하고 가입하기"> -->
			<button type="submit" id="btnSubmit">동의하고 가입하기 </button>
        </div>
	</form>
	<div>
		본인은 <b>만 14세 이상</b>이며, 
		<a href="#">쿠팡 이용약관</a>, 
		<a href="#">전자금융거래이용약관</a>, 
		<a href="#">개인정보 수집 및 이용</a>, 
		<a href="#">개인정보 제공 내용</a>
		을 확인하였으며, <b>동의합니다.</b>
	</div>
	<footer>
		<div>
			&copy;Coupang Corp. All rights reserved.
		</div>
	</footer>
</body>
</html>