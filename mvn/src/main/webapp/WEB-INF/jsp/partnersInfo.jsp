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
<title>渠道商管理</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>page/layui/css/layui.css" media="all"/>
		<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
</head>
<body>
							<div class="layui-tab" lay-filter="demo">
							  <ul class="layui-tab-title">
							    <li class="layui-this">渠道商管理</li>
							    <li>新增渠道商</li>
							  </ul>
							  <div class="layui-tab-content">
							    <div class="layui-tab-item layui-show">
							    <table id="demo" lay-filter="test">
							    	  <div class="layui-form-item">
										    <label class="layui-form-label">
										    <i class="layui-icon layui-icon-search" style="font-size: 30px; color: #1E9FFF;"></i>
										    </label>
										    <div class="layui-input-block" float:left>
										      <input type="text" id="selectpartName" name="selectpartName" required  lay-verify="required" placeholder="请输入查找渠道商的名称" autocomplete="off" class="layui-input"
										    </div>
										  </div>
									    </table>
									 </div>
							    <div class="layui-tab-item">
							   	<!-- 新增  -->
							    <form class="layui-form" action="<%=basePath%>part/saveChannelPartners.do" method="post">
								  <div class="layui-form-item">
								    <label class="layui-form-label">渠道商名称</label>
								    <div class="layui-input-block">
								      <input type="text" name="partName" required  lay-verify="required" placeholder="请输入名称" autocomplete="off" class="layui-input">
								    </div>
								  </div>
								  <div class="layui-form-item">
								    <label class="layui-form-label">是否可用</label>
								    <div class="layui-input-block">
								      <input type="checkbox" name="isValidate" lay-skin="switch" value="1">
								    </div>
								  </div>
								  <div class="layui-form-item layui-form-text">
								    <label class="layui-form-label">备注</label>
								    <div class="layui-input-block">
								      <textarea name="desc" placeholder="请输入内容" class="layui-textarea"></textarea>
								    </div>
								  </div>
								  <div class="layui-form-item">
								    <div class="layui-input-block">
								      <button class="layui-btn" lay-submit lay-filter="formDemo">立即新增</button>
								      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
								    </div>
								  </div>
								</form>
							</div>



<script type="text/javascript" src="<%=basePath%>page/layui/layui.js"></script>

<script type="text/javascript">


layui.use(['element','table'], function(){
  var table = layui.table;
  var element = layui.element;
  var $ = layui.$ ;
  
  //一些事件监听
  element.on('tab(demo)', function(data){
    console.log(data);
  });  
  
  //第一个实例
  table.render({
    elem: '#demo'
    ,height: 400
    ,url: '<%=basePath%>part/getChannelPartnersList.do' //数据接口
    ,page: true //开启分页
    ,cols: [[ //表头
      {field: 'partId', title: 'ID', sort: true, fixed: 'left'}
      ,{field: 'partName', title: '用户名',  sort: true}
      ,{field: 'isValidate', title: '是否可用',  sort: true,templet: '#isValidateRender'}
      ,{field: 'addDate', title: '注册时间',  sort: true} 
      ,{field: 'updateDate', title: '修改时间',  sort: true}
      ,{field:'partId',title: '操作',  toolbar: '#barDemo'}
    ]]
  });
  
	table.on('tool(test)', function(obj){ 
	  	var data = obj.data; 
	  	var layEvent = obj.event; 
	  	var tr = obj.tr; 
	  	if(layEvent === 'del'){ 
	    	layer.confirm('真的删除行么', function(index){
	      	var id = data.partId;
	      	$.ajax({
				url:"<%=basePath%>part/deleteChannelPartners.do",
				type:"post",
				data:{"partId":id},
				dataType:"json",
				success:function(data){
					if(data == "partnersInfo"){
						layer.close(index);
						window.location.reload();
					}
					else{
						window.location.href = "<%=basePath%>part/deleteChannelPartners.do?partId=" + id;
					}
				}	
			})
	    });
	  } else if(layEvent === 'edit'){
	  	var id = data.partId;
	   	window.location.href = "<%=basePath%>part/edit.do?partId=" + id
	  } else if(layEvent === 'checkboxa'){
	  	alert(1)
	  }else if(layEvent === 'select'){
	  
	  }
	}); 
	

	$(".layui-icon-search").click(function(){
		var selectpartName = $("#selectpartName").val();
		if(selectpartName == ''){
			table.reload('demo',{
				where:{
					selectpartName:null
				}
		});
		}else{
		table.reload('demo',{
			where:{
				selectpartName:selectpartName
			}
		});		
		}
	})
  
});

</script>

<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
	<a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
</script>

<script type="text/html" id="isValidateRender">
  {{#  if(d.isValidate == 1){ }}
    	<input type="checkbox"  name="isValidateBox" lay-skin="switch" lay-text="启用|禁用 " id="{{d.partId}}" onclick=showContent('{{d.partId}}') checked>
  {{#  } else { }}
   		<input type="checkbox"  name="isValidateBox" lay-skin="switch" lay-text="启用|禁用 " id="{{d.partId}}" >
  {{#  } }}
</script>	
</body>
</html>