<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
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
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>
<base href="<%=basePath%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<%=basePath%>bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<title>添加涉访人员（读卡）</title>
<!-- 判断是否添加成功 -->
<%
	if (request.getAttribute("result") != null) {
		String result = request.getAttribute("result").toString();
		if ("fail".equals(result)) {
%>
<script type="text/javascript" language="javascript">
	alert("姓名、户口所在地、问题类别、上访（被查控）地点均不能为空，添加失败！");
</script>
<%
	} else if ("nlerror".equals(result)) {
%>
<script type="text/javascript" language="javascript">
	alert("请输入正确的年龄！");
</script>
<%
	} else if ("error".equals(result)) {
%>
<script type="text/javascript" language="javascript">
	alert("出错！添加失败！");
</script>
<%
	}
	}
%>
<style type="text/css">
.page-header {
	text-align: center
}
</style>
<script type="text/javascript">
	setInterval("ReadIDCard()", 1000);

	function ReadIDCard() {
		var ret = CVR_IDCard.ReadCard();
		if (ret == "0") {
			var pName = CVR_IDCard.Name;
			var pSex = CVR_IDCard.Sex;
			var pBorn = CVR_IDCard.Born;
			var pAddress = CVR_IDCard.Address;

			var now = new Date().getFullYear();
			var born = pBorn.substr(0, 4);
			var age = parseInt(now) - parseInt(born);

			document.getElementById("xm").value = pName;
			document.getElementById("xb").value = pSex;
			document.getElementById("nl").value = age;
			document.getElementById("hkszd").value = pAddress;
		}
	}
	 function getSex()
 {

    var sex=document.getElementsByName("xb");
    for(var i=0;i<sex.length;i++)
    {
     if(sex[i].type=="radio"&&sex[i].checked)
 }
    }
</script>

<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.6287" name=GENERATOR>
</HEAD>
<BODY bgColor=#d2f0ff topMargin=0>
	<script type="text/javascript"
		src="<%=basePath%>bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>bootstrap/js/jquery-3.1.0.min.js"></script>

	<div class="page-header">
		<h1>涉访人员登记</h1>
		<p>添加涉访人员</p>
		<p style="color: red">若要进行读卡操作，放置二代身份证即可</p>
	</div>

	<OBJECT id=CVR_IDCard height=0 width=0
		classid=clsid:10946843-7507-44FE-ACE8-2B3483D179B7 name=CVR_IDCard></OBJECT>
	<form action="addSfryjg" method="post" class="navbar-form navbar-left"
		accept-charset="UTF-8">
		<div class="form-group" style="width: 555px"></div>
		<div class="form-group">
			<label class="col-sm-2 control-label" style="width: 85px"><span
				style="color: red">*</span>姓名</label>
		</div>
		<div class="form-group">
			<div class="col-sm-10">
				<input id="xm" type="text" name="xm" class="form-control"
					style="width: 200px" placeholder="请输入姓名" value="">
			</div>
		</div>
		<br /> <br />
		<div class="form-group" style="width: 560px"></div>
		<div class="form-group">
			<label class="col-sm-2 control-label" style="width: 80px">性别</label>
		</div>
		<div class="form-group">
			<div class="checkbox-inline">
			   <label><input id="xb" type="radio" name="xb" class="form-control"
					 value="男" checked>男</label>
			</div>
			<div class="checkbox-inline">
			   <label><input id="xb" type="radio" name="xb" class="form-control"
					 value="女">女</label>
			</div>
		</div>
		<br /> <br />
		<div class="form-group" style="width: 560px"></div>
		<div class="form-group">
			<label class="col-sm-2 control-label" style="width: 80px">年龄</label>
		</div>
		<div class="form-group">
			<div class="col-sm-10">
				<input id="nl" type="text" name="nl" class="form-control"
					style="width: 200px" placeholder="请输入年龄" value="">
			</div>
		</div>
		<br /> <br />
		<div class="form-group" style="width: 510px"></div>
		<div class="form-group">
			<label class="col-sm-2 control-label" style="width: 130px"><span
				style="color: red">*</span>户口所在地</label>
		</div>
		<div class="form-group">
			<div class="col-sm-10">
				<input id="hkszd" type="text" name="hkszd" class="form-control"
					style="width: 200px" placeholder="请输入户口所在地" value="">
			</div>
		</div>
		<br /> <br />
		<div class="form-group" style="width: 525px"></div>
		<div class="form-group">
			<label class="col-sm-2 control-label" style="width: 115px"><span
				style="color: red">*</span>问题类别</label>
		</div>
		<div class="form-group">
			<div class="col-sm-10">
				<input type="text" name="wtlb" class="form-control"
					style="width: 200px" placeholder="请输入问题类别">
			</div>
		</div>
		<br /> <br />
		<div class="form-group" style="width: 450px"></div>
		<div class="form-group">
			<label class="col-sm-2 control-label" style="width: 190px"><span
				style="color: red">*</span>上访（被查控）地点</label>
		</div>
		<div class="form-group">
			<div class="col-sm-10">
				<input type="text" name="sfdd" class="form-control"
					style="width: 200px" placeholder="请输入上访/被查控地点">
			</div>
		</div>
		<br /> <br />
		<div class="form-group" style="width: 560px"></div>
		<div class="form-group">
			<label class="col-sm-2 control-label" style="width: 80px">登记人</label>
		</div>
		<div class="form-group">
			<div class="col-sm-10">
				<input type="text" name="djr" class="form-control"
					style="width: 200px" value="<%=user%>" readonly>
			</div>
		</div>
		<br /> <br />
		<div class="form-group" style="width: 560px"></div>
		<div class="form-group">
			<label class="col-sm-2 control-label" style="width: 80px">登记时间</label>
		</div>
		<div class="form-group">
			<div class="col-sm-10">
				<input type="text" name="djsj" class="form-control"
					style="width: 200px" value="<%=sdf.format(new Date()) %>"
					 readonly>
			</div>
		</div>
		<br /> <br />
		<div class="form-group" style="width: 560px"></div>
		<div class="form-group">
			<label class="col-sm-2 control-label" style="width: 80px">备注</label>
		</div>
		<div class="form-group">
			<div class="col-sm-10">
				<input type="text" name="bz" class="form-control"
					style="width: 200px" placeholder="请输入备注">
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
		<div class="form-group" style="width: 550px"></div>
		<div class="form-group" style="width: 100px">
			<p style="vertical-align: middle">
				<a href="<%=basePath %>main?username=<%=user %>" style="cursor: pointer" class="back">返回</a>
			</p>
		</div>
	</form>
</BODY>
</HTML>