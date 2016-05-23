//index.html
var Baseurl = "http://42.96.131.73/SmartScenic";

function index() {
	var AQIvalue;
	$(function() {
			$("#side1").hide();
			$("#sidediv").click(function() { //鼠标进入元素时执行第一个函数，离开元素时执行第二个函数
				$("#side2").hide(200);
				$("#side1").toggle(200);
			})
			$("#side2").hide();
			$("#city").click(function() { //鼠标进入元素时执行第一个函数，离开元素时执行第二个函数
				$("#side1").hide(200);
				$("#side2").toggle();
				$(".topImages").css("position", "fixed");
			})
			$("#turn").click(function() {
				$("#side2").hide();
				$(".topImages").css("position", "relative");
			})
			var cityname = "绵阳市";
			localStorage.setItem("cityname","绵阳市");
			Spot(cityname);
			note(cityname);
			recommend(cityname);

			/*所有城市*/
			$.ajax({
				type: "GET",
				url: Baseurl + "/areaFind/areaFind_showAllCity.action",
				dataType: "jsonp",
				data: {

				},
				success: function(data) {
					$("#cityTable").html("<tr><td style='color: #27C24C'>" + data[0].provinceName + "</td><td class='class0'></td></tr>");
					if (data[0].cityNames.length != 0) {
						for (var i = 0; i < data[0].cityNames.length; i++) {
							$("#cityTable tr .class0").append("<p onclick='chooseCity(\"" + data[0].cityNames[i] + "\")'>" + data[0].cityNames[i] + "</p>");
						}
					}
					for (var k = 1; k < data.length; k++) {
						$("#cityTable").append("<tr><td style='color: #27C24C'>" + data[k].provinceName + "</td><td class='class" + k + "'></td></tr>");
						if (data[k].cityNames.length != 0) {
							for (var i = 0; i < data[k].cityNames.length; i++) {
								$("#cityTable tr .class" + k).append("<p onclick='chooseCity(\"" + data[k].cityNames[i] + "\",\"" + data[k].provinceName + "\")'>" + data[k].cityNames[i] + "</p>");
							}
						}
					}
				},
				error: function(e) {
					console.log(e.responseText);
				}
			});

			/*轮播图片*/
			var bxsliderImgs = $(".carousel-inner").find("img");
			var bxsliderItem = $(".carousel-inner").find(".item");
			var image_w = [];
			var image_h = [];
			var min_w = {
				width: 9999,
				height: 9999
			};
			var min_h = {
				width: 9999,
				height: 9999
			};

			var bxsliderImgResize = function() {
				var i = 0;
				/*获取所以图片长宽*/
				bxsliderImgs.each(function(i, item) {
						image_w[i] = item.width;
						image_h[i] = item.height;
					})
					/*获得图片最小分辨率*/
				for (var j = 0; j < image_w.length; j++) {
					if (image_w[i] / image_h[i] > min_w.width / min_h.height) {
						min_h.width = image_w[i];
						min_h.height = image_h[i];
					}
				}

				var b_w = $(window).width();
				var b_h = $(window).height();
				/*如果屏幕宽比高长(大屏幕)*/
				if (b_w > b_h) {
					bxsliderItem.each(function(i, item) {
						var image_h = b_h;
						$(item).width(b_w);
						$(item).height(image_h);
					})
					bxsliderImgs.each(function(i, item) {
						var ratio = 1200 / 800;
						if (b_w / b_h > ratio) {
							var img_w = b_w;
							var img_h = img_w / ratio;
							$(item).width(img_w);
							$(item).height(img_h);
						} else {
							var img_h = b_h;
							var img_w = img_h * ratio;
							$(item).width(img_w);
							$(item).height(img_h);
						}
					})
				}
				/*如果屏幕宽比高短(小屏幕)*/
				else {
					bxsliderItem.each(function(i, item) {
						var image_h = b_w / (min_h.width / min_h.height);
						$(item).width(b_w);
						$(item).height(image_h);
					})
					bxsliderImgs.each(function(i, item) {
						var b_w = $(window).width();
						var b_h = $(window).height();
						var ratio = img_w / img_h;
						if (b_w / b_h > ratio) {
							var img_w = b_w;
							var img_h = img_w / ratio;
							$(item).width(img_w);
							$(item).height(img_h);
						} else {
							var img_h = b_h;
							var img_w = img_h * ratio;
							$(item).width(img_w);
							$(item).height(img_h);
						}
					})
				}
			}
			bxsliderImgResize();
			var resizeTimer = null;
			$(window).on('resize', function() {
				if (resizeTimer) {
					clearTimeout(resizeTimer)
				}
				//通过定时器来减少重绘的频率  
				resizeTimer = setTimeout(function() {
					bxsliderImgResize();
				}, 400);
			});
		})
		/*大图滚动事件*/
	$(window).scroll(function() {
		if ($(window).scrollTop() > 5) {
			$(".header").slideDown(500);
		}
		if ($(window).scrollTop() == 0) {
			$(".header").slideUp(200);
		}

	});

}

