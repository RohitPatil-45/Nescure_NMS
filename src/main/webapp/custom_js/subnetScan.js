$(function () {
  $.validator.setDefaults({
    submitHandler: function () {
      addUpdate();
    },
  });
  $("#subnetScanId").validate({
    rules: {
      subnetScan: {
        required: true,
      },
      endsubnetScan: {
        required: true,
      },
    },
    messages: {
      subnetScan: {
        required: "Please provide Start IP Address",
      },
      endsubnetScan: {
        required: "Please provide Subnet Mask Address",
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

function editSubnetScan(id) {
alert("helo123");
  var l = window.location;
  var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
  var serviceUrl = base_url + "/master/editSubnetScan";

  $.ajax({
    type: "POST",
    url: serviceUrl,
    data: { id: id }, // Pass data as an object
    success: function (data) {
      alert(data);
      // Handle the data as needed for editing
    },
    error: function (xhr, status, error) {
      console.error("AJAX Request Failed:", status, error);
      // Handle the error as needed
    },
  });
}


function updateSubnetScan() {
  var l = window.location;
  var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
  var serviceUrl = base_url + "/master/updateSubnetScan";
  $.ajax({
    type: "POST",
    url: serviceUrl,
    data:$("#subnetScanId").serialize(),
  }).done(function (data) {
    console.log(data);

    if (data === "success") {
      Swal.fire({
        position: "top",
        icon: "success",
        title: "Update Subnet SCAN Data Successfully",
        showConfirmButton: true,
        timer: 3000,
      }).then(() => {
        var l = window.location;
        var base_url =
          l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
        window.location.href = base_url + "/master/viewSubnetScan";
      });
    } else {
      Swal.fire({
        position: "top",
        icon: "warning",
        title: "Failed",
        showConfirmButton: false,
        timer: 3000,
      });
    }
  });
}

function deleteSubnetScan(id) {
  Swal.fire({
    position: "top",
    title: "Are you sure?",
    text: "You want to delete the record:" + id,
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Yes, delete it!",
  }).then((result) => {
    if (result.value) {
      var l = window.location;
      var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
      var serviceUrl = base_url + "/master/deleteSubnetScan";

      // Use $.ajax for more control and error handling
      $.ajax({
        type: "POST",
        url: serviceUrl,
        data: { id: id }, // Pass data as an object
        success: function (data) {
          if (data === "success") {
            Swal.fire({
              position: "top",
              icon: "success",
              title: "Delete Record Successfully",
              showConfirmButton: false,
              timer: 3000,
            }).then(() => {
              var l = window.location;
              var base_url =
                l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
              window.location.href = base_url + "/master/viewSubnetScan";
            });
          } else {
            Swal.fire({
              position: "top",
              icon: "warning",
              title: "Record Not Deleted",
              showConfirmButton: false,
              timer: 3000,
            });
          }
        },
        error: function (xhr, status, error) {
          console.error("AJAX Request Failed:", status, error);
          // Handle the error as needed
        },
      });
    }
  });
}

function addUpdate() {
  var dbid = $("#id").val();
  //alert("DB ID"+dbid)

  if (dbid == 0) {
    addSubnetScan();
  } else {
    //alert("Update action call"+dbid)
    updateSubnetScan();
  }
}

function addSubnetScan() {
  var l = window.location;
  var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
  var serviceUrl = base_url + "/master/addSubnetScan";
  $.ajax({
    type: "POST",
    url: serviceUrl,
    data:
      "groupName=" +
      $("#groupName").val() +
      "&subnetScan=" +
      $("#subnetScan").val() +
      "&endsubnetScan=" +
      $("#endsubnetScan").val(),
  }).done(function (data) {
    // alert(data)
    console.log(data);

    if (data === "success") {
      Swal.fire({
        position: "top",
        icon: "success",
        title: "Add Subnet Scan Successfull",
        showConfirmButton: true,
        timer: 3000,
      }).then(() => {
        var l = window.location;
        var base_url =
          l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
        window.location.href = base_url + "/master/subnetScan";
      });
    } else {
      Swal.fire({
        position: "top",
        icon: "warning",
        title: "Failed ! Please Try Again..",
        showConfirmButton: false,
        timer: 3000,
      });
    }
  });
}
