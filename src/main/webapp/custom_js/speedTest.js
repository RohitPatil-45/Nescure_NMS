

$( window ).on("load", function() {
//alert("window is loaded");
	var ip= $('#nodeipdetailsstore').text();
	var infname= $('#interfacenamedetailsstroe').text();
	functiontogetspeed(ip, infname);

	$('#checkSrcIP').hide();
	$('#checkSrcName').hide();
	$('#checkDesIP').hide();
	$('#checkDesName').hide();
	$('#listingcardID').hide();

//	$('#setSrcIP').text(srcIPtxt);
//	// $('#setSrcInterface').text(srcInterface);
//	$('#setDesIP').text(desIPtxt);
//	// $('#setDesInterface').text(desInterface);

	$('#showSpeedTest').show();
	$('#speedTestForm').hide();
	$('#headerCard').hide();
});
function speedTest() {

	var srcIP = $('#srcIP').val();
	var desIP = $('#desIP').val();

	var srcIPtxt = $("option:selected", '#srcIP').text();
	var desIPtxt = $("option:selected", '#desIP').text();
	// var srcInterface = $('#srcInterface').val();
	// var desInterface = $('#desInterface').val();

	if (srcIP == "" || desIP == "") {

		$('#checkSrcIP').show();
		$('#checkSrcName').show();
		$('#checkDesIP').show();
		$('#checkDesName').show();
	} else {

		functiontogetspeed(srcIP, desIP);

		$('#checkSrcIP').hide();
		$('#checkSrcName').hide();
		$('#checkDesIP').hide();
		$('#checkDesName').hide();
		$('#listingcardID').hide();

		$('#setSrcIP').text(srcIPtxt);
		// $('#setSrcInterface').text(srcInterface);
		$('#setDesIP').text(desIPtxt);
		// $('#setDesInterface').text(desInterface);

		$('#showSpeedTest').show();
		$('#speedTestForm').hide();
		$('#headerCard').hide();

	}

}

function functiontogetspeed(srcIP, infname) {
	// alert(srcIP)
	// alert(desIP)
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/master/GetSpeedTestDetails";
	$.ajax({
		type : 'POST',
		url : serviceUrl,
		data : 'srcIP=' + srcIP + '&infname=' + infname,
	}).done(function(data) {
		// alert(data)

		if (data === "success") {

		} else if (data === "failbyagent") {

			Swal.fire({
				position : 'top',
				icon : 'warning',
				title : 'Failed (Check Agent)',
				showConfirmButton : false,
				timer : 3000
			})

		} else {
			Swal.fire({
				position : 'top',
				icon : 'warning',
				title : 'Failed',
				showConfirmButton : false,
				timer : 3000
			})

		}
		$('#speedLoader').hide();

		$('#resultping').html(data);
	});

}

function checkAgain() {
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	window.location.href = base_url + "/node/nodeSpeedTest";
}

// const downloadData = [];
// const uploadData = [];
// const pingData = [];
//
// // Set up the Highcharts chart configuration
// const chart = Highcharts.chart('networkChart', {
// chart: {
// type: 'area',
// animation: Highcharts.svg,
// marginRight: 10
// },
// title: {
// text: 'Real-time Network Monitoring'
// },
// xAxis: {
// type: 'datetime',
// tickPixelInterval: 150
// },
// yAxis: {
// title: {
// text: 'Speed/Ping (Mbps/ms)'
// }
// },
// series: [{
// name: 'Download Speed',
// data: downloadData
// }, {
// name: 'Upload Speed',
// data: uploadData
// }, {
// name: 'Ping',
// data: pingData
// }]
// });
//
// // Simulate real-time data updates
// setInterval(() => {
// const currentTime = new Date().getTime();
// const downloadSpeed = Math.floor(Math.random() * 100) + 1; // Random download
// speed between 1 and 100 Mbps
// const uploadSpeed = Math.floor(Math.random() * 100) + 1; // Random upload
// speed between 1 and 100 Mbps
// const ping = Math.floor(Math.random() * 50) + 1; // Random ping between 1 and
// 50 ms
//
// // Add new data points to the chart series
// downloadData.push({ x: currentTime, y: downloadSpeed });
// uploadData.push({ x: currentTime, y: uploadSpeed });
// pingData.push({ x: currentTime, y: ping });
//
// // Limit the number of data points to keep the chart from getting too crowded
// if (downloadData.length > 20) {
// downloadData.shift(); // Remove the oldest data point
// uploadData.shift(); // Remove the oldest data point
// pingData.shift(); // Remove the oldest data point
// }
//
// // Update the chart with the new data
// chart.series[0].setData(downloadData);
// chart.series[1].setData(uploadData);
// chart.series[2].setData(pingData);
// }, 1000); // Update interval: 1 second
