var ip=null;
const ulElement = document.getElementById('myUlElement');
document.addEventListener('DOMContentLoaded', function() {
	const contextMenu = document.getElementById('contextMenu');
	document.addEventListener('click', function() {
		contextMenu.style.display = 'none';
	});

	window.showContextMenu = function(event, group, ipAddress, device) {
		document.getElementById('group').value = group;
		document.getElementById('ipaddress').value = ipAddress;
		ip=ipAddress;
		document.getElementById('device').value = device;
		event.preventDefault();
		ulElement.innerHTML = '';
// ulElement.innerHTML = `
// <li onclick="handleContextMenu('SetParameter')">Set Parameter</li>
// <li onclick="handleContextMenu('DetailPage')">Detail Page</li>
// <li onclick="handleContextMenu('Run Commands')">Run Commands</li>
// <li onclick="handleContextMenu('Manage Device')">Manage Device</li>
// `;
		
		ulElement.innerHTML = `
	    	<li onclick="handleContextMenu('SetParameter')">Set Parameter</li>
	    	<li onclick="handleContextMenu('DetailPage')">Detail Page</li>
	    `;
		contextMenu.appendChild(ulElement);


		contextMenu.style.display = 'block';
		contextMenu.style.left = event.clientX + 'px';
		contextMenu.style.top = event.clientY + 'px';

		// You can use group and ipAddress as needed in your logic
		console.log('Group:', group);
		console.log('IP Address:', ipAddress);
		console.log('device:', device);
	};


});


/*
 * $('#exampleTable1').DataTable({ dom: 'tip', data: hardInvent, columnDefs: [{
 * "defaultContent": "NA", "targets": "_all" }] });
 */


