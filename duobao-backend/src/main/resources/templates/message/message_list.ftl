<#import '../common/layout.ftl' as layout>

<@layout.main pageTitle='消息列表'>

<div class="col-md-12 col-sm-12 col-xs-12">
  <div class="x_panel">
    <div class="x_title">
      <h2>消息列表</h2>
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
      <table id="dataTable_message" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0"
             width="100%">
        <thead>
        <tr>
          <th>ID</th>
          <th>标题</th>
          <th>内容</th>
          <th>类型</th>
        </tr>
        </thead>
      </table>
    </div>
  </div>
</div>

<#--创建modal开始-->
<div class="modal fade" id="modal_create" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel" style="text-align: center">
          添加消息
        </h4>
      </div>
      <div class="modal-body">
        <form id="form_create_message" class="form-horizontal"
              action="${requestContext.contextPath}/messages" method="post">
          <div class="form-group">
            <label class="col-sm-2 control-label" for="txt_message_type">类型</label>
            <div class="col-sm-10">
              <select id="txt_message_type" name="messageType" class="form-control"
                      data-fv-message="<@spring.message 'message.v.type_required'/>" required>
                    <option value="SYSTEM">系统消息</option>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label" for="txt_title">标题</label>
            <div class="col-sm-10">
              <input type="text" id="txt_title" name="title" value="" class="form-control"
                     data-fv-message="<@spring.message 'message.v.title_required'/>" required>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label" for="txt_content">内容</label>
            <div class="col-sm-10">
              <textarea id="txt_content" name="content" class="form-control" placeholder="内容"
                        data-fv-message="<@spring.message 'message.v.title_required'/>" required></textarea>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default"
                    data-dismiss="modal"><@spring.message 'page.close'/></button>
            <button type="submit" class="btn btn-primary"><@spring.message 'page.save'/></button>
          </div>
        </form>
      </div>
    </div>
    <!-- /.modal-content -->
  </div>
  <!-- /.modal-dialog -->
</div>
<#--创建modal结束-->
</@layout.main>
<script src="${requestContext.contextPath}/static/js/modules/message.js" type="text/javascript" charset="UTF-8"></script>