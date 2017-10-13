<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<portlet:defineObjects/>
<h3><portlet:namespace /></h3>
<hr />
<h3>打印名字!</h3>
<form method="post" action="<portlet:actionURL portletMode="view" windowState="normal"/>" >
	<p>输入名字:</p>
	<p><input type="text" name="name" size="10"/></p>
	<p><input type="submit" value="提交"/></p>
</form>