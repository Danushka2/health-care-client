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
            field: 'reference',
            align: 'center',
            valign: 'middle'
        }, {
            title: 'NAME',
            field: 'appointmentInfo.appointmentDate',
            align: 'center',
            valign: 'middle'
        }, {
            title: 'EMAIL',
            field: 'appointmentInfo.session.from',
            align: 'center',
            valign: 'middle'
        }, {
            title: 'ADDRESS',
            field: 'appointmentInfo.patient.name',
            align: 'center',
            valign: 'middle'
        }, {
            title: 'TYPE',
            field: 'fee',
            align: 'center',
            class: 'fee'
        }, {
            title: 'TELEPHONE',
            field: 'paidOn',
            align: 'center',
            sortable: true
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