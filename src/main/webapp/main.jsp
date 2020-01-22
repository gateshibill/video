<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
<link rel="stylesheet"
	href="${ctx}/plugins/font-awesome/css/font-awesome.min.css" media="all">
<style>
.info-box {
	height: 85px;
	background-color: white;
	background-color: #ecf0f5;
}

.info-box .info-box-icon {
	border-top-left-radius: 2px;
	border-top-right-radius: 0;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 2px;
	display: block;
	float: left;
	height: 85px;
	width: 85px;
	text-align: center;
	font-size: 45px;
	line-height: 85px;
	background: rgba(0, 0, 0, 0.2);
}

.info-box .info-box-content {
	padding: 5px 10px;
	margin-left: 85px;
}

.info-box .info-box-content .info-box-text {
	display: block;
	font-size: 16px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	text-transform: uppercase;
}

.info-box .info-box-content .info-box-number {
	display: block;
	font-weight: bold;
	font-size: 25px;
}

.major {
	font-weight: 10px;
	color: #01AAED;
}



.main .layui-row {
	margin: 10px 0;
}

.info-box i {
	font-size: 1em;
	color: #fff;
}

.tmiddle {
	text-align: center;
	padding-top: 15px !important;
	cursor: pointer
}
.layui-fluid{
	padding:0px;
}
</style>
</head>
<body>
	<div class="layui-fluid main">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md2">
				<div class="info-box">
					<span class="info-box-icon"
						style="background-color: #54ade8 !important; color: white;">
						<i class="layui-icon">&#xe611;</i>
					</span>
					<div class="info-box-content tmiddle">
						<span class="info-box-number">${channelCount>0?channelCount:0}</span> <span class="info-box-text">频道总数</span>
					</div>
				</div>
			</div>
			<div class="layui-col-md2">
				<div class="info-box">
					<span class="info-box-icon"
						style="background-color: #FF5722 !important; color: white;">
						<i class="layui-icon">&#xe612;</i>
					</span>
					<div class="info-box-content tmiddle">
						<span class="info-box-number">${vodCount>0?vodCount:0}</span> <span
							class="info-box-text">视频总数</span>
					</div>
				</div>
			</div>
			<div class="layui-col-md2">
				<div class="info-box">
					<span class="info-box-icon"
						style="background-color: #009688 !important; color: white;">
						<i class="layui-icon">&#xe613;</i>
					</span>
					<div class="info-box-content tmiddle">
						<span class="info-box-number">${userCount>0?userCount:0}</span> <span
							class="info-box-text">用户总数</span>
					</div>
				</div>
			</div>
			<c:if test="${loginPlat == null || loginPlat == ''}">
			<div class="layui-col-md2">
				<div class="info-box">
					<span class="info-box-icon"
						style="background-color: #5FB878 !important; color: white;">
						<i class="layui-icon">&#xe630;</i>
					</span>
					<div class="info-box-content tmiddle">
						<span class="info-box-number">${anchorCount}</span> <span
							class="info-box-text">直播总数</span>
					</div>
				</div>
			</div>
			</c:if>
			<div class="layui-col-md2">
				<div class="info-box">
					<span class="info-box-icon"
						style="background-color: #F7B824 !important; color: white;">
						<i class="layui-icon">&#xe637;</i>
					</span>
					<div class="info-box-content tmiddle">
						<span class="info-box-number">${picCount>0?picCount:0}</span> <span
							class="info-box-text">图片总数</span>
					</div>
				</div>
			</div>
			<div class="layui-col-md2">
				<div class="info-box">
					<span class="info-box-icon"
						style="background-color: #2F4056 !important; color: white;">
						<i class="layui-icon">&#xe62a;</i>
					</span>
					<div class="info-box-content tmiddle">
						<span class="info-box-number">${newsCount>0?newsCount:0}</span> <span
							class="info-box-text">文章总数</span>
					</div>
				</div>
			</div>
			<c:if test="${loginPlat != null && loginPlat != ''}">
			<div class="layui-col-md2">
				<div class="info-box">
					<span class="info-box-icon"
						style="background-color: #5FB878 !important; color: white;">
						<i class="layui-icon">&#xe630;</i>
					</span>
					<div class="info-box-content tmiddle">
						<span class="info-box-number">${newsCount!=''?newsCount:0}</span> <span
							class="info-box-text">总金额</span>
					</div>
				</div>
			</div>
			</c:if>
		</div>
	</div>

<c:if test="${loginPlat == null || loginPlat == ''}">
	<div class="layui-fluid main">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md6">
				<div class="sysNotice col">
					<blockquote class="layui-elem-quote title">基础统计</blockquote>
					<div class="layui-elem-quote layui-quote-nm"
						style="text-align: center">图形统计区域</div>
				</div>
			</div>
			<div class="layui-col-md6">
				<blockquote class="layui-elem-quote title">电视频道排名</blockquote>
				<div class="layui-elem-quote layui-quote-nm">
					<table class="layui-table">
						<tbody>
							<tr>
								<td class="version1">1.CCTV5</td>
							</tr>
							<tr>
								<td class="version1">2.CCTV5+</td>
							</tr>
							<tr>
								<td class="version1">3.ESPN</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</c:if>
</body>
</html>