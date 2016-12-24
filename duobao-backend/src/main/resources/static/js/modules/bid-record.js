/**
 * <p>
 *
 * </p>
 * @author WuGang
 * @since 1.0
 */
(function ($) {

  var $table_id = 'dataTable_bidRecord', $table_search, $btn_search, $btn_reset;
  //查询参数
  var $txt_bidId, $txt_luckyNum, $cmb_status;

  $(document).ready(init());

  function init() {
    initElements();
    initEvents();
  }

  function initElements() {
    $table_search = $('#table_search');
    $btn_search = $('#btn_search');
    $btn_reset = $('#btn_reset');

    $txt_bidId = $table_search.find('#txt_bid_id');
    $txt_luckyNum = $table_search.find('#txt_lucky_num');
    $cmb_status = $table_search.find('#cmb_status');
  }

  function initEvents() {
    initDataTable();

    // 搜索
    $btn_search.on("click", function () {
      table.settings()[0].ajax.data = function (d) {
        return $.extend({}, d, {
          'table': JSON.stringify(d),
          'bidId': $txt_bidId.val(),
          'luckyNum': $txt_luckyNum.val(),
          'bidStatus': $cmb_status.val()
        });
      };
      table.ajax.reload();
    });
    // 重置
    $btn_reset.on("click", function () {
      $txt_bidId.val('');
      $txt_luckyNum.val('');
      $cmb_status.val('');
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
      order: [[1, 'desc']],
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
        url: '/bidRecords/table.json',
        data: function (d) {
          return $.extend({}, d, {
            "table": JSON.stringify(d),
            'bidId': $txt_bidId.val(),
            'cellphone': $txt_luckyNum.val()
          });
          // return JSON.stringify(d);
        }
      },
      columns: [
        {data: null},
        {data: 'bidId'},
        {data: 'goodsName'},
        {data: 'totalAmount'},
        {data: 'winnerName'},
        {data: 'joinAmount'},
        {data: 'luckyNum'},
        {data: 'publishTime'},
        {data: 'bidStatus'}
      ],
      columnDefs: [
        {
          targets: 0,
          orderable: false,
          render: function (data, type, row, meta) {
            return meta.row + 1;
          }
        },
        {
          targets: 2,
          orderable: false
        },
        {
          targets: 3,
          orderable: false
        },
        {
          targets: 5,
          orderable: false
        }
      ],
      fnInitComplete: function () {
        //隐藏搜索框
        $('#' + $table_id + '_filter').hide();
      }
    });
  }

})(jQuery);