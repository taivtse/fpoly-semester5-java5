<%--
  Created by IntelliJ IDEA.
  User: vothanhtai
  Date: 1/24/19
  Time: 22:35
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title><fmt:message key="label.staff.page.title" bundle="${lang}"/></title>
    <content tag="specific_css">
        <link rel="stylesheet"
              href="<c:url value='/template/admin/vendor/jquery-datatables-bs3/assets/css/datatables.css'/>"/>
    </content>
</head>
<body>
<section role="main" class="content-body">
    <header class="page-header">
        <h2><fmt:message key="label.staff.page.title" bundle="${lang}"/></h2>
    </header>

    <!-- start: page -->
    <section class="panel">
        <header class="panel-heading">
            <h2 class="panel-title"><fmt:message key="label.staff.list" bundle="${lang}"/></h2>
        </header>
        <div class="panel-body">
            <div class="row">
                <div class="col-sm-6">
                    <div class="mb-md">
                        <button id="addToTable" class="btn btn-primary">
                            <fmt:message key="label.add" bundle="${lang}"/>
                            <i class="fa fa-plus"></i></button>
                    </div>
                </div>
            </div>
            <table class="table table-bordered table-striped mb-none" id="datatable-default">
                <thead>
                <tr>
                    <th><fmt:message key="label.staff.code" bundle="${lang}"/></th>
                    <th><fmt:message key="label.staff.name" bundle="${lang}"/></th>
                    <th><fmt:message key="label.staff.email" bundle="${lang}"/></th>
                    <th><fmt:message key="label.staff.level" bundle="${lang}"/></th>
                    <th><fmt:message key="label.depart" bundle="${lang}"/></th>
                    <th><fmt:message key="label.action" bundle="${lang}"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="staffDto" items="${staffDtoList}" varStatus="loop">
                    <tr>
                        <td>${staffDto.code}</td>
                        <td>${staffDto.name}</td>
                        <td>${staffDto.email}</td>
                        <td>${staffDto.level}</td>
                        <td>${staffDto.departDto.name}</td>
                        <td class="actions">
                            <a href="info/${staffDto.code}" class="on-default edit-row"><i
                                    class="fa fa-pencil"></i></a>
                            <a href="info/${staffDto.code}" class="on-default remove-row"><i
                                    class="fa fa-trash-o"></i></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </section>
    <!-- end: page -->
</section>

<content tag="specific_html">
    <div id="dialog" class="modal-block mfp-hide">
        <section class="panel">
            <header class="panel-heading">
                <h2 class="panel-title">Are you sure?</h2>
            </header>
            <div class="panel-body">
                <div class="modal-wrapper">
                    <div class="modal-text">
                        <p>Are you sure that you want to delete this row?</p>
                    </div>
                </div>
            </div>
            <footer class="panel-footer">
                <div class="row">
                    <div class="col-md-12 text-right">
                        <button id="dialogConfirm" class="btn btn-primary">Confirm</button>
                        <button id="dialogCancel" class="btn btn-default">Cancel</button>
                    </div>
                </div>
            </footer>
        </section>
    </div>
</content>

<content tag="specific_script">
    <script src="<c:url value='/template/admin/vendor/jquery-datatables/jquery.dataTables.min.js'/>"></script>
    <script src="<c:url value='/template/admin/vendor/jquery-datatables-bs3/assets/js/datatables.js'/>"></script>
    <script src="<c:url value='/template/admin/vendor/pnotify/pnotify.custom.js'/>"></script>
</content>

<content tag="local_script">
    <script src="<c:url value='/template/admin/javascripts/tables/staff.datatables.default.js'/>"></script>
</content>
</body>
</html>
