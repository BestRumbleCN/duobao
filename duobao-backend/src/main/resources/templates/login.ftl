<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimal-ui">
    <meta name="renderer" content="webkit">
    <meta name="description" content="">
    <meta name="keyword" content="">
    <meta name="author" content="">
    <title><@spring.message "project.name"/> - <@spring.message "page.login"/></title>
<#include "common/header_js_css.ftl">
</head>
<body class="login">
<div>
    <div class="login_wrapper">
        <h1 class="animate form login_form">
            <section class="login_content">
                <form action="${requestContext.contextPath}/login" method="post">
                    <h1> <@spring.message "project.name"/> </h1>
                    <input type="hidden" name="${_csrf.parameterName!('')}" value="${_csrf.token!('')}"/>

                    <div>
                        <input type="text" class="form-control" placeholder="username" required name="username"/>
                    </div>
                    <div>
                        <input type="password" class="form-control" placeholder="password" required name="password"/>
                    </div>
                    <div>
                        <button class="btn btn-block btn-default" type="submit"><@spring.message "page.login"/></button>
					<#--<a class="reset_pass" id="reset_pass">Lost your password?</a>-->
                    </div>

                </form>
			<#if error??>
                <code class="text-danger">${error!('')}</code>
			</#if>
            </section>
    </div>
</div>
<!-- FOOTER -->
<#include "common/footer_js.ftl"/>
</body>
</html>