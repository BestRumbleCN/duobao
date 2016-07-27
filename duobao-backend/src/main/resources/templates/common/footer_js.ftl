<#-- @ftlvariable name="requestContext" type="javax.servlet.http.HttpServletRequest" -->
<script type="text/javascript" charset="UTF-8">
    var contextPath = '${requestContext.contextPath}';
    var token = '${_csrf.token!('')}';
    var header = '${_csrf.headerName!('')}';
</script>
<script src="${requestContext.contextPath}/static/js/jquery.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/bootstrap.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/custom.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/jquery.dataTables.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/dataTables.bootstrap.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/dataTables.buttons.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/buttons.bootstrap.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/dataTables.responsive.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/responsive.bootstrap.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/validator.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/main.js" type="text/javascript" charset="UTF-8"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="${requestContext.contextPath}/static/js/ie10-viewport-bug-workaround.js" type="text/javascript" charset="UTF-8"></script>