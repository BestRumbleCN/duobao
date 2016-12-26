<#-- @ftlvariable name="activity" type="team.wuxie.crowdfunding.domain.TActivity" -->
<#-- @ftlvariable name="activityCategories" type="java.util.List<team.wuxie.crowdfunding.domain.TActivityCategory>" -->
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
  <h4 class="modal-title" id="myModalLabel" style="text-align: center">
  <@spring.message "page.edit"/>
  </h4>
</div>
<div class="modal-body">
  <form id="form_edit_activity" class="form-horizontal"
        action="${requestContext.contextPath}/activities/${activity.activityId}" method="post">
    <div class="form-group">
      <label class="control-label col-md-2 col-sm-2 col-xs-12"
             for="cmb_category_id">活动分类 <span
          class="required">*</span>
      </label>

      <div class="col-md-10 col-sm-10 col-xs-12">
        <select type="text" id="cmb_category_id" name="categoryId" class="form-control col-md-7 col-xs-12"
                required data-fv-message="<@spring.message 'activity.v.category_id_required'/>">
        <#list activityCategories as activityCategory>
          <option value="${activityCategory.categoryId}"
                  <#if activity.categoryId == activityCategory.categoryId>selected</#if>>${activityCategory.name}</option>
        </#list>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-md-2 col-sm-2 col-xs-12"
             for="txt_name">活动名称 <span
          class="required">*</span>
      </label>

      <div class="col-md-10 col-sm-10 col-xs-12">
        <input type="text" id="txt_name" name="name" class="form-control col-md-7 col-xs-12" value="${activity.name}"
               placeholder="活动名称" required data-fv-message="<@spring.message 'activity.v.name_required'/>">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-md-2 col-sm-2 col-xs-12"
             for="txt_content">活动内容 <span
          class="required">*</span>
      </label>

      <div class="col-md-10 col-sm-10 col-xs-12">
        <input type="text" id="txt_content" name="content" class="form-control col-md-7 col-xs-12"
               value="${activity.content!('')}"
               placeholder="活动内容" required data-fv-message="<@spring.message 'activity.v.content_required'/>">
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
    var $form_editActivity = $('#form_edit_activity');

    $form_editActivity.formValidation({
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
      $($form_editActivity).data('formValidation').disableSubmitButtons(false);
    });
  })(jQuery)
</script>