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
  var $txt_goodsName, $cmb_goodsStatus, $cmb_goodsType;

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
    $cmb_goodsType = $table_search.find('#cmb_goods_type');
  }

  function initEvents() {
    initDataTable();

    // 搜索
    $btn_search.on("click", function () {
      table.settings()[0].ajax.data = function (d) {
        return $.extend({}, d, {
          'table': JSON.stringify(d),
          'goodsName': $txt_goodsName.val(),
          'goodsStatus': $cmb_goodsStatus.val(),
          'typeId': $cmb_goodsType.val()
        });
      };
      table.ajax.reload();
    });
    // 重置
    $btn_reset.on("click", function () {
      $txt_goodsName.val('');
      $cmb_goodsStatus.val('');
      $cmb_goodsType.val('');
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
        {data: 'img'},
        {data: 'imgDetail'},
        {data: 'singlePrice'},
        {data: 'totalAmount'},
        {data: 'bidAmount'},
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
          targets: 2,
          ordering: false,
          render: function (data, type, row, meta) {
            // return row.typeImg.length ? '<img src=" ' + row.typeImg + ' ">' : '';
            var $template;
            if (row.img != undefined && row.img != '') {

              $template = '<a href="javascript:;" onclick="showImage(this);" ' +
                  'class="btn btn-success btn-xs"><i class="fa fa-eye"></i> ' +
                  '预览</a><img style="display: none" src="' + row.img + '" />';
            } else {
              $template = '<code>无</code>'
            }
            return $template;
          }
        },
        {
          targets: 3,
          ordering: false,
          render: function (data, type, row, meta) {
            // return row.typeImg.length ? '<img src=" ' + row.typeImg + ' ">' : '';
            var $template;
            if (row.imgDetail != undefined && row.imgDetail != '') {
              $template = '<a href="javascript:;" onclick="showImage(this);" ' +
                  'class="btn btn-success btn-xs"><i class="fa fa-eye"></i> ' +
                  '预览</a><img style="display: none" src="' + row.imgDetail + '" />';
            } else {
              $template = '<code>无</code>'
            }
            return $template;
          }
        },
        {
          targets: 5,
          orderable: false
        },
        {
            targets: 6,
            orderable: false,
            render: function (data, type, row, meta) {
              return row.bidAmount == -1 ? '无限制'
                  : row.bidAmount;
            }
          },
        {
          targets: 8,
          orderable: false,
          render: function (data, type, row, meta) {
            return row.goodsStatus ? '<code class="text-success">上架</code>'
                : '<code class="text-danger">下架</code>';
          }
        },
        {
          targets: 9,
          orderable: false,
          render: function (data, type, row, meta) {
            var html = row.goodsStatus ?
                '<button class="btn btn-warning btn-xs" onclick="updateStatus(' + row.goodsId + ')"><i class="fa fa-toggle-off"></i> 下架</button>'
                : '<button class="btn btn-primary btn-xs" onclick="updateStatus(' + row.goodsId + ')"><i class="fa fa-toggle-on"></i> 上架</button>';
            // html += '&nbsp;<button class="btn btn-danger btn-xs" onclick="removeGoods(' + row.goodsId + ')"><i class="fa fa-remove"></i> 删除 </button>';
            html += '&nbsp;<a data-toggle="modal" data-target="#modal_edit" class="btn btn-primary btn-xs" href="' + contextPath + '/goods/' + row.goodsId + '"><i class="fa fa-edit"></i> 编辑 </a>';
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

var goodsImg = $('#modal_create').find('file');
goodsImg.fileinput({
  overwriteInitial: true,
  language: 'zh',
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
  browseLabel: '选择图片',
  browseIcon: '<i class="glyphicon glyphicon-folder-open"></i>'
});/*.on("filebatchselected", function (event, files) {
  $(this).fileinput("upload");
}).on("fileuploaded", function (event, data, previewId, index) {
  if (data.response) {
    addPics.push(data.response.message);
  }
}).on('filesuccessremove', function (event, previewId) {
  var index = $("#modal_create .file-preview-frame").index("#" + previewId);
  console.log(index);
  addPics.splice(index, 1);
});*/

/**
 * 删除数据
 *
 * @param goodsId
 */
// function removeGoods(goodsId) {
//   ajaxRequest('/goods/' + goodsId, 'DELETE', table);
// }

/**
 * 更新状态
 *
 * @param goodsId
 */
function updateStatus(goodsId) {
  ajaxRequest('/goods/' + goodsId + '/status', 'POST', table);
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

$("#form_create_goods").formValidation({
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
          max: 100000,
          message: '夺宝价格范围1~100000(元)'
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
    type: {
      validators: {
        notEmpty: {
          message: '类型不能为空'
        }
      }
    }
  }
}).off('success.form.fv').on('success.form.fv', function (e) {//.off('success.form.fv')和e.preventDefault();都是为了防止表单重复提交
  //防止表单重复提交
  e.preventDefault();
  var form = $(e.target);
  ajaxSubmit(e, form, null, null, table);
  $("#form_create_goods").data('formValidation').disableSubmitButtons(false);
});

function showImage(o) {
  var _src = $(o).parents('td').find('img').attr('src');
  var imgSrc = _src.split(',');
  var template = '';
  $.each(imgSrc, function (i) {
    template = '<img src="http://ocgfma6io.bkt.clouddn.com/' + imgSrc[i] + '" />';
    layer.open({
      type: 1,
      title: '图片预览',
      closeBtn: 1,
      area: ['966px', '590px'],
      maxWidth: 'auto',
      skin: 'layui-layer-lan',
      shadeClose: true,
      content: template
    });
  });
}