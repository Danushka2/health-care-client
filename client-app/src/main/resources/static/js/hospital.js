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