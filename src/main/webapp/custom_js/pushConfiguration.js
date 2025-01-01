window.onload = function () {
  var l = window.location;
  var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
  var serviceUrl = base_url + "/master/getTemplateName";
  $.ajax({
    type: "GET",
    url: serviceUrl,
    success: function (data) {
      var html =
        " <select class='form-control'  id='templateName' onchange='getCommands(this.value)' name='templateName'>";
      html += data + "</select>";
      document.getElementById("templateName22").innerHTML = html;
    },
  });
};

function getCommands(templateName) {
  var l = window.location;
  var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
  var serviceUrl = base_url + "/master/getCommandsOnTemplateName";
  $.ajax({
    type: "GET",
    url: serviceUrl,
    data: "templateName=" + templateName,
    success: function (data) {
      $("#command").text(data);
    },
  });
}
function myFunction() {
  var change = document.getElementById("toggle");
  if (change.innerHTML == "Select All") {
    $(".checkers").each(function () {
      this.checked = true;
    });
    change.innerHTML = "Un-Select All";
  } else {
    $(".checkers").each(function () {
      this.checked = false;
    });
    change.innerHTML = "Select All";
  }
}

$(function () {
  $("[data-mask]").inputmask();
  // Bootstrap Duallistbox
});

function groupDevices(option) {
  var grpname = option.value;
  // alert(grpname);
  var l = window.location;
  var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
  var serviceUrl = base_url + "/nodeReport/getGroupDeviceDetails";

  $.ajax({
    type: "get",
    dataType: "json",
    url: serviceUrl,
    data: "groupName=" + grpname,
  }).done(function (data5) {
    $("#example").show();
    var table = $("#example").DataTable();
    table.destroy();
    $("#example thead tr")
      .clone(true)
      .appendTo("#example thead")
      .addClass("searchRow");
    $(".searchRow").css("display", "none");
    $("#example thead tr:eq(1) th").each(function (i) {
      var title = $(this).text();

      if (title === "Select" || title === "Action") {
        $(this).html("<div><label>&nbsp;</label></div>");
      } else {
        $(this).html(
          '<input type="text" class="customSearch" placeholder="Search" />'
        );

        $("input", this).on("keyup change", function () {
          if (table.column(i).search() !== this.value) {
            table.column(i).search(this.value).draw();
          }
        });
      }
    });

    var table = $("#example").DataTable({
      dom: '<"pull-left"Bl><"pull-right"f>rtip',
      orderCellsTop: true,
      // fixedHeader: true,
      scrollX: true,
      data: data5,
      paging: false,
      scrollY: "350px",
      orderCellsTop: true,
      fixedHeader: true,
      buttons: [
        {
          text: '<div id="toggle" onclick="myFunction()">Select All</div>',
          className: "newButton",
          action: function (e, dt, node, config) {},
        },
        {
          text: "Toggle Search",

          action: function () {
            var element = document.querySelector(".searchRow");
            var style = getComputedStyle(element);
            var displaystyle = style.display;

            if (displaystyle === "none") {
              $(".searchRow").css("display", "contents");
            } else {
              $(".searchRow").css("display", "none");
            }
          },
        },
      ],
    });

    if ($("#utype").val() == "User") {
      $(".newButton").hide();
      // table.columns([0,1]).visible(false);
    }
  });
}
// ----------------------------------------------------------------------
$(function () {
  $.validator.setDefaults({
    submitHandler: function () {
    	$("#speedLoadercheck").show();
      addPushConfiguration();
    },
  });
  $("#pushConfigurationid").validate({
    rules: {
      templateName: {
        required: true,
      },
      command: {
        required: true,
      },

      datepicker: {
        required: true,
      },
    },
    messages: {
      templateName: {
        required: "Please enter choose Template Name",
      },
      command: {
        required: "Command Not Exits ",
      },
      datepicker: {
        required: "Please enter choose Date And Time Name",
      },

      terms: "Please accept our terms",
    },
    errorElement: "span",
    errorPlacement: function (error, element) {
      error.addClass("invalid-feedback");
      element.closest(".form-group").append(error);
    },
    highlight: function (element, errorClass, validClass) {
      $(element).addClass("is-invalid");
    },
    unhighlight: function (element, errorClass, validClass) {
      $(element).removeClass("is-invalid");
    },
  });
});

function addPushConfiguration() {
// alert("jdskjads");
	
	
  var l = window.location;
  var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
  var addServiceUrl = `${base_url}/master/addPushConfiguration`;

  // Get checked checkboxes values
  var val = $(".checkers:checked")
    .map(function () {
      return $(this).val();
    })
    .get();

  var ipAddress = val.join(",");
  var status = "pending";

  if (!ipAddress.length > 0) {
    Swal.fire({
      position: "top",
      icon: "info",
      title: "Please Select atleast one checkbox",
      showConfirmButton: false,
      timer: 3000,
    });
    
    $("#speedLoadercheck").hide();
  } else {
    $.ajax({
      type: "POST",
      url: addServiceUrl,
      data: {
        templateName: $("#templateName").val(),
        command: $("#command").val(),
        datepicker: $("#datepicker").val(),
        status: status,
        ipAddressCheck: ipAddress,
      },
    }).done(function (data) {
      if (data === "success") {
        // Use Swal for a consistent user experience
        Swal.fire({
          position: "top",
          icon: "success",
          title: "Push configuration Added Successfully",
          showConfirmButton: true,
          timer: 3000,
        }).then(() => {
          window.location.href = `${base_url}/master/pushConfiguration`;
        });
      } else {
        // Use Swal for a consistent user experience
        Swal.fire({
          position: "top",
          icon: "warning",
          title: data,
          showConfirmButton: false,
          timer: 3000,
        });
      }
    });
    
    $("#speedLoadercheck").hide();
  }
}
// -----------------------------------------------------------------------

