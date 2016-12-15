<#macro main pageTitle>
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
    <title>
		<#if pageTitle??>
			<@spring.message "project.name"/> - ${pageTitle}
		<#else>
			<@spring.message "project.name"/>
		</#if>
    </title>
	<#include "header_js_css.ftl">
</head>
<#--nav-md：侧边导航展开、nav-sm：侧边到户收起-->
<body class="nav-md">

<div class="container body">
    <div class="main_container">
        <div class="col-md-3 left_col">
            <div class="left_col scroll-view">
                <div class="navbar nav_title" style="border: 0;">
                    <a href="${requestContext.contextPath}/index" class="site_title"><i class="fa fa-paw"></i> <span> <@spring.message "project.name"/> </span></a>
                </div>

                <div class="clearfix"></div>

                <!-- menu profile quick info -->
                <#--<div class="profile">-->
                    <#--<div class="profile_pic">-->
                        <#--<img src="${requestContext.contextPath}/static/images/img.jpg" alt="..." class="img-circle profile_img">-->
                    <#--</div>-->
                    <#--<div class="profile_info">-->
                        <#--<span>Welcome,</span>-->
                        <#--<h2>-->
	                        <#--<#if currentUser??>-->
							<#--${currentUser.username!('')}-->
							<#--</#if>-->
                        <#--</h2>-->
                    <#--</div>-->
                <#--</div>-->
                <!-- /menu profile quick info -->

                <br />

                <!-- sidebar menu -->
	            <#include 'sidebar.ftl'>

                <!-- /menu footer buttons -->
                <#--<div class="sidebar-footer hidden-small">-->
                    <#--<a data-toggle="tooltip" data-placement="top" title="Settings">-->
                        <#--<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>-->
                    <#--</a>-->
                    <#--<a data-toggle="tooltip" data-placement="top" title="FullScreen">-->
                        <#--<span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>-->
                    <#--</a>-->
                    <#--<a data-toggle="tooltip" data-placement="top" title="Lock">-->
                        <#--<span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>-->
                    <#--</a>-->
                    <#--<a data-toggle="tooltip" data-placement="top" title="Logout">-->
                        <#--<span class="glyphicon glyphicon-off" aria-hidden="true"></span>-->
                    <#--</a>-->
                <#--</div>-->
                <!-- /menu footer buttons -->
            </div>
        </div>

		<#include 'top_navigation.ftl'>

        <!-- page content -->
        <div class="right_col" role="main" id="mainContent">
	        <#nested/>
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <footer>
            <div class="pull-right">
                Copyright by Duobao
            </div>
            <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
    </div>
</div>

	<#include "footer_js.ftl">
</#macro>
</body>
</html>
