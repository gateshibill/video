<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta charset="utf-8">
<title>频道列表</title>
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
					<label class="layui-form-label">频道名称</label>
					<div class="layui-input-block ">
						<input type="text" name="name" id="name"
								placeholder="请输入用户名称" class="layui-input"/>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">频道状态</label>
					<div class="layui-input-block">
						<select id="status" name="status" class="search-select">
								<option value="">全部</option>
								<option value="1">正常</option>
								<option value="2">禁用</option>
							</select>
					</div>
				</div>
						
				<div class="layui-inline pane-select">
					<label class="layui-form-label">频道类型</label>
					<div class="layui-input-block ">
						<input type="text" name="type" id="type"
								placeholder="请输入用户关键字" class="layui-input">
					</div>
				</div>			
				<div class="layui-inline">
					<label class="layui-form-label">频道区域</label>
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

               <div class="layui-upload">
                     <button type="button" class="layui-btn layui-btn-normal" id="test8">选择文件</button>
                     <button type="button" class="layui-btn" id="test9">开始上传</button>
               </div>
			
			</div>
	</form>
	</blockquote>
	<table class="layui-table" id="descovery" lay-filter="descovery"></table>
	<script type="text/html" id="toolBar">
    <a class="layui-btn layui-btn layui-btn-mini" lay-event="player" >播放</a>
	<a class="layui-btn layui-btn layui-btn-mini" lay-event="lookUser" >查看</a>
   <%-- <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="setAgent" >设置代理</a> --%>
  	<a class="layui-btn layui-btn-danger layui-btn-mini" onclick="deleteUser({{d.userId}})">删除</a>
    <a class="layui-btn layui-btn-danger layui-btn-mini" onclick="play({{d.userId}})">播放</a>

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
	layui.use([ 'upload','layer', 'table', 'laydate' ], function() {
	    var $ = layui.jquery
		upload = layui.upload;
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

		function getlist(pid, lp2, ism, id, name,type,groupId,status,rank,isAgent) {
			tableIns = table.render({
				elem : '#descovery',
				id : 'descovery',
				method : 'post',
				where : {
					'loginPlat' : pid,
					'loginPlat1' : lp2,
					//'isManager' : ism,
					'id' : id, 
					'name' : name,
					'type':type,
					'status':status,
					'groupId' : groupId,
					'rank':rank,
				},
				url : '${ctx}/channel/getChannelList.do',
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
					title : '频道',
					width : 140,
					align : 'center',
					//templet : "#nameTpl"
				}, {
					field : 'delay',
					title : '延迟',
					width : 140,
					align : 'center',
					templet : "#delayTpl"
				}, 
				{
					field : 'rank',
					title : '排序',
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
					field : 'groupId',
					title : '地区',
					width : 100,
					align : 'center',
					templet : "#groupIdTpl"
					
				}
				, {
					field : 'status',
					title : '状态',
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
					title : 'source',
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
					content : "${ctx}/channel/getChannelById.do?id="+data.id,
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
						url : '${ctx}/channel/updateUseer.do',
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
				} else if (obj.event === 'player') {
					//点击某个按钮 => 弹出层 => 播放视频
				    //$('#player').click(function () {
				        playUrl = data.playUrl;  //获取到播放的url
				    	//layer.msg('播放串:'+playUrl);
				        var loadstr = '<video width="100%" height="100%"  controls="controls" autobuffer="autobuffer"  autoplay="autoplay" loop="loop">' +
				                '<source src='+playUrl+' type="video/mp4"></source></video>';
				        layer.open({
				            type: 1,
				            title: false,
				            area: ['730px', '500px'],
				            shade: [0.8, 'rgb(14, 16, 22)'],
				            skin: 'demo-class',
				            content: loadstr
				        });
				    //});
				} else {
					$.ajax({
						data : 'json',
						url : '${ctx}/channel/updateUseer.do',
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
						url : '${ctx}/channel/updateUseer.do',
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
						url : '${ctx}/channel/updateUseer.do',
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
				$.post('${ctx}/channel/deleteUserId.do', {
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
		
		//播放视频
		function play(){
			//layer.msg('play：'+vUrl);
			//点击某个按钮 => 弹出层 => 播放视频
		    //$('#player').click(function () {
		       // vUrl = $(this).attr('src');  //获取到播放的url
		       vUrl = 'http://www.haoshi360.com/girl/7/7.mp4';
		        var loadstr = '<video width="100%" height="100%"  controls="controls" autobuffer="autobuffer"  autoplay="autoplay" loop="loop">' +
		                '<source src=‘+
		                vUrl+
		                ’ type="video/mp4"></source></video>';
		        layer.open({
		            type: 1,
		            title: false,
		            area: ['730px', '500px'],
		            shade: [0.8, 'rgb(14, 16, 22)'],
		            skin: 'demo-class',
		            content: loadstr
		        });
		 //   });
		}

		 //选完文件后不自动上传
		upload.render({
		    elem: '#test8'
		    ,url: '${ctx}/video/importExcelChannels.do' //改成您自己的上传接口
		    ,auto: false
		    //,multiple: true
		    , type: "file"
		    ,accept: 'file' //普通文件
		    ,bindAction: '#test9'
		    ,done: function(res){
		      layer.msg('上传成功');
		      console.log(res)
		    }
		  });	
		 //指定允许上传的文件类型
		  upload.render({
		    elem: '#test3'
		    ,url: 'https://httpbin.org/post' //改成您自己的上传接口
		    ,accept: 'file' //普通文件
		    ,done: function(res){
		      layer.msg('上传成功');
		      console.log(res);
		    }
		  });

	</script>
</body>
</html>