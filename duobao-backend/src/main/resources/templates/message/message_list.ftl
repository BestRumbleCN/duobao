<#-- @ftlvariable name="messageTypeMap" type="java.util.Map<String, String>" -->
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
    <#--搜索开始-->
      <div class="table-responsive">
        <table id="table_search" class="table table-striped table-bordered">
          <tbody>
          <tr>
            <td class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-3 control-label" for="txt_user_id">用户ID:</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control input-sm" id="txt_user_id" name="userId"
                         title=""
                         placeholder="用户ID">
                </div>
              </div>
            </td>
            <td class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-3 control-label" for="txt_username">用户名:</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control input-sm" id="txt_username" name="username"
                         title=""
                         placeholder="用户名">
                </div>
              </div>
            </td>
            <td class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-3 control-label" for="cmb_message_type">类型:</label>
                <div class="col-sm-9">
                  <select class="form-control input-sm" id="cmb_message_type" name="messageType" title="">
                    <option value="">--全部--</option>
                    <#list messageTypeMap?keys as key>
                      <option value="${key}">${messageTypeMap[key]}</option>
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

      <table id="dataTable_message" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0"
             width="100%">
        <thead>
        <tr>
          <th>ID</th>
          <th>标题</th>
          <th>内容</th>
          <th>类型</th>
          <th>是否已读</th>
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
                    <option value="">--请选择--</option>
                    <#list messageTypeMap?keys as key>
                      <option value="${key}">${messageTypeMap[key]}</option>
                    </#list>
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