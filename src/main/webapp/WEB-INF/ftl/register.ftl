<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆</title>
</head>
<body>
	<#if status??>
		<#if status == -1>
			<div>插入数据错误</div>
		<#else>
			<div>成功插入${status}个数据</div>
		</#if>
	</#if>
	<div></div>
	<div>
		<form id="userInfoForm" action="insertUser.html" name="userInfo" method="post">
			<table>
				<tr>
					<td>姓名:</td>
					<td><input id="username" type="text" name="username" /></td>
				</tr>
				<tr>
					<td>密码:</td>
					<td><input id="password" type="password" name="password" /></td>
				</tr>
				<tr>
					<td>职务:</td>
					<td>
						<select name="job_id" >
							<#if jobList??>
							<#list jobList as zxc>
								<option value="${zxc.job_id}">${zxc.job_name}</option>
							</#list>
							</#if>
						</select>
					</td>					
				</tr>
				
				<tr>
					<td>部门:</td>
					<td>
						<select name="dept_id" >
							<#if deptList??>
							<#list deptList as zxc>
								<option value="${zxc.dept_id}">${zxc.dept_name}</option>
							</#list>
							</#if>
						</select>
					</td>					
				</tr>
				<tr>
					<td>主管领导:</td>
					<td>
						<select name="mgr_id" >
							<#if userList??>
							<#list userList as zxc>
								<option value="${zxc.id}">${zxc.USERNAME}</option>
							</#list>
							</#if>
						</select>
					</td>	
				</tr>
				<tr>
					<td><input type="button" value="提交" onclick="validate()"/></td>
					<td><input type="button" value="返回" onclick="window.location.href='index.html'"/></td>
				</tr>
			</table>
			
		</form>
	</div>
</body>
<script type="text/javascript">
	function validate(){
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		if(username.length < 1 || password < 1){
			alert("Please type something");
			return;
		}
		if(username.length > 16 || password.length > 16){
			alert("You type too much");
			return;
		}
		document.getElementById("userInfoForm").submit();
	}
</script>
</html>