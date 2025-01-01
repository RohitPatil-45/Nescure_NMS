//$('#exportLatency').click(function(){
//	
////	alert("ip = "+$('#deviceIP').val()+"\nfromDate = "+$('#from_date').val()+"\nToDate = "+$('#to_date').val())
//	
//	var l = window.location;
//	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
//	var serviceUrl = base_url + "/nodeReport/exportNodeLatency";
//	
//	$.ajax({
//		type : 'GET',
//		url : serviceUrl,
//		data : 'ipAddress=' +$('#deviceIP').val()
//				+ "&fromDate=" + $('#from_date').val()
//				+ "&toDate=" +$('#from_date').val(),
//		dataType : 'json',
//		success : function(data) {
//			var table = $('#exportNodeLatencyTable').DataTable({
//				lengthChange : false,
//				autoWidth : false,
//				searching : true,
//				paging : true,
//				info : false,
//				ordering : false,
//
//				data : data,
//				"pageLength" : 5,
//				scrollX : true,
////				scrollY : true
//			});
//
//		}
//	});
//
//	
//});
