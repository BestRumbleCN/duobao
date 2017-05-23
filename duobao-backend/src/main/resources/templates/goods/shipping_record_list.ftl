<#-- @ftlvariable name="shippingStatusMap" type="java.util.Map<String, String>" -->
<#import '../common/layout.ftl' as layout>

<@layout.main pageTitle=springMacroRequestContext.getMessage("pageTitle.shipping_record")>

<div class="col-md-12 col-sm-12 col-xs-12">
  <div class="x_panel">
    <div class="x_title">
      <h2><@spring.message "pageTitle.shipping_record"/></h2>
      <ul class="nav navbar-right panel_toolbox">
        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
        </li>
      </ul>
      <div class="clearfix"></div>
    </div>
    <div class="x_content">
    <#--搜索开始-->
      <div class="table-responsive">
        <table id="table_search" class="table table-striped table-bordered">
          <tbody>
          <tr>
            <td class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-3 control-label" for="txt_bid_id">幸运号码:</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control input-sm" id="txt_lucky_num" name="luckyNum"
                         title=""
                         placeholder="幸运号码">
                </div>
              </div>
            </td>
            <td class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-3 control-label" for="txt_cellphone">手机号:</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control input-sm" id="txt_cellphone" name="cellphone"
                         title=""
                         placeholder="手机号">
                </div>
              </div>
            </td>
            <td class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-3 control-label" for="cmb_shipping_status">状态:</label>
                <div class="col-sm-9">
                  <select class="form-control input-sm" id="cmb_shipping_status" name="shippingStatus" title="">
                    <option value="">--全部--</option>
                    <#list shippingStatusMap?keys as key>
                        <option value="${key}">${shippingStatusMap[key]}</option>
                    </#list>
                  </select>
                </div>
              </div>
            </td>
          </tr>
          <tr>
            <td class="form-horizontal" colspan="3">
              <div class="form-group">
                <button id="btn_search" class="btn btn-primary btn-sm"><i class="fa fa-search"></i> 搜索 </button>
                <button id="btn_reset" class="btn btn-warning btn-sm"><i class="fa fa-refresh"></i> 重置 </button>
              </div>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    <#--搜索结束-->

      <table id="dataTable_shippingRecord" class="table table-striped table-bordered dt-responsive nowrap"
             cellspacing="0"
             width="100%">
        <thead>
        <tr>
          <th>序号</th>
          <th>期数</th>
          <th>中奖时间</th>
          <th>幸运号码</th>
          <th>奖品名称</th>
          <th>收货人用户ID</th>
          <th>收货人姓名</th>
          <th>手机号码</th>
          <th>收货地址</th>
          <th>状态</th>
          <th>操作</th>
        </tr>
        </thead>
      </table>
    </div>
  </div>
</div>

</@layout.main>
<script src="${requestContext.contextPath}/static/js/modules/shipping-record.js" type="text/javascript"
        charset="UTF-8"></script>

