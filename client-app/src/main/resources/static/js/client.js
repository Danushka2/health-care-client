const BASIC_AUTH_TOKEN = "Basic Y2xpZW50OjVleXZMZjhUc1Q1ZEJGck0=";

var typeIcons = [];
typeIcons['info'] = 'icon far fa-info-circle';
typeIcons['success'] = 'icon far fa-check';
typeIcons['warning'] = 'icon far fa-exclamation-triangle';
typeIcons['danger'] = 'icon far fa-ban';

function showNotification(type, msg) {
    $.notify({
        icon: typeIcons[type],
        message: msg

    }, {
        type: type,
        timer: 5000,
        placement: {
            from: 'top',
            align: 'right'
        }
    });
}

function showProgress() {
    $('#progress').modal({
        backdrop: 'static',
        keyboard: false
    }).modal('show');
    $('body').css('overflow-y', 'hidden');
}

function hideProgress(e) {
    $('#progress').modal('hide');
}

function showConfirm(title, text, callbackFn, data) {
    $('#confirm .modal-title').html(title);
    $('#confirm .modal-text').html(text);

    $('#confirm button[type="submit"]').on('click', function (e) {
        e.preventDefault();
        callbackFn(data);
    });

    $('#confirm').modal({
        backdrop: 'static',
        keyboard: false,
    }).on('hide.bs.modal', function (e) {
        $('#confirm button[type="submit"]').unbind("click");
    }).modal('show');
}

function hideConfirm() {
    $('#confirm').modal('hide');
}

function ajaxGet(url, enableCache, async, success) {
    $.ajax({
        type: "GET",
        url: url,
        cache: enableCache,
        async: async,
        success: function (response, status, xhr) {
            commonResponseHandler(response, status, xhr, success);
        },
        error: commonErrorHandler
    });
}

function ajaxDelete(url, onSubmit, success) {
    $.ajax({
        type: "DELETE",
        url: url,
        beforeSend: function () {
            showProgress();
            if (onSubmit) {
                onSubmit();
            }
        },
        success: function (response, status, xhr) {
            commonResponseHandler(response, status, xhr, success);
        },
        error: commonErrorHandler
    });
}

function ajaxJson(url, type, json, success, onSubmit) {
    $.ajax({
        url: url,
        type: type ? type : 'POST',
        data: JSON.stringify(json),
        beforeSend: function (xhr) {
            if (onSubmit) {
                onSubmit(xhr);
            }
        },
        success: function (response, status, xhr) {
            commonResponseHandler(response, status, xhr, success);
        },
        cache: false,
        contentType: 'application/json',
        processData: false,
        error: commonErrorHandler
    });
}

function ajaxFormJson($form, success) {
    $form = $($form);
    $.ajax({
        url: $form.attr('action'),
        type: $form.attr('method'),
        data: JSON.stringify($form.serializeJSON()),
        beforeSend: function (xhr) {
            showProgress();
        },
        success: function (response, status, xhr) {
            commonResponseHandler(response, status, xhr, success);
        },
        cache: false,
        contentType: 'application/json',
        processData: false,
        error: commonErrorHandler
    });
}

function ajaxPost($form, success) {
    ajaxPost($form, null, null, success)
}

function ajaxPost($form, formData, onSubmit, success) {
    $form = $($form);
    var postData;
    var url = $form.attr('action');
    var type = 'POST';
    if ($form.attr('enctype') == "multipart/form-data") {
        postData = formData ? formData : new FormData($form[0]);
        $.ajax({
            url: url,
            type: type,
            data: postData,
            beforeSend: function (xhr) {
                showProgress();
                if (onSubmit) {
                    onSubmit(xhr);
                }
            },
            success: function (response, status, xhr) {
                commonResponseHandler(response, status, xhr, success);
            },
            cache: false,
            contentType: false,
            processData: false,
            error: commonErrorHandler
        });
    } else {
        postData = formData ? formData : $form.serialize();
        $.ajax({
            url: url,
            type: type,
            data: postData,
            beforeSend: function (xhr) {
                showProgress();
                if (onSubmit) {
                    onSubmit(xhr);
                }
            },
            processData: false,
            contentType: false,
            success: function (response, status, xhr) {
                commonResponseHandler(response, status, xhr, success);
            },
            error: commonErrorHandler
        });
    }
}

function commonErrorHandler(jqXHR, textStatus, errorThrown) {
    hideProgress();
    showNotification('danger', "Problem with server call.<br>Please try again.<br>Technical" +
        " details: " + jqXHR.status + ':' + jqXHR.statusText);
}

function commonResponseHandler(response, status, xhr, callback) {
    //response = $.parseJSON(response);
    if (response && 'hasError' in response) {
        if (response.hasError) {
            hideProgress();
            showNotification('warning', response.error);
            return;
        }
    }

    if (callback) {
        callback(response, status, xhr);
    }
}

function containsAny(source, target) {
    var result = source.filter(function (item) {
        return target.indexOf(item) > -1
    });
    return (result.length > 0);
}

function contains(arr, elm) {
    return (arr.indexOf(elm) > -1);
}

$(function () {
    $('.selectpicker').selectpicker();
    $('.inputmask').inputmask();
    $('[data-toggle="tooltip"]').tooltip();
    $('.datepicker').datetimepicker({
        icons: {
            previous: "far fa-chevron-left",
            next: "far fa-chevron-right"
        },
        format: "YYYY/MM/DD"
    });
    $('.timepicker').datetimepicker({
        icons: {
            up: "far fa-chevron-up",
            down: "far fa-chevron-down"
        },
        format: 'LT'
    });

    $('.nav-tabs a').click(function (e) {
        e.preventDefault();

        var url = $(this).attr("data-url");
        var href = this.hash;
        var pane = $(this);

        $(this).closest('li').addClass('active');
        $(this).closest('li').siblings().removeClass('active');

        // ajax load from data-url
        $(href).load(url, function (result) {
            pane.tab('show');
        });
    });

    if ($('.tab-content').length) {
        //load first tab content
        $('.tab-content .tab-pane.active').load($('.nav-tabs .active a').attr("data-url"),
            function (result) {
                $(this).tab('show');
            });
    }
});

window.Parsley
    .addValidator('datelte', {
        requirementType: 'string',
        validateString: function (value, toDateField) {
            if (!value || !$(toDateField).val()) {
                return true;
            }
            return new Date(value) <= new Date($(toDateField).val());
        }
    })
    .addValidator('dategte', {
        requirementType: 'string',
        validateString: function (value, fromDateField) {
            if (!value || !$(fromDateField).val()) {
                return true;
            }
            return new Date(value) >= new Date($(fromDateField).val());
        }
    })
    .addValidator('email', {
        requirementType: 'string',
        validateString: function (value) {
            if (!value) {
                return true;
            }
            var re = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
            return new RegExp(re).test(value);
        }
    });

function formatCurrency(val) {
    return Number(val).toFixed(2).toString().replace(/(\d)(?=(\d\d\d)+([^\d]|$))/g, '$1,');
}