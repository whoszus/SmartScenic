function showaddr() {
	$(".spot-lists").css("display", "none");
	$(".revise").css("display", "inline");
}


var Baseurl = "http://202.115.161.220:8080/SmartScenic";
//var Baseurl = "http://10.10.4.20";

/*   index.html   */
//景点显示
function showSpot() {
	$.ajax({
		type: "GET",
		url: Baseurl+"/scenic/scenic_findByCityName.action",
		dataType: "jsonp",
		data: {
			cityName: "绵阳"
		},
		success: function(data) {
			$(".panel .table-responsive .table-hover tbody").html("<tr class='point-active' onclick='lineChange("+data[0].scenicSpotNo+",0)'>"+
				"<td>"+1+"</td><td>"+data[0].scenicSpotName+"</td>"+
				"<td><a href='scenicSpot.html'><span class='label label-success'>点击操作</span></a></td>"+
				"<td><span class='badge badge-warning' onclick='linkToPoint("+data[0].scenicSpotNo+")'>点击操作</span></td>"+
				"</tr>");
			for (var i = 1,count=2; i < data.length; i++,count++) {
				$(".panel .table-responsive .table-hover tbody").append("<tr onclick='lineChange("+data[i].scenicSpotNo+","+i+")'>"+
					"<td>"+count+"</td><td>"+data[i].scenicSpotName+"</td>"+
					"<td><a href='scenicSpot.html'><span class='label label-success'>点击操作</span></a></td>"+
					"<td><span class='badge badge-warning' onclick='linkToPoint("+data[i].scenicSpotNo+")'>点击操作</span></td>"+
					"</tr>");
			}
			lineChange(data[0].scenicSpotNo,0);
		},
		error: function(e) {
			console.log(e.responseText);
		}
	});
}

//detection.html点击链接
function linkToPoint(i){
	window.location.href = "detectionPoint.html";
	localStorage.setItem("SpotNo",i);
}

//监测点显示
function showpoint(No){
	var color = ["alert-success","alert-info","alert-warning","alert-danger"];
	$.ajax({
		type:'GET',
		url:Baseurl+'/detection/detection_findByScenicSpotNo.action',
		dataType:'jsonp',
		data:{
			scenicSpotNo:No
		},
		success: function(data) {
			if(data != null){
				$("#noti-box").html("<div class='alert alert-danger'>" + data[0].detectionPointName + "</div>");
				for (var i = 1, count = 0; i < data.length; i++, count++) {
					if (count == 4) {
						count = 0;
					}
					$("#noti-box").append("<div class='alert " + color[0] + "'>" + data[i].detectionPointName + "</div>");
				}
			}else{
				$("#noti-box").html("暂时没有添加任何监测点！");
			}
		},
		error:function(e){
			console.log(e.responseText);
		}
	});
}

//景点行切换
function lineChange(No,i){
	var list = $('.table-hover > tbody > tr');
	list.eq(i).addClass('point-active').siblings().removeClass('point-active');
	if(No != -555){
		showpoint(No);
	}

}

/*  detectionPoint.html  */
//监测点管理
function managePoint(){
	var No = localStorage.getItem("SpotNo");
	$.ajax({
		type:'GET',
		url:Baseurl+'/detection/detection_findByScenicSpotNo.action',
		dataType:'jsonp',
		data:{
			scenicSpotNo:No
		},
		success:function(data){
			if(data != null) {
				$("#pointLists").html("<li class='point-active'>" +
					"<div class='task-checkbox'>" +
					"<span>1</span>" +
					"</div>" +
					"<div class='task-title'>" +
					"<span class='task-title-sp'>" + data[0].detectionPointName + "</span>" +
					"<div class='pull-right hidden-phone'>" +
					"<button class='btn btn-default btn-xs'  onclick='showRevise()'><i class='fa fa-pencil'></i></button>" +
					"<button class='btn btn-default btn-xs' onclick='deletePoint()'><i class='fa fa-times'></i></button>" +
					"</div></div></li>");
				for (var i = 1, count = 2; i < data.length; i++, count++) {
					$("#pointLists").append("<li class=''>" +
						"<div class='task-checkbox'>" +
						"<span>" + count + "</span>" +
						"</div>" +
						"<div class='task-title'>" +
						"<span class='task-title-sp'>" + data[i].detectionPointName + "</span>" +
						"<div class='pull-right hidden-phone'>" +
						"<button class='btn btn-default btn-xs'  onclick='showRevise()'><i class='fa fa-pencil'></i></button>" +
						"<button class='btn btn-default btn-xs' onclick='deletePoint()'><i class='fa fa-times'></i></button>" +
						"</div></div></li>");
				}
			}else{
				$("#pointLists").html("当前景点暂时没有添加任何监测点！");
			}
		},
		error:function(e){
			console.log(e.responseText);
		}
	})
}

