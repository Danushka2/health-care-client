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
        height: '700',
        columns: [{
            title: 'ID',
            field: 'id',
            align: 'center',
            valign: 'middle',
            sortable: true
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
            align: 'center'
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
    
        actions += '<button class="btn btn-link link-primary edit" data-toggle="modal" data-target="#update-modal" '
            + 'data-backdrop="static" data-id="' + row.id + '" title="' + "Edit" + '">'
            + '<i class="fad fa-edit"></i></button>'
            + '<button class="btn btn-link link-danger refund" data-toggle="modal" data-target="#delete-modal" '
            + 'data-backdrop="static" data-toggle="tooltip" data-id="' + row.id + '" title="' + "Delete" + '">'
            + '<i class="fas fa-trash-alt"></i></button>';
    
    return actions;
}

$('#create-modal').on('show.bs.modal', function (e) {
    $(this).find('form:first').parsley().reset();
}).on('hidden.bs.modal', function (e) {
    $('#h-create-form input[name="username"]').val('');
    $('#h-create-form input[name="name"]').val('');
    $('#h-create-form input[name="address"]').val('');
    $('#h-create-form input[name="email"]').val('');
    $('#h-create-form input[name="fax"]').val('');
    $('#h-create-form input[name="tel"]').val('');
    $('#h-create-form input[name="password"]').val('');
});

$('#h-create-form').on('submit', function (e) {
    e.preventDefault();
    var $form = $(this);
    if ($form.parsley().validate()) {
        ajaxFormJson($form, function (response) {
            hideProgress();
            $('#create-modal').modal('hide');
            showNotification('success', 'Successfully Added!');
            $table.bootstrapTable('refresh');
        });
    }
});

$('#delete-modal').on('show.bs.modal', function (e) {
    var $invoker = $(e.relatedTarget);
    var id = $invoker.data('id');
    $('#h-delete-form').attr('action', HOSPITAL_DELETE_URL.replace('{id}', id));
}).on('hidden.bs.modal', function (e) {
    $('#h-delete-form').attr('action', HOSPITAL_DELETE_URL);
});

$('#h-delete-form').on('submit', function (e) {
    e.preventDefault();
    var $form = $(this);
    ajaxFormJson($form, function (response) {
        hideProgress();
        $('#delete-modal').modal('hide');
        showNotification('success', 'Successfully Deleted!');
        $table.bootstrapTable('refresh');
    });
});

$('#update-modal').on('show.bs.modal', function (e) {
    $(this).find('form:first').parsley().reset();
    var $invoker = $(e.relatedTarget);
    var id = $invoker.data('id');
    $('#h-update-form').attr('action', HOSPITAL_UPDATE_URL.replace('{id}', id));
    ajaxGet('/hospitals/' + id, false, false, function (response) {
        $('#h-update-form input[name="id"]').val(response.id);
        $('#h-update-form input[name="name"]').val(response.hospitalName);
        $('#h-update-form input[name="address"]').val(response.hospitalAddress);
        $('#h-update-form input[name="email"]').val(response.hospitalEmail);
        $('#h-update-form input[name="fax"]').val(response.hospitalFax);
        $('#h-update-form input[name="tel"]').val(response.hospitalTell);
        $('#h-update-form input[name="type"]').val(response.hospitalType);
        $('#h-update-form input[name="status"]').val(response.hospitalStatus);
    });
}).on('hidden.bs.modal', function (e) {
    $('#h-update-form input[name="id"]').val('');
    $('#h-update-form input[name="name"]').val('');
    $('#h-update-form input[name="address"]').val('');
    $('#h-update-form input[name="email"]').val('');
    $('#h-update-form input[name="fax"]').val('');
    $('#h-update-form input[name="tel"]').val('');
    $('#h-update-form input[name="type"]').val('');
    $('#h-update-form input[name="status"]').val('');
    $('#h-update-form form').attr('action', HOSPITAL_UPDATE_URL);
});

$('#h-update-form').on('submit', function (e) {
    e.preventDefault();
    var $form = $(this);
    if ($form.parsley().validate()) {
        ajaxFormJson($form, function (response) {
            hideProgress();
            $('#update-modal').modal('hide');
            showNotification('success', 'Successfully Updated!');
            $table.bootstrapTable('refresh');
        });
    }
});