//index.html
var Baseurl = "http://10.10.4.20";
function index() {
	var AQIvalue;
	$(function() {
		$("#side").hide();
		$("#sidediv").hover(function() { //鼠标进入元素时执行第一个函数，离开元素时执行第二个函数
			$("#side").slideDown(200).end();
		}, function() {
			$("#side").slideUp(200);
		})

		//温馨提示
		$.ajax({
			type: "GET",
			url: Baseurl+"/scenic/scenic_choiceDefault.action",
			dataType: "jsonp",
			data: {
				cityName: "绵阳市"
			},
			success: function(data) {
				console.log(data[0]);
				$('#notice').html("<b>" + data.scenicSpotName + "空气质量状况：</b>" + data.ddAirPollutionIndex + "<br/><b>健康建议：</b>" + data.warmReminder);
				AQIvalue = data.ddAqi;
				dash_board();
			},
			error: function(e) {
				console.log(e.responseText);
			}
		});

		//景点显示
		$.ajax({
			type: "GET",
			url: Baseurl+"/scenic/scenic_findByCityName.action",
			dataType: "jsonp",
			data: {
				cityName: "绵阳市"
			},
			success: function(data) {
				for (var i = 0; i < data.length; i++) {
					console.log(data[i]);
					$("#side").append("<li><a style='cursor:pointer'>" + data[i].scenicSpotName + "</a></li>");
				}
			},
			error: function(e) {
				console.log(e.responseText);
			}
		});

		//景点推介
		$.ajax({
			type: "GET",
			url: Baseurl+"/scenic/scenic_spotRecommend.action",
			dataType: "jsonp",
			data: {
				cityName: "绵阳市"
			},
			success: function(data) {
				for (var i = 0; i < data.length; i++) {
					console.log(data[i]);
					$('#marketing').append("<article class='col-md-4 isotopeItem webdesign'><div class='portfolio-item'>" +
						"<div class='wow rotateInUpLeft' data-animation-delay='4.8s'>" +
						"<img src='img/points/xianhaihu.jpg' alt='welcome'/></div><div class='portfolio-desc align-center'>" +
						"<div class='folio-info'><h5><a href='xianhaihu.html'>" + data[i].scenicSpotShortInfo + "</a></h5>" +
						"<a href='xianhaihu.html' class='fancybox'><i class='fa fa-external-link fa-2x'></i></a>" +
						"</div></div></div> </article>");
				}
			},
			error: function(e) {
				console.log(e.responseText);
			}
		});

	});
	//仪表盘显示
	function dash_board() {
		require.config({
			paths: {
				echarts: 'http://echarts.baidu.com/build/dist'
			}
		});
		require(
			[
				'echarts',
				'echarts/chart/bar',
				'echarts/chart/line',
				'echarts/chart/gauge'
			],
			function(ec) {
				var myChart = ec.init(document.getElementById('dash_board'));
				option = {
					tooltip: {
						formatter: "{a} <br/>{b} : {c}"
					},
					toolbox: {
						show: true
					},
					series: [{
						show: true,
						startAngle: 190,
						endAngle: -10,
						radius: [0, '85%'],
						min: 0,
						max: 500,
						splitNumber: 10,
						name: 'AQI参数',
						type: 'gauge',
						detail: {
							formatter: '{value}'
						},
						data: [{
							value: AQIvalue,
							name: 'AQI'
						}],
						axisLine: { // 坐标轴线
							show: true, // 默认显示，属性show控制显示与否
							lineStyle: { // 属性lineStyle控制线条样式
								color: [
									[0.1, '#008000'],
									[0.2, '#FFFF00'],
									[0.3, '#FFA500'],
									[0.4, '#FF0000'],
									[0.6, '#800080'],
									[1, '#7E2004']
								],
								width: 35
							}
						},
						axisLabel: { // 坐标轴文本标签，详见axis.axisLabel
							show: true,
							formatter: function(v) {
								switch (v + '') {
									default: return '';
								}
							},
							textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
								color: '#fff'
							}
						},
					}]
				};
				myChart.setOption(option);
			}
		);
	}
}

