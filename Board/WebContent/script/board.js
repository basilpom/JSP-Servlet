function boardCheck()
{
	if(document.frm.name.value.length == 0)
	{
		alert("작성자를 입력하세요.");
		frm.name.focus();
		return false;
	}
	if(document.frm.pass.value.length == 0)
	{
		alert("비밀번호를 입력하세요.");
		frm.pass.focus();
		return false;
	}
	if(document.frm.title.value.length == 0)
	{
		alert("제목을 입력하세요.");
		frm.title.focus();
		return false;
	}
	if(document.frm.content.value.length == 0)
	{
		alert("내용을 입력하세요.");
		frm.content.focus();
		return false;
	}
	// 금칙어 검사
	var keywords = ['바보','멍청이'];
//	keywords.forEach(function(keyword){
//		if(document.frm.content.value.indexOf(keyword) != -1)
//		{
//			alert(keyword+"는 사용할 수 없는 단어입니다.");
//			return false;
//		}
//	});
	for(i in keywords)
	{
		if(document.frm.content.value.indexOf(keywords[i]) != -1)
		{
			alert(keywords[i]+"는 사용할 수 없는 단어입니다.");
			return false;
		}
	}
	
}
function open_win(url, name)
{
	window.open(url, name, "width=500, height=230");
}
function passCheck()
{
	if(document.frm.pass.value.length == 0)
	{
		alert("비밀번호를 입력하세요.");
		return false;
	}
}