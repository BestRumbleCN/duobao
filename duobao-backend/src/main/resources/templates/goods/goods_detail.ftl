<#-- @ftlvariable name="goods" type="team.wuxie.crowdfunding.domain.TGoods" -->
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
  <h4 class="modal-title" id="myModalLabel" style="text-align: center">
  <@spring.message "page.edit"/>
  </h4>
</div>
<div class="modal-body">
  <form role="form" class="form-horizontal submit-form" id="form_update-goods"
        action="${requestContext.contextPath}/goods/${goods.goodsId}" method="post" enctype="multipart/form-data">

    <input hidden id="typeId" name="typeId" value="" title="">

    <div class="form-group">
      <label for="goodsName" class="col-sm-2 control-label"><@spring.message "page.goods_name"/> </label>

      <div class="col-sm-10">
        <input class="form-control col-sm-22" id="goodsName" name="goodsName" value="${goods.goodsName}" required
               placeholder="<@spring.message "page.goods_name"/>">
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