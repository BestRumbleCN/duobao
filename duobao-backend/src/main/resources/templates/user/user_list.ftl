<#import '../common/layout.ftl' as layout>

<@layout.main pageTitle=springMacroRequestContext.getMessage("pageTitle.user_list")>

<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
        <div class="x_title">
            <h2><@spring.message "pageTitle.user_list"/></h2>
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">Settings 1</a>
                        </li>
                        <li><a href="#">Settings 2</a>
                        </li>
                    </ul>
                </li>
                <li><a class="close-link"><i class="fa fa-close"></i></a>
                </li>
            </ul>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <table id="dataTable-user" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th><@spring.message "tableHeader.user_id"/></th>
                    <th><@spring.message "tableHeader.username"/></th>
                    <th><@spring.message "tableHeader.status"/></th>
                    <th><@spring.message "tableHeader.role"/></th>
                    <th><@spring.message "tableHeader.create_time"/></th>
                    <th><@spring.message "tableHeader.operation"/></th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>

<div class="modal fade" id="modal_update" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel" style="text-align: center">
					<@spring.message "page.edit"/>
                </h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal submit-form" id="form_insert-channelOrder"
                      action="${requestContext.contextPath}/users" method="post">

                    <div class="form-group">
                        <label for="username"
                               class="col-sm-3 control-label"><@spring.message "page.username"/> </label>

                        <div class="col-sm-9">
                            <input class="form-control col-sm-22" id="username" name="username" required
                                   placeholder="<@spring.message "page.username"/>">
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal"><@spring.message "page.close"/></button>
                        <button type="submit" id="save-channelOrder"
                                class="btn btn-primary submit-btn"><@spring.message "page.save"/></button>
                    </div>
                </form>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

</@layout.main>
<script>
    var contextPath = "${requestContext.contextPath}";
    var tableId = 'dataTable-user';
    var table;
    $(function () {
        table = $('#' + tableId).dataTable({
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
                        return '<button type="button" class="btn btn-danger btn-xs" onclick="del( ' + row.userId + ' )"><@spring.message "page.delete"/></button>';
                    }
                }
            ],
            fnInitComplete: function () {
                //搜索框div：dataTable-user + _filter
                var searchDiv = $('#' + tableId +'_filter');
	            searchDiv.html('<label><div class="input-group"><input type="search" class="form-control input-sm" '
			            + 'placeholder="" aria-controls="' + tableId + '"><span class="input-group-btn"><button '
			            + 'type="button" class="btn btn-sm btn-primary">'
			            + '<@spring.message "page.search"/></button></span></div></label>');
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
                }, success: function (data) {
                    table.ajax.reload();
                    console.log("删除成功" + data);
                }
            });
        }
    }

    function edit(row) {
        var editModel = $('#modal_update');
        $('#phone').val(row.phone);
        editModel.modal('show');
    }
</script>

