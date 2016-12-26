<#-- @ftlvariable name="goodsTypes" type="team.wuxie.crowdfunding.domain.TGoodsType[]" -->
<#-- @ftlvariable name="goods" type="team.wuxie.crowdfunding.domain.TGoods" -->
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
  <h4 class="modal-title" id="myModalLabel" style="text-align: center">
  <@spring.message "page.edit"/>
  </h4>
</div>
<div class="modal-body">
  <form role="form" class="form-horizontal" id="form_edit_goods"
        action="${requestContext.contextPath}/goods/${goods.goodsId}" method="post" enctype="multipart/form-data">

    <div class="form-group">
      <label for="txt_goods_name" class="col-sm-2 control-label"><@spring.message "page.goods_name"/> </label>

      <div class="col-sm-10">
        <input class="form-control col-sm-22" id="txt_goods_name" name="goodsName" value="${goods.goodsName}"
               placeholder="<@spring.message "page.goods_name"/>" required data-fv-message="<@spring.message 'goods.v.goodsName_required'/>">
      </div>
    </div>

    <div class="form-group">
      <label class="control-label col-md-2 col-sm-2 col-xs-12"
             for="cmb_type_id"><@spring.message "page.type_name"/> <span
          class="required">*</span>
      </label>

      <div class="col-md-10 col-sm-10 col-xs-12">
        <select id="cmb_type_id" name="typeId" class="form-control col-md-7 col-xs-12"
                required data-fv-message="<@spring.message 'goods.v.typeId_required'/>">
          <option value="">--请选择--</option>
        <#list goodsTypes as goodsType>
            <option value="${goodsType.typeId}" <#if goodsType.typeId == goods.typeId>selected</#if>>${goodsType.typeName}</option>
        </#list>
        </select>
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
    var $form_editGoods = $('#form_edit_goods');

    $form_editGoods.formValidation({
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
      $($form_editGoods).data('formValidation').disableSubmitButtons(false);
    });
  })(jQuery)
</script>