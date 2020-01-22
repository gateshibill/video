<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta charset="utf-8">
<title>项目报价</title>
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
#Images li{
	float:left;
	width:300px;
	height:300px;
	margin-bottom:30px;
	margin-right:10px;
}
#Images img{
	width:300px;
	height:300px;
}
.img_del{
    cursor: pointer;
}
.layui-form-select .layui-edge{
	right:5%;
}
.layui-form-select dl, .layui-nav-child {
    min-width: 95%;
    margin-left: 20px;
}
.projectTable{
	margin: 0 auto;
	width: 800px;
	height: 250px;
	border-top: 1px solid #666;
}
.projectTable tr{	
	border-bottom: 1px solid #666;
}
.projectTable th{
	width:75%;
	border-left: 1px solid #666;
	text-align: left; 
	padding-left: 20px;
} 
.projectTable .p1{
	color: #666;
	font-size: 23px;
	font-weight: 400;
	
}
.projectTable .p2{
	color: #339d5b;
	font-size: 20px;
	font-weight: 400;
	padding-left: 20px;
}
.projectTable .p3{
	color: red;
	font-size: 20px;
	font-weight: 400;
	padding-left: 20px;
}   
.projectTable td{
	width:25%;
	border-right: 1px solid #666;
	text-align: center;
}
.projectTable span{
	color: #999;
	font-size: 23px;
	cursor: pointer;
}
.buttonbtn{
	margin: 0 auto;
	width: 800px;
	padding-top:20px;
	text-align: right;
}
</style>
</head> 
<body> 
	<div style="margin: 15px;"> 
		<table class="projectTable">
			<tr> 
				<th>
					<p class="p1">1.上传客户新家硬装报价方案</p>
					<c:if test="${projectOfferC1 == 't'}">
						<p class="p2">已上传</p>
					</c:if>
					<c:if test="${projectOfferC1 == 'f'}">
						<p class="p3">未上传</p>
					</c:if>
				</th>
				<td><span onclick="uploadOffer(1);">点击进入</span></td>
			</tr>
			<tr>
				<th>
					<p class="p1">2.上传客户新家软装报价方案</p>
					<c:if test="${projectOfferC2 == 't'}">
						<p class="p2">已上传</p>
					</c:if>
					<c:if test="${projectOfferC2 == 'f'}">
						<p class="p3">未上传</p>
					</c:if>
				</th>
				<td><span onclick="uploadOffer(2);">点击进入</span></td>
			</tr>
			<tr>
				<th>
					<p class="p1">3.查看业主是否有调整报价</p>
					<c:if test="${projectViewC == 't'}">
						<p class="p2">是</p>
					</c:if>
					<c:if test="${projectViewC == 'f'}">
						<p class="p3">否</p>
					</c:if>
				</th>  
				<td><span onclick="updateDesign();">点击进入</span></td>
			</tr>  
		</table> 
		<div class="buttonbtn">
			<button class="layui-btn queding">确定</button>
		</div>
		
	</div> 
</body>
<script type="text/javascript" src="${ctx}/layui/layui.all.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript">

//上传报价
function uploadOffer(offerType){
	var index = layui.layer
	.open({
		title : "上传报价",
		type : 2,
		content : "${ctx}/newProject/goUploadOffer.do?projectId="
				+ "${project.id}"+"&offerType="+offerType,
		success : function(layero, index) {
			setTimeout(
				function() {
					layui.layer.tips(
						'点击此处返回',
						'.layui-layer-setwin .layui-layer-close',
						{
							tips : 3
						});
				}, 500)
		}
	})
	layui.layer.full(index);
}
//查看是否有调整设计图/报价
function updateDesign(){
	var index = layui.layer
	.open({
		title : "调整设计图/报价",
		type : 2,
		content : "${ctx}/newProject/findProjectView.do?projectId="
				+ "${project.id}&viewType=2",
		success : function(layero, index) {
			setTimeout(
				function() {
					layui.layer.tips(
						'点击此处返回',
						'.layui-layer-setwin .layui-layer-close',
						{
							tips : 3
						});
				}, 500)
		}
	})
	layui.layer.full(index);
}
	 
$(".queding").click(function(){
	setTimeout(function () {
		 layui.layer.closeAll("iframe");
			//刷新父页面
			 parent.location.reload();
	  }, 100);
})	
	
	
function goBack(){
	window.history.go(-1);
}
</script>
</html>