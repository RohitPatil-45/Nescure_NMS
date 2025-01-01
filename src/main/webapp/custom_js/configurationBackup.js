$("#addConfigurationBackup").click(function () {
  
  if ($("#configBackupTemplate").valid()) {
    var l = window.location;
    var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
    /* serviceUrl = `${base_url}/master/addConfigurationBackup`;*/
    var addServiceUrl = `${base_url}/master/addConfigurationBackup`;
    var updateUrl = `${base_url}/master/updateConfigurationBackup`;
    
     var selectedIPs = $("#deviceIp option:selected");
       if (selectedIPs.length === 0) {
        Swal.fire({
            position: "top",
            icon: "warning",
            title: "Please select at least one IP address.",
            showConfirmButton: false,
            timer: 3000,
          });
         }
    $.ajax({
      type: "POST",
      /*  url:serviceUrl ,*/
      url: $("#ID").val() == 0 ? addServiceUrl : updateUrl,
      /*data: {
      id: $("#ID").val(),
        deviceIp: $("#deviceIp").val(),
        enablePass: $("#enablePass").val(),
        deviceMode: $("#deviceMode").val(),
        confCommand: $("#confCommand").val(),	
        username: $("#username").val(),
        foCommand: $("#foCommand").val(),
        password: $("#password").val(),
      },*/
      data: $("#configBackupTemplate").serialize(),
    }).done(function (data) {
      if (data!=null) {
        Swal.fire({
          position: "top",
          icon: "success",
          /* title: "Add Backup Configuration Successfully",*/
          title: $("#ID").val() == 0 ? `Configuration Credential ${data} Successfully` : "Configuration Credential updated Successfully",
          showConfirmButton: true,
          timer: 3000,
        }).then(() => {
          var l = window.location;
          var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
          window.location.href = `${base_url}/master/configurationBackup`;
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
});

$(function () {
  var l = window.location;
  var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
  var serviceUrl = `${base_url}/master/viewConfigurationBackup`;
  $.ajax({
    type: "GET",
    url: serviceUrl,
    dataType: "json",
    success: function (data4) {
      // alert(data4)
      var table = $("#viewConfigurationBackup").DataTable({
        lengthChange: false,
        autoWidth: false,
        data: data4,
        pageLength: 5,
        scrollX: true,
        scrollY: true,
      });
      table.buttons().container().appendTo("#example1_wrapper .col-sm-6:eq(0)");
    },
  });
});


window.onload = function () {
  $("#deviceIp").multiselect({
    enableFiltering: true,
    includeSelectAllOption: true,
    buttonWidth: "100%",
    maxHeight: 350,
  });
}

  function deleteConfigurationBackup(ID) {
    Swal.fire({
      position: "top",
      title: "Are you sure?",
      text: "You want to delete the record:" + ID,
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085D6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, delete it!",
    }).then((result) => {
      if (result.value) {
        var l = window.location;
        var base_url =
          l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
        var serviceUrl = base_url + "/master/deleteConfigurationBackup";
        $.ajax({
          type: "POST",
          url: serviceUrl,
          data: "ID=" + ID,
        }).done(function (data) {
          if (data === "success") {
            Swal.fire({
              position: "top",
              icon: "success",
              title: "Record Delete Successfully",
              showConfirmButton: false,
              timer: 3000,
            }).then(() => {
              var l = window.location;
              var base_url =
                l.protocol + "//" + l.host + "/" + l.pathname.split("/")[1];
              window.location.href = base_url + "/master/configurationBackup";
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


  $(document).ready(function () {
    $.validator.setDefaults({
      submitHandler: function () {
        $("#configBackupTemplate").submit();
      },
    });



    $("#configBackupTemplate").validate({
      rules: {
		deviceIp: { required: true },
        confCommand: { required: true },
        username: { required: true },
        foCommand: { required: true },
        password: { required: true },
        enablePass: { required: true },
        
      },
      messages: {
		deviceIp: { required: "Please provide device ip" },
        confCommand: { required: "Please provide configuration command" },
        username: { required: "Please provide username" },
        foCommand: { required: "Please provide full command" },
        password: { required: "Please provide password" },
        enablePass: { required: "Please provide enable password" },
        
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