//change.html
function change() {
	wow = new WOW({

		})
		.init();

	var change_date, select;
	var aqi, Comf;

	var index_date, index_time, Index, text, index_id; //
	$(function() {
		//景点显示
		$("#side").hide();
		$("#sidediv").hover(function() { //鼠标进入元素时执行第一个函数，离开元素时执行第二个函数
			$("#side").slideDown(200).end();
		}, function() {
			$("#side").slideUp(200);
		});
		$.ajax({
			type: "GET",
			url: Baseurl+"/scenic/scenic_findByCityName.action",
			dataType: "jsonp",
			data: {
				city: "绵阳市"
			},
			success: function(data) {
				for (var i = 0; i < data.length; i++) {
					console.log(data[i]);
					$("#side").append("<li class='list'><a style='cursor:pointer'>" + data[i].scenicSpotName + "</a></li>");
				}
			},
			error: function(e) {
				console.log(e.responseText);
			}
		});

		//各种指数显示
		var num = $('.list-group'); //各种指数显示div
		$.ajax({
			type: "GET",
			url: Baseurl+"/weather/weather_showZhishu.action",
			dataType: "jsonp",
			data: {
				cityName: "绵阳"
			},
			success: function(data) {
				for (var i = 0, count = -1; i < data.length; i++) {
					if (i % 2 == 0)
						count++;
					console.log(data[i]);
					$(num[count]).append("<div class='list-group-item active'>" +
						"<h4 class='list-group-item-heading' style='line-height: 200%'>" + data[i].name + ":" + data[i].value + "</h4></div><div class='list-group-item' style='margin-bottom: 30px'>" +
						"<p class='list-group-item-text'>" + data[i].detail + "</p></div>");
				}
			},
			error: function(e) {
				console.log(e.responseText);
			}
		});

		//AQI和舒适度切换
		var $index = $('#index > ul li');
		select = $('#index').find('.active').val(); //标签切换
		$index.click(function() {
			$(this).addClass('active').siblings().removeClass('active');
			select = $('#index').find('.active').val();
			AQIandComf();
		});
		$.ajax({
			type: "GET",
			url: Baseurl+"/dayData/dayData_showAqiAndComf.action",
			dataType: "jsonp",
			data: {
				scenicSpotNo: 1
			},
			success: function(data) {
				console.log(data[0]);
				aqi = data.aqi;
				Comf = data.comf;
				AQIandComf();
			},
			error: function(e) {
				console.log(e.responseText);
			}
		});

		//其他折线图:温度，湿度，紫外线，含氧量
		var num = $('.list-group'); //各种指数显示div
		$.ajax({
			type: "GET",
			url: Baseurl+"/dayData/dayData_showOtherIndex.action",
			dataType: "jsonp",
			data: {
				scenicSpotNo: 1
			},
			success: function(data) {
				console.log(data);
				index_time = data.time;
				index_date = data.temperature;
				Index = "湿度";
				index_id = "#humidity";
				text = "湿度 (%rh)";
				unit = "%rh";
				otherindex();
				index_date = data.humidity;
				Index = "温度";
				index_id = "#temperature";
				text = "温度 (°C)";
				unit = "°C";
				otherindex();
				index_date = data.ultraviolet;
				Index = "紫外线";
				index_id = "#ultraviolet";
				text = "紫外线";
				unit = "级";
				otherindex();
				index_date = data.oxygenContent;
				Index = "含氧量";
				index_id = "#oxygenContent";
				text = "含氧量 (kPa)";
				unit = "kPa";
				otherindex();
			},
			error: function(e) {
				console.log(e.responseText);
			}
		});
	});

	//AQI和舒适度显示表
	function AQIandComf() {
		var d = new Date()
		var vYear = d.getFullYear()
		var vMon = d.getMonth() + 1
		var vDay = d.getDate()
		if (vDay > 30) {
			vDay = vDay - 30;
		} else {
			vDay = 30 - vDay;
			vMon--;
			vDay = 30 - vDay;
		}

		if (select == 0) {
			change_date = aqi;
			Index = "空气质量指数";
		} else {
			change_date = Comf;
			Index = "舒适度";
		}
		$('#AQI').highcharts({
			chart: {
				zoomType: 'x',
				spacingRight: 20
			},
			title: {
				text: Index + '变化折线图'
			},
			xAxis: {
				type: 'datetime',
				maxZoom: 14 * 24 * 3600000, // fourteen days
				title: {
					text: null
				}
			},
			yAxis: {
				title: {
					text: Index
				}
			},
			tooltip: {
				shared: true
			},
			legend: {
				enabled: false
			},
			plotOptions: {
				area: {
					fillColor: {
						linearGradient: {
							x1: 0,
							y1: 0,
							x2: 0,
							y2: 1
						},
						stops: [
							[0, Highcharts.getOptions().colors[0]],
							[1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
						]
					},
					lineWidth: 1,
					marker: {
						enabled: false
					},
					shadow: false,
					states: {
						hover: {
							lineWidth: 1
						}
					},
					threshold: null
				}
			},

			series: [{
				type: 'area',
				name: Index,
				pointInterval: 24 * 3600 * 1000,
				pointStart: Date.UTC(vYear, vMon, vDay),
				data: change_date
			}]

		})
	}

	//其他指数
	function otherindex() {
		$(index_id).highcharts({
			title: {
				text: Index + '变化折线图'
			},
			xAxis: {
				categories: index_time
			},
			yAxis: {
				title: {
					text: text
				},
				plotLines: [{
					value: 0,
					width: 1,
					color: '#808080'
				}]
			},
			tooltip: {
				valueSuffix: unit //单位
			},
			series: [{
				name: Index,
				data: index_date
			}]
		});
	}

}

//rant.html
function rant() {
	wow = new WOW({})
		.init();
	$(function() {
		$("#side").hide();
		$("#sidediv").hover(function() { //鼠标进入元素时执行第一个函数，离开元素时执行第二个函数
			$("#side").slideDown(200).end();
		}, function() {
			$("#side").slideUp(200);
		});
	});
	var select_time = 1;
	var select_index = 0;
	var urld;
	var $index = $('#index > ul li');
	$index.click(function() {
		$(this).addClass('active').siblings().removeClass('active'); //点击添加active样式，同级元素移除active样式
		select_index = $(this).val();
		response();
	});
	var $time = $('#time > ul li');
	$time.click(function() {
		$(this).addClass('active').siblings().removeClass('active'); //点击添加active样式，同级元素移除active样式
		select_time = $(this).val();
		response();
	});
	$(function() {
		response();
	});

	function response() {
		console.log(select_time);
		switch (select_time) {
			case 0:
				switch (select_index) {
					case 0:
						urld = Baseurl+"/sort/sort_yerterdaySortByAqi.action";
						break;
					case 1:
						urld = Baseurl+"/sort/sort_sortYesterdayBycomfertable.action";
						break;
					default:
						urld = Baseurl+"/sort/sort_sortYesterdayByComprehensive.action";
						break;
				}
				break;
			case 1:
				switch (select_index) {
					case 0:
						urld = Baseurl+"/sort/sort_weekSortByAqi.action";
						break;
					case 1:
						urld = Baseurl+"/sort/sort_weekSortBYComf.action";
						break;
					default:
						urld = Baseurl+"/sort/sort_weekSortBYComprehensive.action";
						break;
				}
				break;
			default:
				switch (select_index) {
					case 0:
						urld = Baseurl+"/sort/sort_monthSortByAqi.action";
						break;
					case 1:
						urld = Baseurl+"/sort/sort_monthSortBYComf.action";
						break;
					default:
						urld = Baseurl+"/sort/sort_monthSortBYComprehensive.action";
						break;
				}
				break;
		}
		var temp = $(".rant_list");
		$.ajax({
			type: "GET",
			url: urld,
			dataType: "jsonp",
			data: {
				cityName: "绵阳市"
			},
			success: function(data) {
				console.log(data[0]);
				$(temp[0]).html("<div class='list-group-item'><p class='rant_detail'>" + 1 + "</p> </div>");
				$(temp[1]).html("<div class='list-group-item'><p class='rant_detail'>" + data[0].airQuality + "</p> </div>");
				$(temp[2]).html("<div class='list-group-item'><p class='rant_detail'>" + data[0].scenicSpotName + "</p> </div>");
				$(temp[3]).html("<div class='list-group-item'><p class='rant_detail'>" + data[0].cityName + "</p> </div>");
				$(temp[4]).html("<div class='list-group-item'><p class='rant_detail'>" + data[0].Aqi + "</p> </div>");
				$(temp[5]).html("<div class='list-group-item'><p class='rant_detail'>" + data[0].ComfortLevel + "</p> </div>");
				for (var i = 1, count = 2; i < data.length; i++, count++) {
					console.log(data[i]);
					$(temp[0]).append("<div class='list-group-item'><p class='rant_detail'>" + count + "</p> </div>");
					$(temp[1]).append("<div class='list-group-item'><p class='rant_detail'>" + data[i].airQuality + "</p> </div>");
					$(temp[2]).append("<div class='list-group-item'><p class='rant_detail'>" + data[i].scenicSpotName + "</p> </div>");
					$(temp[3]).append("<div class='list-group-item'><p class='rant_detail'>" + data[i].cityName + "</p> </div>");
					$(temp[4]).append("<div class='list-group-item'><p class='rant_detail'>" + data[i].Aqi + "</p> </div>");
					$(temp[5]).append("<div class='list-group-item'><p class='rant_detail'>" + data[i].ComfortLevel + "</p> </div>");
				}
			},
			error: function(e) {
				console.log(e.responseText);
			}
		})
	}

	$(function() {
		//地点切换
		$.ajax({
			type: "GET",
			url: Baseurl+"/scenic/scenic_findByCityName.action",
			dataType: "jsonp",
			data: {
				city: "绵阳市"
			},
			success: function(data) {
				for (var i = 0; i < data.length; i++) {
					console.log(data[i]);
					$("#side").append("<li><a style='cursor:pointer'>" + data[i].scenicSpotName + "</a></li>");
				}
			},
			error: function(e) {
				console.log(e.responseText);
			}
		});
	})
}

//introduce.html
function introduce() {
	wow = new WOW({}).init();
		
	$(function() {
		$("#side").hide();
		$("#sidediv").hover(function() { //鼠标进入元素时执行第一个函数，离开元素时执行第二个函数
			$("#side").slideDown(200).end();
		}, function() {
			$("#side").slideUp(200);
		});
	});

	$(function() {
		//景区数据显示
		var temp = $(".rant_list");
		$.ajax({
			type: "GET",
			url: Baseurl+"/scenic/scenic_showSpotDatas.action",
			dataType: "jsonp",
			data: {
				scenicSpotNo: 1
			},
			success: function(data) {
				console.log(data);
				for (var i = 0; i < data.datas.length; i++) {
					$(temp[0]).append("<div class='list-group-item'><p class='text_center'>" + data.datas[i].time + "</p></div>");
					$(temp[1]).append("<div class='list-group-item'><p class='text_center'>" + data.datas[i].aqi + "</p></div>");
					$(temp[2]).append("<div class='list-group-item'><p class='text_center'>" + data.datas[i].comf + "</p></div>");
					$(temp[3]).append("<div class='list-group-item'><p class='text_center'>" + data.datas[i].pm25 + "</p></div>");
				}
			},
			error: function(e) {
				console.log(e.responseText);
			}
		})

		//天气预报
		$.ajax({
			type: "GET",
			url: Baseurl+"/weather/weather_showForecast.action",
			dataType: "jsonp",
			data: {
				cityName: "绵阳"
			},
			success: function(data) {
				for (var i = 0; i < data.length; i++) {
					console.log(data[i]);

					//天气预报显示：日期、图片、温度/风级
					$('#weatherForecast').append("<div class='col-xs-2'><p class='text_center'>" + data[i].date + "</p>" + "<img src='img/weather/" + data[i].weather + ".png' style='width: 100%'>" + "<p class='text_center'>" + data[i].temp + "<br />" + data[i].wind_force + "</p></div>");
				}
			},
			error: function(e) {
				console.log(e.responseText);
			}
		});
	})
}