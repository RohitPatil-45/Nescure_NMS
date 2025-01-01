// Date range as a button
$('#daterange-btn').daterangepicker(
		{
			timePicker : true,
			timePickerIncrement : 10,
			ranges : {
				'Today' : [ moment().hours(0).minutes(0).seconds(0),
						moment().hours(23).minutes(59).seconds(59) ],
				'Yesterday' : [
						moment().hours(0).minutes(0).seconds(0).subtract(1,
								'days'),
						moment().hours(23).minutes(59).seconds(59).subtract(1,
								'days') ],
				'Last 7 Days' : [
						moment().hours(0).minutes(0).seconds(0).subtract(6,
								'days'),
						moment().hours(23).minutes(59).seconds(59) ],
				'Last 30 Days' : [
						moment().hours(0).minutes(0).seconds(0).subtract(29,
								'days'),
						moment().hours(23).minutes(59).seconds(59) ],
				'This Month' : [ moment().startOf('month'),
						moment().endOf('month') ],
				'Last Month' : [
						moment().subtract(1, 'month').startOf('month'),
						moment().subtract(1, 'month').endOf('month') ],
			// 'Last 3 Month' : [
			// moment().subtract(4, 'month').startOf('month'),
			// moment().subtract(1, 'month').endOf('month') ],
			// 'Last 6 Month' : [
			// moment().subtract(7, 'month').startOf('month'),
			// moment().subtract(1, 'month').endOf('month') ],
			// 'Last Year' : [
			// moment().subtract(21, 'month').startOf('month'),
			// moment().subtract(10, 'month').endOf('month') ],
			},
			startDate : moment().subtract(29, 'days'),
			endDate : moment()
		}, function(start, end) {
			var from_date = document.getElementById("from_date");
			from_date.value = start.format('YYYY-MM-DD HH:mm:ss');
			var to_date = document.getElementById("to_date");
			to_date.value = end.format('YYYY-MM-DD HH:mm:ss');

		})
