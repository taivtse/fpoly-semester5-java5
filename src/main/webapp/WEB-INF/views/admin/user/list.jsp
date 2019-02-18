<%--
  Created by IntelliJ IDEA.
  User: vothanhtai
  Date: 1/24/19
  Time: 22:35
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="searchUrl" value="/admin/user/search"></c:url>
<c:url var="infoUrl" value="/admin/user/info/"></c:url>
<html>
<head>
    <title><fmt:message key="label.user.page.title" bundle="${lang}"/></title>
    <content tag="specific_css">
        <link rel="stylesheet"
              href="<c:url value='/template/admin/vendor/jquery-datatables-bs3/assets/css/datatables.css'/>"/>
    </content>
</head>
<body>
<section role="main" class="content-body">
    <header class="page-header">
        <h2><fmt:message key="label.user.page.title" bundle="${lang}"/></h2>
    </header>

    <!-- start: page -->
    <section class="panel">
        <header class="panel-heading">
            <h2 class="panel-title"><fmt:message key="label.user.list" bundle="${lang}"/></h2>
        </header>
        <div class="panel-body">
            <div class="row">
                <div class="col-sm-6">
                    <div class="mb-md">
                        <a href="${infoUrl}" id="addToTable" class="btn btn-primary">
                            <fmt:message key="label.insert" bundle="${lang}"/>
                            <i class="fa fa-plus"></i></a>
                    </div>
                </div>
                <div class="col-sm-6">
                    <form action="${searchUrl}" method="get">
                        <div class="pull-right col-sm-8" style="padding: 0;">
                            <input type="text" name="name" value="${param.name}" class="form-control" placeholder="Search">
                        </div>
                    </form>
                </div>
            </div>
            <table class="table table-bordered table-striped mb-none" id="datatable-default">
                <thead>
                <tr>
                    <th><fmt:message key="label.numerical.order" bundle="${lang}"/></th>
                    <th><fmt:message key="label.user.username" bundle="${lang}"/></th>
                    <th><fmt:message key="label.user.fullName" bundle="${lang}"/></th>
                    <th><fmt:message key="label.action" bundle="${lang}"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="userDto" items="${command.listResult}" varStatus="loop">
                    <tr data-id="${userDto.id}" data-username="${userDto.username}">
                        <td>${loop.index + 1}</td>
                        <td>${userDto.username}</td>
                        <td>${userDto.fullName}</td>
                        <td class="actions">
                            <a href="<c:url value='${infoUrl}${userDto.username}'/>" class="on-default edit-row"><i
                                    class="fa fa-pencil"></i></a>
                            <a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
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
                <h2 class="panel-title">
                    <fmt:message key="label.delete.ask.title" bundle="${lang}"></fmt:message>
                </h2>
            </header>
            <div class="panel-body">
                <div class="modal-wrapper">
                    <div class="modal-text">
                        <p>
                            <fmt:message key="label.delete.ask.text" bundle="${lang}"></fmt:message>
                        </p>
                    </div>
                </div>
            </div>
            <footer class="panel-footer">
                <div class="row">
                    <div class="col-md-12 text-right">
                        <button id="dialogConfirm" class="btn btn-primary">
                            <fmt:message key="label.confirm" bundle="${lang}"></fmt:message>
                        </button>
                        <button id="dialogCancel" class="btn btn-default">
                            <fmt:message key="label.cancel" bundle="${lang}"></fmt:message>
                        </button>
                    </div>
                </div>
            </footer>
        </section>
    </div>
</content>

<content tag="specific_script">
    <script src="<c:url value='/template/admin/vendor/jquery-datatables/jquery.dataTables.min.js'/>"></script>
    <script src="<c:url value='/template/admin/vendor/jquery-datatables-bs3/assets/js/datatables.js'/>"></script>
</content>

<content tag="local_script">
    <script src="<c:url value='/template/admin/javascripts/tables/staff.datatables.default.js'/>"></script>
    <script type="application/javascript">
        $(document).ready(function () {
            <c:if test="${not empty command.pNotifyDto}">
            new PNotify({
                title: '${command.pNotifyDto.title}',
                text: '${command.pNotifyDto.text}  ',
                type: '${command.pNotifyDto.type}'
            });
            </c:if>

            addEventDeleteButton();
        });

        function addEventDeleteButton() {
            $('.remove-row').on('click', function (e) {
                e.preventDefault();

                var $row = $(this).closest('tr');

                $.magnificPopup.open({
                    items: {
                        src: '#dialog',
                        type: 'inline'
                    },
                    preloader: false,
                    modal: true,
                    callbacks: {
                        open: function () {
                            $('#dialogConfirm').on('click', function (e) {
                                e.preventDefault();
                                $.magnificPopup.close();

                                deleteStaffViaAjax($row);
                            });

                            $('#dialogCancel').on('click', function (e) {
                                $.magnificPopup.close();
                            });

                        },
                        close: function () {
                            $('#dialogConfirm').off('click');
                            $('#dialogCancel').off('click');
                        }
                    }
                });
            })
        }

        function deleteStaffViaAjax($row) {
            $.ajax({
                type: "DELETE",
                url: "/admin/user/{0}".format($row.data("id")),
                success: function (msg) {
                    var alertType = '<fmt:message key="label.response.success" bundle="${lang}"></fmt:message>';
                    var alertTitle = '<fmt:message key="label.delete.success" bundle="${lang}"></fmt:message>';
                    var alertText = '<fmt:message key="label.user.delete.success" bundle="${lang}"></fmt:message>'.format($row.data("username"), $row.find("td").eq(2).text());

                    if (msg === '<fmt:message key="label.response.success" bundle="${lang}"></fmt:message>') {
                        $('#datatable-default').DataTable().row($row).remove().draw();

                        var index = 1;
                        var trList = $("#datatable-default").find('tbody').find('tr');
                        if (trList.eq(0).find("td").length > 1) {
                            trList.each(function () {
                                $(this).find("td").eq(0).text(index);
                                index++;
                            });
                        }
                    } else {
                        alertType = '<fmt:message key="label.response.error" bundle="${lang}"></fmt:message>';
                        alertTitle = '<fmt:message key="label.delete.error" bundle="${lang}"></fmt:message>';

                        switch (msg) {
                            case '<fmt:message key="label.response.primary_key" bundle="${lang}"></fmt:message>':
                                alertText = '<fmt:message key="label.user.error.primary_key" bundle="${lang}"></fmt:message>'.format($row.data("id"));
                                break;
                            default:
                                alertText = '<fmt:message key="label.error" bundle="${lang}"></fmt:message>';
                        }
                    }

                    new PNotify({
                        title: alertTitle,
                        text: alertText,
                        type: alertType
                    });
                },
                error: function (error) {
                    console.log("ERROR: ", error);
                }
            });
        }
    </script>
</content>
</body>
</html>
