// HTML parsing with all XSS goodness

window.onload = function() {
	// alert("hi")
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/master/getManualTopology";
	//	
	$.ajax({
				type : 'POST',
				url : serviceUrl,
				dataType : "JSON",
				success : function(data) {
					// alert("data")
					console.log(data)
					let Array = data;
					var nodes = Array[0];
					// alert(nodes)
					var edges = Array[1];
					console.log("nodes :" + JSON.stringify(nodes))
					console.log("edges :" + JSON.stringify(edges))

					function htmlTitle(html) {
						const container = document.createElement("div");
						container.innerHTML = html;
						return container;
					}

					var color = "black";
					// var DIR = "../img/indonesia/";
					var DIR = "/NPMWebConsoleV2/webtemplate/visjs/img/routerDown.png";
					// create a network
					var container = document.getElementById("mynetwork");
					var data = {
						nodes : nodes,
						edges : edges,
					};
					var options = {
						nodes : {
							shape : "box",
							chosen : false,
							shadow : true,
							borderWidth : 2,

						},
						edges : {
							width : 2,
							shadow : true,
							smooth : true
						},
						
						layout : {
							hierarchical : {
								direction : "UD",
								sortMethod : "directed",
							},
						},
						physics : {
							hierarchicalRepulsion : {
								//avoidOverlap : 3,
								springConstant : 0.001,
							},
							
							minVelocity : 1
						}
					};
					network = new vis.Network(container, data, options);

					network
							.on(
									"showPopup",
									function(params) {
										// alert("showPopup params :"+params)
										document
												.getElementById("eventSpanHeading").innerHTML = "showPopup event: ";
										document
												.getElementById("eventSpanContent").innerHTML = JSON
												.stringify(params, null, 4);
									});

					network.on("hidePopup", function() {
						console.log("hidePopup Event");
					});

					network
							.on(
									"doubleClick",
									function(params) {
										// alert("doubleClick :"+params)
										console.log(params);
										params.event = "[original event]";
										document
												.getElementById("eventSpanContent").innerHTML = JSON
												.stringify(params, null, 4);
										return false;
									});

					network.on("stabilizationIterationsDone", function() {
						network.setOptions({
							physics : false
						});
					});
				}
			})

	setInterval(function() {
		topology()
	}, 120000);
//	setInterval(function() {
//		topology();
//
//	}, 20000);
}
function topology() {

	// alert("hi")
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/master/getManualTopology";
	//	
	$
			.ajax({
				type : 'POST',
				url : serviceUrl,
				dataType : "JSON",
				success : function(data) {
					// alert("data")
					console.log(data)
					let Array = data;
					var nodes = Array[0];
					// alert(nodes)
					var edges = Array[1];
					console.log("nodes :" + JSON.stringify(nodes))
					console.log("edges :" + JSON.stringify(edges))

					function htmlTitle(html) {
						const container = document.createElement("div");
						container.innerHTML = html;
						return container;
					}

					var color = "black";
					// var DIR = "../img/indonesia/";
					var DIR = "/NPMWebConsoleV2/webtemplate/visjs/img/wifi-router-down.png";

									// create a network
					var container = document.getElementById("mynetwork");
					var data = {
						nodes : nodes,
						edges : edges,
					};
					var options = {
						nodes : {
							shape : "box",
							chosen : false,
							shadow : true,
							borderWidth : 2,

						},
						edges : {
							width : 2,
							shadow : true,
							smooth : true
						},
					
						layout : {
							hierarchical : {
								direction : "UD",
								sortMethod : "directed",
							},
						},
						physics : {
							hierarchicalRepulsion : {
								//avoidOverlap : 3,
								springConstant : 0.001,
							},
							
							minVelocity : 1
						}
					};
					network = new vis.Network(container, data, options);

					network
							.on(
									"showPopup",
									function(params) {
										// alert("showPopup params :"+params)
										document
												.getElementById("eventSpanHeading").innerHTML = "showPopup event: ";
										document
												.getElementById("eventSpanContent").innerHTML = JSON
												.stringify(params, null, 4);
									});

					network.on("hidePopup", function() {
						console.log("hidePopup Event");
					});

					network
							.on(
									"doubleClick",
									function(params) {
										// alert("doubleClick :"+params)
										console.log(params);
										params.event = "[original event]";
										document
												.getElementById("eventSpanContent").innerHTML = JSON
												.stringify(params, null, 4);
										return false;
									});

					network.on("stabilizationIterationsDone", function() {
						network.setOptions({
							physics : false
						});
					});
				}
			})

}
