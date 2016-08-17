/**
 * <p>
 *
 * </p>
 * @author wushige
 * @date   2016-07-27 11:35
 */
var tableId = 'dataTable-user';
var table;
$(function () {
    table = $('#' + tableId).DataTable({
//            responsive: true,
//            order: [[ 0, 'asc' ]],
        language: {
            url: contextPath + '/static/js/chinese.json'
        },
//	        searching: false,
        processing: true,
        serverSide: true,
        ajax: {
            type: 'get',
            url: '/users/userPage'
        },
        columns: [
            {"data": "userId"},
            {"data": "username"},
            {"data": "userStatus"},
            {"data": "createTime"},
            {"data": null}
        ],
        columnDefs: [
            {
                //指定是第1列
                targets: 0,
                render: function (data, type, row, meta) {
                    return "<a href='javascript:void(0);' onclick='edit(" + JSON.stringify(row) + ")'>" + data +"</a>";
                }
            },
            {
                //指定是第3列
                targets: 2,
                ordering: false,
                render: function (data, type, row, meta) {
                    //return row.userStatus ? '正常' : '禁用';
                    return row.userStatus ? '<span class="label label-primary">正常</span>' : '<span class="label label-danger">禁用</span>';
                }
            },
            {
                //指定是第5列
                targets: 4,
                ordering: false,
                render: function (data, type, row, meta) {
                    var html = row.userStatus ? '<button class="btn btn-warning btn-xs" onclick="updateStatus( '
                    + row.userId + ' )"><i class="fa fa-toggle-off"></i> 禁用</button>'
                        : '<button class="btn btn-primary btn-xs" onclick="updateStatus( '
                    + row.userId + ' )"><i class="fa fa-toggle-on"></i> 正常</button>';
                    return html + '&nbsp;<button type="button" class="btn btn-danger btn-xs" onclick="deleteUser( '
                        + row.userId + ' )"><i class="fa fa-remove"></i> 删除 </button>';
                }
            }
        ],
        fnInitComplete: function () {
            //搜索框div：dataTable-user + _filter
            var searchDiv = $('#' + tableId +'_filter');
            searchDiv.html('<label><div class="input-group"><input type="search" class="form-control input-sm" '
                + 'placeholder="" aria-controls="' + tableId + '"><span class="input-group-btn"><button '
                + 'type="button" class="btn btn-sm btn-primary">'
                + '搜索</button></span></div></label>');
            searchDiv.find('input').unbind().bind('keyup', function (e) {
                if (e.keyCode == 13) {
                    table.search(this.value).draw();
                }
            });
            searchDiv.find('button').bind('click', function () {
                table.search(searchDiv.find('input').val()).draw();
            });
        }
    });
});

/**
 * 删除数据
 * @param userId
 */
function deleteUser(userId) {
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