function handleContextMenu(option) {
	// Implement your logic for each option here
	console.log('Selected option:', option);
	var group = document.getElementById('group').value;
	var ipAddress = document.getElementById('ipaddress').value;
	console.log('My Group:', group);
	console.log('My IP Address:', ipAddress);

	/* if(option==="Detail Page"){ */
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
    var serviceUrl = base_url + "/dashboard/serverDetails";

    if(option==="SetParameter"){
	
          /*
			 * var l = window.location; var base_url =
			 * `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`; var
			 * serviceUrl = `${base_url}/admin/setParameter/${ipAddress}/`;
			 * window.location.href = serviceUrl;
			 */
      
      var element1 = document.querySelector('.setParameter');
      var element2 = document.querySelector('.deviceDetails');
      element1.style.display = 'block';
      element2.style.display = 'none';
      
      
     
	var l = window.location;
	var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
	var serviceUrl = `${base_url}/dashboard/viewSetParameterData`;
	$.ajax({
		type: "GET",
		url: serviceUrl,
		data: {
        ipAddress: ip
         },
		dataType: "json",
		success: function(data) {
			
            $('#exampleTable51').DataTable().clear().rows.add(data).draw();
		},
	});
 

	}else if(option==="DetailPage"){
	
	var element1 = document.querySelector('.setParameter');
      var element2 = document.querySelector('.deviceDetails');
      element1.style.display = 'none';
      element2.style.display = 'block';
      
      console.log("helll :"+serviceUrl);
	$.ajax({
		type: "POST",
		url: serviceUrl,
		data:"group=" + group + "&ipAddress=" + $("#ipaddress").val()+ "&device_name=" + $("#device").val(),
		dataType: "json",
	}).done(function(data) {

		
            console.log(data.swInvent);
            console.log(data.diskConf);
            console.log(data.hardInvent);
            console.log(data.memoryModule);
            console.log(data.logicalDrive);
            console.log(data.printalDt);
            console.log(data.hwConfig);
            console.log("os type :: "+data.OSType);
           
           if(data.OSType=== 'Ubuntu'){
        	   
        	   $("#SoftwareinventorLinuxtable").show();
        	   $("#SoftwareinventorDefaultable").hide();
        	   
        	   
        	   $("#hwInventoryLinuxID").show();
        	   $("#hwInventoryDefaultID").hide();
        	   
        	   $("#HWConfigurationdefaultID").hide();
        	   $("#PatchDetailsdefaultID").hide();
        	   
        	   $("#InstallMemoryModulesLinuxValue").show();
        	   $("#InstallMemoryModulesDefaultValue").hide();
        	   
        	   $("#HardDiskDefaultID").hide();
        	   $("#LogicalDriveDefaultID").hide();
        	   
        	   
        	   $('#exampleTable1').DataTable().clear().rows.add(data.hardInvent).draw();
        	    $('#exampleTableInstallMemoryModulesLinuxValue').DataTable().clear().rows.add(data.memoryModule).draw();
        	    $('#exampleTable12').DataTable().clear().rows.add(data.diskConf).draw();
        	    $('#exampleTable13').DataTable().clear().rows.add(data.logicalDrive).draw();
        	    $('#exampleTable14').DataTable().clear().rows.add(data.driveDetails).draw();
        	    
        	    $('#exampleTableLinuxSw').DataTable().clear().rows.add(data.swInvent).draw();
        	    $('#exampleTable3').DataTable().clear().rows.add(data.patchData).draw();
        	    $('#exampleTable4').DataTable().clear().rows.add(data.processData).draw();
        	      $('#exampleTable5').DataTable().clear().rows.add(data.serviceData).draw();
        	    $('#exampleTable6').DataTable().clear().rows.add(data.firewallData).draw();
        	    
        		console.log(data.hwConfig);
        		var dataArray = data.hwConfig;
        		var fieldIds = [
        			"ipAddressLinux", "pcNameLinux", "biosInfoLinux", "graphicCardLinux",
        		    "hddDriveLinux", "motherboardNameLinux", "opNameLinux", "processorNameLinux",
        		    "ramDetailsLinux", "discoverTimeLinux"
        	];

        	for (var j = 0; j < fieldIds.length; j++) {
        	    $('#' + fieldIds[j]).val('');
        	}
        	        // Iterate through the array and set values in the form
        	        for (var i = 0; i < dataArray.length; i++) {
        	     
        	    var innerArray = dataArray[i];

        	        for (var j = 0; j < fieldIds.length; j++) {
        	          /* $("#" + fieldIds[j] + "-" + i).val(innerArray[j]); */
        	              $('#' + fieldIds[j]).val(innerArray[j]);
        	            console.log(innerArray[j]);
        	        }
        	}
        	        
        	      

        	   
           }else{
        	   
        	   $("#SoftwareinventorLinuxtable").hide();
        	   $("#SoftwareinventorDefaultable").show();
        	   
        	   
        	   $("#hwInventoryLinuxID").hide();
        	   $("#hwInventoryDefaultID").show();
        	   
        	   $("#HWConfigurationdefaultID").show();
        	   $("#PatchDetailsdefaultID").show();
        	   
        	   $("#InstallMemoryModulesLinuxValue").hide();
        	   $("#InstallMemoryModulesDefaultValue").show();
        	   
        	   $("#HardDiskDefaultID").show();
        	   $("#LogicalDriveDefaultID").show();
        	   
        	   
        	   $('#exampleTable1').DataTable().clear().rows.add(data.hardInvent).draw();
        	    $('#exampleTable11').DataTable().clear().rows.add(data.memoryModule).draw();
        	    $('#exampleTable12').DataTable().clear().rows.add(data.diskConf).draw();
        	    $('#exampleTable13').DataTable().clear().rows.add(data.logicalDrive).draw();
        	    $('#exampleTable14').DataTable().clear().rows.add(data.driveDetails).draw();
        	    
        	    $('#exampleTable2').DataTable().clear().rows.add(data.swInvent).draw();
        	    $('#exampleTable3').DataTable().clear().rows.add(data.patchData).draw();
        	    $('#exampleTable4').DataTable().clear().rows.add(data.processData).draw();
        	      $('#exampleTable5').DataTable().clear().rows.add(data.serviceData).draw();
        	    $('#exampleTable6').DataTable().clear().rows.add(data.firewallData).draw();

        		console.log(data.hwConfig);
        		var dataArray = data.hwConfig;
        		var fieldIds = [
        	    "ipAddress", "macAddress", "manufacturer", "processorCount", "osType", 
        	    "diskSpace", "processorName", "servicePack", "domain", "originalSerialNumber",
        	    "totalMemory", "processorManufacturer", "biosName", "biosManufacturer", 
        	    "biosReleaseDate", "biosVersion", "buildNumber", "hostName", "osName", "osVersion"
        	];

        	for (var j = 0; j < fieldIds.length; j++) {
        	    $('#' + fieldIds[j]).val('');
        	}
        	        // Iterate through the array and set values in the form
        	        for (var i = 0; i < dataArray.length; i++) {
        	     
        	    var innerArray = dataArray[i];

        	        for (var j = 0; j < fieldIds.length; j++) {
        	          /* $("#" + fieldIds[j] + "-" + i).val(innerArray[j]); */
        	              $('#' + fieldIds[j]).val(innerArray[j]);
        	            console.log(innerArray[j]);
        	        }
        	}
        	   
           }
   
	
	


	 });

	contextMenu.style.display = 'none';
	
	
	
	

	
	
	
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/basicInfoDetails";
	$
			.ajax({
				type : 'GET',
				url : serviceUrl,
				data : 'ip_address=' + ipAddress,
				dataType : 'json',
				success : function(data) {
						/*
						 * alert(data[0]['Total_RAM'])
						 * alert(data[0]['Used_RAM'])
						 */
						
				
					var donutData = [ {
						label : 'Used %',
						data : data[0]['Used_RAM'],
						color : '#DD1C1C'
					},
					{
						label : 'Free %',
						data : data[0]['Free_RAM'],
						color : '#52ED15'
					} ]
					$.plot('#donut-chart', donutData, {
						series : {
							pie : {
								show : true,
								radius : 1,
								innerRadius : 0.5,
								label : {
									show : true,
									radius : 2 / 3,
									formatter : labelFormatter,
									threshold : 0.1
								}

							}
						},
						legend : {
							show : false
						}
					})
					/*
					 * END DONUT CHART
					 */
				}
			});
			
			
			
			
			
			
			get();
			
			
			
			
			}
			
			
			
			

			
			
			
			
};






