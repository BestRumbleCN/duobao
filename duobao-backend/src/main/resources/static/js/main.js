
/**
 * 定义点击或者单击事件
 * @type {string}
 */
var TAP_CLICK_EVENT = 'touchend MSPointerUp pointerup click';  // mouseup

$('.logout').on(TAP_CLICK_EVENT, function () {
    $('#logout-form').submit();
});

var form = $('form');

form
    .on('blur', 'input[required], input.optional, select.required', validator.checkField)
    .on('change', 'select.required', validator.checkField)
    .on('keypress', 'input[required][pattern]', validator.keypress);

$('.multi.required').on('keyup blur', 'input', function() {
    validator.checkField.apply($(this).siblings().last()[0]);
});

form.submit(function(e) {
    e.preventDefault();
    var submit = true;

    // evaluate the form using generic validaing
    if (!validator.checkAll($(this))) {
        submit = false;
    }

    if (submit)
        this.submit();

    return false;
});