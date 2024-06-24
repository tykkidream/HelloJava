<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3><portlet:namespace /></h3>
<hr />
<h3>打印名字!</h3>
<c:if test="${param.msg != null }">
<p>噢，原来你是${param.msg }，久仰大名了！</p>
</c:if>
<c:if test="${param.msg == null }">
<p>嗨！你是谁啊？</p>
</c:if>