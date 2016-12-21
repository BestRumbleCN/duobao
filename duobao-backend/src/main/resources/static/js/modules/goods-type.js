/**
 * <p>
 * 商品类型js
 * </p>
 * @author wushige
 * @date   2016-08-18 11:35
 */
(function ($) {

  var $table_id = 'dataTable_goodsType';

  $(document).ready(init());

  function init() {
    initElements();
    initEvents();
  }

  function initElements() {
  }

  function initEvents() {
    initDataTable();
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
        url: '/goodsTypes/table.json',
        data: function (d) {
          return $.extend({}, d, {
            "table": JSON.stringify(d)
          });
          // return JSON.stringify(d);
        }
      },
      columns: [
        {data: 'typeId'},
        {data: 'typeName'},
        {data: 'typeImg'},
        {data: 'status'},
        {data: 'createTime'},
        {data: null}
      ],
      columnDefs: [
        {
          targets: 0,
          render: function (data, type, row, meta) {
            return "<a href='javascript:void(0);' onclick='edit(" + JSON.stringify(row) + ")'>" + data + "</a>";
          }
        },
        {
          //指定是第3列
          targets: 2,
          ordering: false,
          render: function (data, type, row, meta) {
            return row.typeImg.length ? '<img src=" ' + row.typeImg + ' ">' : '';
          }
        },
        {
          //指定是第4列
          targets: 3,
          ordering: false,
          render: function (data, type, row, meta) {
            return row.status ? '<code class="text-success">上架</code>' : '<code class="text-danger">下架</code>';
          }
        },
        {
          targets: 5,
          orderable: false,
          render: function (data, type, row, meta) {
            var html = row.status ?
                '<button class="btn btn-warning btn-xs" onclick="updateStatus('+row.typeId+')"><i class="fa fa-toggle-off"></i> 下架</button>'
                : '<button class="btn btn-primary btn-xs" onclick="updateStatus('+row.typeId+')"><i class="fa fa-toggle-on"></i> 上架</button>';
            html += '&nbsp;<button class="btn btn-danger btn-xs" onclick="removeGoods('+row.typeId+')"><i class="fa fa-remove"></i> 删除 </button>';
            html += '&nbsp;<a data-toggle="modal" data-target="#modal_edit" class="btn btn-primary btn-xs" href="'+contextPath+'/goodsTypes/'+row.typeId+'"><i class="fa fa-edit"></i> 编辑 </a>';
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

var typeImg = $('#modal_create').find('#typeImg');
typeImg.fileinput({
  overwriteInitial: true,
  language: 'zh',
  previewFileType: 'image',
  allowedFileTypes: ['image'],
  allowedFileExtensions: ['jpg', 'jpeg', 'png', 'gif', 'bmp'],
  maxFilePreviewSize: 200,
  maxFileSize: 200,
  showClose: false,
  showUpload: false,
  showRemove: false,
  browseLabel: '选择图片',
  browseIcon: '<i class="glyphicon glyphicon-folder-open"></i>'
});

/**
 * 删除数据
 * @param typeId
 */
function removeGoods(typeId) {
  ajaxRequest('/goodsTypes/' + typeId, 'DELETE', table);
}

/**
 * 更新状态
 * @param typeId
 */
function updateStatus(typeId) {
  ajaxRequest('/goodsTypes/' + typeId + '/status', 'POST', table);
}

/**
 * 编辑
 * @param row
 */
function edit(row) {
  var editModel = $('#modal_update');
  editModel.find('#typeName').val(row.typeName);
  editModel.find('#typeId').val(row.typeId);
  editModel.find('#typeImg').fileinput({
    overwriteInitial: true,
    language: 'zh',
    previewFileType: 'image',
    allowedFileTypes: ['image'],
    allowedFileExtensions: ['jpg', 'jpeg', 'png', 'gif', 'bmp'],
    maxFilePreviewSize: 200,
    maxFileSize: 200,
    showClose: false,
    showUpload: false,
    showRemove: false,
    browseLabel: '选择图片',
    browseIcon: '<i class="glyphicon glyphicon-folder-open"></i>',
    defaultPreviewContent: '<img src=" ' + row.typeImg + '" alt="商品分类图片">'
  });
  editModel.modal('show');
}