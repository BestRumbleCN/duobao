<#-- @ftlvariable name="messageTypeMap" type="java.util.Map<String, String>" -->
<#import '../common/layout.ftl' as layout>

<@layout.main pageTitle=springMacroRequestContext.getMessage("pageTitle.user_list")>

<div class="col-md-12 col-sm-12 col-xs-12">
  <div class="x_panel">
    <div class="x_title">
      <h2><@spring.message "pageTitle.user_list"/></h2>
      <ul class="nav navbar-right panel_toolbox">
        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
        <li><a href="#" data-toggle="modal" data-target="#modal_create" title="<@spring.message "page.add"/>">
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
                  <input type="text" class="form-control input-sm" id="txt_user_id" name="userId" title="" placeholder="用户ID">
                </div>
              </div>
            </td>
            <td class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-3 control-label" for="txt_username">用户名:</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control input-sm" id="txt_username" name="username" title="" placeholder="用户名，模糊查询">
                </div>
              </div>
            </td>
            <td class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-3 control-label" for="cmb_status">状态:</label>
                <div class="col-sm-9">
                  <select type="text" class="form-control input-sm" id="cmb_status" name="status" title="">
                    <option value="">--请选择--</option>
                    <option value="1">正常</option>
                    <option value="0">禁用</option>
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

      <table id="dataTable-user" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0"
             width="100%">
        <thead>
        <tr>
          <th><@spring.message "tableHeader.user_id"/></th>
          <th><@spring.message "tableHeader.username"/></th>
          <th>昵称</th>
          <th>头像</th>
          <th><@spring.message "tableHeader.status"/></th>
          <th><@spring.message "tableHeader.create_time"/></th>
          <th><@spring.message "tableHeader.operation"/></th>
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
              action="${requestContext.contextPath}/users" method="post">
          <div class="form-group">
            <label class="control-label col-md-2 col-sm-2 col-xs-12"
                   for="txt_username"><@spring.message "page.username"/> <span
                class="required">*</span>
            </label>

            <div class="col-md-10 col-sm-10 col-xs-12">
              <input type="text" id="txt_username" name="username" class="form-control col-md-7 col-xs-12"
                     placeholder="<@spring.message "page.username"/>" required data-fv-message="<@spring.message 'user.v.username_required'/>">
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-md-2 col-sm-2 col-xs-12"
                   for="txt_password"><@spring.message "page.password"/> <span
                class="required">*</span>
            </label>

            <div class="col-md-10 col-sm-10 col-xs-12">
              <input type="password" id="txt_password" name="password" class="form-control col-md-7 col-xs-12"
                     placeholder="<@spring.message "page.password"/>" required data-fv-message="<@spring.message 'user.v.password_required'/>">
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

<div class="modal fade" id="modal_send_message" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel" style="text-align: center">
          发送消息
        </h4>
      </div>
      <div class="modal-body">
        <form id="form_create_message" class="form-horizontal"
              action="${requestContext.contextPath}/messages" method="post">
          <input hidden id="userId" name="userId" value="" title="">
          <div class="form-group">
            <label class="col-sm-2 control-label" for="txt_message_type">类型</label>
            <div class="col-sm-10">
              <select id="txt_message_type" name="messageType" class="form-control"
                      data-fv-message="<@spring.message 'message.v.type_required'/>" required>
                <option value="">--请选择--</option>
                <#list messageTypeMap?keys as key>
                  <#if key != 'DEFAULT'>
                    <option value="${key}">${messageTypeMap[key]}</option>
                  </#if>
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
                        data-fv-message="<@spring.message 'message.v.title_required'/>" required rows="5"></textarea>
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

</@layout.main>
<script src="${requestContext.contextPath}/static/js/modules/user.js" type="text/javascript" charset="UTF-8"></script>

