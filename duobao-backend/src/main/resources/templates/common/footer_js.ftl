<#-- @ftlvariable name="requestContext" type="javax.servlet.http.HttpServletRequest" -->
<script type="text/javascript" charset="UTF-8">
    var contextPath = '${requestContext.contextPath}';
    var token = '${_csrf.token!('')}';
    var header = '${_csrf.headerName!('')}';
    var table;
</script>
<script src="${requestContext.contextPath}/static/js/jquery.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/bootstrap.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/jquery-form.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/custom.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/jquery.dataTables.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/dataTables.bootstrap.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/dataTables.buttons.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/dataTables.select.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/bootstrap-datetimepicker.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/buttons.bootstrap.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/dataTables.responsive.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/responsive.bootstrap.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/pnotify.custom.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/formValidation.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/formValidation_bootstrap.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/formValidation_locale_zh_CN.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/fileinput.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/fileinput_locale_zh.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/layer.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/main.js" type="text/javascript" charset="UTF-8"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="${requestContext.contextPath}/static/js/ie10-viewport-bug-workaround.js" type="text/javascript" charset="UTF-8"></script>