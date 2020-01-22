<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta charset="utf-8">
<title>添加管理表单</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">

<link rel="stylesheet" href="${ctx}/plugins/layui/css/layui.css?v=<%=new java.util.Random().nextInt()%>"
	media="all" />
<link rel="stylesheet"
	href="${ctx}/plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${ctx}/css/font-awesome.min.css" />
<style type="text/css">
.icon-plus {
	font-size: 23px;
	position: absolute;
	left: 0;
	top: -3px;
	cursor: pointer;
	color: #35CE8D;
}
</style>
</head>
<body>
	<div style="margin: 15px;">
		<div class="layui-form">
		<div class="layui-form-item">
					<label class="layui-form-label">充值金额：</label>
				    <input type="number" style="display: inline !important; width: 200px;"
						name="rechargeAmount" value="${amount.rechargeAmount}" lay-verify="rechargeAmount" autocomplete="off"
						class="layui-input" step="50">元
				</div>
			<div class="layui-form-item">
				<label class="layui-form-label">赠送金额：</label>
				<div class="layui-input-inline" style="width: 300px;">
					<input type="number" style="display: inline !important; width: 200px;"
						name="sendAmount" lay-verify="sendAmount" autocomplete="off"
						class="layui-input" value="${amount.sendAmount}" step="50">元
				</div>
			</div>
			<div class="layui-form-item">
					<label class="layui-form-label">是否有效：</label>
					<div class="layui-input-inline" style="width: 210px;">
						<select name="isEffect" lay-verify="isEffect" autocomplete="off"
						class="layui-input">
						<c:if test="${amount.isEffect == 1}">
							<option value="1" selected="selected">是</option>
							<option value="0">否</option>
						</c:if>	
						<c:if test="${amount.isEffect != 1}">
							<option value="1">是</option>
							<option value="0" selected="selected">否</option>
						</c:if>	
						</select>
					</div>
				</div>
			<div class="layui-form-item">
				<label class="layui-form-label"></label>
				<div class="layui-input-block">
				<input type="hidden" name="discountId" id="discountId" value="${amount.discountId}"/>
					<button class="layui-btn" lay-submit="" lay-filter="demo1">确认</button>
					<button onclick="goBack();" class="layui-btn layui-btn-primary">返回</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${ctx}/plugins/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript">
var loginPlat = "${loginPlat}";
layui.use([ 'form', 'layedit', 'laypage', 'laydate' ],
		function() {
	 form = layui.form(),layer = layui.layer;
			
	
		//自定义验证规则
		form.verify({
			rechargeAmount :function(value) {
				if(value.length == "") {
						   return "请输入充值金额";
								}
							},
			 sendAmount :function(value) {
					if(value.length == "") {
				           return "请输入赠送金额";
					}
				},
		});
		
		
		//监听提交
		form.on('submit(demo1)', function(data) {
			$.ajax({
				type : "post",
				url : "${ctx}/rechargeamount/doupdateamount.do",
				dataType:"json",
				data : {
					"rechargeAmount":data.field.rechargeAmount,
					"sendAmount":data.field.sendAmount,
					"isEffect":data.field.isEffect,
					"discountId":data.field.discountId
				},
				success : function(result) {
					if(result.code==0){
						layer.msg("编辑充值优惠成功");
						setTimeout(function () {
							 layer.closeAll("iframe");
		      				//刷新父页面
		      				 parent.location.reload();
						  }, 1000);
					} else{
						layer.msg(result.msg,{time:2000});
					}
				},error:function(){
					layer.msg("编辑失败");
				}
			});
			return false;
		});
	});
	



function goBack(){
	window.history.go(-1);
}
</script>
</html>