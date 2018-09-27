<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>渠道商模块维护</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>page/layui/css/layui.css" media="all"/>
		<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
</head>
<body>
							<div class="layui-tab" lay-filter="demo">
								&nbsp;<button class="layui-btn layui-btn-radius layui-btn-warm" onclick="initialization()">初始化</button>
								<table id="demo" lay-filter="test"></table>
							</div>



<script type="text/javascript" src="<%=basePath%>page/layui/layui.js"></script>

<script type="text/javascript">


layui.use(['element','table'], function(){
  var table = layui.table;
  var element = layui.element;
  var $ = layui.$ ; 
  
  //第一个实例
  table.render({
    elem: '#demo'
    ,height: 550
    ,url: '<%=basePath%>partTem/getPartnersTemplateList.do' //数据接口
    ,page: true //开启分页
    ,cols: [[ //表头
      {field: 'templateid', title: 'ID', sort: true, fixed: 'left'}
      ,{field: 'partid', title: '渠道商id',  sort: true}
      ,{field: 'isvalidate', title: '是否可用',  sort: true,templet: '#isValidateRender'}
      ,{field: 'content', title: '模板内容',  sort: true} 
      ,{field: 'subcontent', title: '子模板内容',  sort: true}
      ,{field: 'datatype', title: '数据类型',  sort: true}
      ,{field:'partid',title: '操作',  toolbar: '#barDemo'}
    ]]
  });
  
  
  	table.on('tool(test)', function(obj){ 
	  	var data = obj.data; 
	  	var layEvent = obj.event; 
	  	var tr = obj.tr; 
	  	if(layEvent === 'edit'){ 
	  		var id = data.partid;
	    	window.location.href = "<%=basePath%>partTem/editPartTemplate.do?partId="+id;
	  } 
	}); 
	
	
});

	function initialization(){
		window.location.href = "<%=basePath%>partTem/initialization.do"
	}
</script>

<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
</script>

<script type="text/html" id="isValidateRender">
  {{#  if(d.isvalidate == 1){ }}
    	<input type="checkbox"  name="isValidateBox" lay-skin="switch" lay-text="启用|禁用 " id="{{d.partId}}" onclick=showContent('{{d.partId}}') checked>
  {{#  } else { }}
   		<input type="checkbox"  name="isValidateBox" lay-skin="switch" lay-text="启用|禁用 " id="{{d.partId}}" >
  {{#  } }}
</script>	
</body>
</html>