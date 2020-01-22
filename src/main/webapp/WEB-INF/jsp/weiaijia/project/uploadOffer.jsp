<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta charset="utf-8">
<title>上传硬装报价/软装</title>
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
<link rel="stylesheet" href="${ctx}/ueditor/themes/default/css/ueditor.css">
<style type="text/css">
#Images li{
	float:left;
	width:300px;
	height:300px;
	margin-bottom:20px;
}
#Images img{
	width:300px;
	height:300px;
}
.img_del{
    cursor: pointer;
}
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
	width:30%;
	min-height: 40px;
	border-left: 1px solid #666;
	border-right: 1px solid #666;
	text-align: left; 
	padding: 10px;
	color: #666;
	font-size: 20px;
	font-weight: 400;
	text-align: center;
}
.projectTable td .span{	
	cursor: pointer;
} 
</style>
</head>
<body>
	<div style="margin: 15px;">	
		
		<div class="layui-form">
			<div class="layui-form-item">
				<label class="layui-form-label">名称：</label>
				<div class="layui-input-block">
					<input type="text" name="offerName" value="${projectOfferDetails.offerName}"  autocomplete="off" class="layui-input" lay-verify="offerName">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">报价详情：</label>
				<div class="layui-input-block">
				<script id="content" type="text/plain" style="width:92%;height:500px;"></script>
				</div>
			</div>
			
			
			<div class="layui-form-item">
				<label class="layui-form-label"></label>
				<div class="layui-input-block">
				<input type="hidden" value="" name="id"/>
					<button class="layui-btn" lay-submit="" lay-filter="demo1">确认</button>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript" src="${ctx}/layui/layui.all.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/ueditor/ueditor.config.js"></script>	
<script type="text/javascript" src="${ctx}/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript">
var ue = UE.getEditor("content");
var content = '${projectOfferDetails.offerImage}';
ue.addListener("ready", function () {
    ue.setContent(content); //赋值 
});
 
layui.use([ 'form', 'layer'],
		function() {
	 	var form = layui.form, layer = layui.layer;
	 
		
		//监听提交
		form.on('submit(demo1)', function(data) {
			
			$.ajax({
				type : "post",
				url : "${ctx}/newProject/addProjectOffer.do",
				dataType:"json", 
				data : {
					"offerImage":ue.getContent(),
					"offerName":data.field.offerName,
					"projectId":"${project.id}",
					"offerType": "${offerType}"
				},
				success : function(result) {
					if(result.code==0){
						layer.msg("上传成功");
						setTimeout(function () {
		      				//刷新页面
		      				 window.location.reload();
						  }, 1000);
					} else{
						layer.msg(result.msg,{time:2000});
					}
				},error:function(){
					layer.msg("上传失败");
				}
			});
			return false;
		});
	});
	

</script>
</html>