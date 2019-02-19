<%--
  Created by IntelliJ IDEA.
  User: vothanhtai
  Date: 1/24/19
  Time: 22:35
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="searchUrl" value="/admin/synthesis/search"></c:url>
<html>
<head>
    <title><fmt:message key="label.synthesis.page.title" bundle="${lang}"/></title>
    <content tag="specific_css">
        <link rel="stylesheet"
              href="<c:url value='/template/admin/vendor/jquery-datatables-bs3/assets/css/datatables.css'/>"/>
    </content>
</head>
<body>
<section role="main" class="content-body">
    <header class="page-header">
        <h2><fmt:message key="label.synthesis.page.title" bundle="${lang}"/></h2>
    </header>

    <!-- start: page -->
    <section class="panel">
        <header class="panel-heading">
            <h2 class="panel-title"><fmt:message key="label.synthesis.list" bundle="${lang}"/></h2>
        </header>
        <div class="panel-body">
            <div class="row">
                <div class="col-sm-6">
                    <div class="mb-md">
                        <a href="/admin/record/info/" id="addToTable" class="btn btn-primary">
                            Thêm mới
                            <i class="fa fa-plus"></i></a>
                    </div>
                </div>
                <div class="col-sm-6">
                    <form action="${searchUrl}" method="get">
                        <div class="pull-right col-sm-8 " style="padding: 0;">
                            <input type="text" name="staff-code" value="${param.staff-code}" class="form-control"
                                   placeholder="Search">
                        </div>
                    </form>
                </div>
            </div>
            <table class="table table-bordered table-striped mb-none" id="datatable-default">
                <thead>
                <tr>
                    <th><fmt:message key="label.numerical.order" bundle="${lang}"/></th>
                    <th><fmt:message key="label.staff.code" bundle="${lang}"/></th>
                    <th><fmt:message key="label.synthesis.total.reward" bundle="${lang}"/></th>
                    <th><fmt:message key="label.synthesis.total.punishment" bundle="${lang}"/></th>
                    <th><fmt:message key="label.synthesis.total.result" bundle="${lang}"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="synthesisDto" items="${command.listResult}" varStatus="loop">
                    <tr>
                        <td>${loop.index + 1}</td>
                        <td>${synthesisDto.staffCode}</td>
                        <td>${synthesisDto.totalReward}</td>
                        <td>${synthesisDto.totalPunishment}</td>
                        <td>${synthesisDto.totalResult}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </section>
    <!-- end: page -->
</section>

<content tag="specific_script">
    <script src="<c:url value='/template/admin/vendor/jquery-datatables/jquery.dataTables.min.js'/>"></script>
    <script src="<c:url value='/template/admin/vendor/jquery-datatables-bs3/assets/js/datatables.js'/>"></script>
</content>

<content tag="local_script">
    <script src="<c:url value='/template/admin/javascripts/tables/staff.datatables.default.js'/>"></script>
</content>
</body>
</html>
