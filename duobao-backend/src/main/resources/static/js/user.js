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
            {"data": "status"},
            {"data": "role"},
            {"data": "createTime"},
            {"data": null}
        ],
        columnDefs: [
            {
                //指定是第1列
                targets: 0,
                render: function (data, type, row, meta) {
                    return "<a href='javascript:void(0);' onclick='edit(" + JSON.stringify(row) + ")'>" + data +"</a>";;
                }
            },
            {
                //指定是第6列
                targets: 5,
                ordering: false,
                render: function (data, type, row, meta) {
                    return '<button type="button" class="btn btn-danger btn-xs" onclick="del( '
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
                    table.fnFilter(this.value);
                }
            });
            searchDiv.find('button').bind('click', function () {
                table.fnFilter(searchDiv.find('input').val());
            });
        }
    });
});

/**
 * 删除数据
 * @param userId
 */
function del(userId) {
    if (confirm('确定删除？')) {
        $.ajax({
            url: '/users/' + userId,
            type: 'DELETE',
            data: {
                "userId": userId
            },
            beforeSend: function(request) {
                request.setRequestHeader(header, token);
            },
            success: function (data) {
                table.ajax.reload();
                console.log("删除成功" + data);
            }
        });
    }
}

function edit(row) {
    var editModel = $('#modal_update');
    $('#username').val(row.username);
    editModel.modal('show');
}