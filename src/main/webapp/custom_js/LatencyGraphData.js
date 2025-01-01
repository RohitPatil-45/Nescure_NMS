	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	//var serviceUrl = base_url + "/dashboard/interfaceBandwidthUtil";
	var serviceUrl = base_url + "/nodeDashboard/cpuAndMemoryUtilization";
	$.ajax({
		type : 'POST',
		url : serviceUrl,
		data : {
			ip_address : ip
		
		},
		dataType : 'json',
		success : function(jsondata) {
			//alert("Latnecy Data:"+jsondata);
			
			//alert(jsondata[0].latency)
			//alert(jsondata[0].packetLoss)
			
			Highcharts.stockChart('interactive', {
			    chart: {
			    	//type : 'area',
			        events: {
			            load: function () {
			            	
			                // set up the updating of the chart each second
			                var series = this.series[0];
			                var series2 = this.series[1];
			                
			                
			                setInterval(function () {
			          
			                	var l = window.location;
			                	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
			                	var serviceUrl = base_url + "/nodeDashboard/cpuAndMemoryUtilization";
			                	$.ajax({
			                		type : 'POST',
			                		url : serviceUrl,
			                		data : {
			                			ip_address : ip
			                			
			                		},
			                		dataType : 'json',
			                		success : function(jsondata1) {
			                	
			                			var x = (new Date()).getTime(), // current
																		// time
				                    		y=jsondata1[0].cpuUtilization,
				                    		z=jsondata1[0].memoryUtilization;
				                    
				                    series.addPoint([x, y], true, true);
				                    series2.addPoint([x, z], true, true);
			                			
			                		}
			                		
			                	});
			              
			                }, 2000);
			               
			            }
			        }
			    },

			    accessibility: {
			        enabled: false
			    },

			    time: {
			        useUTC: false
			    },

			    rangeSelector: {
			        buttons: [{
			            count: 1,
			            type: 'minute',
			            text: '1M'
			        }, {
			            count: 5,
			            type: 'minute',
			            text: '5M'
			        }, {
			            type: 'all',
			            text: 'All'
			        }],
			        inputEnabled: false,
			        selected: 0
			    },

			    
			    exporting: {
			        enabled: false
			    },

			    
			    series: [{
			        name: 'CPU Utilization(%)',
			        data: (function () {
			            // generate an array of random data
			            var data = [],
			                time = (new Date()).getTime(),
			                i;

			            for (i = -999; i <= 0; i += 1) {
			                data.push([
			                    time + i * 1000,
			                    jsondata[0].cpuUtilization
			                ]);
			            }
			            return data;
			        }())
			    },
//			    {
//			        name: 'Memory Utilization',
//			        data: (function () {
//			            // generate an array of random data
//			            var data = [],
//			                time = (new Date()).getTime(),
//			                i;
//
//			            for (i = -999; i <= 0; i += 1) {
//			                data.push([
//			                    time + i * 1000,
//			                    jsondata[0].memoryUtilization
//			                ]);
//			            }
//			            return data;
//			        }())
//			    }
			    ]
			});
			

		}
	});