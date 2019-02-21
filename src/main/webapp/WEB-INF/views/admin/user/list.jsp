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
    <title><spring:message code="label.user.page.title"/></title>
    <content tag="specific_css">
        <link rel="stylesheet"
              href="<c:url value='/template/admin/vendor/jquery-datatables-bs3/assets/css/datatables.css'/>"/>
    </content>
</head>
<body>
<section role="main" class="content-body">
    <header class="page-header">
        <h2><spring:message code="label.user.page.title"/></h2>
    </header>

    <!-- start: page -->
    <section class="panel">
        <header class="panel-heading">
            <h2 class="panel-title"><spring:message code="label.user.list"/></h2>
        </header>
        <div class="panel-body">
            <div class="row">
                <div class="col-sm-6">
                    <div class="mb-md">
                        <a href="${infoUrl}" id="addToTable" class="btn btn-primary">
                            <spring:message code="label.insert"/>
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
                    <th><spring:message code="label.numerical.order"/></th>
                    <th><spring:message code="label.user.username"/></th>
                    <th><spring:message code="label.user.fullName"/></th>
                    <th><spring:message code="label.action"/></th>
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
                    <spring:message code="label.delete.ask.title"/>
                </h2>
            </header>
            <div class="panel-body">
                <div class="modal-wrapper">
                    <div class="modal-text">
                        <p>
                            <spring:message code="label.delete.ask.text"/>
                        </p>
                    </div>
                </div>
            </div>
            <footer class="panel-footer">
                <div class="row">
                    <div class="col-md-12 text-right">
                        <button id="dialogConfirm" class="btn btn-primary">
                            <spring:message code="label.confirm"/>
                        </button>
                        <button id="dialogCancel" class="btn btn-default">
                            <spring:message code="label.cancel"/>
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
                    var alertType = '<spring:message code="label.response.success"/>';
                    var alertTitle = '<spring:message code="label.delete.success"/>';
                    var alertText = '<spring:message code="label.user.delete.success"/>'.format($row.data("username"), $row.find("td").eq(2).text());

                    if (msg === '<spring:message code="label.response.success"/>') {
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
                        alertType = '<spring:message code="label.response.error"/>';
                        alertTitle = '<spring:message code="label.delete.error"/>';

                        switch (msg) {
                            case '<spring:message code="label.response.primary_key"/>':
                                alertText = '<spring:message code="label.user.error.primary_key"/>'.format($row.data("id"));
                                break;
                            default:
                                alertText = '<spring:message code="label.error"/>';
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
