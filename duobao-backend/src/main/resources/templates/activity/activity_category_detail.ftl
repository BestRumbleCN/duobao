<#-- @ftlvariable name="activityCategory" type="team.wuxie.crowdfunding.domain.TActivityCategory" -->
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
  <h4 class="modal-title" id="myModalLabel" style="text-align: center">
  <@spring.message "page.add"/>
  </h4>
</div>
<div class="modal-body">
  <form id="form_edit_activity_category" class="form-horizontal"
        action="${requestContext.contextPath}/activityCategories/${activityCategory.categoryId}" method="post" enctype="multipart/form-data">
    <div class="form-group">
      <label class="control-label col-md-2 col-sm-2 col-xs-12"
             for="txt_name">分类名称 <span
          class="required">*</span>
      </label>

      <div class="col-md-10 col-sm-10 col-xs-12">
        <input type="text" id="txt_name" name="name" class="form-control col-md-7 col-xs-12" value="${activityCategory.name}"
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
      <button type="submit" class="btn btn-primary submit-btn"><@spring.message "page.save"/></button>
    </div>
  </form>
</div>

<script>
  (function ($) {
    var $form_editActivityCategory = $('#form_edit_activity_category');

    $form_editActivityCategory.formValidation({
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
      $($form_editActivityCategory).data('formValidation').disableSubmitButtons(false);
    });
  })(jQuery)
</script>