const DOCTOR_DELETE_URL = '/doctors/{id}',
    DOCTOR_UPDATE_URL = '/doctors/{id}';

var $table = $('#doctor-tbl');
$(function () {
    $('#doctor-menu').addClass('active');
    $('#doctor-menu').siblings().removeClass('active');
    initTable();
});


function actionFormatter(value, row, index) {
    var actions = '';


    actions += '<button class="btn btn-link link-primary " data-toggle="modal"' +
        ' data-target="#UpdateDoctorModal" '
        + 'data-backdrop="static" data-id="' + row.id + '" title="' + Update + '">'
        + '<i class="fad fa-edit"></i></button>'
        + '<button class="btn btn-link link-danger " data-toggle="modal" data-target="#DeleteDoctorModal" '
        + 'data-backdrop="static" data-toggle="tooltip" data-id="' + row.id + '" title="' + Delete + '">'
        + '<i class="fad fa-undo"></i></button>';


    return actions;
}


function initTable() {
    $table.bootstrapTable('destroy').bootstrapTable({
        height: '900',
        columns: [{
            title: 'id',
            field: 'id',
            align: 'left',
            valign: 'middle',

        }, {
            title: 'Specialization',
            field: 'specialization',
            align: 'left',
            valign: 'middle',

        }, {
            title: 'Name',
            field: 'name',
            align: 'left',
            valign: 'middle'
        }, {
            title: 'Phone',
            field: 'tel',
            align: 'left',
            valign: 'middle'
        }, {
            title: 'Email',
            field: 'email',
            align: 'right',
            valign: 'middle'


        }, {
            title: 'Status',
            field: 'status',
            align: 'left',
            valign: 'middle',

        }, {
            title: 'Action',
            align: 'center',
            formatter: actionFormatter
        }
        ],
        classes: 'table',
    });

    $table.on('load-success.bs.table', function (data) {
        $('.info').popover({
            html: true,
            placement: 'bottom'
        });
    });
}


$('#makeDoctorModal form').on('submit', function (e) {
    e.preventDefault();


    ajaxJson('/doctors', 'POST', {
        "specialization": $('#specialization').val(),
        "name": $('#name').val(),
        "tel": $('#tel').val(),
        "email": $('#email').val(),
        "status": $('#status').val(),
        "username": $('#username').val(),
        "password": $('#password').val(),

    }, function () {
        hideProgress();
        $('#makeDoctorModal').modal('hide');
        showNotification('success', "Successfully Add");
        $table.bootstrapTable('refresh');
    });


});


$('#DeleteDoctorModal').on('show.bs.modal', function (e) {
    var $invoker = $(e.relatedTarget);
    var id = $invoker.data('id');
    $('#DeleteDoctorModal form').attr('action', DOCTOR_DELETE_URL.replace('{id}', id));
}).on('hidden.bs.modal', function (e) {
    $('#DeleteDoctorModal form').attr('action', DOCTOR_DELETE_URL);

});

$('#DeleteDoctorModal form').on('submit', function (e) {
    e.preventDefault();
    if ($(this).parsley().validate()) {
        ajaxFormJson($(this), function (response) {
            hideProgress();
            $('#DeleteDoctorModal').modal('hide');
            showNotification('success', Delete_success);
            $table.bootstrapTable('refresh');
        });
    }
});


$('#UpdateDoctorModal').on('show.bs.modal', function (e) {
    $(this).find('form:first').parsley().reset();
    var $invoker = $(e.relatedTarget);
    var id = $invoker.data('id');

    $('#UpdateDoctorModal form').attr('action', DOCTOR_UPDATE_URL.replace('{id}', id));
    //$('#UpdateDoctorModal input[name="ide"]').val(id);

    ajaxGet('/doctors/' + id, false, false, function (response) {
        $('#UpdateDoctorModal input[name="ide"]').val(response.id);
        $('#UpdateDoctorModal input[name="specializationn"]').val(response.specialization);
        $('#UpdateDoctorModal input[name="namee"]').val(response.name);
        $('#UpdateDoctorModal input[name="phonee"]').val(response.tel);
        $('#UpdateDoctorModal input[name="emaill"]').val(response.email);
    });


}).on('hidden.bs.modal', function (e) {
    $('#UpdateDoctorModal input[name="ide"]').val('');
    $('#UpdateDoctorModal form').attr('action', DOCTOR_UPDATE_URL);
});

$('#UpdateDoctorModal form').on('submit', function (e) {
    e.preventDefault();
    var $form = $(this);
    ajaxFormJson($form, function (response) {
        var id = $('#UpdateDoctorModal input[name="ide"]').val();

        ajaxJson('/doctors/' + id, 'PUT', {
            "specialization": $('#specializationn').val(),
            "name": $('#namee').val(),
            "tel": $('#phonee').val(),
            "email": $('#emaill').val()


        }, function () {
            hideProgress();
            $('#UpdateDoctorModal').modal('hide');
            showNotification('success', Update_Success);
            $table.bootstrapTable('refresh');
        });


    });


});


