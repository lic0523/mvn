<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta charset="utf-8">
  <title>用户管理</title>
  <link rel="stylesheet" href="<%=basePath%>page/layui/css/layui.css" media="all">
</head>
					<body>
 
						<table id="demo" lay-filter="test">
							<div class="layui-form-item">
							    <label class="layui-form-label">
							    <i class="layui-icon layui-icon-search" style="font-size: 30px; color: #1E9FFF;"></i>
							    </label>
							    <div class="layui-input-block" float:left>
							      <input type="text" id="selectUserName" name="selectUserName" required  lay-verify="required" placeholder="请输入查找用户的名称" autocomplete="off" class="layui-input"
							    </div>
							  </div>
									</table>
 
<script src="<%=basePath%>page/layui/layui.js"></script>
<script>
layui.use(['layer','table'], function(){
  var table = layui.table;
  var layer = layui.layer;
  var $ = layui.$ ;
  var index ;
  
  //第一个实例
  table.render({
    elem: '#demo'
    ,height: 500
    ,url: '<%=basePath%>user/getAllUser.do' //数据接口
    ,page: true //开启分页
    ,cols: [[ //表头
      {field: 'id', title: 'ID', sort: true, fixed: 'left'}
      ,{field: 'userName', title: '用户名', sort: true}
      ,{field: 'password', title: '密码', sort: true}
      ,{field: 'age', title: '年龄', sort: true}
      ,{field:'partId',title: '操作',  toolbar: '#barDemo'} 
    ]]
  });
  
  
  
  	$(".layui-icon-search").click(function(){
		var selectUserName = $("#selectUserName").val();
		if(selectUserName == ''){
			table.reload('demo',{
				where:{
					selectUserName:null
				}
		});
		}else{
		table.reload('demo',{
			where:{
				selectUserName:selectUserName
			}
		});		
		}
	})
	
	
	
	
	table.on('tool(test)', function(obj){ 
	  	var data = obj.data; 
	  	var layEvent = obj.event; 
	  	var tr = obj.tr; 
	  	if(layEvent === 'del'){ 
	    	index = layer.confirm('真的删除用户么', function(){
	      	var id = data.id;
	      	$.ajax({
				url:"<%=basePath%>user/deleteUserById.do",
				type:"post",
				data:{"userId":id},
				success:function(data){
					if(data == "userInfo"){
						layer.close(index);
						window.location.reload();
					}
					else{
						window.location.reload();
					}
				}	
			})	
	    });
	  }
	  else if(layEvent === 'edit'){
	  	var id = data.id;
	  	window.location.href = "<%=basePath%>user/updateUser.do?id="+id;
	  }
	  
	}); 
	
	
	
	
});
</script>
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
	<a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
</script>
</body>
</html>