<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta charset="utf-8">
<title>添加小类施工计划</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">

<link rel="stylesheet" href="${ctx}/layui/css/layui.css?v=<%=new java.util.Random().nextInt()%>"
	media="all" />
<link rel="stylesheet"
	href="${ctx}/plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${ctx}/css/font-awesome.min.css" />
<style type="text/css">
.flex-box::before, .flex-box::after {
    display: block;
    clear: both;
    height: 0;
    content: "";
}
.projectTable{
	margin: 0 auto;
	width: 800px;
	border-top: 1px solid #666;
	margin-bottom:50px;
}
.projectTable tr{	
	border-bottom: 1px solid #666;
}
.projectTable th{
	width:20%;
	min-height: 40px;
	border-left: 1px solid #666;
	text-align: center; 
	padding: 10px;
	color: #666;
	font-size: 20px;
	font-weight: 400;
}
.projectTable td{
	text-align: center;
	width:60%;
	min-height: 40px;
	border-left: 1px solid #666;
	border-right: 1px solid #666;
	text-align: left; 
	padding: 10px;
	color: #666;
	font-size: 20px;
	font-weight: 400;
}
.layui-form-select .layui-input{
	width:400px;
}
</style>
</head>
<body>
	<div style="margin: 15px;">
		
		<div class="layui-form">
			<div class="layui-form-item">
				<label class="layui-form-label">名称：</label>
				<div class="layui-input-block" style="width:400px;margin-left:130px">
					<input type="text" name="planName"  autocomplete="off" class="layui-input" lay-verify="planName">
				</div>
			</div>
			<div class="layui-form-item">
			      <label class="layui-form-label">计划开始时间：</label>
			      <div class="layui-input-block" style="width:400px;margin-left:130px">
			        <input name="planStartTime" type="text" class="layui-input" id="test1" placeholder="请选择计划开始时间" lay-verify="planStartTime">
			      </div>
			</div>
			<div class="layui-form-item">
			      <label class="layui-form-label">计划结束时间：</label>
			      <div class="layui-input-block" style="width:400px;margin-left:130px">
			        <input name="planEndTime" type="text" class="layui-input" id="test2" placeholder="请选择计划结束时间" lay-verify="planStartTime">
			      </div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">是否完成：</label>
				<div class="layui-input-block" style="width:400px;margin-left:130px">
					<select name="isFinish" lay-verify="isFinish" style="width:400px">
						<option value="0">未进行</option>
						<option value="1">施工完成</option>
						<option value="2">正在进行</option>
						<option value="3">未完成</option>
					</select>
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label"></label>
				<div class="layui-input-block">
				<input type="hidden" value="" name="id"/>
					<button class="layui-btn" lay-submit="" lay-filter="demo1">确认</button>
					<button onclick="goBack();" class="layui-btn layui-btn-primary">返回</button>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript" src="${ctx}/layui/layui.all.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript">
layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  
	  //执行一个laydate实例
	  laydate.render({
	    elem: '#test1' //指定元素
	    ,type: 'datetime'
	  });
	  
	  laydate.render({
	    elem: '#test2' //指定元素
	    ,type: 'datetime'
	  });
	});
layui.use([ 'form', 'layer'],
		function() {
	 	var form = layui.form, layer = layui.layer;
	 
		//自定义验证规则
		form.verify({	
			planName : function(value) {
				if (value.length == 0) {
					return "请输入名称";
				}
			},
			planStartTime: function(value) {
				if (value.length == 0) {
					return "请选择计划开始时间";
				}
			},
			planEndTime: function(value) {
				if (value.length == 0) {
					return "请选择计划结束时间";
				}
			},
			
		});
		//监听提交
		form.on('submit(demo1)', function(data) {
			console.log(data.field.planEndTime);
			$.ajax({
				type : "post",
				url : "${ctx}/newProject/addWorkPlan.do",
				dataType:"json", 
				data : {
					"planEndTimeS":data.field.planEndTime,
					"planStartTimeS":data.field.planStartTime,
					"planTypeId":"${projectWorkPlan.planTypeId}",
					"PlanName":data.field.planName,
					"isFinish":data.field.isFinish,
					"projectId":"${projectWorkPlan.projectId}"
				},
				success : function(result) {
					if(result.code==0){
						layer.msg("添加成功");
						setTimeout(function () {
		      				//刷新页面
		      				 parent.location.reload();
						  }, 1000);
					} else{
						layer.msg(result.msg,{time:2000});
					}
				},error:function(){
					layer.msg("添加失败");
				}
			});
			return false;
		});
	});
function goBack(){
	setTimeout(function () {
		 layer.closeAll("iframe");
		 //刷新父页面
		 parent.location.reload();
	  }, 100);
}
</script>
</html>