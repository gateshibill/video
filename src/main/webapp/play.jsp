<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <meta charset="UTF-8">
    <title>前端播放m3u8格式视频</title>
    <link href="https://vjs.zencdn.net/7.4.1/video-js.css" rel="stylesheet">
    <script src='https://vjs.zencdn.net/7.4.1/video.js'></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/videojs-contrib-hls/5.15.0/videojs-contrib-hls.min.js" type="text/javascript"></script>
    <!-- videojs-contrib-hls 用于在电脑端播放 如果只需手机播放可以不引入 -->
 
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
    <%   
        String url=request.getParameter("url");   
     %>  
    <style>
        .video-js .vjs-tech {position: relative !important;}
    </style>
    <div>
        <video id="myVideo" class="video-js vjs-default-skin vjs-big-play-centered" controls preload="auto" data-setup='{}' style='width: 100%;height: auto'>
            <source id="source" src=<%=url%> type="application/x-mpegURL"></source>
        </video>
    </div>
    <div class="qiehuan" style="width:100px;height: 100px;background: red;margin:0 auto;line-height: 100px;color:#fff;text-align: center">切换视频</div>
</body>
 
<script>
    // videojs 简单使用
    var myVideo = videojs('myVideo', {
        bigPlayButton: true,
        textTrackDisplay: false,
        posterImage: false,
        errorDisplay: false,
    })
    myVideo.play()
    var changeVideo = function (vdoSrc) {
        if (/\.m3u8$/.test(vdoSrc)) { //判断视频源是否是m3u8的格式
            myVideo.src({
                src: vdoSrc,
                type: 'application/x-mpegURL' //在重新添加视频源的时候需要给新的type的值
            })
        } else {
            myVideo.src(vdoSrc)
        }
        myVideo.load();
        myVideo.play();
    }
    var src = 'http://1252093142.vod2.myqcloud.com/4704461fvodcq1252093142/f865d8a05285890787810776469/playlist.f3.m3u8';
    document.querySelector('.qiehuan').addEventListener('click', function () {
        changeVideo(src);
    })
</script>
</html>
