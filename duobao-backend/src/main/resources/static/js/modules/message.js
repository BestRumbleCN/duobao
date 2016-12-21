/**
 * @author WuGang
 * @since 1.0
 */
(function ($) {

  var $modal_create = $('#modal_create');
  var $table_id = 'dataTable_message';
  var $form_createMessage;

  $(document).ready(init());

  function init() {
    initElements();
    initEvents();
  }

  function initElements() {
    $form_createMessage = $modal_create.find('#form_create_message');
  }

  function initEvents() {
    initDataTable();
    initFormValidation();
  }

  function initDataTable() {
    table = $('#' + $table_id).DataTable({
      responsive: true,
      order: [[0, 'desc']],
      language: {
        url: contextPath + '/static/js/lib/dataTables/dataTable_zh_CN.json'
      },
      pageLength: 10,
      pagingType: "full_numbers",  //分页样式：simple,simple_numbers,full,full_numbers
      renderer: "bootstrap",
      autoWidth: true,  //自动调整列宽
      searching: true,
      processing: true,
      serverSide: true,
      select: true,
      ajax: {
        type: 'get',
        url: '/messages/table.json',
        data: function (d) {
          return $.extend({}, d, {
            "table": JSON.stringify(d)
          });
          // return JSON.stringify(d);
        }
      },
      columns: [
        {data: 'messageId'},
        {data: 'title'},
        {data: 'content'},
        {data: 'messageType'}
      ],
      columnDefs: [
        {
          targets: 2,
          orderable: false
        }
      ],
      fnInitComplete: function () {
        //隐藏搜索框
        $('#' + $table_id + '_filter').hide();
      }
    });
  }

  function initFormValidation() {
    $form_createMessage.formValidation({
      framework: 'bootstrap',
      excluded: [':disabled', ':hidden', ':not(:visible)'],
      icon: false,
      autoFocus: true,
      live: 'enabled',
      message: '请填写必填项目'
    }).off('success.form.fv').on('success.form.fv', function (e) {//.off('success.form.fv')和e.preventDefault();都是为了防止表单重复提交
      //防止表单重复提交
      e.preventDefault();
      var form = $(e.target);
      ajaxSubmit(e, form, null, null, table);
      $($form_createMessage).data('formValidation').disableSubmitButtons(false);
    });
  }

})(jQuery);