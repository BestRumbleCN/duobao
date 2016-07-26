
<#import 'common/layout.ftl' as layout>

<@layout.main pageTitle=springMacroRequestContext.getMessage("pageTitle.home")>

<div class="col-md-12 col-sm-12 col-xs-12">
    <@spring.message "page.welcome"/>
</div>

</@layout.main>

