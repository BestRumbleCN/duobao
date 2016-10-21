/**
 * 定义全局点击事件限制变量
 */
var CAN_CLICK = false;

/**
 * 定义点击或者单击事件
 * @type {string}
 */
var TAP_CLICK_EVENT = 'touchend MSPointerUp pointerup click';  // mouseup

$('.logout').on(TAP_CLICK_EVENT, function () {
    $('#logout-form').submit();
});

$('[data-toggle="tooltip"]').tooltip();


/**
 * 通用Notify方法
 * @param title 标题
 * @param text 内容
 * @param type 类型：success/info/error/warning
 */
function showNotify(title, text, type) {
	PNotify.prototype.options.delay = 500;
    new PNotify({
        title: title,
        text: text,
        type: type,
        styling: 'bootstrap3'
    });
}

/**
 * 显示操作正确提示
 * @param message
 */
function showSuccessNotify(message) {
    showNotify('操作成功', message, 'success')
}

/**
 * 显示警告提示
 * @param message
 */
function showWarningNotify(message) {
    showNotify('警告', message, 'warning')
}

/**
 * 显示错误提示
 * @param message
 */
function showErrorNotify(message) {
    showNotify('错误提示', message, 'error')
}

/**
 * Ajax包装
 * @param url
 * @param method
 * @param table
 */
function ajaxCore(url, method, table) {
    $.ajax({
        type: method,
        url: contextPath + url,
        dataType: 'json',
        beforeSend: function (request) {
            request.setRequestHeader(header, token);
        },
        success: function (data) {
            ajaxSuccess(data, null, null, table);
        },
        error: function (data, textstatus) {
            ajaxError(data, textstatus, null, null);
        }
    });
}

/**
 * Ajax 成功处理
 * @return {boolean}
 */
function ajaxSuccess(data, button, buttonText, table) {
    if (data.status === 1 || data.status === -1) {
        if (data.view != undefined && data.view.toString() != '' && data.view != null) {
            if (data.view.indexOf('http')) {
                window.location.href = data.view;
            }
            location.href = contextPath + data.view;
        } else {
            if (table != undefined) table.ajax.reload();
            $('.modal').modal('hide');
            showSuccessNotify(data.message);
            //setTimeout("location.reload()", 2000);
        }
    } else {
        showWarningNotify(data.message);
        button.removeClass("disabled");
        button.removeAttr("disabled");
        button.text(buttonText);
        CAN_CLICK = false;
        return false;
    }
}

/**
 * Ajax 错误处理
 * @param data
 * @param textstatus
 * @param button
 * @param buttonText
 */
function ajaxError(data, textstatus, button, buttonText) {
    showErrorNotify(data.status + textstatus + ":" + data.responseText);
    button.removeClass("disabled");
    button.removeAttr("disabled");
    button.text(buttonText);
}

/**
 * Ajax Request包装
 * @param url      请求连接
 * @param method   请求方法：GET、POST、DELETE
 * @param table    可选参数，如果table != undefined，表示和DataTable操作相关
 */
function ajaxRequest(url, method, table) {
    if (method === 'DELETE') {
        if (confirm('确定要删除吗？')) {
            ajaxCore(url, method, table);
        }
    } else {
        ajaxCore(url, method, table);
    }
}

/**
 * Ajax post请求
 * @param url		请求连接
 * @param params 	参数
 * @param table		可选参数，如果table != undefined，表示和DataTable操作相关
 */
function ajaxPost(url, params, table){
	url =url +"?1=";
	for(var key in params){
		url = url + "&" + key + "=" + params[key];
	}
	ajaxCore(url, "POST", table);
}

/**
 * 确认框
 * @param content 	确认框提示信息
 * @param url      请求连接
 * @param method   请求方法：GET、POST、DELETE
 * @param table    可选参数，如果table != undefined，表示和DataTable操作相关
 */
function confirmNotify(title,content,url, method, table) {
	(new PNotify({
        title: title,
        text: content,
        styling: 'bootstrap3',
        type:'warning',
        hide: false,
        confirm: {
            confirm: true
        },
        history: {
            history: false
        },
        buttons: {
            closer: false,
            sticker: false
        },
    })).get().on('pnotify.confirm', function(){
    	ajaxRequest(url, method, table)
    }).on('pnotify.cancel', function(){
    });
}

/**
 * 包装AjaxSubmit Request
 * @param e
 * @param form
 * @param button
 * @param buttonText
 * @param table
 */
function ajaxSubmit(e, form, button, buttonText, table) {
    e.preventDefault();
    form.ajaxSubmit({
        semantic: true,
        dataType: 'json',
        beforeSend: function (request) {
            request.setRequestHeader(header, token);
        },
        success: function (data) {
            ajaxSuccess(data, button, buttonText, table);
        },
        error: function (data, textstatus) {
            ajaxError(data, textstatus, button, buttonText);
        }
    })
}

/**
 * 表单提交按钮绑定事件
 */
$('.form-ajax-submit').on(TAP_CLICK_EVENT, function () {
    CAN_CLICK = true;
    var buttonId = $(this).attr('id');
    var form = $('#form_' + buttonId);
    var button = $('#' + buttonId);
    var buttonText = button.text();
    button.addClass('disabled', 'disabled');
    button.text('加载中。。。');

    if (CAN_CLICK) {
        ajaxSubmit(form, button, buttonText);
    }

    button.removeClass("disabled");
    button.removeAttr("disabled");
    button.text(buttonText);
});

/**
 * 保存按钮绑定事件
 */
$('.ajax-request').on(TAP_CLICK_EVENT, function () {
    var url = $(this).data('link');
    var method = $(this).data('method');
    ajaxRequest(url, method);
});

$('.submit-form').formValidation({
    framework: 'bootstrap',
    excluded: [':disabled', ':hidden', ':not(:visible)'],
    icon: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    live: 'enabled',
    message: '请填写必填项目',
    trigger: null,
    fields: {
        username: {
            validators: {
                notEmpty: {
                    message: '用户名不能为空'
                },
                stringLength: {
                    min: 5,
                    max: 16,
                    message: '用户名长度范围5~16'
                }
            }
        },
        password: {
            validators: {
                notEmpty: {
                    message: '密码不能为空'
                },
                stringLength: {
                    min: 5,
                    max: 30,
                    message: '密码长度范围5~30'
                }
            }
        },
        name: {
            validators: {
                notEmpty: {
                    message: '名称不能为空'
                },
                stringLength: {
                    min: 5,
                    max: 30,
                    message: '名称长度范围5~30'
                }
            }
        },
        size: {
            validators: {
                notEmpty: {
                    message: '产品大小不能为空'
                },
                between: {
                    min: 5,
                    max: 12288,
                    message: '产品大小范围5~12288(MB)'
                }
            }
        },
        type: {
            validators: {
                notEmpty: {
                    message: '类型不能为空'
                }
            }
        }
    }
}).on('success.form.fv', function (e) {
    e.preventDefault();
    var form = $(e.target);
    var button = form.find('.submit-btn');
    //var table = '';
    ajaxSubmit(e, form, button, button.text(), table);
});

$('.modal').on('hidden.bs.modal hide.bs.modal', function () {
    $(this).removeData("bs.modal");
    $(this).clearForm();
});

/**
 * 数组增加根据下标删除数据方法
 */
Array.prototype.del = function(dx) 
{ 
if(isNaN(dx)||dx>this.length){return false;} 
this.splice(dx,1); 
} 

