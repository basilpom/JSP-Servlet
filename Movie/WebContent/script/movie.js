function movieCheck()
{
	if(document.frm.title.value.length == 0)
	{
		alert("영화 제목을 입력하세요.");
		frm.name.focus();
		return false;
	}
	if(document.frm.price.value.length == 0)
	{
		alert("가격을 입력하세요.");
		frm.price.focus();
		return false;
	}
	if(isNaN(document.frm.price.value))
	{
		alert("가격은 숫자만 입력 가능합니다.");
		frm.price.select();
		return false;
	}
}