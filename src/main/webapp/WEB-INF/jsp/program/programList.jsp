<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta charset="utf-8">
<title>节目列表</title>
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
		<form class="layui-form layui-form-pane" action="">
			<div class="layui-form-item">
	
				<div class="layui-inline pane-select">
					<label class="layui-form-label">节目名称</label>
					<div class="layui-input-block ">
						<input type="text" name="name" id="name"
								placeholder="请输入名称" class="layui-input"/>
					</div>
				</div>			
				<div class="layui-inline">
					<label class="layui-form-label">频道号</label>
					<div class="layui-input-block">
						<input type="text" class="layui-input" name="groupId" id="groupId"
								placeholder="1">
					</div>
				</div>
				<div class="layui-inline">
					<a class="layui-btn search_btn" onclick="doSearch()">
					 <i class="layui-icon">&#xe615;</i>查询
					</a>
				</div>
			</div>
	</form>
	</blockquote>
	<table class="layui-table" id="descovery" lay-filter="descovery"></table>
	<script type="text/html" id="toolBar">
	<a class="layui-btn layui-btn layui-btn-mini" lay-event="lookUser" >查看</a>
   <%-- <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="setAgent" >设置代理</a> --%>
  	<a class="layui-btn layui-btn-danger layui-btn-mini" onclick="deleteUser({{d.userId}})">删除</a>

</script>
	<!-- 上架状态模板 -->
