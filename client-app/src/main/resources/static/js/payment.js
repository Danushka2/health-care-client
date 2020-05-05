/**
 * Created by හShaන් සNදීප on 5/2/2020 2:14 PM
 */
const PAY_MODIFY_URL = '/payments/{id}/modify/init', PAY_REFUND_URL = '/payments/{id}';
const STRIPE = Stripe('pk_test_ZihsequdXDHlLpxAmYjzqCUE00cujjwpI9');
const ELEMENTS = STRIPE.elements();
var card;

// Set up Stripe.js and Elements to use in checkout form
var style = {
    base: {
        color: "#32325d",
    }
};

//initialize stripe card element
initCardElement();

var $table = $('#payment-tbl');
$(function () {
    $('#payment-menu').addClass('active');
    $('#payment-menu').siblings().removeClass('active');
    initTable();
});


function actionFormatter(value, row, index) {
    var actions = '';
    if (row.status.toLowerCase() == PAY_STATUS_REFUND.toLowerCase()) {
        actions += '<button class="btn btn-link link-info info" data-trigger="hover" '
            + 'data-toggle="popover" title="' + LBL_REFUND_INFO + '" ';
        ajaxGet('/payments/' + row.id + '/refund-info', false, false, function (response) {
            actions += 'data-content="<div><b>' + LBL_REASON + '</b>\t' + response.body.reason + '</div>'
                + '<div><b>' + LBL_REFUND_ON + '</b>\t' + response.body.refundOn + '</div>"';
        });
        actions += ' data-id="' + row.id + '" title="' + LBL_REFUND_INFO + '"><i class="fad fa-info-circle"></i></button>';
    } else {
        actions += '<button class="btn btn-link link-primary edit" data-toggle="modal" data-target="#modifyPaymentModal" '
            + 'data-backdrop="static" data-id="' + row.id + '" title="' + LBL_MODIFY + '">'
            + '<i class="fad fa-edit"></i></button>'
            + '<button class="btn btn-link link-danger refund" data-toggle="modal" data-target="#refundPaymentModal" '
            + 'data-backdrop="static" data-toggle="tooltip" data-id="' + row.id + '" title="' + LBL_REFUND + '">'
            + '<i class="fad fa-undo"></i></button>';
    }
    return actions;
}

function totalTextFormatter(data) {
    return COL_TOT;
}

function totalCountFormatter(data) {
    return data.length;
}

function totalPriceFormatter(data) {
    var field = this.field;
    var tot = data.map(function (row) {
        if (row.status.toLowerCase() != PAY_STATUS_REFUND.toLowerCase()) {
            return +row[field];
        }
        return +0;
    }).reduce(function (sum, i) {
        return sum + i
    }, 0);
    return formatCurrency(tot);
}

function amountFormatter(value, row, index) {
    if (row.status.toLowerCase() == PAY_STATUS_REFUND.toLowerCase()) {
        value = -Math.abs(value);
    }
    return formatCurrency(value);
}

function rowFormatter(row, index) {
    if (row.status.toLowerCase() == PAY_STATUS_REFUND.toLowerCase()) {
        return {classes: 'row-refund'};
    }
    return {classes: ''};
}

function initTable() {
    $table.bootstrapTable('destroy').bootstrapTable({
        height: '900',
        columns: [{
            title: COL_REF,
            field: 'reference',
            align: 'left',
            valign: 'middle',
            footerFormatter: totalTextFormatter
        }, {
            title: COL_APPT_D,
            field: 'appointmentInfo.appointmentDate',
            align: 'left',
            valign: 'middle',
            footerFormatter: totalCountFormatter
        }, {
            title: COL_APPT_T,
            field: 'appointmentInfo.session.from',
            align: 'left',
            valign: 'middle'
        }, {
            title: COL_PT,
            field: 'appointmentInfo.patient.name',
            align: 'left',
            valign: 'middle'
        }, {
            title: COL_FEE,
            field: 'fee',
            align: 'right',
            valign: 'middle',
            class: 'fee',
            formatter: amountFormatter,
            footerFormatter: totalPriceFormatter
        }, {
            title: COL_PAID_ON,
            field: 'paidOn',
            align: 'left',
            valign: 'middle',
            sortable: true
        }, {
            title: COL_STATUS,
            field: 'status',
            align: 'left',
            valign: 'middle',
            sortable: true
        }, {
            title: COL_ACTIONS,
            align: 'center',
            events: window.actionEvents,
            formatter: actionFormatter
        }],
        classes: 'table',
        formatSearch: function () {
            return LBL_SEARCH_BOX_PLACEHOLDER
        }
    });

    $table.on('load-success.bs.table', function (data) {
        $('.info').popover({
            html: true,
            placement: 'bottom'
        });
    });
}

