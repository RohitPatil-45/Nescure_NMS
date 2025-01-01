window.onload = function() {
    var l = window.location;
    var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;

    var addServiceUrl = `${base_url}/node/mapTopologyData`;
    var nodeDetailUrl = base_url + "/dashboard/nodeDetailsPage?nodeIP=";
    
    (async () => {
        const topology = await fetch(
            'https://code.highcharts.com/mapdata/countries/in/in-all.topo.json'
        ).then(response => response.json());

        const dynamicDataResponse = await fetch(addServiceUrl);
        const dynamicData = await dynamicDataResponse.json();

        const upData = dynamicData.filter(item => item.status === 'Up');
        const downData = dynamicData.filter(item => item.status === 'Down');

        const mapData = (data) => data.map(item => ({
            name: item.deviceIP,
            deviceIP: item.deviceIP, // Add this line
            lat: parseFloat(item.latitude) || 0,
            lon: parseFloat(item.longitude) || 0,
            status: item.status,
            location: item.location,
            value: 100 // Modify as needed based on your data
        }));

        // Create the chart
        Highcharts.mapChart('indiaMap', {
            chart: {
                map: topology,
                margin: 1
            },
            title: {
                text: 'Network Topology Map',
                floating: true,
                style: {
                    textOutline: '5px contrast'
                }
            },
            subtitle: {
                text: '',
                floating: true,
                y: 36,
                style: {
                    textOutline: '5px contrast'
                }
            },
            mapNavigation: {
                enabled: true,
                buttonOptions: {
                    alignTo: 'spacingBox',
                    verticalAlign: 'bottom'
                }
            },
            mapView: {
                padding: [0, 0, 85, 0]
            },
            legend: {
                floating: true,
                backgroundColor: '#ffffffcc'
            },
            plotOptions: {
                mappoint: {
                    keys: ['id', 'lat', 'lon', 'name', 'y'],
                    marker: {
                        lineWidth: 1,
                        lineColor: '#000',
                        symbol: 'mapmarker',
                        radius: 8
                    },
                    dataLabels: {
                        enabled: false
                    }
                }
            },
            tooltip: {
                headerFormat: '<span style="color:{point.color}">\u25CF</span> {point.key}<br/>',
                pointFormat: 'Location: {point.location}<br/>Status: {point.status}',
            },
            series: [
                {
                    allAreas: true,
                    name: 'Node Status',
                    states: {
                        inactive: {
                            opacity: 0.2
                        }
                    },
                    dataLabels: {
                        enabled: false
                    },
                    enableMouseTracking: false,
                    showInLegend: false,
                    borderColor: 'blue',
                    opacity: 0.3,
                    borderWidth: 10
                },
                {
                    allAreas: true,
                    name: 'Countries',
                    states: {
                        inactive: {
                            opacity: 1
                        }
                    },
                    dataLabels: {
                        enabled: false
                    },
                    enableMouseTracking: false,
                    showInLegend: false,
                    borderColor: 'rgba(0, 0, 0, 0.25)'
                },
                {
                    name: 'UP',
                    color: 'rgb(94, 242, 112)',
                    data: mapData(upData),
                    type: 'mappoint',
                    events: {
                        click: function(event) {
                            // Handle click event here
                            const deviceIP = event.point.deviceIP;
                            const url = nodeDetailUrl + deviceIP;
                            window.location.href = url; // Redirect to the node details page
                        }
                    }
                },
                {
                    name: 'Down',
                    color: 'rgb(240, 58, 48)',
                    data: mapData(downData),
                    type: 'mappoint',
                    events: {
                        click: function(event) {
                            // Handle click event here
                            const deviceIP = event.point.deviceIP;
                            const url = nodeDetailUrl + deviceIP;
                            window.location.href = url; // Redirect to the node details page
                        }
                    }
                }
            ]
        });

    })();
}; // Added semicolon here
