<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta charset="utf-8">
<title>layui</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet"
	href="${ctx}/layui/css/layui.css?a=<%=new java.util.Random().nextInt()%>"
	media="all" />
<link rel="stylesheet" href="${ctx}/css/global.css" media="all">
<link rel="stylesheet"
	href="${ctx}/plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${ctx}/css/table.css?a=<%=new java.util.Random().nextInt()%>" />
<link rel="stylesheet"
	href="${ctx}/css/layer-search.css?v=<%=new java.util.Random().nextInt()%>" />
<style type="text/css">
	.layui-form-item{
		margin-bottom:0px;
	}
</style>
</head>
<body>
	<blockquote class="layui-elem-quote">
	<form class="layui-form layui-form-pane" method="post" action="" id="listform">
			<div class="layui-form-item">
	
				<div class="layui-inline">
					<label class="layui-form-label">文章编号</label>
					<div class="layui-input-block">
						<input type="text" name="topicId" id="topicId"
								placeholder="请输入文章编号" class="layui-input">
					</div>
				</div>
				
				<div class="layui-inline">
					<label class="layui-form-label">文章标题</label>
					<div class="layui-input-block">
						<input type="text" name="topicName" id="topicName"
								placeholder="请输入文章标题" class="layui-input" value="${desc.topicName}">
					</div>
				</div>
				
				
				<div class="layui-inline">
					<label class="layui-form-label">文章链接</label>
					<div class="layui-input-block">
						<input type="text" name="fromUrl" id="fromUrl"
								placeholder="请输入发布者名称" class="layui-input">
					</div>
				</div>
				
				<div class="layui-inline">
					<label class="layui-form-label">发布状态</label>
					<div class="layui-input-block">
						<input type="text" class="layui-input" name="topicStatus"
								id="topicStatus" placeholder="">
					</div>
				</div>
				<div class="layui-inline">
					<a class="layui-btn search_btn" onclick="doSearch()"> <i
						class="layui-icon">&#xe615;</i>查询
					</a>
				</div>
			</div>
	</form>
