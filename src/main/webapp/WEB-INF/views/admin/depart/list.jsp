<%--
  Created by IntelliJ IDEA.
  User: vothanhtai
  Date: 1/31/19
  Time: 21:18
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title><spring:message code="label.depart.page.title"/></title>
    <content tag="specific_css">
        <link rel="stylesheet"
              href="<c:url value='/template/admin/vendor/jquery-datatables-bs3/assets/css/datatables.css'/>"/>
    </content>
</head>
<body>
<section role="main" class="content-body">
    <header class="page-header">
        <h2><spring:message code="label.depart.page.title"/></h2>
    </header>

    <!-- start: page -->
    <section class="panel">
        <header class="panel-heading">
            <h2 class="panel-title"><spring:message code="label.depart.list"/></h2>
        </header>
        <div class="panel-body">
            <div class="row">
                <div class="col-sm-6">
                    <div class="mb-md">
                        <button id="addToTable" class="btn btn-primary">
                            <spring:message code="label.insert"/>
                            <i class="fa fa-plus"></i></button>
                    </div>
                </div>
            </div>
            <table class="table table-bordered table-striped mb-none" id="datatable-editable">
                <thead>
                <tr>
                    <th><spring:message code="label.numerical.order"/></th>
                    <th><spring:message code="label.depart.id"/></th>
                    <th><spring:message code="label.depart.name"/></th>
                    <th><spring:message code="label.action"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="departDto" items="${command.listResult}" varStatus="loop">
                    <tr data-id= ${departDto.id}>
                        <td data-editable="false">${loop.index + 1}</td>
                        <td data-editable="false">${departDto.id}</td>
                        <td>${departDto.name}</td>
                        <td class="actions">
                            <a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
                            <a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
                            <a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
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
    <script src="<c:url value='/template/admin/javascripts/tables/depart.datatables.editable.js'/>"></script>
    <script type="application/javascript">
        function addNewDepartViaAjax(_self, $row, values) {
            var departDto = {
                id: values[1],
                name: values[2]
            };

            $.ajax({
                type: "POST",
                url: "/admin/depart/insert",
                data: departDto,
                success: function (msg) {
                    var alertType = '<spring:message code="label.response.success"/>';
                    var alertTitle = '<spring:message code="label.insert.success"/>';
                    var alertText = '<spring:message code="label.depart.insert.success"/>'.format(departDto.id, departDto.name);

                    if (msg === '<spring:message code="label.response.success"/>') {
                        // add row
                        _self.datatable.row($row.get(0)).data(values);

                        // set actions button to default
                        $actions = $row.find('td.actions');
                        if ($actions.get(0)) {
                            _self.rowSetActionsDefault($row);
                        }

                        // remove adding css class
                        $row.removeClass('adding');

                        // set editable td
                        $row.find("td").eq(0).attr('data-editable', false);
                        $row.find("td").eq(1).attr('data-editable', false);

                        // add row id
                        $row.attr('data-id', departDto.id);
                    } else {
                        alertType = '<spring:message code="label.response.error"/>';
                        alertTitle = '<spring:message code="label.insert.error"/>';

                        switch (msg) {
                            case '<spring:message code="label.response.duplicate"/>':
                                alertText = '<spring:message code="label.depart.error.duplicate"/>'.format(departDto.id);
                                break;
                            case '<spring:message code="label.response.too_long"/>':
                                alertText = '<spring:message code="label.depart.error.too_long"/>';
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

        function updateDepartViaAjax(_self, $row, values) {
            var departDto = {
                id: values[1],
                name: values[2]
            };

            $.ajax({
                type: "POST",
                url: "/admin/depart/update",
                data: departDto,
                success: function (msg) {
                    var alertType = '<spring:message code="label.response.success"/>';
                    var alertTitle = '<spring:message code="label.update.success"/>';
                    var alertText = '<spring:message code="label.depart.update.success"/>'.format(departDto.id, departDto.name);

                    if (msg === '<spring:message code="label.response.success"/>') {
                        // update row
                        _self.datatable.row($row.get(0)).data(values);

                        // set actions button to default
                        $actions = $row.find('td.actions');
                        if ($actions.get(0)) {
                            _self.rowSetActionsDefault($row);
                        }
                    }
                    else {
                        alertType = '<spring:message code="label.response.error"/>';
                        alertTitle = '<spring:message code="label.update.error"/>';

                        switch (msg) {
                            case '<spring:message code="label.response.primary_key"/>':
                                alertText = '<spring:message code="label.depart.error.primary_key"/>'.format(departDto.id);
                                break;
                            case '<spring:message code="label.response.too_long"/>':
                                alertText = '<spring:message code="label.depart.error.too_long"/>';
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

        function deleteDepartViaAjax(_self, $row) {
            $.ajax({
                type: "DELETE",
                url: "/admin/depart/{0}".format($row.data("id")),
                success: function (msg) {
                    var alertType = '<spring:message code="label.response.success"/>';
                    var alertTitle = '<spring:message code="label.delete.success"/>';
                    var alertText = '<spring:message code="label.depart.delete.success"/>'.format($row.data("id"), $row.find("td").eq(2).text());

                    if (msg === '<spring:message code="label.response.success"/>') {
                        _self.datatable.row($row.get(0)).remove().draw();

                        var index = 1;
                        var trList = $("#datatable-editable").find('tbody').find('tr');
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
                                alertText = '<spring:message code="label.depart.error.primary_key"/>'.format($row.data("id"));
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
