/**
 * @author WuGang
 * @since 1.0
 */
(function ($) {

  var $modal_create = $('#modal_create'), $modal_edit = $('#modal_edit');
  var $table_id = 'dataTable_banner';
  var $form_createBanner;

  $(document).ready(init());

  function init() {
    initElements();
    initEvents();
  }

  function initElements() {
    $form_createBanner = $modal_create.find('#form_create_banner');
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
      pageLength: 100,
      pagingType: "full_numbers",  //分页样式：simple,simple_numbers,full,full_numbers
      renderer: "bootstrap",
      autoWidth: true,  //自动调整列宽
      searching: true,
      processing: true,
      serverSide: true,
      select: true,
      ajax: {
        type: 'get',
        url: '/banners/table.json',
        data: function (d) {
          return $.extend({}, d, {
            "table": JSON.stringify(d)
          });
          // return JSON.stringify(d);
        }
      },
      columns: [
        {data: 'bannerId'},
        {data: 'bannerType'},
        {data: 'img'},
        {data: 'content'},
        {data: 'status'},
        {data: null}
      ],
      columnDefs: [
        {
          targets: 2,
          orderable: false,
          render: function (data, type, row, meta) {
            var $template;
            if (row.img != undefined && row.img != '') {
              $template = '<a href="javascript:;" onclick="showImage(this);" ' +
                  'class="btn btn-success btn-xs"><i class="fa fa-eye"></i> 预览</a><img style="display: none" src="' + row.img + '" />';
            } else {
              $template = '<code>无</code>'
            }
            return $template;
          }
        },
        {
          targets: 4,
          orderable: false,
          render: function (data, type, row, meta) {
            return row.status
                ? '<span class="label label-success">正常</span>'
                : '<span class="label label-danger">关闭</span>';
          }
        },
        {
          targets: 3,
          orderable: false
        },
        {
          targets: 5,
          orderable: false,
          render: function (data, type, row, meta) {
            var template = '';
            template += row.status
                ? '<button class="btn btn-warning btn-xs" onclick="updateStatus(' + row.bannerId + ')"><i class="fa fa-toggle-off"></i> 关闭</button>'
                : '<button class="btn btn-success btn-xs" onclick="updateStatus(' + row.bannerId + ')"><i class="fa fa-toggle-on"></i> 打开</button>';
            template += '&nbsp;<a data-toggle="modal" data-target="#modal_edit" class="btn btn-primary btn-xs" href="'+contextPath+'/banners/' + row.bannerId + '"><i class="fa fa-edit"></i> 编辑 </a>';
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

  function initFormValidation() {
    $form_createBanner.formValidation({
      framework: 'bootstrap',
      excluded: [':disabled', ':hidden', ':not(:visible)'],
      icon: false,
      autoFocus: true,
      live: 'enabled',
      message: '请填写必填项目',
      fields: {
        file: {
          validators: {
            file: {
              extension: 'jpg,jpeg,png',
              message: '支持图片格式jpg、jpeg、png'
            }
          }
        }
      }
    }).off('success.form.fv').on('success.form.fv', function (e) {//.off('success.form.fv')和e.preventDefault();都是为了防止表单重复提交
      //防止表单重复提交
      e.preventDefault();
      var form = $(e.target);
      ajaxSubmit(e, form, null, null, table);
      $($form_createBanner).data('formValidation').disableSubmitButtons(false);
    });
  }

})(jQuery);

/**
 * 更新状态
 * @param bannerId
 */
function updateStatus(bannerId) {
  ajaxRequest('/banners/' + bannerId + '/status', 'POST', table);
}

function showImage(o) {
  var _src = $(o).parents('td').find('img').attr('src');
  layer.open({
    type: 1,
    title: false,
    closeBtn: 0,
    area: 'auto',
    maxWidth: 'auto',
    skin: 'layui-layer-nobg', //没有背景色
    shadeClose: true,
    content: '<img src="' + _src + '" />'
  });
}