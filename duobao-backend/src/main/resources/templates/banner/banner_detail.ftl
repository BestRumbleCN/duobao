<#-- @ftlvariable name="bannerTypeMap" type="java.util.Map<String, String>" -->
<#-- @ftlvariable name="banner" type="team.wuxie.crowdfunding.domain.TBanner" -->
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
  <h4 class="modal-title" id="myModalLabel" style="text-align: center">
    编辑Banner
  </h4>
</div>
<div class="modal-body">
  <form id="form_edit_banner" class="form-horizontal"
        action="${requestContext.contextPath}/banners/${banner.bannerId}" method="post" enctype="multipart/form-data">
    <div class="form-group">
      <label class="col-sm-2 control-label" for="txt_banner_type">类型</label>
      <div class="col-sm-10">
        <select id="txt_banner_type" name="bannerType" class="form-control"
                data-fv-message="<@spring.message 'banner.v.bannerType_required'/>" required>
          <option value="">--请选择--</option>
        <#list bannerTypeMap?keys as key>
          <#if key != 'DEFAULT'>
            <option value="${key}" <#if banner.bannerType?string == key>selected</#if>>${bannerTypeMap[key]}</option>
          </#if>
        </#list>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label" for="txt_file">图片</label>
      <div class="col-sm-10">
        <input type="file" id="txt_file" name="file" value="" class="form-control">
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label" for="txt_content">内容</label>
      <div class="col-sm-10">
        <textarea id="txt_content" name="content" class="form-control" placeholder="内容">${banner.content}</textarea>
      </div>
    </div>
    <div class="modal-footer">
      <button type="button" class="btn btn-default"
              data-dismiss="modal"><@spring.message 'page.close'/></button>
      <button type="submit" class="btn btn-primary"><@spring.message 'page.save'/></button>
    </div>
  </form>
</div>

<script>
  (function ($) {
    var $form_editBanner = $('#form_edit_banner');

    $form_editBanner.formValidation({
      framework: 'bootstrap',
      excluded: [':disabled', ':hidden', ':not(:visible)'],
      icon: false,
      autoFocus: true,
      live: 'enabled',
      message: '请填写必填项目',
      fields: {
        file: {
          validators: {
            file: {
              extension: 'jpg,jpeg,png',
              message: '支持图片格式jpg、jpeg、png'
            }
          }
        }
      }
    }).on('success.form.fv').on('success.form.fv', function (e) {
      //防止表单重复提交
      e.preventDefault();
      var $form = $(e.target);
      ajaxSubmit(e, $form, null, null, table);
      $($form_editBanner).data('formValidation').disableSubmitButtons(false);
    });
  })(jQuery)
</script>