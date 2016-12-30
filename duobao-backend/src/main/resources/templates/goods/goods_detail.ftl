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
      <label class="control-label col-md-2 col-sm-2 col-xs-12"
             for="txt_goods_name"><@spring.message "page.goods_name"/> <span
          class="required">*</span>
      </label>

      <div class="col-md-10 col-sm-10 col-xs-12">
        <input type="text" id="txt_goods_name" name="goodsName" class="form-control col-md-7 col-xs-12" value="${goods.goodsName}"
               placeholder="<@spring.message "page.goods_name"/>" required data-fv-message="<@spring.message 'goods.v.goodsName_required'/>">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-md-2 col-sm-2 col-xs-12"
             for="singlePrice"><@spring.message "page.goods_single_price"/> <span
          class="required">*</span>
      </label>

      <div class="col-md-10 col-sm-10 col-xs-12">
        <input type="number" id="singlePrice" name="singlePrice" class="form-control col-md-7 col-xs-12" value="${goods.singlePrice}"
               placeholder="<@spring.message "page.goods_single_price"/>" required>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-md-2 col-sm-2 col-xs-12"
             for="totalAmount"><@spring.message "page.goods_total_amount"/> <span
          class="required">*</span>
      </label>

      <div class="col-md-10 col-sm-10 col-xs-12">
        <input type="number" id="totalAmount" name="totalAmount" class="form-control col-md-7 col-xs-12" value="${goods.totalAmount}"
               placeholder="<@spring.message "page.goods_total_amount"/>" required>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-md-2 col-sm-2 col-xs-12"
             for="channel"><@spring.message "page.goods_channel"/> <span
          class="required">*</span>
      </label>

      <div class="col-md-10 col-sm-10 col-xs-12">
        <select id="channel" name="channel" class="form-control col-md-7 col-xs-12" required>
          <option value="0" <#if goods.channel == 0>selected</#if>>普通</option>
          <option value="1" <#if goods.channel == 1>selected</#if>>爆款</option>
          <option value="2" <#if goods.channel == 2>selected</#if>>新货</option>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-md-2 col-sm-2 col-xs-12"
             for="typeId"><@spring.message "page.type_name"/> <span
          class="required">*</span>
      </label>

      <div class="col-md-10 col-sm-10 col-xs-12">
        <select id="typeId" name="typeId" class="form-control col-md-7 col-xs-12" required
                data-fv-message="<@spring.message 'goods.v.typeId_required'/>">
          <option value="">--请选择--</option>
        <#list goodsTypes as goodsType>
            <option value="${goodsType.typeId}" <#if goods.typeId == goodsType.typeId>selected</#if>>${goodsType.typeName}</option>
        </#list>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-md-2 col-sm-2 col-xs-12"
             for="file_img"><@spring.message "page.goods_img"/> </label>

      <div class="col-md-10 col-sm-10 col-xs-12">
        <input type="file" multiple id="file_img" name="imgFiles" class="form-control col-md-7 col-xs-12">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-md-2 col-sm-2 col-xs-12"
             for="file_img_detail">图文详情
      </label>

      <div class="col-md-10 col-sm-10 col-xs-12">
        <input type="file" id="file_img_detail" name="imgDetailFile" class="form-control col-md-7 col-xs-12">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-md-2 col-sm-2 col-xs-12"
             for="statement"><@spring.message "page.goods_statement"/> <span
          class="required">*</span>
      </label>

      <div class="col-md-10 col-sm-10 col-xs-12">
               <textarea rows="5" id="statement" name="statement" class="form-control col-md-7 col-xs-12"
                         placeholder="<@spring.message "page.goods_statement"/>" required
                         data-fv-message="<@spring.message 'goods.v.statement_required'/>">${goods.statement}</textarea>
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