<!DOCTYPE html>
<!-- saved from url=(0051)http://gppz.55778899.cn/stock/home.html?code=600000 -->

<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <title>
行情
</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
    <link href="./resource/css/mui.min.css" rel="stylesheet">
    <link href="./resource/css/person.css" rel="stylesheet">
    
<style type="text/css">
	body{
		background: #f5f5f5 !important;
	}
    .red {
        color: #FC5155;
    }
    .green {
        color: green
    }
</style>

</head>

<body class="quick_body mui-ios mui-ios-10 mui-ios-10-3 mui-ios-11 mui-ios-11-0" style="">

    
		<header class="has_back_top ">
			<span id="guName" data-code="600519" data-fullcode="sh600519">
				
			</span>
	
		</header>

		<div class="flex_nowrap koptions_nav">
			<p class="active">
				<a data-type="0">分时</a>
			</p>
			<p>
				<a data-type="6">日K</a>
			</p>
			<p>
				<a data-type="7">周K</a>
			</p>
			<p>
				<a data-type="8">月K</a>
			</p>
		</div>

		<div class="line_con">
			<div id="chart" style="-webkit-tap-highlight-color: transparent; user-select: none; background: rgb(255, 255, 255); cursor: default; position: relative; overflow: hidden; width: 350px; height: 250px; margin: 0px auto;" _echarts_instance_="ec_1560240282490"><div style="position: relative; overflow: hidden; width: 350px; height: 250px; padding: 0px; margin: 0px; border-width: 0px;"><canvas width="1050" height="750" data-zr-dom-id="zr_0" style="position: absolute; left: 0px; top: 0px; width: 350px; height: 250px; user-select: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); padding: 0px; margin: 0px; border-width: 0px;"></canvas></div><div></div></div>
		</div>

		<div class="stock-price f-right mui-row" id="stock-price">
            <ul class="sell mui-col-xs-6 mui-row clear_fl">
                <li class=""><em>卖⑤</em><b class="red">--</b><i>--</i></li>
                <li class=""><em>卖④</em><b class="red">--</b><i>--</i></li>
                <li class=""><em>卖③</em><b class="red">--</b><i>--</i></li>
                <li class=""><em>卖②</em><b class="red">--</b><i>--</i></li>
                <li class=""><em>卖①</em><b class="red">--</b><i>--</i></li>
            </ul>
            <ul class="buy mui-col-xs-6 mui-row clear_fl">
                <li><em>买①</em><b class="red">--</b><i>--</i></li>
                <li><em>买②</em><b class="red">--</b><i>--</i></li>
                <li><em>买③</em><b class="red">--</b><i>--</i></li>
                <li><em>买④</em><b class="red">--</b><i>--</i></li>
                <li><em>买⑤</em><b class="red">--</b><i>--</i></li>
            </ul>
    	</div>


    



<script src="./resource/js/jquery-2.2.0.min.js"></script>
<script src="./resource/js/mui.min.js"></script>

<script type="text/javascript" src="./resource/js/common.js"></script>
<script type="text/javascript" src="./resource/js/config.js"></script>
<script type="text/javascript" src="./resource/js/echarts.min.js"></script>
<script type="text/javascript">
    $(".add_zixuan").click(function(){
        var _url = '/user/add-optional.html',
            _code = $("#guName").data("code"),
            _oData = {code:_code};
        $ajaxCustom(_url, _oData, function(res){
            if(res.state){ // 登录成功
                $alert("自选添加成功！");
                // $(this).html("取消自选");
            }else{
                $alert(res.info);
            }
        });
    });

    var kLineUrl = '/stock/kline.html';
    var areaLineUrl = "/stock/real.html";
    var refreshUrl = '/stock/inc-real.html';

        
	/**---- 获取跳转参数更新页面内容 ----*/
	 var code = getQueryString("symbol");
	// var url = config.api.base + config.api.getSharesByKeywords;
	// $ajaxCustom(url, {keywords: code}, function(res){
	// 	console.log(res);
	// });
</script>
<script type="text/javascript" src="./resource/js/candle.js"></script>
</body></html>