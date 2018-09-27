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
				<h3>后台系统登录</h3>
				<div class="m-login-warp">
					<form class="layui-form" name="loginform" action="<%=basePath%>user/checklogin.do" method="post">
						<div class="layui-form-item">
							<input type="text" name="username" required lay-verify="required|username" placeholder="用户名" autocomplete="off" class="layui-input">
						</div>
						<div class="layui-form-item">
							<input type="password" name="password" required lay-verify="required|password" placeholder="密码" autocomplete="off" class="layui-input">
						</div>
						<div class="layui-form-item">
							<div class="layui-inline">
								<input type="text" name="verity" id="verity" style="width:120px;;float:left" required lay-verify="required|verity" placeholder="验证码" autocomplete="off" class="layui-input">
								<div id="yzm" style="width:200px;height:40px;float:right" >	
								</div>
							</div>							
						</div>
						<div class="layui-form-item m-login-btn">
							<div class="layui-inline">
								<button class="layui-btn layui-btn-normal"  style="display: none;" lay-submit id="loginbutton">登录</button>
								<button class="layui-btn " onclick="checkyzm()" type="button">登录</button>
							</div>
							<div class="layui-inline">
								<button type="reset" class="layui-btn layui-btn-primary" onclick="regist()">注册新用户</button>
							</div>
						</div>
					</form>
				</div>
				<p class="copyright">Copyright 2015-2016 by XIAODU</p>
			</div>
		</div>
		  
		<script src="<%=basePath%>/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>/js/gVerify.js" type="text/javascript" charset="utf-8"></script>
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
					verity: [/(.+){4}$/, '验证码必须是4位'],
					
				});
				
				form.on('submit("loginButton")', function(data){alert()
				    layer.msg(JSON.stringify(data.field));
				    return false;
				  });
			  					
			});
		</script>
		<script type="text/javascript">
			function regist(){
				window.location.href = "<%=basePath%>user/regist.do"
			}
			
			
					
			var verifyCode = new GVerify("yzm");
			verifyCode.refresh();
			function checkyzm(){
				var value = document.getElementById("verity").value;
				if(!verifyCode.validate(value)){
					layer.alert("验证码输入错误");
				}else{
					document.getElementById("loginbutton").click();
				}
			}
		</script>
  </body>
</html>
