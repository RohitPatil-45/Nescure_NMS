<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
body {
	font-family: 'Arial', sans-serif;
	background: #f8f9fa;
}

#sidebar-container {
	position: fixed;
	width: 250px;
	height: 100%;
	background: #343a40;
	padding-top: 20px;
	color: #fff;
	z-index: 1;
}

#sidebar {
	padding-left: 15px;
}

#sidebar .sidebar-header {
	text-align: center;
	padding-bottom: 20px;
}

#sidebar ul.components {
	list-style: none;
}

#sidebar ul li a {
	padding: 10px;
	font-size: 16px;
	color: #d1d1d1;
	display: block;
	text-decoration: none;
}

#sidebar ul li a:hover {
	color: #fff;
	background: #212529;
}

#content {
	margin-left: 250px;
	padding: 20px;
}

@media ( max-width : 768px) {
	#sidebar-container {
		width: 100%;
		height: auto;
		position: relative;
	}
	#sidebar {
		padding-left: 15px;
	}
	#content {
		margin-left: 0;
	}
}
</style>
</head>
<body>
	<nav id="sidebar">
		<div class="sidebar-header">
			<h3>Monitoring</h3>
		</div>
		<ul class="list-unstyled components">
			<li><a href="<%=request.getContextPath()%>/dashboard/serverMonitoringDashboard">Dashboard</a></li>
			<li><a href="<%=request.getContextPath()%>/dashboard/serviceMonitoringDashboard">Services Monitoring</a></li>
			<li><a href="<%=request.getContextPath()%>/dashboard/processMonitoringDashboard">Process Monitoring</a></li>
			<li><a href="<%=request.getContextPath()%>/dashboard/driveMonitoringDashboard">Drive Details</a></li>
		</ul>
	</nav>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#sidebarCollapse').on('click', function() {
				$('#sidebar-container').toggleClass('active');
			});
		});
	</script>
</body>
</html>