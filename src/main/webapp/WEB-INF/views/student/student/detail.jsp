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
            <li class="active"><spring:message code="student.title.list"/></li>
        </ul><!-- /.breadcrumb -->
    </div>

    <div class="page-content">

        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <div class="row">
                    <div class="col-xs-12">
                            <h3 class="header smaller lighter blue"><spring:message code="student.title.list" />
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="student/student/list.do" class="btn btn-sm btn-primary"><i class="ace-icon fa fa-angle-left"></i>
                                    <spring:message code="common.back"/>
                                </a>
                            </span>
                            </h3>
                        <table id="simple-table" class="table  table-bordered table-hover">
                            <thead>
                            <tr>
                                <th><spring:message code="student.code"/></th>
                                <th><spring:message code="student.name"/></th>
                                <th><spring:message code="student.course.name"/></th>
                                <th><spring:message code="student.grade.name"/> </th>
                            </tr>
                            </thead>

                            <tbody>

                            <tr id="tr-${student.id}">
                                <td>${student.code}</td>
                                <td>${student.name}</td>
                                <td>
                                    <c:forEach items="${courses}" var="course">
                                        ${course}
                                    </c:forEach>
                                </td>
                                <td>${grade.className}</td>
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
