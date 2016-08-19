<#import '../common/layout.ftl' as layout>

<@layout.main pageTitle=springMacroRequestContext.getMessage("pageTitle.activity_list")>

<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
        <div class="x_title">
            <h2><@spring.message "pageTitle.activity_list"/></h2>
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
		<#--<table id="dataTable-user" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0"-->
                   <#--width="100%">-->
                <#--<thead>-->
                <#--<tr>-->
                    <#--<th><@spring.message "tableHeader.user_id"/></th>-->
                    <#--<th><@spring.message "tableHeader.username"/></th>-->
                    <#--<th><@spring.message "tableHeader.status"/></th>-->
                    <#--<th><@spring.message "tableHeader.create_time"/></th>-->
                    <#--<th><@spring.message "tableHeader.operation"/></th>-->
                <#--</tr>-->
                <#--</thead>-->
            <#--</table>-->
        </div>
    </div>
</div>

</@layout.main>