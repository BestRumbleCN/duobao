<#import '../common/layout.ftl' as layout>

<@layout.main pageTitle=springMacroRequestContext.getMessage("pageTitle.activity_category_list")>

<div class="col-md-12 col-sm-12 col-xs-12">
  <div class="x_panel">
    <div class="x_title">
      <h2><@spring.message "pageTitle.activity_category_list"/></h2>
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
      <table id="dataTable_activityCategory" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0"
             width="100%">
        <thead>
        <tr>
          <th>活动分类ID</th>
          <th>分类名称</th>
          <th>分类图片</th>
          <th>状态</th>
          <th>操作</th>
        </tr>
        </thead>
      </table>
    </div>
  </div>
</div>

<div class="modal fade" id="modal_create" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel" style="text-align: center">
          <@spring.message "page.add"/>
        </h4>
      </div>
      <div class="modal-body">
        <form id="form_create-user" class="form-horizontal form-label-left submit-form"
              action="${requestContext.contextPath}/activityCategories" method="post" enctype="multipart/form-data">
          <div class="form-group">
            <label class="control-label col-md-2 col-sm-2 col-xs-12"
                   for="txt_name">分类名称 <span
                class="required">*</span>
            </label>

            <div class="col-md-10 col-sm-10 col-xs-12">
              <input type="text" id="txt_name" name="name" class="form-control col-md-7 col-xs-12"
                     placeholder="分类名称" required data-fv-message="<@spring.message 'activityCategory.v.name_required'/>">
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-md-2 col-sm-2 col-xs-12"
                   for="txt_file">分类图片</label>

            <div class="col-md-10 col-sm-10 col-xs-12">
              <input type="file" id="txt_file" name="file" class="form-control col-md-7 col-xs-12" placeholder="分类图片">
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default"
                    data-dismiss="modal"><@spring.message "page.close"/></button>
            <button type="submit" id="create-user-btn"
                    class="btn btn-primary submit-btn"><@spring.message "page.save"/></button>
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
<script src="${requestContext.contextPath}/static/js/modules/activity-category.js" type="text/javascript"
        charset="UTF-8"></script>