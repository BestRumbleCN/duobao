/**
 * <p>
 * 商品类型js
 * </p>
 *
 * @author wushige
 * @date 2016-08-18 11:35
 */
(function ($) {

  var $table_id = 'dataTable_goods', $table_search, $btn_search, $btn_reset;
  //查询参数
  var $txt_goodsName, $cmb_goodsStatus;

  $(document).ready(init());

  function init() {
    initElements();
    initEvents();
  }

  function initElements() {
    $table_search = $('#table_search');
    $btn_search = $('#btn_search');
    $btn_reset = $('#btn_reset');

    $txt_goodsName = $table_search.find('#txt_goods_name');
    $cmb_goodsStatus = $table_search.find('#cmb_goods_status');
  }

  function initEvents() {
    initDataTable();

    // 搜索
    $btn_search.on("click", function () {
      table.settings()[0].ajax.data = function (d) {
        return $.extend({}, d, {
          'table': JSON.stringify(d),
          'goodsName': $txt_goodsName.val(),
          'goodsStatus': $cmb_goodsStatus.val()
        });
      };
      table.ajax.reload();
    });
    // 重置
    $btn_reset.on("click", function () {
      $txt_goodsName.val('');
      $cmb_goodsStatus.val('');
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
        url: '/goods/table.json',
        data: function (d) {
          return $.extend({}, d, {
            "table": JSON.stringify(d),
            'goodsName': $txt_goodsName.val(),
            'goodsStatus': $cmb_goodsStatus.val()
          });
          // return JSON.stringify(d);
        }
      },
      columns: [
        {data: 'goodsId'},
        {data: 'goodsName'},
        {data: 'singlePrice'},
        {data: 'totalAmount'},
        {data: 'typeName'},
        {data: 'goodsStatus'},
        {data: null}
      ],
      columnDefs: [
        {
          targets: 0,
          render: function (data, type, row, meta) {
            return "<a href='javascript:void(0);' onclick='edit("
                + JSON.stringify(row)
                + ")'>"
                + data + "</a>";
          }
        },
        {
          targets: 4,
          orderable: false
        },
        {
          targets: 5,
          orderable: false,
          render: function (data, type, row, meta) {
            return row.goodsStatus ? '<code class="text-success">上架</code>'
                : '<code class="text-danger">下架</code>';
          }
        },
        {
          targets: 6,
          orderable: false,
          render: function (data, type, row, meta) {
            var html = row.goodsStatus ?
                '<button class="btn btn-warning btn-xs" onclick="updateStatus('+row.goodsId+')"><i class="fa fa-toggle-off"></i> 下架</button>'
                : '<button class="btn btn-primary btn-xs" onclick="updateStatus('+row.goodsId+')"><i class="fa fa-toggle-on"></i> 上架</button>';
            html += '&nbsp;<button class="btn btn-danger btn-xs" onclick="removeGoods('+row.goodsId+')"><i class="fa fa-remove"></i> 删除 </button>';
            html += '&nbsp;<a data-toggle="modal" data-target="#modal_edit" class="btn btn-primary btn-xs" href="'+contextPath+'/goods/'+row.goodsId+'"><i class="fa fa-edit"></i> 编辑 </a>';
            return html;
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

var addPics = [];

var goodsImg = $('#modal_create').find('#pic');
goodsImg.fileinput({
  overwriteInitial: true,
  language: 'zh',
  uploadUrl: '/goods/pic',
  previewFileType: 'image',
  allowedFileTypes: ['image'],
  allowedFileExtensions: ['jpg', 'jpeg', 'png', 'gif', 'bmp'],
  maxFilePreviewSize: 20000,
  maxFileSize: 20000,
  maxFileCount: 5,
  showClose: false,
  showUpload: false,
  showRemove: false,
  showZoom: true,
  maxFileCount: 10,
  browseLabel: '选择图片',
  browseIcon: '<i class="glyphicon glyphicon-folder-open"></i>'
}).on("filebatchselected", function (event, files) {
  $(this).fileinput("upload");
}).on("fileuploaded", function (event, data, previewId, index) {
  if (data.response) {
    addPics.push(data.response.message);
  }
}).on('filesuccessremove', function (event, previewId) {
  var index = $("#modal_create .file-preview-frame").index("#" + previewId);
  console.log(index);
  addPics.splice(index, 1);
});

/**
 * 删除数据
 *
 * @param goodsId
 */
function removeGoods(goodsId) {
  ajaxRequest('/goods/' + goodsId, 'DELETE', table);
}

/**
 * 更新状态
 *
 * @param goodsId
 */
function updateStatus(goodsId) {
  confirmNotify('提示', '确认更新吗？', '/goods/' + goodsId + '/status', 'POST',
      table);
}

/**
 * 编辑
 *
 * @param row
 */
function edit(row) {
  var editModel = $('#modal_update');
  editModel.find('#goodsName').val(row.goodsName);
  // editModel.find('#typeId').val(row.typeId);
  editModel.modal('show');
}

/**
 * 查询
 */
$("#pic").click(function () {
  table.ajax.reload();
});

$("#modal_create").formValidation({
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
    goodsName: {
      validators: {
        notEmpty: {
          message: '商品名不能为空'
        },
        stringLength: {
          min: 5,
          max: 50,
          message: '商品名长度范围5~50'
        }
      }
    },
    singlePrice: {
      validators: {
        notEmpty: {
          message: '夺宝价格不能为空'
        },
        between: {
          min: 1,
          max: 100,
          message: '夺宝价格范围5~12288(元)'
        }
      }
    },
    totalAmount: {
      validators: {
        notEmpty: {
          message: '总需人数不能为空'
        },
        between: {
          min: 1,
          max: 100000,
          message: '总需人数范围1~100000'
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
});

$("#create-user-btn").click(function () {
  $("#modal_create").data('formValidation').validate();
  if ($("#modal_create").data('formValidation').isValid()) {
    var params = {
      goodsName: $("#goodsName").val(),
      typeId: $("#typeId").val(),
      totalAmount: $("#totalAmount").val(),
      channel: $("#channel").val(),
      singlePrice: $("#singlePrice").val(),
      img: addPics
    }
    ajaxPost('/goods', params, table);
  }
});