/*  scenicSpot.html  */
function manageSpot() {
	var color = ["success","info","warning","danger"];
	$.ajax({
		type: "GET",
		url: Baseurl+"/scenic/scenic_findByCityName.action",
		dataType: "jsonp",
		data: {
			cityName: "绵阳"
		},
		success: function(data) {
			$(".panel .table-responsive .table-hover tbody").html("<tr class='point-active' onclick='lineChange("+data[0].scenicSpotNo+",0)'>"+
				"<td>"+1+"</td><td>"+data[0].scenicSpotName+"</td>"+
				"<td><span class='label label-success info-revise' onclick='revise("+data[0].scenicSpotNo+")'>点击操作</span></td>"+
				"<td><span class='badge badge-success point-manage' onclick='linkToPoint("+data[0].scenicSpotNo+")'>点击操作</span></td>"+
				"<td><span class='badge badge-success info-delete' onclick='spotDelete("+data[0].scenicSpotNo+")'>点击操作</span></td>"+
				"</tr>");
			for (var i = 1,count=2; i < data.length; i++,count++) {
				if (count == 4) {
					count = 0;
				}
				$(".panel .table-responsive .table-hover tbody").append("<tr onclick='lineChange("+data[i].scenicSpotNo+","+i+")'>"+
					"<td>"+count+"</td><td>"+data[i].scenicSpotName+"</td>"+
					"<td><span class='label label-"+color[i]+" info-revise' onclick='revise("+data[0].scenicSpotNo+")'>点击操作</span></td>"+
					"<td><span class='badge badge-"+color[i]+" point-manage' onclick='linkToPoint("+data[i].scenicSpotNo+")'>点击操作</span></td>"+
					"<td><span class='badge badge-"+color[i]+"  info-delete' onclick='spotDelete("+data[0].scenicSpotNo+")'>点击操作</span></td>"+
					"</tr>");
			}
			lineChange(-555,0);
		},
		error: function(e) {
			console.log(e.responseText);
		}
	});
}

//景点信息修改
function revise(No){
	var No = localStorage.getItem("SpotNo");
	$(".spot-list").hide();
	$(".revise").css("display","inline");
}

//监测点信息修改
function spotDelete(No){
	$.ajax({
		type: "GET",
		url: Baseurl+"/scenic/scenic_deleteScenicSpot.action",
		dataType: "jsonp",
		data: {
			scenicSpotNo: No
		},
		success: function(data) {
			if(data.message == "successed"){
				alert("景点删除成功！")
			}else if(data.message == "failed"){
				alert("景点删除失败，请重试！")
			}
		},
		error: function(e) {
			console.log(e.responseText);
		}
	});
}

/*  changeCity.html  */
function showCity(){
	$.ajax({
		type: "GET",
		url: Baseurl+"/areaFind/areaFind_showAllCity.action",
		dataType: "jsonp",
		data: {

		},
		success: function(data) {
			$("#cityTable").html("<tr><td style='color: #27C24C'>"+data[0].provinceName+"</td><td class='class0'></td></tr>");
			if(data[0].cityNames.length != 0){
				for(var i=0;i<data[0].cityNames.length;i++){
					$("#cityTable tr .class0").append("<p onclick='changeCity("+data[0].cityNames[i]+")'>"+data[0].cityNames[i]+"</p>");
				}
			}
			for(var k=1;k<data.length;k++){
				$("#cityTable").append("<tr><td style='color: #27C24C'>"+data[k].provinceName+"</td><td class='class"+k+"'></td></tr>");
				if(data[k].cityNames.length != 0){
					for(var i=0;i<data[k].cityNames.length;i++){
						$("#cityTable tr .class"+k).append("<p onclick='changeCity("+data[0].cityNames[i]+","+data[0].provinceName+")'>"+data[k].cityNames[i]+"</p>");
					}
				}
			}
		},
		error: function(e) {
			console.log(e.responseText);
		}
	});
}

function changeCity(Province,ccc){
	/*if(Province == null){
		localStorage.setItem("address",City);
	}else{
		var addr = Province+City;
		localStorage.setItem("address",addr);
	}*/
	//var addr = localStorage.getItem("address");
	alert(ccc)	;
}