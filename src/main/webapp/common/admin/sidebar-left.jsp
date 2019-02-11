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
                    <li>
                        <a href="/">
                            <i class="fa fa-home" aria-hidden="true"></i>
                            <span>Dashboard</span>
                        </a>
                    </li>
                    <li>
                        <a href="/admin/depart/">
                            <i class="fa fa-building" aria-hidden="true"></i>
                            <span><fmt:message key="label.sidebar.depart" bundle="${lang}"/></span>
                        </a>
                    </li>
                    <li>
                        <a href="/admin/staff/">
                            <i class="fa fa-users" aria-hidden="true"></i>
                            <span><fmt:message key="label.sidebar.staff" bundle="${lang}"/></span>
                        </a>
                    </li>
                    <li class="nav-parent nav-expanded nav-active">
                        <a>
                            <i class="fa fa-edit" aria-hidden="true"></i>
                            <span><fmt:message key="label.sidebar.record" bundle="${lang}"/></span>
                        </a>
                        <ul class="nav nav-children">
                            <li class="nav-active">
                                <a href="layouts-default.html">
                                    <fmt:message key="label.sidebar.record.reward" bundle="${lang}"/>
                                </a>
                            </li>
                            <li>
                                <a href="layouts-default.html">
                                    <fmt:message key="label.sidebar.record.punish" bundle="${lang}"/>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </nav>

        </div>

    </div>

</aside>
<!-- end: sidebar -->