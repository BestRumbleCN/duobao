<#-- @ftlvariable name="bidStatusMap" type="java.util.Map<String, String>" -->
<#import '../common/layout.ftl' as layout>

<@layout.main pageTitle=springMacroRequestContext.getMessage("pageTitle.bid_record")>

<div class="col-md-12 col-sm-12 col-xs-12">
  <div class="x_panel">
    <div class="x_title">
      <h2><@spring.message "pageTitle.bid_record"/></h2>
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
                <label class="col-sm-3 control-label" for="txt_bid_id">夺宝号码:</label>
                <div class="col-sm-9">
                  <input type="number" class="form-control input-sm" id="txt_bid_id" title="" placeholder="夺宝号码">
                </div>
              </div>
            </td>
            <td class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-3 control-label" for="txt_lucky_num">幸运号码:</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control input-sm" id="txt_lucky_num" title="" placeholder="幸运号码">
                </div>
              </div>
            </td>
            <td class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-3 control-label" for="cmb_status">状态:</label>
                <div class="col-sm-9">
                  <select class="form-control input-sm" id="cmb_status" title="">
                    <option value="">--请选择--</option>
                    <#list bidStatusMap?keys as key>
                      <option value="${key}">${bidStatusMap[key]}</option>
                    </#list>
                  </select>
                </div>
              </div>
            </td>
          <tr>
            <td class="form-horizontal" colspan="3">
              <div class="form-group">
                <button id="btn_search" class="btn btn-primary btn-sm"><i class="fa fa-search"></i> 搜索</button>
                <button id="btn_reset" class="btn btn-warning btn-sm"><i class="fa fa-refresh"></i> 重置
                </button>
              </div>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    <#--搜索结束-->

      <table id="dataTable_bidRecord" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0"
             width="100%">
        <thead>
        <tr>
          <th>序号</th>
          <th>期数</th>
          <th>商品名称</th>
          <th>总需</th>
          <th>获奖者</th>
          <th>参数次数</th>
          <th>夺宝号码</th>
          <th>夺宝时间</th>
          <th>状态</th>
        </tr>
        </thead>
      </table>
    </div>
  </div>
</div>

</@layout.main>
<script src="${requestContext.contextPath}/static/js/modules/bid-record.js" type="text/javascript"
        charset="UTF-8"></script>