<script type="text/html" id="usersexTpl">
    {{# var issj=d.userSex}}
    {{# if (issj=='1'){}}
    	男
    {{# } else{ }}
   		女
    {{# } }}
</script>
	<!-- 审核状态模板 -->
	<script type="text/html" id="isManagerTpl">
    {{# var ispass=d.isManager}}
    {{# if (ispass=='0'){}}
     <a href="#" lay-event="nopassDescovery">否</a>
    {{# } else{ }}
   		<a href="#" lay-event="nopassDescovery" style="color:green;">是</a>
    {{# } }}
</script>
	<script type="text/html" id="userStatusTpl">
    {{# var ispass=d.userStatus}}
    {{# if (ispass=='1'){}}
     <a href="#" style="color:green" lay-event="passDescovery">正常</a>
    {{# } else{ }}
   	<a href="#" lay-event="passDescovery" style="color:red;">禁用</a>
    {{# } }}
</script>
	<script type="text/html" id="nicknameTpl">
    {{# var destype=d.userNickName}}
     {{# if (destype==null){}}
            	用户未找到
    {{# } else{ }}
   		{{destype}}
    {{# } }}
</script>
	<script type="text/html" id="phoneTpl">
       {{#if(d.UserPhone){}}
      	 	{{d.UserPhone?d.UserPhone:'未设置'}}
       {{# }else{}}
        	未设置     
       {{#}}}
</script>
	<script type="text/html" id="e_mailTpl">
       {{#if(d.eMail){}}
       		{{d.eMail?d.eMail:'未设置'}}
		{{# }else{}}
        	未设置    
       {{#}}}
</script>

	<script type="text/html" id="isAgentTpl">
    {{# var isAgent=d.isAgent}}
    {{# if (isAgent == 1){}}
                            是
    {{# } else{ }}
   	             否  
    {{# } }}
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
				elem : '#test6',
				range : true
			});
			//--------------方法渲染TABLE----------------
			pid = $("#loginPlat").val();
			getlist(pid, '', '', '', '', '', '','');
		});
		function doSearch() {

			var name = $("#name").val();
			var type = $("#type").val();
			var groupId=$("#groupId").val();
			var status=$("#status").val();

			getlist(name,type,goupId,status);
		}

		function getlist(name,type,source,startTime,endTime) {
			tableIns = table.render({
				elem : '#descovery',
				id : 'descovery',
				method : 'post',
				where : {
					//'loginPlat' : pid,
					//'loginPlat1' : lp2,
					//'isManager' : ism,
					//'id' : id, 
					'name' : name,
					'type':type,
					'source':source,
					'startTime' : startTime,
					'endTime':endTime,
				},
				url : '${ctx}/program/getProgramList.do',
				cols : [ [ 
// 				  {
// 					checkbox : true,
// 					LAY_CHECKED : false,
// 					fixed : true
// 				}, 
                   {
					field : 'id',
					title : 'id',
					width : 100,
					align : 'center',
					fixed : true,
					sort : true
				}, {
					field : 'name',
					title : '节目名',
					width : 140,
					align : 'center',
					//templet : "#nameTpl"
				}, {
					field : 'channelId',
					title : '频道号',
					width : 140,
					align : 'center',
					templet : "#delayTpl"
				}, 
				{
					field : 'event',
					title : '赛事',
					width : 140,
					align : 'center'
				},  
				{
					field : 'type',
					title : '类型',
					width : 140,
					align : 'center',
					templet : "#typeTpl"
					
				}, {
					field : 'startTime',
					title : '开始时间',
					width : 100,
					align : 'center',
					templet : "#groupIdTpl"
					
				}
				, {
					field : 'endTime',
					title : '结束时间',
					width : 100,
					align : 'center',
					templet : "#statusTpl"
					
				},{
					field : 'playUrl',
					title : '播放串',
					width : 180,
					align : 'center',
					sort : true,
				},{
					field : 'posterUrl',
					title : '海报',
					width : 180,
					align : 'center',
					sort : true,
				},{
					field : 'source',
					title : '来源',
					width : 180,
					align : 'center',
					sort : true,
				}, {
					title : '操作',
					width : 250,
					align : 'center',
					fixed : 'right',
					toolbar : '#toolBar'
				} ] ],
				page : true,
				limits : [ 1, 10, 20, 30, 50, 100 ],
				limit : 15, //默认采用20
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
		table.on('tool(descovery)', function(obj) {
			var data = obj.data;
			if (obj.event === 'lookUser') {
				var index = layui.layer
				.open({
					title : "编辑",
					type : 2,
					content : "${ctx}/program/getProgramById.do?id="+data.id,
					success : function(
							layero,
							index) {
						setTimeout(
								function() {
									layui.layer
											.tips(
													'点击此处返回',
													'.layui-layer-setwin .layui-layer-close',
													{
														tips : 3
													});
								}, 500)
					}
				})
				layui.layer.full(index);
			} else if (obj.event === 'passDescovery') {
				if(data.status==1){
					$.ajax({
						data : 'json',
						url : '${ctx}/program/updateUseer.do',
						type : "post",
						data : {
							id : data.id,
							status:2
							
						},
						success : function(data) {
							if (data.code == '0') {
								layer.msg('禁用用户成功');
								//同步更新缓存对应的值
								obj.update({
									status : 2
								});
							} 
						}
					});
				} else {
					$.ajax({
						data : 'json',
						url : '${ctx}/program/updateUseer.do',
						type : "post",
						data : {
							id : data.id,
							status:1
						},
						success : function(data) {
							if (data.code == '0') {
								layer.msg('恢复用户成功');
								//同步更新缓存对应的值
								obj.update({
									status : 1
								});
							} 
						}
					});
				}
			} else if (obj.event === 'nopassDescovery') {
				if(data.isManager==0){
					$.ajax({
						data : 'json',
						url : '${ctx}/program/updateUseer.do',
						type : "post",
						data : {
							id : data.id,
							isManager:1
						},
						success : function(data) {
							if (data.code == '0') {
								layer.msg('设置管理成功');
								//同步更新缓存对应的值
								obj.update({
									isManager : 1
								});
							} 
						}
					});
				} else {
					$.ajax({
						data : 'json',
						url : '${ctx}/program/updateUseer.do',
						type : "post",
						data : {
							id : data.id,
							isManager:0
						},
						success : function(data) {
							if (data.code == '0') {
								layer.msg('取消管理成功');
								//同步更新缓存对应的值
								obj.update({
									isManager : 0
								});
							} 
						}
					});
				}
			} 
		});

	
		
		//删除用户
		function deleteUser(id){
			layer.confirm('您确认删除该用户么?', {
				btn : [ '取消', '确认' ]
			}, function() {
				layer.closeAll();
			}, function() {
				$.post('${ctx}/program/deleteid.do', {
					'id' : id
				}, function(data) {
					if (data) {
						layer.msg('删除用户成功');
						setTimeout(function() {
							window.location.reload();
						}, 1000);
					} else {
						layer.msg('删除失败', {
							time : 2500,
							icon : 0
						});
					}
				});
			})
		}
	</script>
</body>
</html>