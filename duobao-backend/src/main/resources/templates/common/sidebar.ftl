<!-- sidebar menu -->
<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
  <div class="menu_section">
  <#--<h3>General</h3>-->
    <ul class="nav side-menu">
      <li><a href="${requestContext.contextPath}/index"><i class="fa fa-home"></i> <@spring.message "sidebar.home"/>
      </a>
      </li>
      <li><a href="${requestContext.contextPath}/bidRecords"><i
          class="fa fa-trophy"></i> <@spring.message "sidebar.bid_record"/> </a></li>
      <li><a href="${requestContext.contextPath}/shippingRecords"><i
          class="fa fa-paper-plane"></i> <@spring.message "sidebar.shipping_record"/> </a></li>
      <li><a><i class="fa fa-product-hunt"></i> <@spring.message "sidebar.goods"/> <span
          class="fa fa-chevron-down"></span></a>
        <ul class="nav child_menu">
          <li><a href="${requestContext.contextPath}/goodsTypes"> <@spring.message "sidebar.goods_type_list"/> </a></li>
          <li><a href="${requestContext.contextPath}/goods"> <@spring.message "sidebar.goods_list"/> </a></li>
        </ul>
      </li>
      <li><a><i class="fa fa-bullhorn"></i> <@spring.message "sidebar.activity"/> <span
          class="fa fa-chevron-down"></span></a>
        <ul class="nav child_menu">
          <li><a
              href="${requestContext.contextPath}/activityCategories"> <@spring.message "sidebar.activity_category_list"/> </a>
          </li>
          <li><a href="${requestContext.contextPath}/activities"> <@spring.message "sidebar.activity_list"/> </a></li>
        </ul>
      </li>
      <li><a href="${requestContext.contextPath}/users"><i
          class="fa fa-users"></i> <@spring.message "sidebar.user_list"/> </a></li>
    <#--<li><a><i class="fa fa-users"></i> <@spring.message "sidebar.user"/> <span class="fa fa-chevron-down"></span></a>-->
    <#--<ul class="nav child_menu">-->
    <#--<li><a href="${requestContext.contextPath}/users"> <@spring.message "sidebar.user_list"/> </a></li>-->
    <#--</ul>-->
    <#--</li>-->
      <li><a href="${requestContext.contextPath}/banners"><i class="fa fa-gear"></i> APP首页Banner </a></li>
    <#--<li><a><i class="fa fa-gear"></i> 设置 <span class="fa fa-chevron-down"></span></a>-->
    <#--<ul class="nav child_menu">-->
    <#--<li><a href="${requestContext.contextPath}/banners"> APP首页Banner </a></li>-->
    <#--</ul>-->
    <#--</li>-->
      <li><a href="${requestContext.contextPath}/messages"><i class="fa fa-envelope"></i> 消息列表 </a></li>
      <#--<li><a href="${requestContext.contextPath}/privilege"><i-->
          <#--class="fa fa-shield"></i> <@spring.message "sidebar.privilege"/> </a></li>-->
      <li><a href="${requestContext.contextPath}/statistics"><i
          class="fa fa-bar-chart-o"></i> <@spring.message "sidebar.statistics"/> </a></li>
    </ul>
  </div>
</div>
<!-- /sidebar menu -->