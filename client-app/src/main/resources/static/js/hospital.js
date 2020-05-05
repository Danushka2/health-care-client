var HOSPITAL_DELETE_URL = '/hospitals/{id}';
var HOSPITAL_UPDATE_URL = '/hospitals/{id}';
var $table = $('#hospital-table');
var selections = [];

$(function () {
    $('#hospital-menu').addClass('active');
    $('#hospital-menu').siblings().removeClass('active');
    initTable();
});

function initTable() {
    $table.bootstrapTable('destroy').bootstrapTable({
        height: '900',
        columns: [{
            title: 'ID',
            field: 'id',
            align: 'center',
            valign: 'middle'
        }, {
            title: 'NAME',
            field: 'hospitalName',
            align: 'center',
            valign: 'middle'
        }, {
            title: 'EMAIL',
            field: 'hospitalEmail',
            align: 'center',
            valign: 'middle'
        }, {
            title: 'ADDRESS',
            field: 'hospitalAddress',
            align: 'center',
            valign: 'middle'
        }, {
            title: 'TYPE',
            field: 'hospitalType',
            align: 'center',
            class: 'fee'
        }, {
            title: 'TELEPHONE',
            field: 'hospitalTell',
            align: 'center',
            sortable: true
        }, {
            title: COL_ACTIONS,
            align: 'center',
            events: window.actionEvents,
            formatter: actionFormatter
        }],
        classes: 'table',
    });

    $table.on('load-success.bs.table', function (data) {
        $('.info').popover({
            html: true,
            placement: 'bottom'
        });
    });
}

function actionFormatter(value, row, index) {
    var actions = '';
    
        actions += '<button class="btn btn-link link-primary edit" data-toggle="modal" data-target="#update-form" '
            + 'data-backdrop="static" data-id="' + row.id + '" title="' + "Edit" + '">'
            + '<i class="fad fa-edit"></i></button>'
            + '<button class="btn btn-link link-danger refund" data-toggle="modal" data-target="#delete-form" '
            + 'data-backdrop="static" data-toggle="tooltip" data-id="' + row.id + '" title="' + "Delete" + '">'
            + '<i class="fas fa-trash-alt"></i></button>';
    
    return actions;
}


$('#create-form form').on('submit', function (e) {
    e.preventDefault();
    var $form = $(this);
    if ($form.parsley().validate()) {
        ajaxFormJson($form, function (response) {
            hideProgress();
            $('#create-form').modal('hide');
            showNotification('success', 'Successfully Added!');
            $table.bootstrapTable('refresh');
        });
    }
});

$('#delete-form').on('show.bs.modal', function (e) {
    var $invoker = $(e.relatedTarget);
    var id = $invoker.data('id');
    $('#delete-form form').attr('action', HOSPITAL_DELETE_URL.replace('{id}', id));
}).on('hidden.bs.modal', function (e) {
    $('#delete-form form').attr('action', HOSPITAL_DELETE_URL);
});

$('#delete-form form').on('submit', function (e) {
    e.preventDefault();
    var $form = $(this);
    ajaxFormJson($form, function (response) {
        hideProgress();
        $('#delete-form').modal('hide');
        showNotification('success', 'Successfully Deleted!');
        $table.bootstrapTable('refresh');
    });
});

$('#update-form').on('show.bs.modal', function (e) {
    $(this).find('form:first').parsley().reset();
    var $invoker = $(e.relatedTarget);
    var id = $invoker.data('id');
    $('#update-form form').attr('action', HOSPITAL_UPDATE_URL.replace('{id}', id));
    ajaxGet('/hospitals/' + id, false, false, function (response) {
        $('#update-form input[name="id"]').val(response.id);
        $('#update-form input[name="name"]').val(response.hospitalName);
        $('#update-form input[name="address"]').val(response.hospitalAddress);
        $('#update-form input[name="email"]').val(response.hospitalEmail);
        $('#update-form input[name="fax"]').val(response.hospitalFax);
        $('#update-form input[name="tel"]').val(response.hospitalTell);
        $('#update-form input[name="type"]').val(response.hospitalType);
        $('#update-form input[name="status"]').val(response.hospitalStatus);
    });
}).on('hidden.bs.modal', function (e) {
    $('#update-form input[name="id"]').val('');
    $('#update-form input[name="name"]').val('');
    $('#update-form input[name="address"]').val('');
    $('#update-form input[name="email"]').val('');
    $('#update-form input[name="fax"]').val('');
    $('#update-form input[name="tel"]').val('');
    $('#update-form input[name="type"]').val('');
    $('#update-form input[name="status"]').val('');
    $('#update-form form').attr('action', HOSPITAL_UPDATE_URL);
});

$('#update-form form').on('submit', function (e) {
    e.preventDefault();
    var $form = $(this);
    if ($form.parsley().validate()) {
        ajaxFormJson($form, function (response) {
            hideProgress();
            $('#update-form').modal('hide');
            showNotification('success', 'Successfully Updated!');
            $table.bootstrapTable('refresh');
        });
    }
});