</blockquote>
	<div class="layui-btn-group demoTable">
		<button class="layui-btn" style="margin-left: 10px;"
			data-type="addDescovery">
			<i class="layui-icon">&#xe608;</i>发布&nbsp;文章
		</button>
	</div>
	<table class="layui-table" id="topic" lay-filter="topic"></table>
	<script type="text/html" id="toolBar">
	<a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="topicDetail" >查看</a>
	{{# var ispass=d.isPass}}
    {{# if (ispass=='0'){}}
    	
    {{# } else{ }}
    {{# } }}
	{{# if (ispass=='1'){}}
    	
    {{# } else{ }}
    {{# } }}
	
  	<a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="deleteDescovery">删除</a>

</script>
	<!-- 上架状态模板 -->
	<script type="text/html" id="isshangjiaTpl">
    {{# var issj=d.isShangJia}}
    {{# if (issj=='0'){}}
    	<a href="#" lay-event="zxisshangjia" ><span style="color:red;">下架</span></a>
    {{# } else{ }}
   		<a href="#" style="color:#009688" lay-event="zxisshangjia">上架</a>
    {{# } }}
</script>
	<!-- 审核状态模板 -->
	<script type="text/html" id="ispassTpl">
    {{# var ispass=d.isPass}}
    {{# if (ispass=='0'){}}
    	<span style="color:red;">未审核</span>
    {{# } else{ }}
   		已审核
    {{# } }}
</script>
	<!-- 文章类型模板 -->
	<script type="text/html" id="dtypeTpl">
    {{# var destype=d.topicType}}
    {{# if (destype=='1'){}}
    	项目
    {{# } else if(destype=='2'){ }}
   		产品
 	{{# } else if(destype=='3'){ }}
   		技能
 	{{# } else if(destype=='4'){ }}
   		招聘
 	{{# } else if(destype=='5'){ }}
   		资讯
    {{# } else if(destype=='8'){ }}
   		指南
    {{# } else if(destype=='9'){ }}
   		问答
    {{# } }}
</script>	<!-- 是否推荐模板 -->
	<script type="text/html" id="isRecommendTpl">
    {{# var tj=d.isRecommend}}
    {{# if (tj=='0'){}}
    	未推荐
    {{# } else if(tj=='1'){ }}
   		已推荐
    {{# } }}
</script>
	<script type="text/html" id="nicknameTpl">
       {{#if(d.dscvPublisher){}}
              {{d.dscvPublisher.nickName}}
       {{# }else{}}
        <span style="color:red"> 用户未找到   </span>   
       {{#}}}
</script>
<%-- 
<script type="text/html" id="appTypeTpl">
 {{# var tj=d.app.applicationName}}
    {{# if (tj!='null'){}}
        {{tj}}
    {{# } else{ }}
   		 没有找到
    {{# } }}
</script>
--%>
	<script type="text/html" id="realnameTpl">
{{#if(d.dscvPublisher){}}
       {{d.dscvPublisher.realName?d.dscvPublisher.realName:'未设置'}}
{{# }else{}}
        <span style="color:red"> 用户未找到   </span>   
       {{#}}}
</script>
	<script type="text/javascript" src="${ctx}/layui/layui.all.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script>
		layui.use([ 'layer', 'table', 'laydate' ], function() {
			table = layui.table;
			layer = layui.layer;
			var laydate = layui.laydate;
			//日期范围
			laydate.render({
				elem : '#dateRange',
				range : true
			});
			//--------------方法渲染TABLE----------------
			pid = $("#loginPlat").val();
			dst = $("#topicType").val();
			isp = $("#isPass").val();
			isj = $("#isShangJia").val();
			ditit = $("#topicName").val();
			getlist(pid, '', dst, isp, isj, '', ditit,'');
		});
		function doSearch() {
			var pid = $("#loginPlat").val();
			var lp2 = $("#loginPlat1").val();
			var dst = $("#topicType").val();
			var isp = $("#isPass").val();
			var userId = $("#publisherId").val();
			var ukw = $("#userKeyWords").val();
			var isj = $("#isShangJia").val();
			var dsid = $("#topicId").val();
			var ditit = $("#topicName").val();
			var dtr = $("#dateRange").val();
			getlist(pid, lp2, dst, isp, userId, ukw, isj, dsid, ditit, dtr);
		}

		function getlist(pid, loginPlat2, dstype, ispass, userId, userKeyWords,
				isShangjia, topicid, dititle, DateRange) {
			tableIns = table.render({
				elem : '#topic',
				id : 'topic',
				method : 'post',
				where : {
					'loginPlat' : pid,
					'loginPlat1' : loginPlat2,
					'topicType' : dstype,
					'isPass' : ispass,
					'publisherId' : userId,
					'userKeyWords' : userKeyWords,
					'isShangJia' : isShangjia,
					'topicId' : topicid,
					'topicName' : dititle,
					'dateRange' : DateRange
				},
				url : '${ctx}/topic/showDataList.do',
				cols : [ [ 
// 				{
// 					checkbox : true,
// 					LAY_CHECKED : false,
// 					fixed : true
// 				}, 
				{
					field : 'topicId',
					title : 'ID',
					width : 80,
					align : 'center',
					fixed : true,
					sort : true
				}, {
					field : 'topicType',
					title : '文章类型',
					width : 100,
					align : 'center',
					templet : "#appTypeTpl"
				},{
					field : 'topicName',
					title : '文章标题',
					width : 200,
					align : 'center'
				}, {
					field : 'topicStatus',
					title : '是否上架',
					width : 120,
					align : 'center',
					templet : "#isshangjiaTpl"
				}, {
					field : 'topicSort',
					title : '排序',
					width : 120,
					align : 'center'
				}, {
					field : 'fromUrl',
					title : '来源',
					width : 140,
					align : 'center',
// 					sort : true,
					templet : "#nicknameTpl"
				}, {
					title : '操作',
					width : 210,
					align : 'center',
					fixed : 'right',
					toolbar : '#toolBar'
				} ] ],
				page : true,
				limits : [ 1, 10, 20, 30, 50, 100 ],
				limit : 20, //默认采用20
				width : '100%',
				height : 'auto',
				loading : true,
				even : true,
				response : {
					statusName : 'code' //数据状态的字段名称，默认：code
					,
					statusCode : 0 //成功的状态码，默认：0
					,
					countName : 'count' //数据总数的字段名称，默认：count
					,
					dataName : 'data' //数据列表的字段名称，默认：data
				},
				done : function(res, curr, count) {
					//如果是异步请求数据方式，res即为你接口返回的信息。
					//如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
					//console.log(res);
					//得到当前页码
					//console.log(curr);
					//$("#curPageIndex").val(curr);
					//得到数据总量
					//console.log(count);
				}
			});
		}
		table.on('tool(topic)', function(obj) {
			var data = obj.data;
			if (obj.event === 'topicDetail') {
				var index = layui.layer.open({
					title : "编辑文章",
					type : 2,
					content : "${ctx}/topic/topicDetails.do?topicId="+data.topicId,
					success : function(layero, index){
						setTimeout(function(){
							layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
								tips: 3
							});
						},500)
					}
				})			
				layui.layer.full(index);
			} else if (obj.event === 'passDescovery') {
				$.ajax({
					data : 'json',
					url : '${ctx}/withdraw/dealWithdraw.do',
					type : "post",
					data : {
						withdrawId : data.withdrawId
					},
					success : function(result) {
						if (result.code == '0') {
							layer.msg(result.msg);
							//同步更新缓存对应的值
							obj.update({
								withdrawState : 2
							});
						} else {
							layer.msg(result.msg);
							return;
						}
					}
				});
			} else if (obj.event === 'recommend') {
				$.ajax({
					data : 'json',
					url : '${ctx}/topic/tjtoSame.do',
					type : "post",
					data : {
						topicId : data.topicId
					},
					success : function(result) {
						if (result.code == '0') {
							layer.msg(result.msg);
							//同步更新缓存对应的值
							obj.update({
								isRecommend : 1
							});
						} else {
							layer.msg(result.msg);
							return;
						}
					}
				});
			} else if (obj.event === 'deleteDescovery') {
				var topicId = data.topicId;
				layer.confirm('确定删除该条数据', {
					  btn: ['确定','取消'] //按钮
					}, function(){
						$.ajax({
							 url:"${ctx}/topic/dealDescovery.do",
							 type:'post',
							 dataType:'json',
							 data:{'topicId':topicId},
							 success:function(data){
								 if(data.code == 0){
									 layer.msg("删除成功");
									 setTimeout(function(){
										 table.reload("topic");
									 },1000);
								 }else{
									 layer.msg(data.msg);
									 return;
								 }
							 },
							 error:function(data){
								 layer.msg('系统错误,请联系管理员');
								 return;
							 }
						})
					}, function(){
					 
					});
				
			} else if(obj.event === 'zxisshangjia'){
				  if(data.isShangJia==1){
					  $.post('${ctx}/topic/updateDescovery.do', {
							'topicId' : data.topicId,
							'isShangJia' : 0,
						}, function(data) {
							if (data.code==0) {
								layer.msg("下架资讯成功!");
								//同步更新缓存对应的值
								 obj.update({
									 isShangJia : 0
								});
							} 
						});
				  } else{
					  $.post('${ctx}/topic/updateDescovery.do', {
							'topicId' : data.topicId,
							'isShangJia' : 1,
						}, function(data) {
							if (data.code==0) {
								layer.msg("上架资讯成功!");
								//同步更新缓存对应的值
								 obj.update({
									 isShangJia : 1
								});
							} 
						});
				  }
			} else{
				
			}
		});

		 var $ = layui.$, active = {
			addDescovery : function() { //验证是否全选
				if($("#topicType").val()==""){
					layer.msg("请选择要发布的文章类型");
				}else if($("#loginPlat").val()==""){
					layer.msg("请选择要发布的应用");
				} else if($("#topicType").val()==9){
					layer.msg("暂不支持发布问答");
				}else if($("#topicType").val()==10){
					layer.msg("后台暂不支持发布唯爱秀家");
				}else{			
					//window.location.href='${ctx}/topic/goAddDescovery.do?topicType='+$("#topicType").val()+'&id='+$("#loginPlat").val()+'';
					var index = layui.layer.open({
						title : "添加文章",
						type : 2,
						content : '${ctx}/topic/goAddDescovery.do?topicType='+$("#topicType").val()+'&id='+$("#loginPlat").val()+'',
						success : function(layero, index){
							setTimeout(function(){
								layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
									tips: 3
								});
							},500)
						}
					})			
					layui.layer.full(index);
				}
			}
		}; 
		$('.demoTable .layui-btn').on('click', function() {
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
		
	</script>
</body>
</html>