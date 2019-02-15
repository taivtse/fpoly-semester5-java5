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
                            <span>Dashboard</span>
                        </a>
                    </li>
                    <li ${fn:startsWith(pageContext.request.requestURI, '/admin/depart') ? 'class="nav-expanded nav-active"':''}>
                        <a href="<c:url value='/admin/depart'/>">
                            <i class="fa fa-building" aria-hidden="true"></i>
                            <span><fmt:message key="label.sidebar.depart" bundle="${lang}"/></span>
                        </a>
                    </li>
                    <li ${fn:startsWith(pageContext.request.requestURI, '/admin/staff') ? 'class="nav-expanded nav-active"':''}>
                        <a href="<c:url value='/admin/staff'/>">
                            <i class="fa fa-users" aria-hidden="true"></i>
                            <span><fmt:message key="label.sidebar.staff" bundle="${lang}"/></span>
                        </a>
                    </li>
                    <li ${fn:startsWith(pageContext.request.requestURI, '/admin/record') ? 'class="nav-expanded nav-active"':''}>
                        <a href="<c:url value='/admin/record'/>">
                            <i class="fa fa-edit" aria-hidden="true"></i>
                            <span><fmt:message key="label.sidebar.record" bundle="${lang}"/></span>
                        </a>
                    </li>
                </ul>
            </nav>

        </div>

    </div>

</aside>
<!-- end: sidebar -->