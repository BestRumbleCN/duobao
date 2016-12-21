/**
 * <p>
 *
 * </p>
 * @author WuGang
 * @since 1.0
 */
(function ($) {

  var $table_id = 'dataTable_activity', $table_search, $btn_search, $btn_reset;
  //查询参数
  var $cmb_categoryId, $txt_name, $cmb_enabled;

  $(document).ready(init());

  function init() {
    initElements();
    initEvents();
  }

  function initElements() {
    $table_search = $('#table_search');
    $btn_search = $('#btn_search');
    $btn_reset = $('#btn_reset');

    $cmb_categoryId = $table_search.find('#cmb_category_id');
    $txt_name = $table_search.find('#txt_name');
    $cmb_enabled = $table_search.find('#cmb_enabled')
  }

  function initEvents() {
    initDataTable();

    // 搜索
    $btn_search.on("click", function () {
      table.settings()[0].ajax.data = function (d) {
        return $.extend({}, d, {
          'table': JSON.stringify(d),
          'categoryId': $cmb_categoryId.val(),
          'name': $txt_name.val(),
          'enabled': $cmb_enabled.val()
        });
      };
      table.ajax.reload();
    });
    // 重置
    $btn_reset.on("click", function () {
      $cmb_categoryId.val('');
      $txt_name.val('');
      $cmb_enabled.val('');
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
      pagingType: "full_numbers",  //分页样式：simple,simple_numbers,full,full_numbers
      renderer: "bootstrap",
      autoWidth: true,  //自动调整列宽
      searching: true,
      processing: true,
      serverSide: true,
      select: true,
      ajax: {
        type: 'get',
        url: '/activities/table.json',
        data: function (d) {
          return $.extend({}, d, {
            "table": JSON.stringify(d),
            'categoryId': $cmb_categoryId.val(),
            'name': $txt_name.val(),
            'enabled': $cmb_enabled.val()
          });
          // return JSON.stringify(d);
        }
      },
      columns: [
        {data: 'activityId'},
        {data: 'name'},
        {data: 'categoryId'},
        {data: 'enabled'},
        {data: null}
      ],
      columnDefs: [
        {
          targets: 3,
          orderable: false,
          render: function (data, type, row, meta) {
            return row.enabled
                ? '<span class="label label-success">正常</span>'
                : '<span class="label label-danger">关闭</span>';
          }
        },
        {
          targets: 4,
          orderable: false,
          render: function (data, type, row, meta) {
            var template = '';
            template += row.enabled
                ? '<button class="btn btn-warning btn-xs" onclick="updateStatus('+row.activityId+')"><i class="fa fa-toggle-off"></i> 关闭</button>'
                : '<button class="btn btn-success btn-xs" onclick="updateStatus('+row.activityId+')"><i class="fa fa-toggle-on"></i> 打开</button>';
            template += '&nbsp;<a data-toggle="modal" data-target="#modal_edit" class="btn btn-primary btn-xs" href="'+contextPath+'/activities/' + row.activityId + '"><i class="fa fa-edit"></i> 编辑 </a>';
            return template;
          }
        }
      ],
      fnInitComplete: function () {
        //隐藏搜索框
        $('#' + $table_id + '_filter').hide();
      }
    });
  }

})(jQuery);

/**
 * 更新状态
 * @param activityId
 */
function updateStatus(activityId) {
  ajaxRequest('/activities/' + activityId + '/status', 'POST', table);
}