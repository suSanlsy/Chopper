<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
     String path = request.getContextPath();
     String basePath = request.getScheme() + "://"
             + request.getServerName() + ":" + request.getServerPort()
             + path + "/";
     Cookie[] cs = request.getCookies();
     for(Cookie c:cs){
    	 c.setMaxAge(0);
     }
 %> 
<base href="<%=basePath%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<%=basePath%>bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<title>登录</title>

<!-- 判断添加管理员是否成功以及是否登录成功 -->
<%if (request.getAttribute("result") != null) {String result = request.getAttribute("result").toString();if ("success".equals(result)) {%>
<script type="text/javascript" language="javascript">
	alert("添加成功！");
</script>
<%} else if ("fail".equals(result)) {%>
<script type="text/javascript" language="javascript">
	alert("登录失败！");
</script>
<%}}%>

<style type="text/css">
.page-header {
	text-align: center
}
</style>
</head>
<body>
	<script type="text/javascript" src="<%=basePath%>bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>bootstrap/js/jquery-3.1.0.min.js"></script>

	<div class="page-header">
		<h1>涉访人员登记</h1>
		<p>管理员登录</p>
	</div>
	<form action="logResult" method="post" class="navbar-form navbar-left">
		<div class="form-group" style="width: 550px"></div>
		<div class="form-group">
			<label class="col-sm-2 control-label" style="width: 80px">用户名</label>
		</div>
		<div class="form-group">
			<div class="col-sm-10">
				<input type="text" name="username" class="form-control"
					style="width: 200px" placeholder="请输入用户名">
			</div>
		</div>
		<br /> <br />
		<div class="form-group" style="width: 550px"></div>
		<div class="form-group">
			<label class="col-sm-2 control-label" style="width: 80px">密码</label>
		</div>
		<div class="form-group">
			<div class="col-sm-10">
				<input type="password" name="password" class="form-control"
					style="width: 200px" placeholder="请输入密码">
			</div>
		</div>
		<br /> <br />
		<div class="form-group" style="width: 620px"></div>
		<div class="form-group">
			<button type="submit" class="btn btn-default">登录</button>
			<button type="reset" class="btn btn-default">重置</button>
		</div>
	</form>

</body>
</html>