<#-- @ftlvariable name="goodsTypes" type="team.wuxie.crowdfunding.domain.TGoodsType[]" -->

<#import '../common/layout.ftl' as layout>

<@layout.main pageTitle=springMacroRequestContext.getMessage("pageTitle.goods_list")>

<div class="col-md-12 col-sm-12 col-xs-12">
<#--<div class="row">-->
<#--<div class="col-lg-12">-->
<#--<div class="navbar navbar-default bootstrap-admin-navbar-thin">-->
<#--<ol class="breadcrumb bootstrap-admin-breadcrumb">-->
<#--<li><a href="#">首页</a></li>-->
<#--<li class="active"><a href="#">财务信息</a></li>-->
<#--</ol>-->
<#--</div>-->
<#--</div>-->
<#--</div>-->
  <div class="x_panel">
    <div class="x_title">
      <h2><@spring.message "pageTitle.goods_list"/></h2>
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
                <label class="col-sm-3 control-label" for="txt_goods_name">商品名称:</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control input-sm" id="txt_goods_name" name="goodsName"
                         title=""
                         placeholder="商品名称，模糊查询">
                </div>
              </div>
            </td>
            <td class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-3 control-label" for="cmb_goods_type">分类:</label>
                <div class="col-sm-9">
                  <select class="form-control input-sm" id="cmb_goods_type" name="goodsType" title="">
                    <option value="">--全部--</option>
                    <#list goodsTypes as goodsType>
                      <option value="${goodsType.typeId}">${goodsType.typeName}</option>
                    </#list>
                  </select>
                </div>
              </div>
            </td>
            <td class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-3 control-label" for="cmb_goods_status">状态:</label>
                <div class="col-sm-9">
                  <select class="form-control input-sm" id="cmb_goods_status" name="goodsStatus" title="">
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

      <table id="dataTable_goods" class="table table-striped table-bordered dt-responsive nowrap"
             cellspacing="0" width="100%">
        <thead>
        <tr>
          <th><@spring.message "tableHeader.goods_id"/></th>
          <th><@spring.message "tableHeader.goods_name"/></th>
          <th><@spring.message "tableHeader.goods_img"/></th>
          <th>图文详情</th>
          <th><@spring.message "tableHeader.single_price"/></th>
          <th><@spring.message "tableHeader.total_amount"/></th>
          <th><@spring.message "tableHeader.type_name"/></th>
          <th><@spring.message "tableHeader.status"/></th>
          <th><@spring.message "tableHeader.operation"/></th>
        </tr>
        </thead>
      </table>
    </div>
  </div>
</div>

<div class="modal fade" id="modal_create" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     data-backdrop="false">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel" style="text-align: center">
          <@spring.message "page.add"/>
        </h4>
      </div>
      <div class="modal-body">
        <form id="form_create_goods" class="form-horizontal" action="${requestContext.contextPath}/goods" method="post">
          <div class="form-group">
            <label class="control-label col-md-2 col-sm-2 col-xs-12"
                   for="goodsName"><@spring.message "page.goods_name"/> <span
                class="required">*</span>
            </label>

            <div class="col-md-10 col-sm-10 col-xs-12">
              <input type="text" id="goodsName" name="goodsName" class="form-control col-md-7 col-xs-12"
                     placeholder="<@spring.message "page.goods_name"/>" required data-fv-message="<@spring.message 'goods.v.goodsName_required'/>">
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-md-2 col-sm-2 col-xs-12"
                   for="singlePrice"><@spring.message "page.goods_single_price"/> <span
                class="required">*</span>
            </label>

            <div class="col-md-10 col-sm-10 col-xs-12">
              <input type="number" id="singlePrice" name="singlePrice" class="form-control col-md-7 col-xs-12"
                     placeholder="<@spring.message "page.goods_single_price"/>" required>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-md-2 col-sm-2 col-xs-12"
                   for="totalAmount"><@spring.message "page.goods_total_amount"/> <span
                class="required">*</span>
            </label>

            <div class="col-md-10 col-sm-10 col-xs-12">
              <input type="number" id="totalAmount" name="totalAmount" class="form-control col-md-7 col-xs-12"
                     placeholder="<@spring.message "page.goods_total_amount"/>" required>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-md-2 col-sm-2 col-xs-12"
                   for="channel"><@spring.message "page.goods_channel"/> <span
                class="required">*</span>
            </label>

            <div class="col-md-10 col-sm-10 col-xs-12">
              <select id="channel" name="channel" class="form-control col-md-7 col-xs-12" required>
                <option value="0">普通</option>
                <option value="1">爆款</option>
                <option value="2">新货</option>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-md-2 col-sm-2 col-xs-12"
                   for="typeId"><@spring.message "page.type_name"/> <span
                class="required">*</span>
            </label>

            <div class="col-md-10 col-sm-10 col-xs-12">
              <select id="typeId" name="typeId" class="form-control col-md-7 col-xs-12" required
                      data-fv-message="<@spring.message 'goods.v.typeId_required'/>">
                <option value="">--请选择--</option>
                <#list goodsTypes as goodsType>
                  <#if goodsType.status>
                    <option value="${goodsType.typeId}">${goodsType.typeName}</option>
                  </#if>
                </#list>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-md-2 col-sm-2 col-xs-12"
                   for="file_img"><@spring.message "page.goods_img"/> <span
                class="required">*</span>
            </label>

            <div class="col-md-10 col-sm-10 col-xs-12">
              <input type="file" multiple id="file_img" name="imgFiles" class="form-control col-md-7 col-xs-12"
                     required>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-md-2 col-sm-2 col-xs-12"
                   for="file_img_detail">图文详情
            </label>

            <div class="col-md-10 col-sm-10 col-xs-12">
              <input type="file" id="file_img_detail" name="imgDetailFile" class="form-control col-md-7 col-xs-12">
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-md-2 col-sm-2 col-xs-12"
                   for="statement"><@spring.message "page.goods_statement"/> <span
                class="required">*</span>
            </label>

            <div class="col-md-10 col-sm-10 col-xs-12">
               <textarea rows="5" id="statement" name="statement" class="form-control col-md-7 col-xs-12"
                         placeholder="<@spring.message "page.goods_statement"/>" required
                         data-fv-message="<@spring.message 'goods.v.statement_required'/>"></textarea>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default"
                    data-dismiss="modal"><@spring.message "page.close"/></button>
            <button type="submit" class="btn btn-primary"><@spring.message "page.save"/></button>
          </div>
        </form>
      </div>
    </div>
    <!-- /.modal-content -->
  </div>
  <!-- /.modal-dialog -->
</div>


<div class="modal fade" id="modal_edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">

    </div>
    <!-- /.modal-content -->
  </div>
  <!-- /.modal-dialog -->
</div>

</@layout.main>
<script src="${requestContext.contextPath}/static/js/modules/goods.js" type="text/javascript" charset="UTF-8"></script>

