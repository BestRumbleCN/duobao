<#-- @ftlvariable name="goodsType" type="team.wuxie.crowdfunding.domain.TGoodsType" -->
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
  <h4 class="modal-title" id="myModalLabel" style="text-align: center">
  <@spring.message "page.edit"/>
  </h4>
</div>
<div class="modal-body">
  <form role="form" class="form-horizontal" id="form_edit_goodsType"
        action="${requestContext.contextPath}/goodsTypes/${goodsType.typeId}" method="post" enctype="multipart/form-data">

    <div class="form-group">
      <label for="typeName"
             class="col-sm-2 control-label"><@spring.message "page.type_name"/> <span
          class="required">*</span>
      </label>

      <div class="col-sm-10">
        <input class="form-control col-sm-22" id="typeName" name="typeName" value="${goodsType.typeName}" required
               placeholder="<@spring.message "page.type_name"/>">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-md-2 col-sm-2 col-xs-12"
             for="typeImg"><@spring.message "page.type_img"/> </label>

      <div class="col-md-10 col-sm-10 col-xs-12">
        <input type="file" id="typeImg" name="file" class="form-control col-md-7 col-xs-12"
               placeholder="<@spring.message "page.type_img"/>">
      </div>
    </div>

    <div class="modal-footer">
      <button type="button" class="btn btn-default"
              data-dismiss="modal"><@spring.message "page.close"/></button>
      <button type="submit" id="update-user-btn"
              class="btn btn-primary submit-btn"><@spring.message "page.save"/></button>
    </div>
  </form>
</div>

<script>
  (function ($) {
    var $form_editGoodsType = $('#form_edit_goodsType');

    $form_editGoodsType.formValidation({
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
      $($form_editGoodsType).data('formValidation').disableSubmitButtons(false);
    });
  })(jQuery)
</script>