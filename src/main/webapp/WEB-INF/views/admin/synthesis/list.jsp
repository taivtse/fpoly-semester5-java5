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
    <title><spring:message code="label.synthesis.page.title"/></title>
    <content tag="specific_css">
        <link rel="stylesheet"
              href="<c:url value='/template/admin/vendor/jquery-datatables-bs3/assets/css/datatables.css'/>"/>
    </content>
</head>
<body>
<section role="main" class="content-body">
    <header class="page-header">
        <h2><spring:message code="label.synthesis.page.title"/></h2>
    </header>

    <!-- start: page -->
    <section class="panel">
        <header class="panel-heading">
            <h2 class="panel-title"><spring:message code="label.synthesis.list"/></h2>
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
                            <input type="text" name="staff-code"
                                   value="${empty param['staff-code'] ? "" : param['staff-code']}" class="form-control"
                                   placeholder="Search">
                        </div>
                    </form>
                </div>
            </div>
            <table class="table table-bordered table-striped mb-none" id="datatable-default">
                <thead>
                <tr>
                    <th><spring:message code="label.numerical.order"/></th>
                    <th><spring:message code="label.staff.code"/></th>
                    <th><spring:message code="label.staff.name"/></th>
                    <th><spring:message code="label.synthesis.total.reward"/></th>
                    <th><spring:message code="label.synthesis.total.punishment"/></th>
                    <th><spring:message code="label.synthesis.total.result"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="staffSynthesisDto" items="${command.staffSynthesisDtoList}" varStatus="loop">
                    <tr>
                        <td>${loop.index + 1}</td>
                        <td>${staffSynthesisDto.staffCode}</td>
                        <td>${staffSynthesisDto.staffName}</td>
                        <td>${staffSynthesisDto.totalReward}</td>
                        <td>${staffSynthesisDto.totalPunishment}</td>
                        <td>${staffSynthesisDto.totalResult}</td>
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
