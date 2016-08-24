<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
	<head>
		<meta charset="utf-8">
		<title>公众号服务</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		
		<!-- CSS -->
		<link rel='stylesheet' href='css/common/css.css'>
		<link rel="stylesheet" href="css/common/reset.css">
		<link rel="stylesheet" href="css/common/supersized.css">
		<link rel="stylesheet" href="css/login/style.css">
		
		<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
		    <script src="js/common/html5.js"></script>
		<![endif]-->
	
	</head>
	<body>
		<div class="page-container">
			<h1>Login</h1>
			<div class="alert alert-info">
                Please login with your Username and Password.
            </div>
			<form action="" method="post">
				<input type="text" name="loginName" class="username" placeholder="请输入用户名">
				<input type="password" name="password" class="password" placeholder="请输入密码">
				<input type="text" name="validatecode" class="validatecode" placeholder="请输入验证码">
				<img src="common/validateCode.do" title="看不清换一张" class="imgvalidatecode">
				<button type="button" class="btn_login">登 录</button>
				<div class="error">
					<span>+</span>
				</div>
			</form>
		</div>
		
		<div class="copyright">
			Copy Right @ <a href="javascript:void(0);" target="_blank" title="公众号服务">公众号服务</a>
		</div>
		
		<!-- Javascript -->
		<script src="js/common/jquery-1.8.2.min.js"></script>
		<script src="js/common/supersized.3.2.7.min.js"></script>
		<script src="js/login/supersized-init.js"></script>
		<script src="js/login/scripts.js"></script>
	
	</body>

</html>