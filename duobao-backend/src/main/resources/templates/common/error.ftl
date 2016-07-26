<#import 'layout.ftl' as layout>
<@layout.main pageTitle=springMacroRequestContext.getMessage("pageTitle.error")>
<div class="row">
    <div class="col-lg-12">
        <h1>${url!('')}</h1>
        <h1>${exception.message!('')}</h1>
	${errMsg!('')}
    </div>
</div>
</@layout.main>