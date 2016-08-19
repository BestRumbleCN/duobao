/**
 * <p>
 * 商品类型js
 * </p>
 * @author wushige
 * @date   2016-08-18 11:35
 */
var tableId = 'dataTable-goodsType';
var table;
$(function () {
    table = $('#' + tableId).DataTable({
        language: {
            url: contextPath + '/static/js/chinese.json'
        },
        processing: true,
        serverSide: true,
        ajax: {
            type: 'get',
            url: contextPath + '/goodsTypes/dataTable'
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
                //指定是第6列
                targets: 5,
                ordering: false,
                render: function (data, type, row, meta) {
                    var html = row.status ? '<button class="btn btn-warning btn-xs" onclick="updateStatus( '
                    + row.typeId + ' )"><i class="fa fa-toggle-off"></i> 下架</button>'
                        : '<button class="btn btn-primary btn-xs" onclick="updateStatus( '
                    + row.typeId + ' )"><i class="fa fa-toggle-on"></i> 上架</button>';
                    return html + '&nbsp;<button type="button" class="btn btn-danger btn-xs" onclick="remove( '
                        + row.typeId + ' )"><i class="fa fa-remove"></i> 删除 </button>';
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

var typeImg = $('#modal_create').find('#typeImg');
typeImg.fileinput({
    overwriteInitial: true,
    language: 'zh',
    previewFileType: 'image',
    allowedFileTypes: ['image'],
    allowedFileExtensions: ['jpg', 'jpeg', 'png', 'gif', 'bmp'],
    maxFileSize: 200,
    //uploadUrl: '/user/headImg',
    uploadAsync: true,
    showClose: false,
    showCaption: false,
    browseLabel: '选择图片',
    removeLabel: '',
    uploadLabel: '',
    browseIcon: '<i class="glyphicon glyphicon-folder-open"></i>',
    removeIcon: '<i class="glyphicon glyphicon-remove"></i>',
    removeTitle: '取消',
    elErrorContainer: '#kv-avatar-errors',
    msgErrorClass: 'alert alert-block alert-danger',
    //defaultPreviewContent: '<img src=" ' + contextPath + '/static/images/upload/default.jpg' + '" alt="商品类型图片" style="width:160px">',
    layoutTemplates: {main2: '{preview} {upload} {remove} {browse}'}
})/*.on('fileuploaded', function (event, data) {
    if (data.response.status === 1) {
        location.href = contextPath + '/user/profile';
    } else {
        showWarningToast(data.response.message);
    }
})*/;

/**
 * 删除数据
 * @param typeId
 */
function remove(typeId) {
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
    editModel.modal('show');
}