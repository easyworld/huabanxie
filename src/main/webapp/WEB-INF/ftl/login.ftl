<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<#if message??>
	<#if message == 0>
		<meta http-equiv=refresh content='5;url=task.html'>
	<#elseif message == 1>
		<meta http-equiv=refresh content='5;url=index.html'>
	</#if>
</#if>
<title>Login</title>
</head>
<body>
<div>
	<h1>
	<#if message??>
		<#if message == 0>
		Hello,
		<#if userContext??>
			${userContext.USERNAME}.
		</#if>
			<a href="task.html">继续</a>
		<#elseif message == 1>
		Maybe, error username or password.<a href="index.html">返回</a>
		</#if>
	</#if>
	</h1>
	5s后自动跳转。
</div>
</body>
</html>