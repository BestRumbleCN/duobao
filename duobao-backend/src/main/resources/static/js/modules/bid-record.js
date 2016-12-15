/**
 * <p>
 *
 * </p>
 * @author WuGang
 * @since 1.0
 */
(function ($) {

    var $table_id = 'dataTable_bidRecord', $table_search, $btn_search, $btn_reset;
    //查询参数
    var $txt_bid_id, $txt_cellphone;

    $(document).ready(init());

    function init() {
        initElements();
        initEvents();
    }

    function initElements() {
        $table_search = $('#table_search');
        $btn_search = $('#btn_search');
        $btn_reset = $('#btn_reset');

        $txt_bid_id = $table_search.find('#txt_bid_id');
        $txt_cellphone = $table_search.find('#txt_cellphone');
    }

    function initEvents() {
        initDataTable();

        // 搜索
        $btn_search.on("click", function () {
            table.settings()[0].ajax.data = function (d) {
                return $.extend({}, d, {
                    'table': JSON.stringify(d),
                    'bidId': $txt_bid_id.val(),
                    'cellphone': $txt_cellphone.val()
                });
            };
            table.ajax.reload();
        });
        // 重置
        $btn_reset.on("click", function () {
            $txt_bid_id.val('');
            $txt_cellphone.val('');
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
                url: '/bidRecords/table.json',
                data: function (d) {
                    return $.extend({}, d, {
                        "table": JSON.stringify(d),
                        'bidId': $txt_bid_id.val(),
                        'cellphone': $txt_cellphone.val()
                    });
                    // return JSON.stringify(d);
                }
            },
            columns: [
                {data: null},
                {data: 'bidId'},
                {data: 'goodsId'},
                {data: 'totalAmount'},
                {data: 'winnerId'},
                {data: 'joinAmount'},
                {data: 'luckyNum'},
                {data: 'publishTime'},
                {data: 'bidStatus'}
            ],
            columnDefs: [
                {
                    targets: 0,
                    orderable: false,
                    render: function (data, type, row, meta) {
                        return meta.row + 1;
                    }
                },
                {
                    targets: 4,
                    orderable: false
                },
                {
                    targets: 5,
                    orderable: false
                },
                {
                    targets: 6,
                    orderable: false
                },
                {
                    targets: 7,
                    orderable: false
                }
            ],
            fnInitComplete: function () {
                //隐藏搜索框
                $('#' + $table_id + '_filter').hide();
            }
        });
    }

})(jQuery);