<%@ page language="java" %><%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %><%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %><%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %><%@ taglib uri="/WEB-INF/app.tld" prefix="app" %><%@ page import="ServidorApresentacao.TagLib.sop.v3.TimeTableType" %><%@ page import="Util.TipoCurso" %><%@ page import="ServidorApresentacao.Action.sop.utils.SessionConstants" %><bean:define id="institutionUrl" type="java.lang.String"><bean:message key="institution.url" bundle="GLOBAL_RESOURCES"/></bean:define><div class="breadcumbs"><a href="<%= institution.url %>"><bean:message key="institution.name.abbreviation" bundle="GLOBAL_RESOURCES"/></a><bean:define id="institutionUrlTeaching" type="java.lang.String"><bean:message key="institution.url" bundle="GLOBAL_RESOURCES"/>ensino/index.shtml</bean:define>&nbsp;&gt;&nbsp;<a href="<%= institutionUrlTeaching %>"><bean:message key="label.education" /></a>	<bean:define id="degreeType" name="<%= SessionConstants.INFO_DEGREE_CURRICULAR_PLAN %>" property="infoDegree.tipoCurso" />		<bean:define id="infoDegreeCurricularPlan" name="<%= SessionConstants.INFO_DEGREE_CURRICULAR_PLAN %>"  />		&nbsp;&gt;&nbsp;	<html:link page="<%= "/showDegreeSite.do?method=showDescription&amp;executionPeriodOID=" + request.getAttribute(SessionConstants.EXECUTION_PERIOD_OID) + "&amp;degreeID=" + request.getAttribute("degreeID").toString() + "&amp;executionDegreeID="  +  request.getAttribute("executionDegreeID") %>">		<bean:write name="infoDegreeCurricularPlan" property="infoDegree.sigla" />	</html:link>		&nbsp;&gt;&nbsp;	<html:link page="<%= "/showDegreeSite.do?method=showCurricularPlan&amp;degreeID=" + request.getAttribute("degreeID") + "&amp;degreeCurricularPlanID=" + request.getAttribute("degreeCurricularPlanID") + "&amp;executionPeriodOID=" + request.getAttribute(SessionConstants.EXECUTION_PERIOD_OID) + "&amp;executionDegreeID="  %>" >		<bean:message key="label.curricularPlan"/>	</html:link>	&nbsp;&gt;&nbsp; 	<html:link page="<%= "/chooseContextDANew.do?method=nextPagePublic&amp;nextPage=classSearch&amp;inputPage=chooseContext&amp;executionPeriodOID=" +  pageContext.findAttribute(SessionConstants.EXECUTION_PERIOD_OID) + "&amp;degreeID=" + request.getAttribute("degreeID") + "&amp;degreeCurricularPlanID=" + request.getAttribute("degreeCurricularPlanID") %>">		<bean:message key="label.classes"/> 	</html:link>	&nbsp;&gt;&nbsp;<bean:write name="className" /></div><!-- P�GINA EM INGL�S 	<div class="version">		<span class="px10">			<html:link page="<%= "/showDegreeSite.do?method=showCurricularPlan&amp;inEnglish=true&amp;executionPeriodOID=" + request.getAttribute(SessionConstants.EXECUTION_PERIOD_OID) + "&amp;degreeID=" +  request.getAttribute("degreeID") + "&amp;executionDegreeID="  +  request.getAttribute("executionDegreeID") + "&amp;index=" %>" >english version</html:link> <img src="<%= request.getContextPath() %>/images/icon_uk.gif" alt="Icon: English version!" width="16" height="12" />	</span>		</div>--><h1>	<bean:write name="infoDegreeCurricularPlan" property="infoDegree.tipoCurso" />	<bean:message key="label.in" />	<bean:write name="infoDegreeCurricularPlan" property="infoDegree.nome" /></h1><bean:define id="component" name="siteView" property="component" /><bean:define id="execPeriod" name="component" property="executionPeriod" /><h2><span class="greytxt">	<bean:write name="execPeriod" property="semester" /><bean:message key="label.ordinal.semester.abbr" />	<bean:write name="execPeriod" property="executionYear.year" /></span></h2><html:hidden property="<%SessionConstants.EXECUTION_PERIOD_OID%>" value="<%= ""+request.getAttribute(SessionConstants.EXECUTION_PERIOD_OID)%>" /><html:hidden property="page" value="1"/><bean:define id="lessonList" name="component" property="lessons" />	<h2><bean:message key="title.class.timetable" /><bean:write name="className" /></h2><app:gerarHorario name="lessonList"/>		