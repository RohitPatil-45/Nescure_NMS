window.onload = function () {
  $("#groupname").multiselect({
    enableFiltering: true,
    includeSelectAllOption: true,
    buttonWidth: "100%",
    maxHeight: 350,
  });
  $("#location").multiselect({
    enableFiltering: true,
    includeSelectAllOption: true,
    buttonWidth: "100%",
    maxHeight: 350,
  });
  $("#admin").multiselect({
    enableFiltering: true,
    includeSelectAllOption: true,
    buttonWidth: "100%",
    maxHeight: 350,
  });
  $("#dashboard").multiselect({
    enableFiltering: true,
    includeSelectAllOption: true,
    buttonWidth: "100%",
    maxHeight: 350,
  });
  $("#report").multiselect({
    enableFiltering: true,
    includeSelectAllOption: true,
    buttonWidth: "100%",
    maxHeight: 350,
  });
  $("#graph").multiselect({
    enableFiltering: true,
    includeSelectAllOption: true,
    buttonWidth: "100%",
    maxHeight: 350,
  });
};
//-----------------------------------------------------------------------------------------
$(function () {
  $.validator.setDefaults({
    submitHandler: function () {
      addUserScope();
    },
  });
  $("#userScope").validate({
    rules: {
      username: {
        required: true,
      },
      groupname: {
        needsSelection: true,
      },
      location: {
        required: true,
      },
      admin: {
        required: true,
      },
      dashboard: {
        required: true,
      },
      report: {
        required: true,
      },
    },
    messages: {
      username: {
        required: "Please Choose User name",
      },
      groupname: {
        required: "Please Choose Group Name",
      },
      location: {
        required: "Please enter Location",
      },
      admin: {
        required: "Please Choose Admin Field",
      },
      dashboard: {
        required: "Please Choose Dashboard Field",
      },
      report: {
        required: "Please Choose Report Field",
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
function addUserScope() {
  var l = window.location;
  var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;

  var addServiceUrl = `${base_url}/master/addUserManageScope`;

  var username = $("#username option:selected");
  var groupname = $("#groupname option:selected");
  var location = $("#location option:selected");
  var admin = $("#admin option:selected");
  var dashboard = $("#dashboard option:selected");
  var report = $("#report option:selected");
  var graph = $("#graph option:selected");
  if (username.length === 0) {
    Swal.fire({
      position: "top",
      icon: "warning",
      title: "Please choose at least one User Name",
      showConfirmButton: false,
      timer: 3000,
    });
  } else if (groupname.length === 0) {
    Swal.fire({
      position: "top",
      icon: "warning",
      title: "Please choose at least one Group Name",
      showConfirmButton: false,
      timer: 3000,
    });
  } else if (location.length === 0) {
    Swal.fire({
      position: "top",
      icon: "warning",
      title: "Please choose at least one Location",
      showConfirmButton: false,
      timer: 3000,
    });
  } else if (admin.length === 0) {
    Swal.fire({
      position: "top",
      icon: "warning",
      title: "Please choose at least one Admin Name",
      showConfirmButton: false,
      timer: 3000,
    });
  } else if (dashboard.length === 0) {
    Swal.fire({
      position: "top",
      icon: "warning",
      title: "Please choose at least one Dashboard Name",
      showConfirmButton: false,
      timer: 3000,
    });
  } else if (report.length === 0) {
    Swal.fire({
      position: "top",
      icon: "warning",
      title: "Please choose at least one Report Name",
      showConfirmButton: false,
      timer: 3000,
    });
  }else if (graph.length === 0) {
    Swal.fire({
      position: "top",
      icon: "warning",
      title: "Please choose at least one Graph Name",
      showConfirmButton: false,
      timer: 3000,
    });
  } else {
    $.ajax({
      type: "POST",
      url: addServiceUrl,
      data: $("#userScope").serialize(),
    }).done(function (data) {
      if (data === "success") {
        Swal.fire({
          position: "top",
          icon: "success",
          title: "User Scope Added Successfully",
          showConfirmButton: true,
          timer: 3000,
        }).then(() => {
          var l = window.location;
          var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
          window.location.href = `${base_url}/master/userManageScopeForm`;
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
}
//-----------------------------------------------------------------------------------------

function deleteUserManageScope(id) {
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
      var base_url =
        l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
      var serviceUrl = base_url + "/master/deleteUserManageScope";
      $.ajax({
        type: "POST",
        url: serviceUrl,
        data: "id=" + id,
      }).done(function (data) {
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
            window.location.href = base_url + "/master/viewUserManageScope";
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
      });
    }
  });
}
