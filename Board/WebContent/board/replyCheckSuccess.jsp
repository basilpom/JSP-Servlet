<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<script>
	if(window.name == "update")
	{
		window.opener.parent.location.href = "BoardServlet?command=reply_update_form&no=${param.no}";
	}
	else if(window.name == "delete")
	{
		if(confirm("삭제하시겠습니까?"))
		{
			alert("삭제되었습니다.");
			window.opener.parent.location.href = "BoardServlet?command=reply_delete&no=${param.no}";
		}
	}
	window.close();
</script>
</body>
</html>