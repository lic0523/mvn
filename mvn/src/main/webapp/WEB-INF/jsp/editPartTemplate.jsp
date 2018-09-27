<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>编辑</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="<%=basePath%>page/layui/css/layui.css" media="all"/>
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
  </head>
  
  <body>
    ·					<form class="layui-form" action="<%=basePath%>partTem/editPartTemplateInfo.do" method="post">
								  <div class="layui-form-item">
								  	<input type="hidden" name="partid" value="${pt.getPartid()}">
								  </div>
								  <div class="layui-form-item">
								    <label class="layui-form-label">模板内容</label>
								    <div class="layui-input-block">
								      <textarea rows="10px" warp="virtual" cols="150px"  name="content" placeholder="请输入模板内容">${pt.getContent()}</textarea>
								    </div>
								  </div>
								  <div class="layui-form-item">
								    <label class="layui-form-label">子模板内容</label>
								    <div class="layui-input-block">
								    <textarea rows="10px" cols="150px"  name="getSubcontent" placeholder="请输入子模板内容">${pt.getSubcontent()}</textarea>
								    </div>
								  </div>
								  <div class="layui-form-item">
								    <label class="layui-form-label">数据类型</label>
								    <div class="layui-input-block">
								      <input type="text" value="${pt.getDatatype() }" name="datatype" required  lay-verify="required" placeholder="请输新名称" autocomplete="off" class="layui-input">
								    </div>
								  </div>
								  <div class="layui-form-item">
								    <div class="layui-input-block">
								      <button class="layui-btn" lay-submit lay-filter="formDemo">立即修改</button>
								      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
								    </div>
								  </div>
								</form>
								
								<script type="text/javascript" src="<%=basePath%>page/layui/layui.js"></script>
  </body>
</html>
