<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<!-- top navigation -->
<div class="top_nav">
  <div class="nav_menu">
    <nav>
      <div class="nav toggle">
        <a id="menu_toggle"><i class="fa fa-bars"></i></a>
      </div>

      <ul class="nav navbar-nav navbar-right">
        <li class="">
          <a href="javascript:" class="user-profile dropdown-toggle" data-toggle="dropdown"
             aria-expanded="false">
          <#if currentUser??>
            ${currentUser.role!('')}
          </#if>
            <span class=" fa fa-angle-down"></span>
          </a>
          <ul class="dropdown-menu dropdown-usermenu pull-right">
            <li class="logout"><a href="#"><i
                class="fa fa-sign-out pull-right"></i> <@spring.message "page.logout"/> </a>
              <form id="logout-form" action="${requestContext.contextPath}/logout" method="post">
                <input type="hidden" name="${_csrf.parameterName!('')}" value="${_csrf.token!('')}"/>
              </form>
            </li>
          </ul>
        </li>
      </ul>
    </nav>
  </div>
</div>
<!-- /top navigation -->