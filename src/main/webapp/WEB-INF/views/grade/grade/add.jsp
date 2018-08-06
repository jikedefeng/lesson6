<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<lesson:page title="grade.title.${cmd}">
 <jsp:attribute name="script">
		<link rel="stylesheet" href="static-resource/ace/assets/css/bootstrap-datepicker3.min.css"/>
		<script src="static-resource/ace/assets/js/bootstrap-datepicker.min.js"></script>
     <script src="static-resource/ace/assets/js/jquery.bootstrap-duallistbox.min.js"></script>
     <%--<script type="text/javascript">--%>
         <%--jquery(function($){--%>
             <%--$("#clssCode").blur(function () {--%>
                 <%--var className=$("#clssCode").val();--%>
                 <%--if (className!=''){--%>
                     <%--$("#className").text(className+"班");--%>
                 <%--}--%>
             <%--});--%>

         <%--});--%>
     <%--</script>--%>
    </jsp:attribute>
    <jsp:body>

        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="welcome.do">
                        <spring:message code="common.homepage" />
                    </a>
                </li>

                <li>
                    <a href="course/course/list.do">
                        <spring:message code="grade.title.list" />
                    </a>
                </li>
                <li class="active">
                    <spring:message code="grade.title.${cmd}" />
                </li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">

            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue"><spring:message code="grade.title.${cmd}" />
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="grade/grade/list.do" class="btn btn-sm btn-primary"><i class="ace-icon fa fa-angle-left"></i>
                                    <spring:message code="common.back"/>
                                </a>
                            </span>
                            </h3>
                            <form action="grade/grade/save_${cmd}.do" method="post" class="form-horizontal" role="form">
                                <input type="hidden" name="cmd" id="cmd" value="${cmd}"/>
                                <input type="hidden" name="id" id="id" value="${grade.id}"/>
<!--填写班级编号-->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="account">
                                        <spring:message code="grade.grade.code"/>
                                    </label>
                                    <div class="col-sm-6">
                                        <input type="text" autocomplete="off" name="code" value="${grade.code}" class="required form-control" id="clssCode">
                                    </div>
                                </div>
                                <sec:authorize ifAnyGranted="OPT_STUDENT_STUDENT_ADD,OPT_STUDENT_STUDENT_EDIT">
                                    <div class="clearfix form-actions">
                                        <div class="text-center">
                                            <button class="btn btn-info" type="submit">
                                                <i class="ace-icon fa fa-check bigger-110"></i>
                                                <spring:message code="button.submit"/>
                                            </button>
                                        </div>
                                    </div>
                                </sec:authorize>

                            </form>
                        </div><!-- /.span -->
                    </div><!-- /.row -->

                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</lesson:page>