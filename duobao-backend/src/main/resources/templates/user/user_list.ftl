<#-- @ftlvariable name="requestContext" type="javax.servlet.http.HttpServletRequest" -->
<#-- @ftlvariable name="accounts" type="java.util.List<com.niuwan.pojo.TAccount>" -->
<#import '../common/layout.ftl' as layout>

<@layout.main pageTitle=springMacroRequestContext.getMessage("pageTitle.user_list")>

<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
        <div class="x_title">
            <h2>用户管理</h2>
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
                    <th>用户ID</th>
                    <th>用户名</th>
                    <th>状态</th>
                    <th>角色</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>

</@layout.main>
<script>
    var contextPath = "${requestContext.contextPath}";
    var table;
    $(function () {
        table = $('#dataTable-user').dataTable({
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

                        return '<a data-toggle="modal" data-target="#modal_update" ' +
                                'href="' + (contextPath + '/users/' + data) + '">' + data + '</a>';
                    }
                },
                {
                    //指定是第6列
                    targets: 5,
                    render: function (data, type, row, meta) {

                        return '<button class="btn btn-danger btn-xs" onclick="del( ' + row.userId + ' )">删除</button>';
                    }
                }
            ],
            initComplete: function () {
                //搜索框div：dataTable-user + _filter
                var searchDiv = $('#dataTable-user_filter');
                searchDiv.find('label').html(searchDiv.html() +
                        '<button class="btn btn-sm btn-primary" style="border-radius: 0">搜索</button>');
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
</script>

