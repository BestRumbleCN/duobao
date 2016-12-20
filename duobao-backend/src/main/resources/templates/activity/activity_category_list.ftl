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
          <th>序号</th>
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

</@layout.main>
<script src="${requestContext.contextPath}/static/js/modules/activity-category.js" type="text/javascript"
        charset="UTF-8"></script>