function getCommandsonTemplate() {
  var selectedOption = $("#temp").val();

  var l = window.location;
  var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
  var ServiceUrl = `${base_url}/master/fetchCommand`;

  $.ajax({
    type: "GET",
    url: ServiceUrl,
    data: "option=" + selectedOption,
    dataType: "json",
    success: function (data) {
      displayFetchedData(data);
      console.log(data);
    },
    error: function (error) {
      console.error("Error fetching data:", error);
    },
  });
  function displayFetchedData(data) {
    var resultContainer = $("#command");
    resultContainer.empty(); // Clear previous content

    // Assuming data is an array of items, adjust this part based on your data
	// structure
    for (var i = 0; i < data.length; i++) {
      resultContainer.append("<p>" + data[i].name + "</p>"); // Adjust to
																// display
																// relevant data
    }
  }
}

// Date range as a button
$("#daterange-btn").daterangepicker(
  {
    timePicker: true,
    timePickerIncrement: 10,
    ranges: {
      Today: [
        moment().hours(0).minutes(0).seconds(0),
        moment().hours(23).minutes(59).seconds(59),
      ],
      Yesterday: [
        moment().hours(0).minutes(0).seconds(0).subtract(1, "days"),
        moment().hours(23).minutes(59).seconds(59).subtract(1, "days"),
      ],
      "Last 7 Days": [
        moment().hours(0).minutes(0).seconds(0).subtract(6, "days"),
        moment().hours(23).minutes(59).seconds(59),
      ],
      "Last 30 Days": [
        moment().hours(0).minutes(0).seconds(0).subtract(29, "days"),
        moment().hours(23).minutes(59).seconds(59),
      ],
      "This Month": [moment().startOf("month"), moment().endOf("month")],
      "Last Month": [
        moment().subtract(1, "month").startOf("month"),
        moment().subtract(1, "month").endOf("month"),
      ],
    },
    startDate: moment().subtract(29, "days"),
    endDate: moment(),
  },
  function (start, end) {
    var from_date = document.getElementById("from_date");
    from_date.value = start.format("YYYY-MM-DD HH:mm:ss");
    var to_date = document.getElementById("to_date");
    to_date.value = end.format("YYYY-MM-DD HH:mm:ss");
  }
);

$("#reservationtime").daterangepicker(
  {
    timePicker: true,
    timePickerIncrement: 10,
    locale: {
      format: "MM/DD/YYYY hh:mm:ss",
    },
  },
  function (start, end) {
    var from_date = document.getElementById("from_date");
    from_date.value = start.format("YYYY-MM-DD HH:mm:ss");
    var to_date = document.getElementById("to_date");
    to_date.value = end.format("YYYY-MM-DD HH:mm:ss");
  }
);

$(function () {
  $("[data-mask]").inputmask();
  // Bootstrap Duallistbox
});

// Report generate

$(function () {
  $.validator.setDefaults({
    submitHandler: function () {
      pushConfGenerateReport();
    },
  });
  $("#pushConfiReportid").validate({
    rules: {
      from_date: {
        required: true,
      },
      to_date: {
        required: true,
      },

      group_name: {
        required: true,
      },
    },
    messages: {
      from_date: {
        required: "Please select date range picker",
      },
      to_date: {
        required: "",
      },
      group_name: {
        required: "Please enter choose Group Name",
      },
    },
    errorElement: "span",
    errorPlacement: function (error, element) {
      error.addClass("invalid-feedback");
      element.closest(".form-group").append(error);
    },
    highlight: function (element, errorClass, validClass) {
      $(element).addClass("is-invalid");
    },
    unhighlight: function (element, errorClass, validClass) {
      $(element).removeClass("is-invalid");
    },
  });
});
// ------------------------------------------------------------------
function pushConfGenerateReport() {
  var val = [];
  $(".checkers:checked").each(function (i) {
    val[i] = $(this).val();
  });

  var ipAddress = val.join(",");

  if (!ipAddress.length > 0) {
    Swal.fire({
      position: "top",
      icon: "info",
      title: "Please Select atleast one checkbox",
      showConfirmButton: false,
      timer: 3000,
    });
  } else {
    var temp = new Array();
    temp = values.toString().split(",");

    var l = window.location;
    var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
    var serviceUrl = `${base_url}/master/pushConfiReport`;
    var ip_address = [];

    $.ajax({
      type: "POST",
      url: serviceUrl,
      data: {
        ip_address: ip_address,
        from_date: $("#from_date").val(),
        to_date: $("#to_date").val(),
      },
    }).done(function (data) {
      sessionStorage.setItem("pushConfigurationData", data);
      var l = window.location;
      var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
      var serviceUrl = `${base_url}/master/viewPushConfiguration`;
      window.location.href = serviceUrl;
    });
  }
}
