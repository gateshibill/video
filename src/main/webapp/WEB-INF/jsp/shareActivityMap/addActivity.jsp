<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
	<meta charset="utf-8">
	<title>添加活动</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all" />
</head>
<body class="childrenBody">
	<div style="padding-left:50px;;padding-top:30px;">
		<div class="layui-form-item">
			<div class="layui-inline">		
				<label class="layui-form-label">活动内容</label>
				<div class="layui-input-inline">
					<textarea  id="content" class="layui-input newsAuthor" style="width:300px;height:120px;"
					lay-verify="required" name="contentDesc" maxlength="100" placeholder="请输入活动内容"></textarea>
				</div>
			</div>		
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" id="send" >立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</div>	
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/layui/layui.all.js"></script>
<script type="text/javascript">
layui.use('layer',function(){
	layer = layui.layer;
})
    $("#send").on('click',function(){
      var contentDesc = $("#content").val();
      if(!contentDesc){
    	  layer.msg("活动内容不能为空");
    	  return;
      }
    	$.ajax({
    		 url:'${ctx}/shareActivity/doAddContent.do',
    		 type:'post',
    		 dataType:'json',
    		 data:{'contentDesc':contentDesc},
    		 success:function(data){
    			 if(data.code==0){
    				  layer.msg(data.msg);
    				  setTimeout(function(){
    					  layer.closeAll("iframe");
      					//刷新父页面
      				 		parent.location.reload();
    					 // window.location.href = '${ctx}/shareContract/goContentList.do';
    				  },1000);
    			 }else{
    				 layer.msg(data.msg);
    				 return;
    			 }
    		 }
    	});
    })
</script>
</body>
</html>