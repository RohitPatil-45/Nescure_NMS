window.onload = function() {
	showIPAddressName();

}

function showIPAddressName() {

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/nodeDashboard/getIPAddress";

	$
			.ajax({
				type : 'GET',
				url : serviceUrl,
				success : function(data) {
					var html = " <select class='form-control' tabindex='1' id='branch' name='branch' onchange='showInterfaceName()'>";
					html += data + "</select>";

					document.getElementById("fromIpAddress").innerHTML = html;
					document.getElementById("toIpAddress").innerHTML = html;
					// multiselectBranchName();
				}
			});

}
function showInterfaceNameFromIP() {
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/nodeDashboard/getInterfaceOnIPAddress";
	$
			.ajax({
				type : 'POST',
				url : serviceUrl,
				data : 'ipAddress=' + $("#fromIpAddress").val(),
				success : function(data) {
					var html = " <select class='form-control' tabindex='2' id='fromInterface' name='fromInterface'>";
					html += data + "</select>";

					document.getElementById("fromInterface").innerHTML = html;
					// document.getElementById("toInterface").innerHTML = html;
					// multiselectDeviceName();
				}
			});
}
function showInterfaceNameToIP() {
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/nodeDashboard/getInterfaceOnIPAddress";
	$
			.ajax({
				type : 'POST',
				url : serviceUrl,
				data : 'ipAddress=' + $("#toIpAddress").val(),
				success : function(data) {
					var html = " <select class='form-control' tabindex='2' id='toInterface' name='toInterface'>";
					html += data + "</select>";

					// document.getElementById("fromInterface").innerHTML =
					// html;
					document.getElementById("toInterface").innerHTML = html;
					// multiselectDeviceName();
				}
			});
}

function editTopologyPageCall() {

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];

	var serviceUrl23 = base_url + "/nodeDashboard/dynamicNetworkTopologyEdit";
	// alert(serviceUrl23)
	window.location.href = serviceUrl23;

}

function addLink() {
	// getNodePositions("AddLink");
	var fromIp = jQuery("#fromIpAddress").val();
	var toIp = jQuery("#toIpAddress").val();
	var fromInt = jQuery("#fromInterface").val();
	var toInt = jQuery("#toInterface").val();

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl1 = base_url + "/nodeDashboard/NetworkTopologyInsertInterface";
	$.ajax({
		type : 'GET',
		url : serviceUrl1,
		data : 'fromIpAddress=' + $("#fromIpAddress").val() 
		+ '&toIpAddress='+ $("#toIpAddress").val()
		+ '&fromInterface='+ $("#fromInterface").val()
		+ '&toInterface='+ $("#toInterface").val(),
		// dataType : 'json',
		success : function(data) {
			// jQuery.ajax({
			// type : "POST",
			// url : "NetworkTopologyInsertInterface",
			// data : {
			// fromIpaddress : fromIp,
			// toIpaddress : toIp,
			// fromInterface : fromInt,
			// toInterface : toInt
			// },
			// success : function(msg) {
			reDraw();
		}
	});

}

function fromInterface(ipaddress) {

	jQuery.ajax({
		type : "POST",
		url : "NetworkTopologyfromInterfaceList",
		data : {
			ipaddress : ipaddress
		},
		success : function(msg) {
			var jsonNodeValues = JSON.parse(msg);
			$("#fromInterface").empty();
			var selectOption = $('<option/>');
			selectOption.text("Please select");
			selectOption.attr('value', "Please select");
			$('#fromInterface').append(selectOption);

			var length = jsonNodeValues.length;
			for (var j = 0; j < length; j++) {
				var newOption = $('<option/>');
				newOption.text(jsonNodeValues[j].value);
				console.log(jsonNodeValues[j].value);
				newOption.attr('value', jsonNodeValues[j].name);
				$('#fromInterface').append(newOption);
				// $('#fromInterface').append(newOption);
			}
		}
	});
}

