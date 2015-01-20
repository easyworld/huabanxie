<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆</title>
<style type="text/css"> 
body { 
    margin:0; 
    padding:0; 
    background-color:#E4E8EC; 
} 
.wrap { 
    margin:150px auto; 
    width:380px; 
    overflow:hidden; 
} 
.loginForm { 
    box-shadow: 0 0 2px rgba(0, 0, 0, 0.2), 0 1px 1px rgba(0, 0, 0, 0.2), 0 3px 0 #fff, 0 4px 0 rgba(0, 0, 0, 0.2), 0 6px 0 #fff, 0 7px 0 rgba(0, 0, 0, 0.2); 
    position:absolute; 
    z-index:0; 
    background-color:#FFF; 
    border-radius:4px; 
    height:250px; 
    width:300px; 
    background: -webkit-gradient(linear, left top, left 24, from(#EEE), color-stop(4%, #FFF), to(#EEE)); 
    background: -moz-linear-gradient(top, #EEE, #FFF 1px, #EEE 24px); 
    background: -o-linear-gradient(top, #EEEEEE, #FFFFFF 1px, #EEEEEE 24px); 
} 
.loginForm:before { 
    content:''; 
    position:absolute; 
    z-index:-1; 
    border:1px dashed #CCC; 
    top:5px; 
    bottom:5px; 
    left:5px; 
    right:5px; 
    box-shadow: 0 0 0 1px #FFF; 
} 
.loginForm h1 { 
    text-shadow: 0 1px 0 rgba(255, 255, 255, .7), 0px 2px 0 rgba(0, 0, 0, .5); 
    text-transform:uppercase; 
    text-align:center; 
    color:#666; 
    line-height:3em; 
    margin:16px 0 20px 0; 
    letter-spacing: 4px; 
    font:normal 26px/1 Microsoft YaHei, sans-serif; 
} 
fieldset { 
    border:none; 
    padding:10px 10px 0; 
} 
fieldset input[type=text], fieldset input[type=password] { 
    width:100%; 
    line-height:2em; 
    font-size:12px; 
    height:24px; 
    border:none; 
    padding:3px 4px 3px 4px; 
    width:240px; 
}
fieldset input[type=submit] { 
    text-align:center; 
    padding:2px 20px; 
    line-height:2em; 
    border:1px solid #FF1500; 
    border-radius:3px; 
    background: #FF9600;
    height:30px; 
    cursor:pointer; 
    letter-spacing: 4px; 
    margin-left:10px; 
    color:#FFF; 
    font-weight:bold; 
} 
fieldset input[type=submit]:hover { 
    background: #FF9800;
} 
.inputWrap { 
    background:#EEEEEE;
    border-radius:3px; 
    border:1px solid #CCC; 
    margin:10px 10px 0; 
} 
fieldset input[type=checkbox] { 
    margin-left:10px; 
    vertical-align:middle; 
}
fieldset a { 
    color:blue; 
    font-size:12px; 
    margin:6px 0 0 10px; 
    text-decoration:none; 
} 
fieldset a:hover { 
    text-decoration:underline; 
} 
fieldset span { 
    font-size:12px; 
} 
</style> 
<#if userContext??>
	<script type="text/javascript">
		window.location.href="logout.html"; 
	</script>
</#if>
<!--为了让IE支持HTML5元素，做如下操作：--> 
<!--[if IE]> 
<script type="text/javascript"> 
document.createElement("section"); 
document.createElement("header"); 
document.createElement("footer"); 
</script> 
<![endif]--> 
</head>
<body>
	<div class="wrap">
		<form name="login" action="login.html" method="post">
			<section class="loginForm"> <header>
			<h1>登录</h1>
			</header>
			<div class="loginForm_content">
				<fieldset>
					<div class="inputWrap">
						<input type="text" name="username" placeholder="请输入帐号" autofocus
							required>
					</div>
					<div class="inputWrap">
						<input type="password" name="password" placeholder="请输入密码"
							required>
					</div>
				</fieldset>
				<fieldset>
					<input type="submit" value="登录"> <a href="register.html">注册</a>
				</fieldset>
			</div>
			</section>
			<input type="hidden" name="userContext" value="not null" />
		</form>
	</div>
</body>
</html>