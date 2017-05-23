/**
 * <p>
 *
 * </p>
 * @author WuGang
 * @since 1.0
 */
(function ($) {

  var $table_id = 'dataTable_shippingRecord', $table_search, $btn_search, $btn_reset;
  //查询参数
  var $txt_luckyNum, $txt_cellphone, $cmb_shippingStatus;

  $(document).ready(init());

  function init() {
    initElements();
    initEvents();
  }

  function initElements() {
    $table_search = $('#table_search');
    $btn_search = $('#btn_search');
    $btn_reset = $('#btn_reset');

    $txt_luckyNum = $table_search.find('#txt_lucky_num');
    $txt_cellphone = $table_search.find('#txt_cellphone');
    $cmb_shippingStatus = $table_search.find('#cmb_shipping_status')
  }

  function initEvents() {
    initDataTable();

    // 搜索
    $btn_search.on("click", function () {
      table.settings()[0].ajax.data = function (d) {
        return $.extend({}, d, {
          'table': JSON.stringify(d),
          'luckyNum': $txt_luckyNum.val(),
          'cellphone': $txt_cellphone.val(),
          'shippingStatus': $cmb_shippingStatus.val()
        });
      };
      table.ajax.reload();
    });
    // 重置
    $btn_reset.on("click", function () {
      $txt_luckyNum.val('');
      $txt_cellphone.val('');
      $cmb_shippingStatus.val('');
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
        url: '/shippingRecords/table.json',
        data: function (d) {
          return $.extend({}, d, {
            "table": JSON.stringify(d),
            'luckyNum': $txt_luckyNum.val(),
            'cellphone': $txt_cellphone.val(),
            'shippingStatus': $cmb_shippingStatus.val()
          });
          // return JSON.stringify(d);
        }
      },
      columns: [
        {data: 'id'},
        {data: 'bidId'},
        {data: 'publishTime'},
        {data: 'luckyNum'},
        {data: 'goodsName'},
        {data: 'userId'},
        {data: 'receiverName'},
        {data: 'cellphone'},
        {data: 'shippingAddress'},
        {data: 'shippingStatus'},
        {data: null}
      ],
      columnDefs: [
        {
          targets: 0,
          orderable: false
        },
        {
          targets: 3,
          orderable: false
        },
        {
          targets: 6,
          orderable: false
        },
        {
          targets: 7,
          orderable: false
        },
        {
          targets: 8,
          orderable: false
        },
        {
          targets: 10,
          orderable: false,
          render: function (data, type, row, meta) {
            return (row.shippingStatus != '已发货'
                ? "<button class='btn btn-success btn-xs' onclick='deliver(" + JSON.stringify(row) + ")'><i class='fa fa-ship'></i> 发货</button>"
                : "") + "<button class='btn btn-success btn-xs' onclick='updateInfo(" + JSON.stringify(row) + ")'><i class='fa fa-refresh'></i>更新</button>";
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
 * 发货
 *
 */
function deliver(row) {
  var value = '您的商品' + row.goodsName + '已发货';
  var options = {
    title: '给用户发送发货信息',
    formType: 2,
    value: value
  };
  layer.prompt(options, function (data) {
    ajaxRequest('/shippingRecords/' + row.id + '/status?userId=' + row.userId + '&messageType=SHIP&title=发货啦&content=' + data, 'POST', table);
  });
}

function updateInfo(row){
	ajaxCore('/shippingRecords/' + row.id + '/updateInfo', 'POST', table);
}