function toInterface(ipaddress) {

	jQuery.ajax({
		type : "POST",
		url : "NetworkTopologyfromInterfaceList",
		data : {
			ipaddress : ipaddress
		},
		success : function(msg) {
			var jsonNodeValues = JSON.parse(msg);
			var length = jsonNodeValues.length;
			$("#toInterface").empty();

			var selectOption = $('<option/>');
			selectOption.text("Please select");
			selectOption.attr('value', "Please select");
			$('#toInterface').append(selectOption);
			for (var j = 0; j < length; j++) {
				var newOption = $('<option/>');
				newOption.text(jsonNodeValues[j].name);
				newOption.attr('value', jsonNodeValues[j].name);
				$('#toInterface').append(newOption);
				// $('#fromInterface').append(newOption);
			}

		}
	});
}
function getSNMPjsp(selectedNodeName) {

	console.log("infun" + selectedNodeName);
	// var selectedNodeName1= document.getElementById("selectedNodeName");
	//
	// var selectedNodeName1 = document.getElementById("selectedNode");
	// console.log("after"+selectedNodeName1);
	// window.location.href="DesktopDetail.jsp?selectedNodeName1="+selectedNodeName;//
	// this open on same command
}

var popupMenu = undefined;
var selectedNode = undefined;
var DIR = 'img/soft-scraps-icons/';
var jsonNodeValues;
var jsonNodeMap;
var items = '<s:property value="%{data.jsonArray}" />';
var selectedNodeName = undefined;
var nodes = null;
var edges = null;
var network = null;
var data = null;
var selectedNodeName = null;
var nodeType = null;
var refresh = 30000;
var i = 0;
var groupName = null;
groupName = '<%=groupName%>';

$.ajax({
	url : "NetworkTopologyEditMode",
	type : "post",
	data : {
		group_name : groupName
	},
	// data: data,
	// dataType: 'json',
	// contentType:"application/json;charset=utf-8",
	success : function(data) {

		i++;
		var treeval = data.substring(0, data.indexOf("~"));
		var fornext = data.substring(data.indexOf("~") + 1);
		var mapTree = fornext.substring(0, fornext.indexOf("~"));
		console.log(treeval);
		console.log(mapTree);

		jsonNodeValues = JSON.parse(treeval);
		jsonNodeMap = JSON.parse(mapTree);
		jsonvar = data;

		if (i == 1) {
			console.log(jsonNodeValues);
			var length = jsonNodeValues.length;
			var selectOptionfrom = $('<option/>');
			selectOptionfrom.text("Please select");
			selectOptionfrom.attr('value', "Please select");
			$('#fromIpAddress').append(selectOptionfrom);
			for (var j = 0; j < length; j++) {
				var newOption = $('<option/>');
				newOption.text(jsonNodeValues[j].label);
				newOption.attr('value', jsonNodeValues[j].label);
				$('#fromIpAddress').append(newOption);
			}
			var selectOptionto = $('<option/>');
			selectOptionto.text("Please select");
			selectOptionto.attr('value', "Please select");
			$('#toIpAddress').append(selectOptionto);
			for (var j = 0; j < length; j++) {
				var newOption = $('<option/>');
				newOption.text(jsonNodeValues[j].label);
				newOption.attr('value', jsonNodeValues[j].label);

				$('#toIpAddress').append(newOption);
			}
			draw();

		} else {
			edges.clear();
			nodes.clear();
			nodes.update(jsonNodeValues);
			edges.update(jsonNodeMap);

		}

	}
});

