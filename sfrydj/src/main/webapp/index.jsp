<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%String path = request.getContextPath(); %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet">
<title>登录</title>

<link rel="stylesheet"
	href="<%=path %>/css/bootstrap.min.css">
<style type="text/css">
</style>
</head>
<body>
	<script src="<%=path %>/js/bootstrap.min.js"></script>

	<form>
		<div class="form-group">
			<label for="exampleInputEmail1">用户名</label> <input
				type="email" class="form-control" id="exampleInputEmail1"
				placeholder="Email">
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">密码</label> <input
				type="password" class="form-control" id="exampleInputPassword1"
				placeholder="Password">
		</div>
		<button type="submit" class="btn btn-default">登录</button>
	</form>

</body>
</html>