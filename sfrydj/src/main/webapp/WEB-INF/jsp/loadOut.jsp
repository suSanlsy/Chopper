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
alert("出错！导出失败！");
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
		<p>数据导出</p>
		<p>(PS:导出的文件将是07版本的Excel文件，第一列到第八列分别是：</p>
		<p>序号，姓名，性别，年龄，户口所在地，问题类别，上访/被查控地点，备注)</p>
	</div>

	<form action="loadResult" method="post" enctype= "multipart/form-data" class="navbar-form navbar-left">
		<div class="form-group" style="width: 610px"></div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10" style="width: 100px">
				<input type="submit" class="btn btn-default" value="导出">
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