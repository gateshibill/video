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
</head>
<body>
	<blockquote class="layui-elem-quote">
	<form class="layui-form layui-form-pane" method="post" action="" id="listform">
			<div class="layui-inline">
			<label class="layui-form-label">用户编号</label>	
		    <div class="layui-input-inline">
				<input type="text" name="publisherId" id="publisherId"
								placeholder="请输入中奖人编号" class="layui-input">		    </div>
		    </div>
		    	<div class="layui-inline">
			<label class="layui-form-label">用户名称</label>	
		    <div class="layui-input-inline">
				<input type="text" name="userKeyWords" id="userKeyWords"
								placeholder="请输入中奖人名称" class="layui-input">		    </div>
		    </div>
			<div class="layui-inline">
			<label class="layui-form-label">发布日期</label>	
		    <div class="layui-input-inline">
				<input type="text" class="layui-input" name="dateRange"
								id="dateRange" placeholder=" - ">		    </div>
		    <a class="layui-btn search_btn" onclick="doSearch()">查询</a>
		</div>
	</form>
	</blockquote>	
	<div class="layui-btn-group demoTable">
		<button class="layui-btn" data-type="addAllSeleted"> 
		<i class="layui-icon">&#xe608;</i>批量处理</button>
	</div>
	
	<table class="layui-table" id="descovery" lay-filter="descovery"></table>
	<script type="text/html" id="toolBar">
    
    <a class="layui-btn layui-btn-mini" onclick="updateRecord({{d.recordId}})">处理</a>
 	
  	<a class="layui-btn layui-btn-danger layui-btn-mini" onclick="deleteRecord({{d.recordId}})">删除</a>

</script>
	<!-- 发现类型模板 -->
	<script type="text/html" id="statusTpl">
    {{# var destype=d.status}}
    {{# if (destype=='1'){}}
    	<span style="color:#009688">未兑奖</sapn>
    {{# } else if(destype=='2'){ }}
   		<span style="color:#1E9FFF">申请中</span>
 	{{# } else if(destype=='3'){ }}
   		<span style="color:red">已兑换</span>
 	{{# } }}
</script>	
	<script type="text/html" id="nicknameTpl">
       {{#if(d.userInfo){}}
              {{d.userInfo.nickName}}
       {{# }else{}}
        <span style="color:red"> 用户未找到   </span>   
       {{#}}}
</script>

<script type="text/html" id="phoneTpl">
 {{#if(d.userInfo){}}
              {{d.userInfo.phone}}
       {{# }else{}}
        <span style="color:red"> 未设置   </span>   
       {{#}}}
</script>
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
			getlist('', '', '', '', '', '');
		});
		function doSearch() {
			var userId = $("#publisherId").val();
			var ukw = $("#userKeyWords").val();
			var dtr = $("#dateRange").val();
			var status = $("#status").val();
			getlist(userId, ukw, dtr,status);
		}

		function getlist( userId, userKeyWords,DateRange,status) {
			tableIns = table.render({
				elem : '#descovery',
				id : 'descovery',
				method : 'post',
				where : {
					'userId' : userId,
					'userKeyWords' : userKeyWords,
					'dataRange' : DateRange,
					'status' : status,
					'type' : 1
					
				},
				url : '${ctx}/prize/showPrizeList.do',
				cols : [ [ {
					checkbox : true,
					LAY_CHECKED : false,
					fixed : true
				}, {
					field : 'recordId',
					title : 'ID',
					width : 80,
					align : 'center',
					fixed : true,
					sort : true
				}, {
					field : 'userId',
					title : '用户编号',
					width : 140,
					align : 'center'
				}, 
				{
					field : 'userName',
					title : '用户昵称',
					width : 140,
					align : 'center',
					templet : "#nicknameTpl"
				},
				{
					field : 'phone',
					title : '联系方式',
					width : 140,
					align : 'center',
					templet : "#phoneTpl"
				},{
					field : 'prizeId',
					title : '奖品编号',
					width : 140,
					align : 'center'
				}, {
					field : 'prizeName',
					title : '奖品名称',
					width : 140,
					align : 'center',
				}, 
				{
					field : 'status',
					title : '奖品状态',
					width : 140,
					align : 'center',
					templet : "#statusTpl"
				}, {
					field : 'prizeFee',
					title : '中奖金额',
					width : 100,
					align : 'center',
				}, {
					field : 'prizeTime',
					title : '抽奖时间',
					width : 180,
					align : 'center',
					sort : true,
				}, {
					title : '操作',
					width : 180,
					align : 'center',
					fixed : 'right',
					toolbar : '#toolBar'
				} ] ],
				page : true,
				limits : [ 1, 10, 15, 20, 30, 50, 100 ],
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
		table.on('tool(descovery)', function(obj) {
			var data = obj.data;
			
		});

		 var $ = layui.$, active = {
					addAllSeleted : function() { //验证是否全选
						var checkStatus = table.checkStatus('descovery'), data = checkStatus.data;
						var recordIds = "";
						for (var i = 0; i < data.length; i++) {
							recordIds += data[i].recordId + ",";
						}
						;
						if (recordIds == "") {
							layer.msg("请选择数据!");
						} else {
							//layer.alert(JSON.stringify(data));
							$.ajax({
								data : 'json',
								url : '${ctx}/prize/batchDelPrize.do',
								type : "post",
								data : {
									recordIds : recordIds
								},
								success : function(result) {
									if (result.code == '0') {
										layer.msg('批量处理奖品成功!');
//	 									layer.alert(result.msg);
										doSearch();
									} else {
										layer.msg(result.msg);
										doSearch();
									}
								}
							});
						}
					}
		}; 
		 
		$('.demoTable .layui-btn').on('click', function() {
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
		
	function deleteRecord(id){
		layer.confirm('您确认删除该条记录?', {
			btn : [ '确认', '取消' ]
		}, function() {
			
			$.post('${ctx}/prize/deletePeizeId.do', {
				'recordId' : id
			}, function(data) {
				if (data.code==0) {
					layer.msg("删除成功!");
					setTimeout(function(){
						window.location.reload();
					},1000);
				} else {
					layer.msg(data.msg, {
						time : 2500,
						icon : 0
					});
				}
			});
		}, function() {
			layer.closeAll();
		})
	}
	function updateRecord(id){
		layer.confirm('您确认处理该条记录?', {
			btn : [ '确认', '取消' ]
		}, function() {
			$.post('${ctx}/prize/updatePeizeId.do', {
				'recordId' : id,
				'status' : 3,
			}, function(data) {
				if (data) {
					layer.msg("处理成功!");
					setTimeout(function(){
						window.location.reload();
					},1000);
				} else {
					layer.msg('处理失败', {
						time : 2500,
						icon : 0
					});
				}
			});
		}, function() {
			layer.closeAll();
		})
	}
	</script>
</body>
</html>