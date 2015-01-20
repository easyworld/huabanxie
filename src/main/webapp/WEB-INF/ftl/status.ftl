<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>状态</title>
</head>
<body>
	<#if status??>
		<#if status == -1>
			<div>插入数据错误</div>
		<#else>
			<div>成功插入${status}个数据</div>
		</#if>
	</#if>
	<input type="button" value="返回" onclick="window.location.href='register.html'"/>
</body>
</html>