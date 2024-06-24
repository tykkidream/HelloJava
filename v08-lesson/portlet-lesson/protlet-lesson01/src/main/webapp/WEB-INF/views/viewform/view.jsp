<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<portlet:defineObjects/>
<h3><portlet:namespace /></h3>
<hr />
<h3>视图表单!</h3>
<c:if test="${param.msg != null }">
<p>噢，原来你是${param.msg }，久仰大名了！</p>
</c:if>
<c:if test="${param.msg == null }">
<p>嗨！你是谁啊？</p>
</c:if>
<portlet:actionURL var="tt_action" portletMode="view" windowState="normal"/>
<form method="post" action="${tt_action}" >
	<p>输入名字:</p>
	<p><input type="text" name="name" size="10"/></p>
	<p><input type="submit" value="提交"/></p>
</form>