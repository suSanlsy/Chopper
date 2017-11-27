<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.List"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
     String basePath = request.getScheme() + "://"
             + request.getServerName() + ":" + request.getServerPort()
             + path + "/";
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     Cookie[] cs = request.getCookies();
     String user = "";
     for(Cookie c : cs){
    	 if("user".equals(c.getName())){
    		 user = c.getValue();
    	 }
     }
%>
<base href="<%=basePath%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<%=basePath%>bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="<%=basePath%>bootstrap/css/bootstrap-datetimepicker.css"
	rel="stylesheet" type="text/css">
<title>涉访人员</title>
	
	
<!-- 判断添加管理员是否成功以及是否登录成功 -->
<%
	if (request.getAttribute("result") != null) {String result = request.getAttribute("result").toString();if("addsuccess".equals(result)){
%>
<script type="text/javascript" language="javascript">
	alert("添加成功！");
</script>
<%
	}else if("delsuccess".equals(result)){
%>
<script type="text/javascript" language="javascript">
	alert("删除成功！");
</script>
<%
	}else if("delfail".equals(result)){
%>
<script type="text/javascript" language="javascript">
	alert("删除失败！");
</script>
<%
	}else if("loadsuccess".equals(result)){
%>
<script type="text/javascript" language="javascript">
	alert("导出成功！");
</script>
<%
	}else if("error".equals(result)){
%>
<script type="text/javascript" language="javascript">
	alert("出错！");
</script>
<%
	}
	request.setAttribute("result", "");
	}
%>

