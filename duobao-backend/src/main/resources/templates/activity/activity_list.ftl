<#-- @ftlvariable name="categories" type="java.util.List<team.wuxie.crowdfunding.domain.TActivityCategory>" -->
<#import '../common/layout.ftl' as layout>

<@layout.main pageTitle=springMacroRequestContext.getMessage("pageTitle.activity_list")>

<div class="col-md-12 col-sm-12 col-xs-12">
  <div class="x_panel">
    <div class="x_title">
      <h2><@spring.message "pageTitle.activity_list"/></h2>
      <ul class="nav navbar-right panel_toolbox">
        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
        <li class="dropdown">
          <a href="#" data-toggle="modal" data-target="#modal_create" title="创建">
            <i class="fa fa-plus"></i></a>
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
                <label class="col-sm-3 control-label" for="txt_bid_id">类型:</label>
                <div class="col-sm-9">
                  <select class="form-control input-sm" id="cmb_category_id" name="categoryId" title="">
                    <option value="">--全部--</option>
                    <#list categories as category>
                        <option value="${category.categoryId}">${category.name}</option>
                    </#list>
                  </select>
                </div>
              </div>
            </td>
            <td class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-3 control-label" for="txt_cellphone">活动名称:</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control input-sm" id="txt_name" name="name"
                         title=""
                         placeholder="活动名称，模糊查询">
                </div>
              </div>
            </td>
            <td class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-3 control-label" for="cmb_shipping_status">状态:</label>
                <div class="col-sm-9">
                  <select class="form-control input-sm" id="cmb_enabled" name="enabled" title="">
                    <option value="">--全部--</option>
                    <option value="1">启用</option>
                    <option value="0">关闭</option>
                  </select>
                </div>
              </div>
            </td>
          </tr>
          <tr>
            <td class="form-horizontal" colspan="3">
              <div class="form-group">
                <button id="btn_search" class="btn btn-primary btn-sm"><i class="fa fa-search"></i> 搜索</button>
                <button id="btn_reset" class="btn btn-warning btn-sm"><i class="fa fa-refresh"></i> 重置</button>
              </div>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    <#--搜索结束-->

      <table id="dataTable_activity" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0"
             width="100%">
        <thead>
        <tr>
          <th>序号</th>
          <th>活动名称</th>
          <th>类型</th>
          <th>状态</th>
          <th>操作</th>
        </tr>
        </thead>
      </table>
    </div>
  </div>
</div>

</@layout.main>
<script src="${requestContext.contextPath}/static/js/modules/activity.js" type="text/javascript"
        charset="UTF-8"></script>