// Called when the Visualization API is loaded.
function draw() {
	// create people.
	// value corresponds with the age of the person
	// console.log("jsonNodeValues : \n"+jsonNodeValues);
	// console.log("jsonNodeMap : \n"+jsonNodeMap);
	var DIR = '../img/indonesia/';
	// nodes = new vis.DataSet(jsonNodeValues);
	// edges = new vis.DataSet(jsonNodeMap);

	// nodes = jsonNodeValues;
	// edges = jsonNodeMap;

	nodes = new vis.DataSet(jsonNodeValues);
	edges = new vis.DataSet(jsonNodeMap);
	// create a network
	var container = document.getElementById('mynetwork');
	data = {
		nodes : nodes,
		edges : edges
	};

	var options = {

		clickToUse : false,
		nodes : {

			borderWidth : 4,
			size : 30,
			font : {
				color : 'white'
			}
		},
		edges : {
			arrows : 'to',
			physics : true,
			smooth : {
				enabled : true,
				type : "curvedCW",
				forceDirection : "none",
				roundness : 0.3
			}
		},
		interaction : {
			navigationButtons : true,
			hover : true

		},
		physics : {

			barnesHut : {
				avoidOverlap : 0.99,
				gravitationalConstant : 0,
				centralGravity : 0,
				springConstant : 0
			}

		},
		manipulation : {
			initiallyActive : true,
			addNode : false,
			addEdge : function(data, callback) {
				if (data.from == data.to) {
					var r = confirm("Do  node to itself?");
					if (r == true) {
						callback(data);

					}
				} else {
					callback(data);
					$.ajax({
						url : "NetworkTopologyDashboardAddLink",
						type : "post",
						dataType : "text",
						data : {
							data : JSON.stringify(data)
						},
						success : function(out) {
							console.log(" Test");
							alert("DDD ");
						}
					});

				}
			}
		}

	};

	network = new vis.Network(container, data, options);

	network.on('selectNode', function(params) {

		selectedNode = params.nodes[0];

		var indexInt = parseInt(selectedNode - 1);

		// document.getElementById('selection').innerHTML = 'Selection:
		// '+Number(indexInt);

		selectedNodeName = nodes[indexInt].label;
		outer: for (var i = 0; i < nodes.length; i++) {

			if (nodes[i].id === selectedNode) {
				console.log("jsonid " + nodes[i].id);
				console.log("jsonvlaue " + nodes[i].label);
				selectedNode = nodes[i].id;
				selectedNodeName = nodes[i].label;
				nodeType = nodes[i].nodeType;
				break outer;
			}
		}
		console.log("selectedNode - " + selectedNode);
		console.log("selectedNodeName - " + selectedNodeName);
		console.log("nodeType - " + nodeType);
		if (popupMenu !== undefined) {
			popupMenu.parentNode.removeChild(popupMenu);
			popupMenu = undefined;
		}
	});
	network.on('click', function(params) {
		$("#cntnr").hide();
		console.log(">>>on second click event ");
		selectedNode = params.nodes[0];

		console.log("selectedNode - " + selectedNode);
		console.log("selectedNodeName - " + selectedNodeName);

	});

	// right click
	$(document)
			.bind(
					"contextmenu",
					function(e) {
						e.preventDefault();
						// console.log("click found");
						var dataType = undefined;
						var dataValue = undefined;
						var values = undefined;
						var dataID = undefined;
						// console.log(e.pageX + "," + e.pageY);
						$("#cntnr").css("left", e.pageX);
						$("#cntnr").css("top", e.pageY);
						// console.log(selectedNode);
						if (selectedNode !== undefined) {
							if (selectedNode.length !== 0) {
								$("#cntnr").empty();
								$("#cntnr")
										.append(
												'<ul id="items" ><li id="parent">Add Router</li><li id="parent">Add Switch</li><li id="parent">Add Firewall</li><li id="parent">Add Servers</li></ul>');
							} else {
								$("#cntnr").empty();
								$("#cntnr")
										.append(
												'<ul id="items" ><li id="parent">Add Router</li><li id="parent">Add Switch</li><li id="parent">Add Firewall</li><li id="parent">Add Servers</li></ul>');
							}

						} else {
							$("#cntnr").empty();
							$("#cntnr")
									.append(
											'<ul id="items" ><li id="parent">Add Router</li><li id="parent">Add Switch</li><li id="parent">Add Firewall</li><li id="parent">Add Servers</li></ul>');
						}
						// $("#cntnr").hide(100);

						$("#cntnr").fadeIn(200, startFocusOut());

						// first right click select
						$("#items li")
								.click(
										function() {
											// $("#op").text("You have selected
											// "+$(this).text());

											dataType = $(this).text();
											console.log("data type = "
													+ dataType)
											dataID = $(this).attr('id');
											// var datas = { id:1 };
											console.log(" Data id = " + dataID);

											if (dataType == "Remove Node") {
												selectedNodeName = nodeType
														+ "^" + selectedNode
														+ "~"
														+ selectedNodeName;
												console.log("selected data : "
														+ selectedNodeName);
											}

											$
													.ajax({
														url : "NetworkTopologyDashboardContextMenu",
														type : "post",
														dataType : "text",
														data : {
															type : $(this)
																	.text(),
															name : selectedNodeName
														},
														success : function(out) {
															// alert(out);
															var op = out
																	.toString();
															if (op
																	.indexOf("detail") == 0) {
																window
																		.open("secureitdesktop.action?ipAddress="
																				+ selectedNodeName);
															}
															// console.log(op);
															// secound right
															// click
															$("#cntnr").css(
																	"left",
																	e.pageX);
															$("#cntnr").css(
																	"top",
																	e.pageY);
															$("#cntnr").empty();

															$("#cntnr").append(
																	out);

															if (op
																	.indexOf("<input ") == 0) {
																$("#cntnr")
																		.fadeIn(
																				200,
																				selectmulti());
																console
																		.log("if condition satiesfied!!");

															} else if (op
																	.indexOf("<div ") == 0) {
																console
																		.log("else condition satiesfied!!");
																// $("#cntnr").fadeIn(200,startFocusOut());
																swal({
																	title : 'No Data Found',
																	customClass : 'swal-wide',
																	type : "info"
																})
															}

															$("#btn_item1")
																	.click(
																			function() {
																				if ($(
																						"#item1")
																						.val() == null) {
																					swal({
																						title : 'Please select an option',
																						type : "error",
																						customClass : 'swal-wide'
																					})
																					return false;
																				}
																				$(
																						"#cntnr")
																						.hide();
																				values = $(
																						'#item1')
																						.val();
																				console
																						.log("selected data : "
																								+ values);

																				console
																						.log("item1> dataID : "
																								+ dataID
																								+ ", add parent = "
																								+ dataType
																								+ ", value= "
																								+ values
																								+ ", dataID = "
																								+ selectedNode);
																				insertView(
																						dataID,
																						values,
																						dataType,
																						selectedNode);
																			});

															// second right
															// click select
															$("#btn_item2")
																	.click(
																			function() {
																				// dataValue=$(this).text();
																				if ($(
																						"#item2")
																						.val() == null) {
																					// alert("Please
																					// select
																					// an
																					// option");
																					swal({
																						title : 'Please select an option',
																						type : "error",
																						customClass : 'swal-wide'
																					})
																					return false;
																				}
																				$(
																						"#cntnr")
																						.hide();
																				values = $(
																						'#item2')
																						.val();
																				console
																						.log("selected data : "
																								+ values);

																				console
																						.log("items2> dataID : "
																								+ dataID
																								+ ", add parent = "
																								+ dataType
																								+ ", value= "
																								+ values
																								+ ", dataID = "
																								+ selectedNode);
																				insertView(
																						dataID,
																						values,
																						dataType,
																						selectedNode);

																			});

															$("#btn_item3")
																	.click(
																			function() {
																				// dataValue=$(this).text();
																				if ($(
																						"#item3")
																						.val() == null) {
																					swal({
																						title : 'Please select an option',
																						type : "error",
																						customClass : 'swal-wide'
																					})
																					return false;
																				}
																				$(
																						"#cntnr")
																						.hide();
																				values = $(
																						'#item3')
																						.val();
																				console
																						.log("selected data : "
																								+ values);

																				console
																						.log("item2> dataID : "
																								+ dataID
																								+ ", add parent = "
																								+ dataType
																								+ ", value= "
																								+ values
																								+ ", dataID = "
																								+ selectedNode);
																				insertView(
																						dataID,
																						values,
																						dataType,
																						selectedNode);

																			});

														}
													});
										});

					});

	function selectmulti() {
		$('.select').multiselect({
			includeSelectAllOption : true,
			enableFiltering : true,
			maxHeight : 200
		});
	}
	;

	function insertView(dataID, dataValue, dataType, selectedNode) {
		var groupName = null;
		groupName = '<%=groupName%>';
		$.ajax({
			url : "NetworkTopologyDashboardInsertNodes",
			type : "post",
			dataType : "text",
			data : {
				id : dataID,
				value : dataValue.toString(),
				type : dataType,
				parentid : selectedNode,
				group_name : groupName
			},
			success : function(out) {
				if (out === "success") {
					reDraw();
				}
			}
		});
	}

	function startFocusOut() {
		$(document).on("click", function() {
			$("#cntnr").hide();
			$(document).off("click");
		});
	}

}