/*景点显示*/
function Spot(cityname) {
	var color = ["alert-success", "alert-info", "alert-warning", "alert-danger"];
	$.ajax({
		type: "GET",
		url: Baseurl + "/scenic/scenic_findByCityName.action",
		dataType: "jsonp",
		data: {
			cityName: cityname
		},
		success: function(data) {
			
			if (data.length != 0) {
				$("#noti-box").html("<div class='alert alert-danger'  onclick='address(" + data[0].scenicSpotNo + ",\""+data[0].scenicSpotName+"\")'>" + data[0].scenicSpotName + "</div>");
				for (var i = 1, count = 0; i < data.length; i++, count++) {
					if (count == 4) {
						count = 0;
					}
					$("#noti-box").append("<div class='alert " + color[i] + "' onclick='address(" + data[i].scenicSpotNo + ",\"" + data[i].scenicSpotName + "\")'>" + data[i].scenicSpotName + "</div>");
				}
			} else {
				alert(cityname + "暂时没有添加任何景点信息，系统自动返回上次选择！");
			}
		},
		error: function(e) {
			console.log(e.responseText);
		}
	});
}

//景点选择
function address(No,Name) {
	$("#side1").hide(200);
	
	$.ajax({
		type: "GET",
		url: Baseurl + "/scenic/scenic_userDefined.action",
		dataType: "jsonp",
		data: {
			scenicSpotNo: No
		},
		success: function(data) {
			if(data != null){
				localStorage.setItem("spotNo",No);
				$('#notice').html("<b>" + data.scenicSpotName + "空气质量状况：</b>" + data.ddAirPollutionIndex + "<br/><b>健康建议：</b>" + data.warmReminder);
				$("#sidediv").html("<span class='glyphicon glyphicon-map-marker' aria-hidden='true' id='address'></span>" + data.scenicSpotName);
				AQIvalue = data.ddAqi;
				dash_board();
			}else{
				alert("所选景点暂时没有检测数据，请重新选择！");
			}
		},
		error: function(e) {
			console.log(e.responseText);
		}
	});
	
}

//城市选择
function chooseCity(city) {
	
	$("#side2").hide(200);
	$(".topImages").css("position", "relative");
	var color = ["alert-success", "alert-info", "alert-warning", "alert-danger"];
	$.ajax({
		type: "GET",
		url: Baseurl + "/scenic/scenic_findByCityName.action",
		dataType: "jsonp",
		data: {
			cityName: city
		},
		success: function(data) {
			
			if (data.length != 0) {
				localStorage.setItem("flag", 0);
				localStorage.setItem("cityname",city);
				$("#noti-box").html("<div class='alert alert-danger'  onclick='address(" + data[0].scenicSpotNo + ",\""+data[0].scenicSpotName+"\")'>" + data[0].scenicSpotName + "</div>");
				for (var i = 1, count = 0; i < data.length; i++, count++) {
					if (count == 4) {
						count = 0;
					}
					$("#noti-box").append("<div class='alert " + color[i] + "' onclick='address(" + data[i].scenicSpotNo + ",\"" + data[i].scenicSpotName + "\")'>" + data[i].scenicSpotName + "</div>");
				}
			} else {
				localStorage.setItem("flag", 1);
				alert(city + "暂时没有添加任何景点信息，系统自动返回上次选择！");
			}
		},
		error: function(e) {
			console.log(e.responseText);
		}
	});
	var flag = localStorage.getItem("flag");
	if(flag == 0){
		note(city);
		recommend(city);
	}
}

