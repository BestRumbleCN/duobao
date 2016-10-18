/**
 * <p>
 * 商品类型js
 * </p>
 * @author wushige
 * @date   2016-08-18 11:35
 */
var tableId = 'dataTable-goods';
var table;
$(function () {
    table = $('#' + tableId).DataTable({
        language: {
            url: contextPath + '/static/js/chinese.json'
        },
        processing: true,
        serverSide: true,
        "lengthChange" : false,
        "sort" : false,
		"filter" : false,
        ajax: {
            type: 'get',
            url: contextPath + '/goods/dataTable',
            "data" : function(d) {
				// 添加额外的参数传给服务器
				d.goodsName = $("#sGoodsName").val();
				d.goodsStatus = $("#sGoodsStatus").val();
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
                //指定是第1列
                targets: 0,
                render: function (data, type, row, meta) {
                    return "<a href='javascript:void(0);' onclick='edit(" + JSON.stringify(row) + ")'>" + data +"</a>";
                }
            },
            {
                //指定是第6列
                targets: 5,
                ordering: false,
                render: function (data, type, row, meta) {
                    return row.goodsStatus ? '<code class="text-success">上架</code>' : '<code class="text-danger">下架</code>';
                }
            },
            {
                //指定是第7列
                targets: 6,
                ordering: false,
                render: function (data, type, row, meta) {
                    var html = row.goodsStatus ? '<button class="btn btn-warning btn-xs" onclick="updateStatus( '
                    + row.goodsId + ' )"><i class="fa fa-toggle-off"></i> 下架</button>'
                        : '<button class="btn btn-primary btn-xs" onclick="updateStatus( '
                    + row.goodsId + ' )"><i class="fa fa-toggle-on"></i> 上架</button>';
                    return html + '&nbsp;<button class="btn btn-danger btn-xs" onclick="removeGoods( '
                        + row.goodsId + ' )"><i class="fa fa-remove"></i> 删除 </button>';
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
}).on("filebatchselected", function(event, files) {
    $(this).fileinput("upload");
}).on("fileuploaded", function(event, data, previewId, index) {
    if(data.response)
    {
    	addPics.push(data.response.message);
    }
}).on('filesuccessremove', function(event, previewId) {
	var index = $("#modal_create .file-preview-frame").index("#"+previewId);
	console.log(index);
	addPics.splice(index,1);
});

/**
 * 删除数据
 * @param goodsId
 */
function removeGoods(goodsId) {
    ajaxRequest('/goods/' + goodsId, 'DELETE', table);
}

/**
 * 更新状态
 * @param goodsId
 */
function updateStatus(goodsId) {
	confirmNotify('提示','确认更新吗？','/goods/' + goodsId + '/status', 'POST', table);
}

/**
 * 编辑
 * @param row
 */
function edit(row) {
    var editModel = $('#modal_update');
    editModel.find('#goodsName').val(row.goodsName);
    //editModel.find('#typeId').val(row.typeId);
    editModel.modal('show');
}

/**
 * 查询
 */
$("#pic").click(function(){
	table.ajax.reload();
});


$("#create-user-btn").click(function(){
	
});