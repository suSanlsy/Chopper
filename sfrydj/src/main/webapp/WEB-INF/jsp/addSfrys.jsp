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
 %> 
<base href="<%=basePath%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<%=basePath%>bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<title>添加涉访人员</title>
<%if(request.getAttribute("result")!=null){String result = request.getAttribute("result").toString();if("fail".equals(result)){%>
<script type="text/javascript" language="javascript">
alert("出错！添加失败！");
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
		<p>批量添加涉访人员</p>
		<p>(PS:文件必须为07版本的Excel文件，从第二行开始，第一列到第七列分别是：</p>
		<p>姓名，性别，年龄，户口所在地，问题类别，上访/被查控地点，备注)</p>
		<p style="color:red">注意：每一列都必须填写</p>
	</div>

	<form action="addSfryResult" method="post" enctype= "multipart/form-data" class="navbar-form navbar-left">
		<div class="form-group" style="width: 520px"></div>
		<div class="form-group">
			<label class="col-sm-2 control-label" style="width: 60px">文件</label>
		</div>
		<div class="form-group">
			<div class="col-sm-10">
				<input type="file" name="file" class="form-control"
					style="width: 200px" placeholder="浏览">
			</div>
		</div>
		<br /> <br />
		<div class="form-group" style="width: 550px"></div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10" style="width: 100px">
				<input type="submit" class="btn btn-default" value="保存">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10" style="width: 100px">
				<input type="reset" class="btn btn-default" value="重置">
			</div>
		</div>
		<br /> <br />
		<div class="form-group" style="width: 535px"></div>
		<div class="form-group" style="width: 100px">
			<p style="vertical-align: middle">
			<%Cookie[] cs = request.getCookies();
			  String user = "";
			  for(Cookie c:cs){
			  	if("user".equals(c.getName())){
			  		user = c.getValue();
			  	}
			  }%>
				<a href="<%=basePath %>main?username=<%=user %>" style="cursor:pointer" class="back">返回</a>
			</p>
		</div>
	</form>

</body>
</html>