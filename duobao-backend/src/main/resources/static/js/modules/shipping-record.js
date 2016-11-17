/**
 * <p>
 *
 * </p>
 * @author WuGang
 * @since 1.0
 */
(function ($) {

    var $table_id = 'dataTable_shippingRecord', $table_search, $btn_search, $btn_reset;
    //查询参数
    var $txt_bid_id, $txt_cellphone, $cmb_shipping_status;

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
        $cmb_shipping_status = $table_search.find('#cmb_shipping_status')
    }

    function initEvents() {
        initDataTable();

        // 搜索
        $btn_search.on("click", function () {
            table.settings()[0].ajax.data = function (d) {
                return $.extend({}, d, {
                    'table': JSON.stringify(d),
                    'bidId': $txt_bid_id.val(),
                    'cellphone': $txt_cellphone.val(),
                    'shippingStatus': $cmb_shipping_status.val()
                });
            };
            table.ajax.reload();
        });
        // 重置
        $btn_reset.on("click", function () {
            $txt_bid_id.val('');
            $txt_cellphone.val('');
            $cmb_shipping_status.val('');
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
                url: '/shippingRecords/table.json',
                data: function (d) {
                    return $.extend({}, d, {
                        "table": JSON.stringify(d),
                        'bidId': $txt_bid_id.val(),
                        'cellphone': $txt_cellphone.val(),
                        'shippingStatus': $cmb_shipping_status.val()
                    });
                    // return JSON.stringify(d);
                }
            },
            columns: [
                {data: 'id'},
                {data: 'bidId'},
                {data: 'publishTime'},
                {data: 'luckyNum'},
                {data: 'goodsId'},
                {data: 'receiverName'},
                {data: 'cellphone'},
                {data: 'shippingAddress'},
                {data: null}
            ],
            columnDefs: [

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