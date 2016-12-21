<#-- @ftlvariable name="goodsType" type="team.wuxie.crowdfunding.domain.TGoodsType" -->
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
  <h4 class="modal-title" id="myModalLabel" style="text-align: center">
  <@spring.message "page.edit"/>
  </h4>
</div>
<div class="modal-body">
  <form role="form" class="form-horizontal submit-form" id="form_update-goodsType"
        action="${requestContext.contextPath}/goodsTypes/${goodsType.typeId}" method="post" enctype="multipart/form-data">

    <div class="form-group">
      <label for="typeName"
             class="col-sm-2 control-label"><@spring.message "page.type_name"/> <span
          class="required">*</span>
      </label>

      <div class="col-sm-10">
        <input class="form-control col-sm-22" id="typeName" name="typeName" required
               placeholder="<@spring.message "page.type_name"/>">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-md-2 col-sm-2 col-xs-12"
             for="typeImg"><@spring.message "page.type_img"/> <span
          class="required">*</span>
      </label>

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