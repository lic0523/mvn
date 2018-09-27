<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>渠道导流后台管理系统</title>
  <link rel="stylesheet" href="<%=basePath%>page/layui/css/layui.css">
  <script src="<%=basePath%>page/layui/layui.js"></script>
</head>

<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">渠道导流后台管理系统</div>

    <ul class="layui-nav layui-layout-right">
  <li class="layui-nav-item">
    <a href=""><img src="<%=basePath%>/img/${img}" class="layui-nav-img">${username }</a>
    <dl class="layui-nav-child">
      <dd><a href='javascript:newTab("<%=basePath%>user/updateUser.do?id=${id }")' >安全管理</a></dd>
      <dd><a href="<%=basePath%>user/exit.do">退了</a></dd>
    </dl>
  </li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">基础信息维护</a>
          <dl class="layui-nav-child">
            <dd><a href='javascript:newTab("<%=basePath%>part/channelPartnersInfo.do")' >渠道商维护</a></dd>
            <dd><a href='javascript:newTab("<%=basePath%>partTem/partTemplate.do")'>渠道商模板维护</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">接口数据查询</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">接口访问记录查询</a></dd>
            <dd><a href='javascript:newTab("<%=basePath%>asset/getAssetVClipsList.do?clipId=1")'>接口历史数据查询</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">用户管理</a>
          <dl class="layui-nav-child">
            <dd><a href='javascript:newTab("<%=basePath%>user/AllUser.do")' >用户列表</a></dd>
          </dl>
        </li>        
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    <iframe src="" frameborder="0" id="myFrameId" name="myFrameName" style="width: 100%; height: 600px;"></iframe>   
  </div>
  
  <div class="layui-footer">
   
  </div>
</div>
<script>
	layui.use('element', function(){
	  var element = layui.element;
	  
	});
	
	function newTab(url){
	    document.getElementById("myFrameId").src = url;
	}	
</script>
</body>
</html>