function addInterface() {

	alert("HI ");

}

function getNodePositions() {

	var nodesPos = objectToArray(network.getPositions());

	nodesPos.forEach(addConnections);

	var exportValue = JSON.stringify(nodesPos, undefined, 2);

	console.log("exportValue :  " + exportValue);
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl1 = base_url + "/nodeDashboard/SaveNodePositions";
	$.ajax({
		type : 'GET',
		url : serviceUrl1,
		data : {
			positions : exportValue,
			fromTo : JSON.stringify(edges.get(), null, 4)
		},
		// dataType : 'json',
		success : function(data) {
			//
			// $.ajax({
			// url : "SaveNodePositions",
			// type : "post",
			// dataType : "text",
			// data : {positions:exportValue,fromTo:JSON.stringify(edges.get(),
			// null, 4)},
			// success : function() {

			var l = window.location;
			var base_url = l.protocol + "//" + l.host + "/"
					+ l.pathname.split('/')[1];
			window.location.href = base_url
					+ "/nodeDashboard/dynamicNetworkTopologyEdit";

		}
	});

}
function objectToArray(obj) {
	return Object.keys(obj).map(function(key) {
		obj[key].id = key;
		return obj[key];
	});
}

function addConnections(elem, index) {
	// need to replace this with a tree of the network, then get child direct
	// children of the element
	elem.connections = network.getConnectedNodes(index);
}

