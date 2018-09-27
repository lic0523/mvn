<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>后台登录</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/admin/layui/css/layui.css" />
				<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/admin/css/login.css" />
  </head>

  <body>
    <div class="m-login-bg">
			<div class="m-login">
				<h3>后台系统账号修改</h3>
				<div class="m-login-warp">
					<form class="layui-form" name="loginform" action="<%=basePath%>user/updateUserinfo.do" method="post">
						<div class="layui-form-item">
							<input type="hidden" name="id" value="${id }">
							<input type="text" name="username" required lay-verify="required|username" placeholder="用户名" autocomplete="off" class="layui-input">
						</div>
						<div class="layui-form-item">
							<input type="password" name="password" required lay-verify="required|password" placeholder="密码" autocomplete="off" class="layui-input">
						</div>
						<div class="layui-form-item">
							<div class="layui-inline">
								<select name="states" lay-filter="status" id="states"></select>
							</div>
						</div>
						<div class="layui-form-item m-login-btn">
							<div class="layui-inline">
								<button class="layui-btn layui-btn-normal" lay-submit lay-filter="login" lay-filter="loginButton">修改</button>
							</div>
							<div class="layui-inline">
								<button type="reset" class="layui-btn layui-btn-primary" onclick="login()">返回登录</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		  
		<script src="<%=basePath%>/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
		<script>
			layui.use(['form', 'layedit', 'laydate'], function() {
				var form = layui.form(),
					layer = layui.layer;


				//自定义验证规则
				form.verify({
					username: function(value) {
						if(value.length < 5) {
							return '用户名至少得5个字符啊';
						}
					},
					password: [/(.+){6,12}$/, '密码必须6到12位'],
					verity: [/(.+){6}$/, '验证码必须是6位'],
					
				});
				
				form.on('submit("loginButton")', function(data){alert()
				    layer.msg(JSON.stringify(data.field));
				    return false;
				  });
			  					
			});
		</script>
		<script type="text/javascript">
		var sele = document.getElementById("states");
			var defult = document.createElement("option");
			var defulttext = document.createTextNode("请选择一个年龄");
			defult.appendChild(defulttext);
			defult.value = 0;
			sele.appendChild(defult);
			for(var a=1;a<100 ;a++){
				var option  =  document.createElement("option");
				var text  =  document.createTextNode(a);
				option.appendChild(text);
				option.value = a ;
				sele.appendChild(option); 
			}
			
			function login(){
				window.location.href = "<%=basePath%>user/login.do"
			}
		</script>
  </body>
</html>