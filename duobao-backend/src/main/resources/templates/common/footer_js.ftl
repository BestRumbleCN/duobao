<#-- @ftlvariable name="requestContext" type="javax.servlet.http.HttpServletRequest" -->
<script type="text/javascript" charset="UTF-8">
    var contextPath = '${requestContext.contextPath}';
    var token = '${_csrf.token!('')}';
    var header = '${_csrf.headerName!('')}';
    var table;
</script>
<script src="${requestContext.contextPath}/static/js/lib/jquery.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/lib/bootstrap.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/lib/jquery-form.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/lib/custom.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/lib/dataTables/jquery.dataTables.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/lib/dataTables/dataTables.bootstrap.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/lib/dataTables/responsive.bootstrap.js" type="text/javascript" charset="UTF-8"></script>
<#--<script src="${requestContext.contextPath}/static/js/lib/dataTables/dataTables.buttons.min.js" type="text/javascript" charset="UTF-8"></script>-->
<#--<script src="${requestContext.contextPath}/static/js/lib/dataTables/dataTables.select.min.js" type="text/javascript" charset="UTF-8"></script>-->
<#--<script src="${requestContext.contextPath}/static/js/lib/dataTables/buttons.bootstrap.min.js" type="text/javascript" charset="UTF-8"></script>-->
<#--<script src="${requestContext.contextPath}/static/js/lib/dataTables/dataTables.responsive.min.js" type="text/javascript" charset="UTF-8"></script>-->
<script src="${requestContext.contextPath}/static/js/lib/dateTimePicker/bootstrap-datetimepicker.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/lib/dateTimePicker/bootstrap-datetimepicker.zh-CN.js" type="text/javascript" charset="UTF-8"></script>
<#--<script src="${requestContext.contextPath}/static/js/lib/pnotify.custom.min.js" type="text/javascript" charset="UTF-8"></script>-->
<script src="${requestContext.contextPath}/static/js/lib/formValidation/formValidation.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/lib/formValidation/formValidation_bootstrap.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/lib/formValidation/formValidation_locale_zh_CN.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/lib/fileInput/fileinput.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/lib/fileInput/fileinput_locale_zh.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/lib/layer.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/modules/main.js" type="text/javascript" charset="UTF-8"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="${requestContext.contextPath}/static/js/lib/ie10-viewport-bug-workaround.js" type="text/javascript" charset="UTF-8"></script>