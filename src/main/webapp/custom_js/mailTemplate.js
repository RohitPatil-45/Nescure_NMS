window.onload = function () {
  $("#ipAddressList").multiselect({
    enableFiltering: true,
    includeSelectAllOption: true,
    buttonWidth: "100%",
    maxHeight: 350,
  });
};
$(document).ready(function () {
  $("#mailTemplate").validate({
    rules: {
      templateName: { required: true },
      subject: { required: true },
      sendTime: { required: true },
      ipAddressList: { required: true },
      functionName: { required: true },
      msgBody: { required: true },
      attachment: { required: true },
      toMail: { required: true, email: true },
      periods: { required: true },
      attachmentType: { required: true },
      ccMail: { required: true, email: true },
      days: { required: true },
      isActive: { required: true },
      phone :{ required: true, phoneUS: true},
    },
    messages: {
      templateName: { required: "Please provide Template Name" },
      subject: { required: "Please provide Subject" },
      sendTime: { required: "Please provide Send Time" },
      ipAddressList: { required: "Please provide Ip Address List" },
      functionName: { required: "Please provide Function Name" },
      msgBody: { required: "Please provide Massage Body" },
      attachment: { required: "Please provide Attachment" },
      toMail: { required: "Please provide To Mail Addesss" },
      periods: { required: "Please provide Periods" },
      attachmentType: { required: "Please provide Attachment Type" },
      ccMail: { required: "Please provide CC Mail" },
      days: { required: "Please provide Days" },
      isActive: { required: "Please provide Status" },
      phone :{required: "Please Enter Correct Mobile Number"},
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
  $("#mailSubmit").click(function () {
    if ($("#mailTemplate").valid()) {
      var l = window.location;
      var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;

      var addServiceUrl = `${base_url}/master/addMailTemplate`;
      var updateUrl = `${base_url}/master/updateMailTemplate`;
      
      var selectedIPs = $("#ipAddressList option:selected");
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
        url: $("#SR_NO").val() == 0 ? addServiceUrl : updateUrl,
        data: $("#mailTemplate").serialize(),
      }).done(function (data) {
        if (data === "success") {
          Swal.fire({
            position: "top",
            icon: "success",
            title:
              $("#SR_NO").val() == 0
                ? "Mail Template Added Successfully"
                : "Mail template updated successfully",
            showConfirmButton: true,
            timer: 3000,
          }).then(() => {
            var l = window.location;
            var base_url = `${l.protocol}//${l.host}/${
              l.pathname.split("/")[1]
            }`;
            window.location.href = `${base_url}/master/mailTemplate`;
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
  var serviceUrl = `${base_url}/master/viewMailTemplate`;
  $.ajax({
    type: "GET",
    url: serviceUrl,
    dataType: "json",
    success: function (data4) {
      var table = $("#example1").DataTable({
    	 order : [ [ 1, 'asc' ] ],
			responsive : true,
			lengthChange : false,
			autoWidth : false,
			scrollX : true,
        data: data4,
        pageLength: 5,
        scrollX: true,
        scrollY: true,
      });
      
      table.buttons().container().appendTo("#example1_wrapper .col-sm-6:eq(0)");
    },
  });
});

function deleteMailTemplate(SR_NO) {
  Swal.fire({
    position: "top",
    title: "Are you sure?",
    text: "You want to delete the record:" + SR_NO,
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Yes, delete it!",
  }).then((result) => {
    if (result.value) {
      var l = window.location;
      var base_url = `${l.protocol}//${l.host}/${l.pathname.split("/")[1]}`;
      var serviceUrl = `${base_url}/master/deleteMailTemplate`;
      $.ajax({
        type: "POST",
        url: serviceUrl,
        data: "SR_NO=" + SR_NO,
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
            var base_url = `${l.protocol}//${l.host}/${
              l.pathname.split("/")[1]
            }`;
            window.location.href = `${base_url}/master/mailTemplate`;
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
