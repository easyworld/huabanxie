<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>No title</title>
</head>
<body>
<div>
	<h1>
	<#if message??>
		<#if message == 0>
		Hello,
		<#if userContext??>
			${userContext.username}
		</#if>
		.<a href="task.html">继续</a>
		<#elseif message == 1>
		Maybe, error username or password.<a href="index.html">Back!</a>
		</#if>
	</#if>
	</h1>
</div>
</body>
</html>