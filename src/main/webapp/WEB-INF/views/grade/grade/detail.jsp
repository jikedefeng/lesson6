<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<lesson:page title="mainmenu.title.list">
    <div class="breadcrumbs ace-save-state" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="ace-icon fa fa-home home-icon"></i>
                <a href="welcome.do">
                    <spring:message code="common.homepage"/>
                </a>
            </li>
            <li class="active"><spring:message code="grade.title.list"/></li>
        </ul><!-- /.breadcrumb -->
    </div>

    <div class="page-content">

        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <div class="row">
                    <div class="col-xs-12">
                        <h3 class="header smaller lighter blue"><spring:message code="grade.title.list" />
                            <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="grade/grade/list.do" class="btn btn-sm btn-primary"><i class="ace-icon fa fa-angle-left"></i>
                                    <spring:message code="common.back"/>
                                </a>
                            </span>
                        </h3>
                        <table id="simple-table" class="table  table-bordered table-hover">
                            <thead>
                            <tr>
                                <th><spring:message code="grade.grade.code"/></th>
                                <th><spring:message code="grade.grade.name"/></th>
                                <th><spring:message code="grade.grade.name.student"/></th>w
                            </tr>
                            </thead>

                            <tbody>

                            <tr id="tr-${grade.id}">
                                <td>${grade.code}</td>
                                <td>${grade.className}</td>
                                <td>
                                    <c:forEach items="${students}" var="student">
                                        ${student.name}
                                    </c:forEach>
                                </td>
                            </tr>

                            </tbody>
                        </table>
                    </div><!-- /.span -->
                </div><!-- /.row -->

                <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div>
</lesson:page>
