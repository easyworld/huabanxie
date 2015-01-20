<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆</title>
</head>
<body>
	<div>
		<table>
			<form name="login" action="login.html" method="post">
				<tr>
					<td>Username:</td>
					<td><input type="text" name="username" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="登陆" /></td>
					<td><a href="register.html">注册</a></td>
				</tr>
				<input type="hidden" name="userContext" value="not null" />
			</form>
		</table>
	</div>
</body>
</html>