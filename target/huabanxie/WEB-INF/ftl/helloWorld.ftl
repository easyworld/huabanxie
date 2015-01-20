<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>${message}</h2>
<table border='1'>
	<tr>
		<td>job_id</td>
		<td>job_name</td>
	</tr>
	<#if joblist??>
		<#list joblist as zxc>
		    <tr>
		    	<#-- 
				<#list job?keys as k>
					<td>${job[k]}</td>
				</#list>-->
				<td>${zxc.job_id}</td>
				<td>${zxc.job_name}</td>
		    </tr>
		</#list>
	</#if>
</table>
</body>
</html>