<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html>
<head>
	<base href="<%=basePath%>">
	<title>登录页</title>
	<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
	<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(function(){
			
			if(window.top!=window){
				window.top.location=window.location;
			}

			var loginAct=$("#loginAct");
			var loginPwd=$("#loginPwd");
			//清空用户名和密码,并让用户名处获得焦点
			loginAct.val("");
			loginPwd.val("");
			loginAct.focus();
			//点击按钮执行登录操作
			$("button").click(function(){
				login();
			});
			//按键盘回车进行登录操作
			$(window).keydown(function(event){
				if(event.keyCode==13){
					login();
				}
			});
		});
		function login(){
			//把账号和密码的前后空格去掉
			var Act=$.trim($("#loginAct").val());
			var Pwd=$.trim($("#loginPwd").val());
			if(Act=="" || Pwd==""){
				$("#message").text("账号和密码都不能为空，请重试");
				return false;
			}
			$.post("settings/user/login",{"loginAct":Act,"loginPwd":Pwd},rollback,"json");
			function rollback(param){
				//param{"success":true/false,"message":"错误信息"}
				if(param.success){
					window.location.href="workbench/index.jsp";
				} else{
					$("#message").text(param.message);
				}
			}
		}
	</script>
</head>
<body>
	<div style="position: absolute; top: 0px; left: 0px; width: 60%;height: 90%">
		<img src="image/IMG_7114.JPG" style="width: 100%; position: relative; top: 50px;">
	</div>
	<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
		<div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM &nbsp;<span style="font-size: 12px;">&copy;2021&nbsp;华中科技大学</span></div>
	</div>
	
	<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
		<div style="position: absolute; top: 0px; right: 60px;">
			<div class="page-header">
				<h1>登录</h1>
			</div>
			<form action="workbench/index.jsp" class="form-horizontal" role="form">
				<div class="form-group form-group-lg">
					<div style="width: 350px;">
						<input class="form-control" type="text" placeholder="用户名" id="loginAct">
					</div>
					<div style="width: 350px; position: relative;top: 20px;">
						<input class="form-control" type="password" placeholder="密码" id="loginPwd">
					</div>
					<div class="checkbox"  style="position: relative;top: 30px; left: 10px;">
						
							<span id="message" style="color:red"></span>
						
					</div>
					<button type="button" class="btn btn-primary btn-lg btn-block"  style="width: 350px; position: relative;top: 45px;">登录</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>