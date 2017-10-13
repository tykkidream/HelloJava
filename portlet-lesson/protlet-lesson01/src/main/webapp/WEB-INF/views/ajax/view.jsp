<%@ page language="java" contentType="text/html; charset=UTF-8" import="javax.portlet.ResourceURL" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<portlet:defineObjects/>
<portlet:resourceURL var="fc_resourceUrl" />
<!-- 注意这里的路径 -->
<c:url var="fc_jquery" value="/js/jquery-1.11.3.min.js"/>
<h3><portlet:namespace /></h3>
<hr />
<div>
	<h3>Portlet处理Ajax请求!</h3>
	<p id="fc_hi1">嗨！你是谁啊？</p>
	<p>输入名字:</p>
	<p><input type="text" name="name" size="10" id="fc_name"/></p>
	<p><input type="submit" value="提交" onclick="fc_requestPortlet();"/></p>
</div>
<div>
	<h3>Servlet处理Ajax请求!</h3>
	<p id="fc_hi2">嗨！你是谁啊？</p>
	<p>输入名字:</p>
	<p><input type="text" name="name" size="10" id="fc_name"/></p>
	<p><input type="submit" value="提交" onclick="fc_requestServlet();"/></p>
</div>

<script type="text/javascript" src="${fc_jquery}"></script>
<script type="text/javascript">

function fc_requestPortlet() {
	var data = { name : $('#fc_name').val() };
	$.ajax({
        type :"post",
        url : '${fc_resourceUrl }',
        cache : false,
        data : data,
        dataType : "json",
        contentType : "application/x-www-form-urlencoded; charset=UTF-8", //声明编码
        success : function(data){
            if(data['name'] != null){
            	$('#fc_hi1').text('噢，原来你是'+ data.name + '，久仰大名了！');
            } else {
            	$('#fc_hi1').text('嗨！你是谁啊？');
            }
        },
        error : function(data,a,b,c){
        	alert("异常！/r/n" + data); 
        }
    });
}

function fc_requestServlet() {
	var data = { name : $('#fc_name').val() };
	$.ajax({
        type :"post",
        url : '<%= request.getContextPath() %>/ajax', // 注意这里的路径
        cache : false,
        data : data,
        dataType : "json",
        contentType : "application/x-www-form-urlencoded; charset=UTF-8", //声明编码
        success : function(data){
            if(data['name'] != null){
            	$('#fc_hi2').text('噢，原来你是'+ data.name + '，久仰大名了！');
            } else {
            	$('#fc_hi2').text('嗨！你是谁啊？');
            }
        },
        error : function(data,a,b,c){
        	alert("异常！/r/n" + data); 
        }
    });
}
</script>