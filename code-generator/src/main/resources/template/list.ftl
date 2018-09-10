<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://cicpay.com.cn/leopard" prefix="p"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="container-fluid" >
	<div class="row-fluid">
		<div class="span12">
			<ul class="breadcrumb">
				<li>
					<i class="icon-home"></i>
					<a href="<c:url value="/home"/>">首页</a>
					<i class="icon-angle-right"></i>
				</li>
				<li>
					<a href="#">XX管理</a>
					<i class="icon-angle-right"></i>
				</li>
				<li><a href="#">XX信息</a></li>
			</ul>
		</div>
	</div>
	<div class="row-fluid" >
		<div class="span12">
			<jsp:include page="/view/_message.jsp"></jsp:include>
			<div class="portlet">
				<div class="portlet-body" >
						<div class="tab-content">
							<form class="form-inline" method="post" action="<c:url value='/${entityName?uncap_first}/query'/>">
								<div class="row-fluid">
								<#list columnExtendList as columnExtend>
								<#list "${querycolumns}"?split(",") as s>
								<#if "${s?lower_case}" == "${columnExtend.columnName?lower_case}">
									<div class="span2">
										<div class="control-group">
											<label class="control-label">${columnExtend.columnName?lower_case}:</label>
											<div class="controls">		
											<input type="text" name="${columnExtend.field}" value="${r"${param."}${columnExtend.field}${r"}"}" class="m-wrap span12">
											</div>
										</div>
									</div>
								</#if>
								</#list>
								</#list>
									<div class="span2">
										<div class="control-group">
											<label class="control-label">&nbsp;</label>
											<div class="controls">
												<button type="submit" class="btn blue purple-stripe"><i class="icon-search"></i> 查询</button>
											</div>
										</div>
									</div>
									</div>									
							</form>
						</div>
				</div>
			</div>
		</div>
	</div>	
	<div class="row-fluid">	
		<div class="span12">
			<div class="portlet box grey">
				<div class="portlet-title">
					<div class="caption"><i class="icon-bookmark"></i>信息列表</div>
					<div class="actions">
						<a href="<c:url value='/${entityName?uncap_first}/add' />" class="btn green"><i class="icon-plus"></i>添加</a>
					</div>
				</div>
				
				<div class="portlet-body no-more-tables">
					<table class="table table-bordered table-striped table-hover">
						<thead>
							<tr>
							<#list "${viewcolumns}"?split(",") as s>
								<th>${s}</th>
							</#list>	
								<th>操作</th>
							</tr>
						</thead>
						<tbody>		
							<c:forEach var="row" items="${r"${datagrid.rows}"}">
								<tr>
								<#list columnExtendList as columnExtend>				
								<#list "${viewcolumns}"?split(",") as s>				
								<#if "${s?lower_case}" == "${columnExtend.columnName?lower_case}">
								<#if "${columnExtend.convertedType}" =="String">
								<td data-title="${columnExtend.columnName}">${r"${row."}${columnExtend.field}${r"}"}&nbsp;</td>
								<#else>
								<td data-title="注册时间"><fmt:formatDate value="${r"${row."}${columnExtend.field}${r"}"}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;</td>
								</#if>	
								</#if>
								</#list>
								</#list>							
									<td data-title="操作">								
										<a href="<c:url value='/${entityName?uncap_first}/detail?${primaryKey}=${r"${row."}${primaryKey}${r"}"}'/>" class="btn mini green"><i class="icon-search"></i> 详情</a>
										<a href="<c:url value='/${entityName?uncap_first}/${r"${row."}${primaryKey}${r"}"}/edit'/>" class="btn mini blue"><i class="icon-wrench"></i> 修改</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<p:pagination url="/${entityName?uncap_first}/query" total="${r"${datagrid.total}"}"/>
				</div>
			</div>
		</div>
	</div>
</div>



<script type="text/javascript" src="<c:url value="/static/js/bootstrap-datepicker.js"/>"></script>
<link href="<c:url value="/static/css/datetimepicker.css"/>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/static/js/commons-confirm.js"/>"></script> 
<link type="text/css" rel="stylesheet" href="<c:url value="/static/css/select2_metro.css"/>">
<script type="text/javascript" src="<c:url value="/static/js/select2.min.js"/>"></script>
<script type="text/javascript">

$(document).ready(function(){
	
	
	$(".datepicker").datepicker({
		format: 'yyyy-mm-dd',
		language: 'zh',
		clearBtn: true,
		autoclose:true,
		forceParse: false
	});
	

	
});
</script>