$('#makePaymentModal').on('show.bs.modal', function (e) {
    $(this).find('form:first').parsley().reset();
    ajaxGet('/appointments/status/' + APPT_STATUS_PENDING, false, false,
        function (response) {
            if (response.length) {
                $.each(response, function (i, appt) {
                    $('#appointments').append('<option value="' + appt.id + '">' + appt.patient.name
                        + ' | ' + appt.session.doctor.name + ' | ' + appt.appointmentDate + '  '
                        + appt.session.from
                        + '</option>');
                    $('#appointments option[value="' + appt.id + '"]').data('details', appt);
                    $('#appointments').selectpicker('refresh');
                });
            } else {
                $('#appointment-error-container').html('<ul class="parsley-errors-list filled">'
                    + '<li class="parsley-custom-error-message">' + ERR_NO_PENDING_APPT + '</li></ul>');
            }
        });
    card.mount("#makePaymentModal .card-element");
    card.on('change', function ({error}) {
        var errorContainer = $("#makePaymentModal .card-errors");
        if (error) {
            errorContainer.html(error.message);
        } else {
            errorContainer.empty();
        }
    });
}).on('hidden.bs.modal', function (e) {
    $(this).find('form:first').parsley().reset();
    $("#appointments option:not(:first)").remove();
    $('#appointments').selectpicker('refresh');
    $('#appointments').selectpicker('val', $("#appointments option:first").val());
    $('#makePaymentModal .appt-detail').hide();
    card.unmount();
    $('#appointment-error-container').empty();
    $("#makePaymentModal .card-errors").empty();
});

$('#makePaymentModal form').on('submit', function (e) {
    e.preventDefault();
    var $form = $(this);
    if ($form.parsley().validate()) {
        if ($('#makePaymentModal .card-element.StripeElement--complete').length) {
            ajaxFormJson($form, function (response) {
                //charge the card by stripe
                makePayment(response.body.clientSecret, function (result) {
                    ajaxJson('/payments/complete', 'PUT', {
                        "appointmentId": $('#appointments').val(),
                        "paymentIntentId": result.paymentIntent.id
                    }, function () {
                        hideProgress();
                        $('#makePaymentModal').modal('hide');
                        showNotification('success', LBL_PAY_SUCCESS);
                        $table.bootstrapTable('refresh');
                    });
                });
            });
        } else if ($('#makePaymentModal .card-errors').is(':empty')) {
            $('#makePaymentModal .card-errors').html(ERR_INVALID_CARD);
        }
    }
});

$('#modifyPaymentModal').on('show.bs.modal', function (e) {
    $(this).find('form:first').parsley().reset();
    var $invoker = $(e.relatedTarget);
    var id = $invoker.data('id');
    $('#modifyPaymentModal form').attr('action', PAY_MODIFY_URL.replace('{id}', id));
    ajaxGet('/payments/' + id, false, false, function (response) {
        $('#modifyPaymentModal input[name="id"]').val(response.body.id);
        $('#modifyPaymentModal .ref').html(response.body.reference);
        $('#modifyPaymentModal .payon').html(response.body.paidOn);

        var apptInfo = response.body.appointmentInfo;
        $('#modifyPaymentModal .appt-detail .hospital .val').html(apptInfo.session.room.hospital.name);
        $('#modifyPaymentModal .appt-detail .doctor .val').html(apptInfo.session.doctor.name);
        $('#modifyPaymentModal .appt-detail .patient .val').html(apptInfo.patient.name);
        $('#modifyPaymentModal .appt-detail .roomNo .val').html(apptInfo.session.room.roomNo);
        $('#modifyPaymentModal .appt-detail .date .val').html(apptInfo.appointmentDate + ' '
            + apptInfo.session.from);
        $('#modifyPaymentModal .appt-detail .fee .val').html(formatCurrency(apptInfo
            .session.docFee + apptInfo.session.room.roomFee));
    });
    card.mount("#modifyPaymentModal .card-element");
    card.on('change', function ({error}) {
        var errorContainer = $("#modifyPaymentModal .card-errors");
        if (error) {
            errorContainer.html(error.message);
        } else {
            errorContainer.empty();
        }
    });
}).on('hidden.bs.modal', function (e) {
    $('#modifyPaymentModal input[name="id"]').val('');
    $('#fee').val('');
    card.unmount();
    $("#modifyPaymentModal .card-errors").empty();
    $('#modifyPaymentModal form').attr('action', PAY_MODIFY_URL);
});

