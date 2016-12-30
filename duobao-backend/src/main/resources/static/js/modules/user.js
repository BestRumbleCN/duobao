/**
 * <p>
 * 用户js
 * </p>
 * @author wushige
 * @date   2016-07-27 11:35
 */
$(function () {
  var $table_id = 'dataTable-user';
  var $btn_search, $btn_reset, $txt_userId, $cmb_status, $txt_username;

  $(document).ready(init());

  function init() {
    initElements();
    initEvents();
  }

  function initElements() {
    $btn_search = $('#btn_search');
    $btn_reset = $('#btn_reset');
    $txt_userId = $('#txt_user_id');
    $cmb_status = $('#cmb_status');
    $txt_username = $('#txt_username');
  }

  function initEvents() {
    initDataTable();
    // initFormValidation();

    // 搜索
    $btn_search.on("click", function () {
      table.settings()[0].ajax.data = function (d) {
        return $.extend({}, d, {
          'table': JSON.stringify(d),
          'userId': $txt_userId.val(),
          'userStatus': $cmb_status.val(),
          'username': $txt_username.val()
        });
      };
      table.ajax.reload();
    });
    // 重置
    $btn_reset.on("click", function () {
      $txt_userId.val('');
      $cmb_status.val('');
      $txt_username.val('');
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
      order: [[5, 'desc']],
      language: {
        url: contextPath + '/static/js/lib/dataTables/dataTable_zh_CN.json'
      },
      searching: true,
      processing: true,
      serverSide: true,
      select: true,
      ajax: {
        type: 'get',
        url: contextPath + '/users/table.json',
        data: function (d) {
          return $.extend({}, d, {
            "table": JSON.stringify(d)
          });
        }
      },
      columns: [
        {data: 'userId'},
        {data: 'username'},
        {data: 'nickname'},
        {data: 'avatar'},
        {data: 'userStatus'},
        {data: 'createTime'},
        {data: null}
      ],
      columnDefs: [
        {
          //指定是第1列
          targets: 0,
          render: function (data, type, row, meta) {
            return "<a href='javascript:void(0);' onclick='edit(" + JSON.stringify(row) + ")'>" + data + "</a>";
          }
        },
        {
          targets: 3,
          ordering: false,
          render: function (data, type, row, meta) {
            // return row.typeImg.length ? '<img src=" ' + row.typeImg + ' ">' : '';
            var $template;
            if (row.avatar != undefined && row.avatar != '') {
              $template = '<a href="javascript:;" onclick="showImage(this);" ' +
                  'class="btn btn-success btn-xs"><i class="fa fa-eye"></i> 预览</a><img style="display: none" src="' + row.avatar + '" />';
            } else {
              $template = '<code>无</code>'
            }
            return $template;
          }
        },
        {
          targets: 4,
          render: function (data, type, row, meta) {
            return row.userStatus ? '<code class="text-success">正常</code>' : '<code class="text-danger">禁用</c>';
          }
        },
        {
          targets: 6,
          orderable: false,
          render: function (data, type, row, meta) {
            var html = row.userStatus ?
                '<button class="btn btn-warning btn-xs" onclick="updateStatus(' + row.userId + ')"><i class="fa fa-toggle-off"></i> 禁用</button>'
                : '<button class="btn btn-primary btn-xs" onclick="updateStatus(' + row.userId + ')"><i class="fa fa-toggle-on"></i> 解禁</button>';
            html += '&nbsp;<button type="button" class="btn btn-danger btn-xs" onclick="remove(' + row.userId + ')"><i class="fa fa-remove"></i> 删除 </button>';
            html += '&nbsp;<a data-toggle="modal" data-target="#modal_edit" class="btn btn-primary btn-xs" href="' + contextPath + '/users/' + row.userId + '"><i class="fa fa-edit"></i> 编辑 </a>';
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
});

/**
 * 删除数据
 * @param userId
 */
function remove(userId) {
  ajaxRequest('/users/' + userId, 'DELETE', table);
}

/**
 * 更新状态
 * @param userId
 */
function updateStatus(userId) {
  ajaxRequest('/users/' + userId + '/status', 'POST', table);
}

/**
 * 编辑
 * @param row
 */
function edit(row) {
  var editModel = $('#modal_update');
  editModel.find('#username').val(row.username);
  editModel.find('#userId').val(row.userId);
  editModel.modal('show');
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