function get(){
	
	var ipAddress = document.getElementById('ipaddress').value;
	
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	// var serviceUrl = base_url + "/dashboard/interfaceBandwidthUtil";
	var serviceUrl = base_url + "/dashboard/cpuAndMemoryUtilization";
	$.ajax({
		type : 'POST',
		url : serviceUrl,
		data : {
			ip_address : ipAddress
		
		},
		dataType : 'json',
		success : function(jsondata) {
		/* alert("Latnecy Data:"+jsondata); */
			
			// alert(jsondata[0].latency)
			// alert(jsondata[0].packetLoss)
			
			Highcharts.stockChart('interactive', {
			    chart: {
			    	// type : 'area',
			        events: {
			            load: function () {
			            	
			                // set up the updating of the chart each second
			                var series = this.series[0];
			                var series2 = this.series[1];
			                
			                
			                setInterval(function () {
			          
			                	var l = window.location;
			                	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
			                	var serviceUrl = base_url + "/dashboard/cpuAndMemoryUtilization";
			                	$.ajax({
			                		type : 'POST',
			                		url : serviceUrl,
			                		data : {
			                			ip_address : ipAddress
			                			
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
// {
// name: 'Memory Utilization',
// data: (function () {
// // generate an array of random data
// var data = [],
// time = (new Date()).getTime(),
// i;
//
// for (i = -999; i <= 0; i += 1) {
// data.push([
// time + i * 1000,
// jsondata[0].memoryUtilization
// ]);
// }
// return data;
// }())
// }
			    ]
			});
			

		}
	});
}















