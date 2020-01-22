<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta charset="utf-8">
<title>修改工程师</title>
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
	margin-bottom:20px;
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
#design-Images{
	border:1px solid #999;
	margin-top: 10px;
}
#design-Images li{
	width: 300px;
	height: 300px;
	float: left;
	margin-left: 10px;
}
#design-Images li img{
	width: 300px;
	height: 300px;
}
.xiang_del{
	cursor: pointer;
}
</style>
</head>
<body>
	<div style="margin: 15px;">
		<div class="layui-form">
		
			<div class="layui-form-item">
				<label class="layui-form-label">意见：</label>
				<div class="layui-input-block">
					<input type="text" name="content"  autocomplete="off" class="layui-input" lay-verify="content" value="${pCheckView.content}">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">上传图片</label>
				<div class="layui-input-block">
					<button type="button" class="layui-btn" id="uploadNewImg">多图片上传</button>
				</div>
			</div>
			<blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
				    预览图：
				    <div class="layui-upload-list" id="demo2">
				      	<ul class="layer-photos-demo flex-box" id="Images" lay-verify="uploadNewImg"></ul>			      
				    </div>
			 </blockquote>
			
			  <div class="layui-form-item">
				    <label class="layui-form-label">是否处理：</label>
				    <div class="layui-input-inline">
				      <select name="isDeal" lay-verify="isDeal">
				       		<option value="0" <c:if test="${pCheckView.isDeal==0}">selected="selected"</c:if>>否</option>
							<option value="1" <c:if test="${pCheckView.isDeal==1}">selected="selected"</c:if>>是</option>	
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
console.log("${pCheckView.smallPlanId}");
//图片集合删除
function delImages(){
	var i_s=$("#Images .img_del");
	for(var i=0;i<i_s.length;i++){
		$("#Images .img_del").get(i).index=i;
		$("#Images .img_del:eq("+i+")").click(function(){
			var i=this.index;
			$("#Images li:eq("+i+")").remove();
		})
	}
}
delImages();

layui.use('upload', function(){
	  var upload = layui.upload;
	  upload.render({
		    url: '${ctx}/vij/uploadProject/uploadProject.do'
		    ,multiple: true
		    ,accept:'images'
		    ,elem: '#uploadNewImg' //指定原始元素，默认直接查找class="layui-upload-file"
		    ,method: 'post', //上传接口的http类型
		    dataType:"json"
		    ,before: function(obj){}
		    ,done: function(res){
		    	$('#Images').append('<li><img src="'+ res.msg +'" alt="'+ res.msg +'" class="layui-upload-img"><div class="operate"><i class="layui-icon img_del">&#xe640;</i></div></li>');
				delImages();
		    }
		});  
});
layui.use([ 'form', 'layer'],
		function() {
	  var form = layui.form, layer = layui.layer;
	 
		//自定义验证规则
		form.verify({	
			content : function(value) {
				if (value.length == "") {
					return "请输入意见";
				}
			},
			isDeal : function(value) {
				if (value.length == "") {
					return "请选择是否处理";
				}
			},
			images : function(item) {
				if ($("#Images li").length == 0) {
					return "请上传图片";
				}
			}, 
		});
		//监听提交
		form.on('submit(demo1)', function(data) {
			 var uploadStr = "";
			
			$("#Images li").find("img").each(function(i){
				 var imgSrc = $(this).attr("src");
				 console.log("imgSrc-"+i+":"+imgSrc);
				 uploadStr += ","+imgSrc;
			})
			var images = uploadStr.substr(1,uploadStr.length-1);
			$.ajax({
				type : "post",
				url : "${ctx}/newProject/uploadProjectCheckView.do",
				dataType:"json", 
				data : {
					"content":data.field.content,
					"isDeal":data.field.isDeal,
					"images":images,
					"smallPlanId":"${pCheckView.smallPlanId}"
				},
				success : function(result) {
					if(result.code==0){
						layer.msg("编辑成功");
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