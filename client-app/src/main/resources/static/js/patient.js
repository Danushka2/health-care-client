/**
 * Created by Asus-pc on 5/4/2020 2:46 PM
 */

$(document).ready(function() {
    if ($("#PatientalertSuccess").text().trim() == "") {
        $("#PatientalertSuccess").hide();
    }
    $("#PatientalertError").hide();

    function insertPatient(){
        var obj = {name: $('#PatientName').val(), email:$('#PatientEmail').val(), phoneNo:$('#PatientPno').val(),age:$('#PatientAge').val(), gender:$('input[name="PatientrdoGender"]:checked').val(), username:$('#PatientUserName').val(), password:$('#PatientPassword').val()};
        alert(JSON.stringify(obj))
        jQuery.ajax({
            url: 'http://localhost:8080/patients',
            type: 'POST',
            data: JSON.stringify(obj),
            contentType: 'application/json',
            error:function(response,status){
                alert('error');
            },
            success:function(response, status){
                alert('OK');
            }
        });
    }

    $('#PatientbtnSave').click(function(){
        insertPatient();
    });

    function updatePatient(){
        var obj = {name: $('#PatientName').val(), email:$('#PatientEmail').val(), phoneNo:$('#PatientPno').val(),age:$('#PatientAge').val(), gender:$('input[name="PatientrdoGender"]:checked').val(), username:$('#PatientUserName').val(), password:$('#PatientPassword').val()};
        alert(JSON.stringify(obj))
        jQuery.ajax({
            url: 'http://localhost:8080/patients/'+$('#patientId').val(),
            type: 'PUT',
            data: JSON.stringify(obj),
            contentType: 'application/json',
            error:function(response,status){
                alert('error');
            },
            success:function(response, status){
                alert('OK');
            }
        });
    }

    function deletePatient(){
        jQuery.ajax({
            url: 'http://localhost:8080/patients/' + $('#patientId').val(),
            type: 'DELETE',
            error:function(response,status){
                alert('error');
            },
            success:function(response, status){
                alert('OK');
            }
        });
    }
    $('#PatientbtnUpdate').click(function(){
       updatePatient();
    });

    $('#PatientbtnDelete').click(function(){
        deletePatient();
    });

    function findPatientDetails(responseData){
        jQuery.ajax({
            url: 'http://localhost:8080/patients/'+$('#findPatientId').val(),
            type: 'GET',
            error:function(response,status){
                alert('error');
            },
            success:function(response, status){
                responseData(response);
            }
        });
    }

    $('#findPatientBtn').click(function(){
       findPatientDetails(function(response){
           $('#patientDetails').empty();
           var details = 'Name: '+response.name  +'<br> Email:'+response.email+'<br> Phone:'+response.phoneNumber+'<br>Age'+response.age+'<br>Gender: '+response.gender;
           $('#patientDetails').append(details);
           $('#patientModal').modal({
               backdrop : 'static',
               keyboard : false
           }).trigger('focus').modal('toggle').modal('show');
       });
    });

});






/*function validateItemForm()
{

    if ($("#PatientName").val().trim() == "")
    {
        return "Insert Patient Name.";
    }


    if ($("#PatientEmail").val().trim() == "")
    {
        return "Insert Email address.";
    }

    if($("#PatientEmail").val().trim() == "")
    {

        return "insert valid email address";
    }



    if ($("#PatientPno").val().trim() == "")
    {
        return "Insert patient phone number.";
    }






    if ($("#PatientAge").val().trim() == "")
    {
        return "Insert patient age.";
    }

    // is numerical value
    /*var tmpAge = $("#PatientAge").val().trim();
    {
        return "Insert a numerical value for patient age .";
    }*/


   /* if ($('input[name="PatientrdoGender"]:checked').val().trim() == "")
    {
        return "Insert patient gender.";
    }



    return true;
}



//SAVE ============================================
$(document).on("click", "#PatientbtnSave", function(event)
{
    // Clear alerts---------------------
    $("#PatientalertSuccess").text("");
    $("#PatientalertSuccess").hide();
    $("#PatientalertError").text("");
    $("#PatientalertError").hide();

    // Form validation-------------------
    var status = validateItemForm();
    if (status != true) {
        $("#PatientalertError").text(status);
        $("#PatientalertError").show();
        return;
    }

    // If valid------------------------
    //var type = ($("#PatienthidItemIDSave").val() == "") ? "POST" : "PUT";

    /*$.ajax(
        {
            url : "/patients",
            type : type,
            data : $("#formItem").serialize(),
            dataType : "text",
            complete : function(response, status)
            {
                onItemSaveComplete(response.responseText, status);
            }
        });*/


   /* jQuery.ajax({
        url : 'http://localhost:8080/patients/read',
        type : 'GET',
        timeout : 5000,
        success : function(response, status) {
            var arr = [];
            $.each(response, function(index, value) {
                var opt = '<option value = "' + value.id + '">'
                    + value.hospitalName + '</option>';
                arr.push(opt);
            });
            $('#allPatient').append(arr);

        }
    });

});
}
*/
