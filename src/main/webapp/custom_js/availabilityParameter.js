$(document).ready(function () {
  $("#availabilityParameter").validate({
    rules: {
      fromTime: { required: true },
      toTime: { required: true },
    },
    messages: {
      fromTime: { required: "Please provide From Time" },
      toTime: { required: "Please provide To Time" },
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
  $("#apSubmit").click(function () {
    if ($("#availabilityParameter").valid()) {
      var l = window.location;
      var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;

      var addServiceUrl = `${base_url}/node/addAvailabilityParameter`;
   
      $.ajax({
        type: "POST",
        url: addServiceUrl,
        data: $("#availabilityParameter").serialize(),
      }).done(function (data) {
        if (data === "success") {
          Swal.fire({
            position: "top",
            icon: "success",
            title:"Availability Parameter Added Successfully",
            showConfirmButton: true,
            timer: 3000,
          }).then(() => {
            var l = window.location;
            var base_url = `${l.protocol}//${l.host}/${
              l.pathname.split("/")[1]
            }`;
            window.location.href = `${base_url}/node/availabilityParameter`;
          });
        } else {
          Swal.fire({
            position: "top",
            icon: "warning",
            title: data,
            showConfirmButton: false,
            timer: 3000,
          });
        }
      });
    }
  });

  var l = window.location;
  var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
  var serviceUrl = `${base_url}/node/viewAvailabilityParameter`;
  $.ajax({
    type: "GET",
    url: serviceUrl,
    dataType: "json",
    success: function (data4) {
      var table = $("#example1").DataTable({
        lengthChange: true,
        autoWidth: false,
        data: data4,
        pageLength: 5,
        scrollX: true,
        scrollY: true,
        columns: [
        { width: '33%', targets: 0 },  // Adjust the width as needed
        { width: '33%', targets: 1 },
        { width: '34%', targets: 2 }
    ]
      });

      table.buttons().container().appendTo("#example1_wrapper .col-sm-6:eq(0)");
    },
  });
});