$('#modifyPaymentModal form').on('submit', function (e) {
    e.preventDefault();
    var $form = $(this);
    if ($form.parsley().validate()) {
        if ($('#modifyPaymentModal .card-element.StripeElement--complete').length) {
            $('#modifyPaymentModal input[name="fee"]').val($("#fee").val().split('Rs ')[1]
                .replace(/,/gi, ''));
            ajaxFormJson($form, function (response) {
                var id = $('#modifyPaymentModal input[name="id"]').val();
                //charge the card by stripe
                makePayment(response.body.clientSecret, function (result) {
                    ajaxJson('/payments/' + id + '/modify/complete', 'PATCH', {
                        "paymentIntentId": result.paymentIntent.id
                    }, function () {
                        hideProgress();
                        $('#modifyPaymentModal').modal('hide');
                        showNotification('success', LBL_PAY_UPDATED);
                        $table.bootstrapTable('refresh');
                    });
                });
            });
        } else if ($('#modifyPaymentModal .card-errors').is(':empty')) {
            $('#modifyPaymentModal .card-errors').html(ERR_INVALID_CARD);
        }
    }
});

$('#refundPaymentModal').on('show.bs.modal', function (e) {
    var $invoker = $(e.relatedTarget);
    var id = $invoker.data('id');
    $('#refundPaymentModal form').attr('action', PAY_REFUND_URL.replace('{id}', id));
}).on('hidden.bs.modal', function (e) {
    $('#refundPaymentModal form').attr('action', PAY_REFUND_URL);
});

$('#refundPaymentModal form').on('submit', function (e) {
    e.preventDefault();
    if ($(this).parsley().validate()) {
        ajaxFormJson($(this), function (response) {
            hideProgress();
            $('#refundPaymentModal').modal('hide');
            showNotification('success', LBL_PAY_REFUNDED);
            $table.bootstrapTable('refresh');
        });
    }
});

$('#appointments').on('changed.bs.select', function (e, clickedIndex, isSelected,
                                                     previousValue) {
    $(this).parsley().validate();
    var sel = $(this).find("option:selected");
    var appointmentDetails = sel.data('details');
    if (appointmentDetails) {
        if (!$('#makePaymentModal .appt-detail').is(':visible')) {
            $('#makePaymentModal .appt-detail').show(100);
        }
        $('#makePaymentModal .appt-detail .hospital .val').html(appointmentDetails.session.room.hospital.name);
        $('#makePaymentModal .appt-detail .doctor .val').html(appointmentDetails.session.doctor.name);
        $('#makePaymentModal .appt-detail .patient .val').html(appointmentDetails.patient.name);
        $('#makePaymentModal .appt-detail .roomNo .val').html(appointmentDetails.session.room.roomNo);
        $('#makePaymentModal .appt-detail .date .val').html(appointmentDetails.appointmentDate + ' '
            + appointmentDetails.session.from);
        $('#makePaymentModal .appt-detail .fee .val').html(formatCurrency(appointmentDetails
            .session.docFee + appointmentDetails.session.room.roomFee));
    } else {
        $('#makePaymentModal .appt-detail').hide();
    }
});

function initCardElement() {
    card = ELEMENTS.create("card", {style: style});
}

function makePayment(clientSecret, successCallback) {
    STRIPE.confirmCardPayment(clientSecret, {
        payment_method: {
            card: card
        }
    }).then(function (result) {
        if (result.error) {
            hideProgress();
            showNotification('danger', result.error.message);
        } else {
            if (result.paymentIntent.status === 'succeeded') {
                successCallback(result);
            }
        }
    });
}