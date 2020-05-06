/**
 * Created by Asus-pc on 5/4/2020 2:46 PM
 */

$(document).ready(function () {
    if ($("#PatientalertSuccess").text().trim() == "") {
        $("#PatientalertSuccess").hide();
    }
    $("#PatientalertError").hide();
    $("#PatientbtnUpdate2").hide();

    function insertPatient() {
        var obj = {
            name: $('#PatientName').val(),
            email: $('#PatientEmail').val(),
            phoneNo: $('#PatientPno').val(),
            age: $('#PatientAge').val(),
            gender: $('input[name="PatientrdoGender"]:checked').val(),
            user_name: $('#PatientUserName').val(),
            password: $('#PatientPassword').val()
        };
        jQuery.ajax({
            url: 'http://localhost:8080/patients',
            type: 'POST',
            data: JSON.stringify(obj),
            contentType: 'application/json',
            error: function (response, status) {
                alert('error');
            },
            success: function (response, status) {
                alert('OK');
                $('#PatientRegistration')[0].reset();
            }
        });
    }


    $('#PatientbtnSave').click(function () {

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
        insertPatient();

    });

    function updatePatient() {
        var obj = {
            name: $('#PatientName').val(),
            email: $('#PatientEmail').val(),
            phoneNumber: $('#PatientPno').val(),
            age: $('#PatientAge').val(),
            gender: $('input[name="PatientrdoGender"]:checked').val(),
            status : $('#status').val()
        };
        jQuery.ajax({
            url: 'http://localhost:8080/patients/' + $('#patientId').val(),
            type: 'PUT',
            data: JSON.stringify(obj),
            contentType: 'application/json',
            error: function (response, status) {
                alert('error');
            },
            success: function (response, status) {
                alert('OK');
            }
        });
    }

    function deletePatient() {
        jQuery.ajax({
            url: 'http://localhost:8080/patients/' + $('#patientId').val(),
            type: 'DELETE',
            error: function (response, status) {
                alert('error');
            },
            success: function (response, status) {
                alert('OK');
            }
        });
    }

    $('#PatientbtnUpdate').click(function () {
        findPatientDetails($('#patientId').val(), function (response) {
            $('#patientDetails').empty();
            var details = 'Name: ' + response.name + '<br> Email:' + response.email + '<br> Phone:' + response.phoneNumber + '<br>Age' + response.age + '<br>Gender: ' + response.gender;
            $('#patientDetails').append(details);
            $('#PatientName').val(response.name);
            $('#PatientEmail').val(response.email);
            $('#PatientPno').val(response.phoneNumber);
            $('#PatientAge').val(response.age);
            if (response.gender.toLowerCase() == 'male') {
                $('#PatientrdoGenderMale').prop("checked", true);
            } else {
                $('#PatientrdoGenderFemale').prop("checked", true);
            }
            $('#status').val(response.status.id);
            $("#PatientbtnUpdate2").show();
            $("#PatientbtnUpdate").hide();
            $("#PatientbtnSave").hide();
            $("#userAccCredentials").hide();
        });
    });

    $("#PatientbtnUpdate2").click(function () {
        updatePatient();
        $("#PatientbtnUpdate").show();
        $("#PatientbtnUpdate2").hide();
        $("#PatientbtnSave").show();
        $("#userAccCredentials").show();
    });

    $('#PatientbtnDelete').click(function () {
        deletePatient();
    });

    function findPatientDetails(id, responseData) {
        jQuery.ajax({
            url: 'http://localhost:8080/patients/' + id,
            type: 'GET',
            error: function (response, status) {
                alert('error');
            },
            success: function (response, status) {
                responseData(response);
            }
        });
    }

    $('#findPatientBtn').click(function () {
        findPatientDetails($('#findPatientId').val(), function (response) {
            $('#patientDetails').empty();
            var details = 'Name: ' + response.name + '<br> Email:' + response.email + '<br> Phone:' + response.phoneNumber + '<br>Age' + response.age + '<br>Gender: ' + response.gender;
            $('#patientDetails').append(details);
            $('#patientModal').modal({
                backdrop: 'static',
                keyboard: false
            }).trigger('focus').modal('toggle').modal('show');
        });
    });

});


function validateItemForm() {

    if ($("#PatientName").val().trim() == "") {
        return "Insert Patient Name.";
    }

    if ($("#PatientName").val().length < 5) {
        return "Please input the patient name more than 5 characters";
    }

    if ($("#PatientEmail").val().trim() == "") {
        return "Insert Email address.";
    }

    if ($("#PatientEmail").val().trim() == "") {

        return "insert valid email address";
    }


    if ($("#PatientPno").val().trim() == "") {
        return "Insert patient phone number.";
    }
    if ($("#PatientPno").val().length < 10 || $("#PatientPno").val().length > 10) {
        return "inert valid phone number"
    }


    if ($("#PatientAge").val().trim() == "") {
        return "Insert patient age.";
    }


    if ($('input[name="PatientrdoGender"]:checked').val().trim() == "") {
        return "Insert patient gender.";
    }


    return true;
}



