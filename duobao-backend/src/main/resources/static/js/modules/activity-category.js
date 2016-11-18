/**
 * <p>
 *
 * </p>
 * @author WuGang
 * @since 1.0
 */
(function ($) {

    var $table_id = 'dataTable_activityCategory';

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
                url: '/activityCategories/table.json',
                data: function (d) {
                    return $.extend({}, d, {
                        "table": JSON.stringify(d)
                    });
                    // return JSON.stringify(d);
                }
            },
            columns: [
                {data: 'categoryId'},
                {data: 'name'},
                {data: 'img'},
                {data: 'enabled'},
                {data: null}
            ],
            columnDefs: [
                {
                    targets: 3,
                    orderable: false,
                    render: function (data, type, row, meta) {
                        return row.enabled
                            ? '<span class="label label-success">正常</span>'
                            : '<span class="label label-danger">关闭</span>';
                    }
                },
                {
                    targets: 4,
                    orderable: false,
                    render: function (data, type, row, meta) {
                        var template = '';
                        template += row.enabled
                            ? '<button class="btn btn-warning btn-xs" onclick="updateStatus( ' + row.id + ' )"><i class="fa fa-toggle-off"></i> 关闭</button>'
                            : '<button class="btn btn-success btn-xs" onclick="updateStatus( ' + row.id + ' )"><i class="fa fa-toggle-on"></i> 打开</button>';
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

})(jQuery);