function syncData() {

	console.log("sync Data");
	$.ajax({
		url : "NetworkTopologyEditMode",//
		dataType : "text",
		success : function(newData) {
			console.log("newData successfull");
			treeval = null;
			mapTree = null;
			jsonNodeValues = null;
			jsonNodeMap = null;

			treeval = newData.substring(0, newData.indexOf("~"));
			mapTree = newData.substring(newData.indexOf("~") + 1);
			jsonNodeValues = JSON.parse(treeval);
			jsonNodeMap = JSON.parse(mapTree);
			jsonvar = newData;
			console.log(jsonNodeValues);
			console.log(jsonNodeMap);

			nodes = jsonNodeValues;
			edges = jsonNodeMap;

			data = {
				nodes : nodes,
				edges : edges
			};
		}

	});

	// network.setData(data);

}
// setInterval(syncData, 10000);
function reDraw() {

	console.log(" inside redraw()");
	// $.ajax({
	// url : "NetworkTopologyEditMode",
	// type : "post",
	// success : function(data) {

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl1 = base_url + "/nodeDashboard/NetworkTopologyEditMode";
	$.ajax({
		type : 'GET',
		url : serviceUrl1,

		// dataType : 'json',
		success : function(data) {
			// alert(data);
			location.reload();
			var treeval = data.substring(0, data.indexOf("~"));
			var mapTree = data.substring(data.indexOf("~") + 1);
			jsonNodeValues = JSON.parse(treeval);
			jsonNodeMap = JSON.parse(mapTree);
			nodes = new vis.DataSet(jsonNodeValues);
			edges = new vis.DataSet(jsonNodeMap);
			network.destroy();
			network = new vis.Network(container, data, options);
		}
	});
}

console.log("end of js");

network.setOption({
	stabilization : {
		enabled : true
	},
	physics : {
		enabled : false
	}
});
