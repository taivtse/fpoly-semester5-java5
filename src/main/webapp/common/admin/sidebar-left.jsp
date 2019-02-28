<!-- start: sidebar -->
<aside id="sidebar-left" class="sidebar-left">

    <div class="sidebar-header">
        <div class="sidebar-title" style="color: #FFF">
            Navigation
        </div>
        <div class="sidebar-toggle hidden-xs" data-toggle-class="sidebar-left-collapsed" data-target="html"
             data-fire-event="sidebar-left-toggle">
            <i class="fa fa-bars" aria-label="Toggle sidebar"></i>
        </div>
    </div>

    <div class="nano">
        <div class="nano-content">
            <nav id="menu" class="nav-main" role="navigation">
                <ul class="nav nav-main">
                    <li ${pageContext.request.requestURI eq '/admin' ? 'class="nav-expanded nav-active"':''}>
                        <a href="/">
                            <i class="fa fa-home" aria-hidden="true"></i>
                            <span><spring:message code="label.board"/></span>
                        </a>
                    </li>
                    <li ${fn:startsWith(pageContext.request.requestURI, '/admin/depart') ? 'class="nav-expanded nav-active"':''}>
                        <a href="<c:url value='/admin/depart'/>">
                            <i class="fa fa-building" aria-hidden="true"></i>
                            <span><spring:message code="label.depart.page.title"/></span>
                        </a>
                    </li>
                    <li ${fn:startsWith(pageContext.request.requestURI, '/admin/staff') ? 'class="nav-expanded nav-active"':''}>
                        <a href="<c:url value='/admin/staff'/>">
                            <i class="fa fa-briefcase" aria-hidden="true"></i>
                            <span><spring:message code="label.staff.page.title"/></span>
                        </a>
                    </li>
                    <li ${fn:startsWith(pageContext.request.requestURI, '/admin/user') ? 'class="nav-expanded nav-active"':''}>
                        <a href="<c:url value='/admin/user'/>">
                            <i class="fa fa-users" aria-hidden="true"></i>
                            <span><spring:message code="label.user.page.title"/></span>
                        </a>
                    </li>
                    <li ${fn:startsWith(pageContext.request.requestURI, '/admin/record') ? 'class="nav-expanded nav-active"':''}>
                        <a href="<c:url value='/admin/record'/>">
                            <i class="fa fa-edit" aria-hidden="true"></i>
                            <span><spring:message code="label.record.page.title"/></span>
                        </a>
                    </li>
                    <li ${fn:startsWith(pageContext.request.requestURI, '/admin/synthesis') ? 'class="nav-expanded nav-active"':''}>
                        <a href="<c:url value='/admin/synthesis/'/>">
                            <i class="fa fa-bar-chart-o" aria-hidden="true"></i>
                            <span><spring:message code="label.synthesis.page.title"/></span>
                        </a>
                    </li>
                </ul>
            </nav>

        </div>

    </div>

</aside>
<!-- end: sidebar -->