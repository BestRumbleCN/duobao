<#import '../common/layout.ftl' as layout>

<@layout.main pageTitle=springMacroRequestContext.getMessage("pageTitle.statistics")>

<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
        <div class="x_title">
            <h2><@spring.message "pageTitle.statistics"/></h2>
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                        <i class="fa fa-wrench"></i></a>
                </li>
            </ul>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <div id="users-statistics" style="height:350px;"></div>
        </div>
    </div>
</div>

</@layout.main>
<script src="${requestContext.contextPath}/static/js/echarts.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${requestContext.contextPath}/static/js/statistics.js" type="text/javascript" charset="UTF-8"></script>