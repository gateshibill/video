<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="utf-8">
<title>电视频道详情</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">

<link rel="stylesheet" href="${ctx}/plugins/layui/css/layui.css"
	media="all" />
<link rel="stylesheet"
	href="${ctx}/css/layer-search.css?v=<%=new java.util.Random().nextInt()%>"
	media="all" />
<link rel="stylesheet"
	href="${ctx}/plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${ctx}/ueditor/themes/default/css/ueditor.css">
</head>

<body>
	<div style="margin: 15px;">
		<fieldset class="layui-elem-field layui-field-title"
			style="margin-top: 20px;">
			<legend>【${anchor.name}】主播详情</legend>
		</fieldset>
		<div class="layui-form">
		
		<div class="layui-form-item">
			<label class="layui-form-label">ID编号：</label>
			<div class="layui-input-block">
				<label class="detail-label-value">${anchor.id}</label>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">名称：</label>
			<div class="layui-input-block">
				<label class="detail-label-value"> <input type="text"
					style="display: inline !important; width: 200px;"
					name="name" value="${anchor.name}"
					lay-verify="name" autocomplete="off" class="layui-input" /></label>
			</div>
		</div>	
		<div class="layui-form-item">
			<label class="layui-form-label">类型：</label>
			<div class="layui-input-block">
				<label class="detail-label-value"> <input type="text"
					style="display: inline !important; width: 200px;"
					name="type" value="${anchor.type}"
					lay-verify="name" autocomplete="off" class="layui-input" /></label>
			</div>
		</div>							
		<div class="layui-form-item">
			<label class="layui-form-label">播放串：</label>
			<div class="layui-input-block">
				<label class="detail-label-value"> <input type="text"
					style="display: inline !important; width: 500px;"
					name="playUrl" value="${anchor.playUrl}"
					lay-verify="name" autocomplete="off" class="layui-input" /></label>
			</div>
		</div>				
		<div class="layui-form-item">
			<label class="layui-form-label">是否上线：</label>
			<div class="layui-input-block">
				<c:choose>
					<c:when test="${anchor.status==1}">
						<input type="radio" name="status" value="0" title="否">
						<input type="radio" name="status" checked="" value="1" title="是">
					</c:when>
					<c:otherwise>
						<input type="radio" name="status" checked="" value="0" title="否">
						<input type="radio" name="status" value="1" title="是">
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="layui-form-item">
				<label class="layui-form-label">海报</label>
			<div class="layui-input-block">
				<label class="detail-label-value"> <input type="text"
					style="display: inline !important; width: 500px;"
					name="posterUrl" value="${anchor.posterUrl}"
					lay-verify="name" autocomplete="off" class="layui-input" /></label>
			</div>
		</div>
			<div class="layui-form-item">
				<label class="layui-form-label"></label>
				<ul class="layer-photos-demo" id="Images-collCover" lay-verify="uploadNewImg_collCover" width: 500px></ul>
			</div>
		<div class="layui-form-item">
			<label class="layui-form-label">详情：</label>
			<%-- <div class="layui-input-block">
				<span style="color: red;">上传图片后,如果要追加图片,请再次点击文本框,避免覆盖上一张图片,如果需要替换之前的图片,请点击图片再点上传</span>
				<textarea class="layui-textarea layui-hide" name="content"
					lay-verify="content" id="goodsDetails_edit">${anchor.content}</textarea>
			</div> --%>
			<div class="layui-input-block">
						<script id="content" type="text/plain" style="width:92%;height:500px;"></script>
		    </div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="demo1">提交</button>
			</div>
		</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${ctx}/plugins/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/js/artTemplate-simple-3.0.js"></script>
<script type="text/javascript" src="${ctx}/ueditor/ueditor.config.js"></script>	
<script type="text/javascript" src="${ctx}/ueditor/ueditor.all.min.js"></script>
<script>
var ue = UE.getEditor("content");
var content = '${anchor.content}';
//var content = "国足比赛";
ue.addListener("ready", function () {
    ue.setContent(content); //赋值 
 });
//封面回显
var collCoverArr="${anchor.posterUrl}".split(",");
for(var i=0;i<collCoverArr.length;i++){
	$('#Images-collCover').append('<li><img layer-src="'+ collCoverArr[i] +'" src="'+ collCoverArr[i]  +'" alt="'+ collCoverArr[i]  +'" class="layui-upload-img"><div class="operate"><i class="layui-icon img_del">&#xe640;</i></div></li>');
}
//封面删除
function delImages_collCover(){
	var ic_s=$('#Images-collCover .img_del');
	for(var i=0;i<ic_s.length;i++){
		$("#Images-collCover .img_del").get(i).index=i;
		$("#Images-collCover .img_del:eq("+i+")").click(function(){
			var i=this.index;
			$("#Images-collCover li:eq("+i+")").remove();
		})
	}
}
delImages_collCover();

layui.use('upload', function(){
	  layui.upload({
	    url: '${ctx}/upload/upload.do'
	    ,elem: '#uploadNewImg-collCover' //指定原始元素，默认直接查找class="layui-upload-file"
	    ,method: 'post', //上传接口的http类型
	    dataType:"json"
	    ,success: function(res){
	    	$('#Images-collCover').append('<li><img src="'+ res.msg +'" alt="'+ res.msg +'" class="layui-upload-img"><div class="operate"><i class="layui-icon img_del">&#xe640;</i></div></li>');
	    	delImages_collCover();
	    }
	  });
});
	layui.use([ 'form', 'layedit', 'laydate' ],
					function() {
						var form = layui.form(), layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;
						layedit.set({
							uploadImage : {
								url : '${ctx}/layfileUpload/upload.do', //接口url
								type : 'post' //默认post
							}
						});
						//创建一个编辑器
						var editIndex = layedit.build('goodsDetails_edit');
						//自定义验证规则
						form.verify({
							name : function(value) {
								if (value.trim() == '') {
									return '名称不能为空!';
								}
							},
							uploadNewImg_collCover : function(item) {
								if ($("#Images-collCover li").length == 0) {
									return "请上传封面";
								}
							},
							firstCost : [ /^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$/,
									'请输入正确的成本价!' ],
							sellPrice : [ /^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$/,
									'请输入正确的售价!' ]
						});
						form.on('radio(exam_radio)', function(data) {
							$('#myModal').modal('show');
					    });
						//监听提交
						form.on('submit(demo1)',function(data) {
							upload_collCover="";
							$("#Images-collCover li").find("img").each(function(i){
								 var imgSrc_c = $(this).attr("src");
								 console.log("imgSrc-"+i+":"+imgSrc_c);
								 upload_collCover = imgSrc_c;
							})
							var images_collCover = upload_collCover.substr(1,upload_collCover.length-1);
											$.ajax({
														type : "post",
														url : '${ctx}/anchor/updateChannel.do',
														// 								url : "${ctx}/goods/updateGoods.do",
														dataType : "json",
														data : {
															"id" : '${anchor.id}',
															"status" : data.field.status,
															"name" : data.field.name,
															"rank" : data.field.rank,
															"type" : data.field.type,
															"playUrl" : data.field.playUrl,
															"posterUrl":data.field.posterUrl,
															"content" : ue.getContent()
														},
														success : function(
																result) {
															if (result.code == 0) {
																layer
																		.msg("修改成功");
																setTimeout(function() {
																	layer.closeAll("iframe");
																	//刷新父页面
												      				 parent.location.reload();
																			/* location.reload(); */
																		}, 1000);
																//setTimeout(location.reload(),1000);
															}
														},
														error : function() {
															layer.msg("修改失败");
														}
													});
											return false;
										});
					});
</script>
</html>