//排名第一温馨提示
function note(cityname) {
	$.ajax({
		type: "GET",
		url: Baseurl + "/scenic/scenic_choiceDefault.action",
		dataType: "jsonp",
		data: {
			cityName: cityname
		},
		success: function(data) {
			localStorage.setItem("spotNo",data.scenicSpotNo);
			$('#notice').html("<b>" + data.scenicSpotName + "空气质量状况：</b>" + data.ddAirPollutionIndex + "<br/><b>健康建议：</b>" + data.warmReminder);
			$("#sidediv").html("<span class='glyphicon glyphicon-map-marker' aria-hidden='true' id='address'></span>" + data.scenicSpotName);
			AQIvalue = data.ddAqi;
			dash_board();
		},
		error: function(e) {
			console.log(e.responseText);
		}
	});
}

//仪表盘显示
function dash_board() {
	var temp = AQIvalue.toFixed(0);
	var temp =parseInt(temp);
	
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
						value: temp,
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

//景点推介
function recommend(cityname) {

	$.ajax({
		type: "GET",
		url: Baseurl + "/scenic/scenic_spotRecommend.action",
		dataType: "jsonp",
		data: {
			cityName: cityname
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
		
		$("#side").hide();
		$("#sidediv").hover(function() { //鼠标进入元素时执行第一个函数，离开元素时执行第二个函数
			$("#side").slideDown(200).end();
		}, function() {
			$("#side").slideUp(200);
		});
		/*//景点显示$.ajax({
			type: "GET",
			url: Baseurl + "/scenic/scenic_findByCityName.action",
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
		});*/

		//各种指数显示
		var num = $('.list-group'); //各种指数显示div
		var city = localStorage.getItem("cityname").slice(0,2);
		$.ajax({
			type: "GET",
			url: Baseurl + "/weather/weather_showZhishu.action",
			dataType: "jsonp",
			data: {
				cityName: city
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
		var No = localStorage.getItem("spotNo");
		var $index = $('#index > ul li');
		select = $('#index').find('.active').val(); //标签切换
		$index.click(function() {
			$(this).addClass('active').siblings().removeClass('active');
			select = $('#index').find('.active').val();
			AQIandComf();
		});
		$.ajax({
			type: "GET",
			url: Baseurl + "/dayData/dayData_showAqiAndComf.action",
			dataType: "jsonp",
			data: {
				scenicSpotNo: No
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
			url: Baseurl + "/dayData/dayData_showOtherIndex.action",
			dataType: "jsonp",
			data: {
				scenicSpotNo: No
			},
			success: function(data) {
				console.log(data);
				index_time = data.time;
				index_date = data.temperature;
				Index = "温度";
				index_id = "#temperature";
				text = "温度 (°C)";
				unit = "°C";
				otherindex();
				index_date = data.humidity;
				Index = "湿度";
				index_id = "#humidity";
				text = "湿度";
				unit = "%";
				otherindex();
				
				index_date = data.pm25;
				Index = "pm2.5";
				index_id = "#pm25";
				text = "pm2.5(ug/m3)";
				unit = "ug/m3";
				otherindex();
				index_date = data.pm10;
				Index = "pm10";
				index_id = "#pm10";
				text = "pm10 (ug/m3)";
				unit = "ug/m3";
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
            var year = d.getFullYear(); //获取当前日期的年份
            var month = d.getMonth() + 1; //获取当前日期的月份
            var day = d.getDate(); //获取当前日期的日
            var days = new Date(year, month, 0);
            days = days.getDate(); //获取当前日期中月的天数
            var year2 = year;
            var month2 = parseInt(month) - 1;
            if (month2 == 0) {//如果是1月份，则取上一年的12月份
                year2 = parseInt(year2) - 1;
                month2 = 12;
            }
            var day2 = day;
            var days2 = new Date(year2, month2, 0);
            days2 = days2.getDate();
            if (day2 > days2) {//如果原来日期大于上一月的日期，则取当月的最大日期。比如3月的30日，在2月中没有30
                day2 = days2;
            }
            if (month2 < 10) {
                month2 = '0' + month2;//月份填补成2位。
            }
            var t2 = year2 + '-' + month2 + '-' + day2;
//          alert(t2);/*时间测试*/
        

		if (select == 0) {
			change_date = aqi;
			Index = "空气质量指数";
		} else {
			change_date = Comf;
			Index = "舒适度";
		}
		Highcharts.setOptions({
        colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4']
    });
		
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
				pointStart: Date.UTC(year2, month2-1 , day2-1),
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
		switch (select_time) {
			case 0:
				switch (select_index) {
					case 0:
						urld = Baseurl + "/sort/sort_yerterdaySortByAqi.action";
						break;
					case 1:
						urld = Baseurl + "/sort/sort_sortYesterdayBycomfertable.action";
						break;
					default:
						urld = Baseurl + "/sort/sort_sortYesterdayByComprehensive.action";
						break;
				}
				break;
			case 1:
				switch (select_index) {
					case 0:
						urld = Baseurl + "/sort/sort_weekSortByAqi.action";
						break;
					case 1:
						urld = Baseurl + "/sort/sort_weekSortBYComf.action";
						break;
					default:
						urld = Baseurl + "/sort/sort_weekSortBYComprehensive.action";
						break;
				}
				break;
			default:
				switch (select_index) {
					case 0:
						urld = Baseurl + "/sort/sort_monthSortByAqi.action";
						break;
					case 1:
						urld = Baseurl + "/sort/sort_monthSortBYComf.action";
						break;
					default:
						urld = Baseurl + "/sort/sort_monthSortBYComprehensive.action";
						break;
				}
				break;
		}
		var temp = $(".rant_list");
		var city = localStorage.getItem("cityname");
		$.ajax({
			type: "GET",
			url: urld,
			dataType: "jsonp",
			data: {
				cityName: city
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
}

//introduce.html
function introduce() {
	wow = new WOW({}).init();
	var No = localStorage.getItem("spotNo");
	var city = localStorage.getItem("cityname").slice(0,2);
	$(function() {
		//景区数据显示
		var temp = $(".rant_list");
		$.ajax({
			type: "GET",
			url: Baseurl + "/scenic/scenic_showSpotDatas.action",
			dataType: "jsonp",
			data: {
				scenicSpotNo: No
			},
			success: function(data) {
				if(data != null){
					for (var i = 0; i < data.datas.length; i++) {
						$(temp[0]).append("<div class='list-group-item'><p class='text_center'>" + data.datas[i].time + "</p></div>");
						$(temp[1]).append("<div class='list-group-item'><p class='text_center'>" + data.datas[i].aqi + "</p></div>");
						$(temp[2]).append("<div class='list-group-item'><p class='text_center'>" + data.datas[i].comf + "</p></div>");
						$(temp[3]).append("<div class='list-group-item'><p class='text_center'>" + data.datas[i].pm25 + "</p></div>");
					}
				}
			},
			error: function(e) {
				console.log(e.responseText);
			}
		})

		//天气预报
		$.ajax({
			type: "GET",
			url: Baseurl + "/weather/weather_showForecast.action",
			dataType: "jsonp",
			data: {
				cityName: city
			},
			success: function(data) {
				for (var i = 0; i < data.length; i++) {
					console.log(data[i]);

					//天气预报显示：日期、图片、温度/风级
					$('#weatherForecast').append("<div class='col-xs-2'><p class='text_center'>" + data[i].date + "</p>" + "<img src='img/weather/" + data[i].weather + ".jpg' style='width: 100%'>" + "<p class='text_center'>" + data[i].temp + "<br />" + data[i].wind_force + "</p></div>");
				}
			},
			error: function(e) {
				console.log(e.responseText);
			}
		});
	})
}