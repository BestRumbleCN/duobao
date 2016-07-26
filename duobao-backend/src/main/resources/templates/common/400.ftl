<#import 'layout.ftl' as layout>
<@layout.main pageTitle=springMacroRequestContext.getMessage("pageTitle.error")>
<div class="row">
    <div class="col-lg-12">
        <h1><@spring.message "page.error_code"/>：400，Bad Request</h1>
	${errMsg!('')}
    </div>
</div>
</@layout.main>