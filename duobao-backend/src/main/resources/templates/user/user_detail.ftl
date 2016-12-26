<#-- @ftlvariable name="user" type="team.wuxie.crowdfunding.domain.TUser" -->
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
  <h4 class="modal-title" id="myModalLabel" style="text-align: center">
  <@spring.message "page.edit"/>
  </h4>
</div>
<div class="modal-body">
  <form role="form" class="form-horizontal" id="form_edit_user"
        action="${requestContext.contextPath}/users/${user.userId}" method="post">

    <div class="form-group">
      <label for="txt_nickname" class="col-sm-2 control-label">昵称</label>

      <div class="col-sm-10">
        <input class="form-control col-sm-22" id="txt_nickname" name="nickname" value="${user.nickname}" placeholder="<@spring.message "page.username"/>">
      </div>
    </div>

    <div class="modal-footer">
      <button type="button" class="btn btn-default"
              data-dismiss="modal"><@spring.message "page.close"/></button>
      <button type="submit" class="btn btn-primary submit-btn"><@spring.message "page.save"/></button>
    </div>
  </form>
</div>

<script>
  (function ($) {
    var $form_editUser = $('#form_edit_user');

    $form_editUser.formValidation({
      framework: 'bootstrap',
      excluded: [':disabled', ':hidden', ':not(:visible)'],
      icon: false,
      autoFocus: true,
      live: 'enabled',
      message: '请填写必填项目'
    }).on('success.form.fv').on('success.form.fv', function (e) {
      //防止表单重复提交
      e.preventDefault();
      var $form = $(e.target);
      ajaxSubmit(e, $form, null, null, table);
      $($form_editUser).data('formValidation').disableSubmitButtons(false);
    });
  })(jQuery)
</script>