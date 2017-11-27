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
     String user = "";
     for(Cookie c:cs){
    	 if("user".equals(c.getName())){
    		 user = c.getValue();
    	 }
     }
 %> 
<base href="<%=basePath%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<%=basePath%>bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<title>添加管理员</title>

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
		<p>添加管理员</p>
	</div>
	<form action="adResult" method="post" class="navbar-form navbar-left">
		<div class="form-group" style="width: 550px"></div>
		<div class="form-group">
			<label class="col-sm-2 control-label" style="width: 90px"><span style="color:red">*</span>用户名</label>
		</div>
		<div class="form-group">
			<div class="col-sm-10">
				<input type="text" name="username" class="form-control"
					style="width: 200px">
			</div>
		</div>
		<br />
		<br />
		<div class="form-group" style="width: 550px"></div>
		<div class="form-group">
			<label class="col-sm-2 control-label" style="width: 90px">真实姓名</label>
		</div>
		<div class="form-group">
			<div class="col-sm-10">
				<input type="text" name="realname" class="form-control"
					style="width: 200px">
			</div>
		</div>
		<br />
		<br />
		<div class="form-group" style="width: 550px"></div>
		<div class="form-group">
			<label class="col-sm-2 control-label" style="width: 90px"><span style="color:red">*</span>密码</label>
		</div>
		<div class="form-group">
			<div class="col-sm-10">
				<input type="password" name="pass" class="form-control"
					style="width: 200px">
			</div>
		</div>
		<br />
		<br />
		<div class="form-group" style="width: 550px"></div>
		<div class="form-group">
			<label class="col-sm-2 control-label" style="width: 90px">添加人</label>
		</div>
		<div class="form-group">
			<div class="col-sm-10">
				<input type="text" name="fzr" class="form-control" readonly
					style="width: 200px" value="<%=user %>">
			</div>
		</div>
		<br />
		<br />
		<div class="form-group" style="width: 660px"></div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10" style="width: 100px">
				<input type="submit" class="btn btn-default" value="提交">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10" style="width: 100px">
				<input type="reset" class="btn btn-default" value="重置">
			</div>
		</div>
		<br /> <br />
		<div class="form-group" style="width: 550px"></div>
		<div class="form-group" style="width: 100px">
			<p style="vertical-align: middle">
				<a href="<%=basePath %>main?username=<%=user %>" style="cursor:pointer" class="back">返回</a>
			</p>
		</div>
	</form>
	
</body>
</html>