<style type="text/css">
.page-header {
	text-align: center
}
</style>
</head>
<body>
	<%
		String pages = request.getAttribute("pages").toString();
		String recent = request.getAttribute("recent").toString();
	%>
	<div class="page-header">
		<h1>涉访人员登记表</h1>
		<p>当前管理员：<%=user %></p>
	</div>
	<div class="dropdown" style="float: right">
		<nav>
		<ul class="pager">
			<li><a href="addadmin">添加管理员</a></li>
			<li><a href="logout"> 退出登录 </a></li>
		</ul>
		</nav>
	</div>
	<div>
		<br /> <br /> <br />
	</div>
	<div class="alert alert-info">
		&nbsp;&nbsp;<a href="addSfry" class="glyphicon glyphicon-plus-sign">添加涉访人员</a>
		&nbsp;&nbsp;<a href="loadOut" class="glyphicon glyphicon-download-alt">文件导出</a>
	</div>
	<form action="search" class="navbar-form navbar-left" role="search"
		method="post">
		<div class="form-group">
			<input type="text" name="xm" class="form-control" placeholder="搜索"
				value="<%=URLDecoder.decode(URLEncoder.encode("请输入姓名", "UTF-8"),
					"UTF-8")%>"
				onFocus="if(value==defaultValue){value='';this.style.color='#000'}"
				onBlur="if(!value){value=defaultValue;this.style.color='#999999'}"
				style="color: #999999"> <input type="text" name="hkszd"
				class="form-control" placeholder="搜索"
				value="<%=URLDecoder.decode(URLEncoder.encode("请输入户口所在地", "UTF-8"),
					"UTF-8")%>"
				onFocus="if(value==defaultValue){value='';this.style.color='#000'}"
				onBlur="if(!value){value=defaultValue;this.style.color='#999999'}"
				style="color: #999999"> <input type="text" name="wtlb"
				class="form-control" placeholder="搜索"
				value="<%=URLDecoder.decode(URLEncoder.encode("请输入问题类别", "UTF-8"),
					"UTF-8")%>"
				onFocus="if(value==defaultValue){value='';this.style.color='#000'}"
				onBlur="if(!value){value=defaultValue;this.style.color='#999999'}"
				style="color: #999999"> <input type="text" name="sfdd"
				class="form-control" placeholder="搜索"
				value="<%=URLDecoder.decode(
					URLEncoder.encode("请输入上访（被查控）地点", "UTF-8"), "UTF-8")%>"
				onFocus="if(value==defaultValue){value='';this.style.color='#000'}"
				onBlur="if(!value){value=defaultValue;this.style.color='#999999'}"
				style="color: #999999">
			<input id="datetimepick1" class="form-control" type="text"
				name="djks" readonly 
				value="<%=URLDecoder.decode(
							URLEncoder.encode("请输入登记开始时间", "UTF-8"), "UTF-8")%>"
				placeholder="<%=URLDecoder.decode(
							URLEncoder.encode("格式：年-月-日", "UTF-8"), "UTF-8")%>"
				onFocus="if(value==defaultValue){value='';this.style.color='#000'}"
				onBlur="if(!value){value=defaultValue;this.style.color='#999999'}"
				style="color: #999999"> <input id="datetimepick2"
				class="form-control" type="text" name="djjs" readonly
				value="<%=URLDecoder.decode(
							URLEncoder.encode("请输入登记结束时间", "UTF-8"), "UTF-8")%>"
				placeholder="<%=URLDecoder.decode(
							URLEncoder.encode("格式：年-月-日", "UTF-8"), "UTF-8")%>"
				onFocus="if(value==defaultValue){value='';this.style.color='#000'}"
				onBlur="if(!value){value=defaultValue;this.style.color='#999999'}"
				style="color: #999999">
		</div>
		<button type="submit" class="btn btn-default">查询</button>
	</form>
	<table class="table">
		<thead>
			<tr>
				<th style="text-align: center">序号</th>
				<th style="text-align: center">姓名</th>
				<th style="text-align: center">性别</th>
				<th style="text-align: center">年龄</th>
				<th style="text-align: center">户口所在地</th>
				<th style="text-align: center">问题类别</th>
				<th style="text-align: center">上访（被查控）地点</th>
				<th style="text-align: center">备注</th>
				<th style="text-align: center">登记人</th>
				<th style="text-align: center">登记时间</th>
				<th style="text-align: center">操作</th>
			</tr>
		</thead>
		<tbody>
			<%
				if (request.getAttribute("sfry") != null
							&& !"".equals(request.getAttribute("sfry"))) {
						Object obj = request.getAttribute("sfry");
						JSONArray json = JSONArray.fromObject(obj);
						for (int i = 0; i < json.size(); i++) {
							JSONObject sfry = json.getJSONObject(i);
			%>
			<tr>
				<td style="text-align: center"><%=sfry.get("xh")%></td>
				<td style="text-align: center"><%=sfry.get("xm")%></td>
				<td style="text-align: center"><%=sfry.get("xb")%></td>
				<td style="text-align: center"><%=sfry.get("nl")%></td>
				<td style="text-align: center"><%=sfry.get("hkszd")%></td>
				<td style="text-align: center"><%=sfry.get("wtlb")%></td>
				<td style="text-align: center"><%=sfry.get("sfdd")%></td>
				<td style="text-align: center"><%=sfry.get("bz")%></td>
				<td style="text-align: center"><%=sfry.get("djr")%></td>
				<td style="text-align: center"><%=sfry.get("djsj")%></td>
				<td style="text-align: center"><a
					href="delete?xh=<%=sfry.get("xh")%>&&recent=<%=recent%>"
					class="glyphicon glyphicon-trash">删除</a></td>
			</tr>
			<%
				}
					}
			%>
		</tbody>
		<nav style="float:right">
		<ul class="pagination">
			<li><a href="previous?recent=<%=recent%>" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span>
			</a></li>
			<%
				for (int index = 0; index < Integer.parseInt(pages); index++) {
			%>
			<li><a href="turnTo?index=<%=index%>"><%=index + 1%></a></li>
			<%
				}
			%>
			<li><a href="next?recent=<%=recent%>" aria-label="Next"> <span
					aria-hidden="true">&raquo;</span></a></li>
			<li><span aria-hidden="true">当前第<%=Integer.parseInt(recent) + 1%>页
			</span></li>
		</ul>
		</nav>
	</table>

	<script type="text/javascript"
		src="<%=basePath%>bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>bootstrap/js/jquery-3.1.0.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>bootstrap/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>bootstrap/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>bootstrap/js/locales/bootstrap-datetimepicker.min.js">
	</script>
	<script type="text/javascript"
		src="<%=basePath%>bootstrap/js/locales/bootstrap-datetimepicker.pt-BR.js">
	</script>
	<script type="text/javascript">
		$('#datetimepick1').datetimepicker({
			format:'yyyy-mm-dd',
			weekStart: 1,
			language: "zh-CN",
			minView: "month",
			autoclose:true,
			minView:'month'
		});
		$('#datetimepick2').datetimepicker({
			format:'yyyy-mm-dd',
			weekStart: 1,
			language: "zh-CN",
			minView: "month",
			autoclose:true,
			minView:'month'
		});
	</script>


</body>
</html>