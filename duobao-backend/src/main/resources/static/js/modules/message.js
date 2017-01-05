/**
 * @author WuGang
 * @since 1.0
 */
(function ($) {

  var $table_id = 'dataTable_message', $table_search, $btn_search, $btn_reset;
  //查询参数
  var $txt_userId, $cmb_messageType;
  var $modal_create = $('#modal_create');
  var $form_createMessage;

  $(document).ready(init());

  function init() {
    initElements();
    initEvents();
  }

  function initElements() {
    $table_search = $('#table_search');
    $btn_search = $('#btn_search');
    $btn_reset = $('#btn_reset');

    $txt_userId = $table_search.find('#txt_user_id');
    $cmb_messageType = $table_search.find('#cmb_message_type');

    $form_createMessage = $modal_create.find('#form_create_message');
  }

  function initEvents() {
    initDataTable();
    initFormValidation();

    // 搜索
    $btn_search.on("click", function () {
      table.settings()[0].ajax.data = function (d) {
        return $.extend({}, d, {
          'table': JSON.stringify(d),
          'userId': $txt_userId.val(),
          'messageType': $cmb_messageType.val()
        });
      };
      table.ajax.reload();
    });
    // 重置
    $btn_reset.on("click", function () {
      $txt_userId.val('');
      $cmb_messageType.val('');
      table.settings()[0].ajax.data = function (d) {
        return $.extend({}, d, {
          'table': JSON.stringify(d)
        });
      };
      table.ajax.reload();
    });
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
            "table": JSON.stringify(d),
            'userId': $txt_userId.val(),
            'messageType': $cmb_messageType.val()
          });
        }
      },
      columns: [
        {data: 'messageId'},
        {data: 'userId'},
        {data: 'nickname'},
        {data: 'title'},
        {data: 'content'},
        {data: 'messageType'},
        {data: 'readFlag'}
      ],
      columnDefs: [
        {
          targets: 1,
          render: function (data, type, row, meta) {
            return row.userId != undefined && row.userId != ''
                ? row.userId
                : '无';
          }
        },
        {
          targets: 2,
          orderable: false,
          render: function (data, type, row, meta) {
            return row.nickname != undefined && row.nickname != ''
                ? row.nickname
                : '无';
          }
        },
        {
          targets: 4,
          orderable: false
        },
        {
          targets: 6,
          render: function (data, type, row, meta) {
            return row.readFlag
                ? '<button class="btn btn-primary btn-xs">已读</button>'
                : '<button class="btn btn-warning btn-xs">未读</button>';
          }
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