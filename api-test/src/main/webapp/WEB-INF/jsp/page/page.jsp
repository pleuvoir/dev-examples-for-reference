<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%> 
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
<title>${page.title}</title>
</head>
<body>
	<jsp:include page="../common/menu.jsp"/>
	
<div class="container">

	<div class="row clearfix">
		<div class="col-md-12 column">
			<p class="lead text-success">
				 ${page.description}
			</p>
		</div>
	</div>
	
	<div class="row clearfix">
		<div class="col-md-5 column">
			<form id="form" class="form-horizontal" role="form">
				<c:forEach var="column" items="${page.columns}">  
					<div class="form-group">
					<div class="col-sm-4" >
						<h4><span class="label label-default <c:if test="${!column.required}">label-danger</c:if> ">${column.name}</span></h4>
					</div>
					 <div class="col-sm-8">
					  <c:choose>
					        <c:when test="${column.elementsType == 'INPUT'}">   
					        <input class="form-control" name="${column.name}" placeholder="${column.placeholder}"
								<c:choose>
						         <c:when test="${column.name == 'mid'}"> value= "${mid}" </c:when>
						         <c:otherwise> value="<c:out value="${column.value}"></c:out>"</c:otherwise>
						      </c:choose>  />
					         </c:when>
					         
					         <c:when test="${column.elementsType == 'TEXTAREA'}">   
					           <textarea  rows="3" style="width: 100%;">${column.value}</textarea>
					         </c:when>
					         
					         <c:when test="${column.elementsType == 'SELECT'}">   
					            <select name="${column.name}" class="form-control">
					           	   <c:forEach var="option" items="${column.select.options}">  
					                 <option value="${option.value}">${option.showValue}</option>
					               </c:forEach>
					            </select>
					         </c:when>
					  </c:choose>
					 </div> 
				   </div>
				</c:forEach>
											
				<br><br>
				<div class="form-group">
					  <label class="col-sm-3 control-label">密钥</label>
					  <div class="col-sm-6"><input class="form-control"   name="key" value="${key}"  placeholder="密钥"/></div>
					 <div class="col-sm-3">  <button type="button" class="btn btn-primary btn-default" onclick="getSign();">计算签名</button></div> 
				</div>
				
				<div class="form-group">
					  <label class="col-sm-3 control-label">sign</label>
					  <div class="col-sm-9"><input class="form-control" readonly id="sign" name="sign" value=""  placeholder="签名"/></div>
				</div>
			</form>
		</div>
		
		
		<div class="col-md-7 column">
			<table class="table table-condensed  table-bordered ">
				<thead>
					<tr class="">
						<th>参数</th>
							<th>说明</th>
							<th>备注</th>
						</tr>
				</thead>
				<tbody>
				<c:forEach var="column" items="${page.columns}"> 
				   <tr class="<c:if test="${!column.required}">danger</c:if>">
							<td><small>${column.name}</small></td>
							<td><small>${column.placeholder}</small></td>
							<td><small>${column.remark}</small></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
	<hr>
	
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">
						请求示例：
					</h3>
				</div>
				<div id="requestDemo" class="panel-body">
				</div>
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-2">
		</div>
		<div class="col-md-6">
			<form  class="form-horizontal" role="form">
				<div class="input-group">
					<span class="input-group-addon">请求地址</span>
					 <input class="form-control"  id="requestURL" name="requestURL" value="${page.requestURL}">
					<span class="input-group-btn">
						<button class="btn btn-success btn-default" onclick="go();" type="button">开车!</button>
					</span>
				</div>
				</form>
		</div>
	</div>
	
	<div class="panel-body">
		<div>
			<textarea id="RawJson" rows="5" style="width: 100%;"></textarea>
		</div>
	</div>
</div>
      <hr>
      <footer class="text-center">
        <p>&copy; 2017 pleuvoir &middot; <a href="#">Back to top</a></p>
      </footer>


<script type="text/javascript">
  function getSign(){
	$.ajax({
		type: "post",
		url: "${ctx}/utils/getSign",
		data: $('#form').serialize(),
		async : false,
		success: function(data){
			$("#sign").val(data.genSign);
			$("#requestDemo").text(data.requestDemo);
		},
		error: function(data){
			console.log("提示信息","请求数据失败!","error");
		}
	});
  }
  
  function go(){
	  $("#RawJson").val("");
	  getSign();
	  var $requestURL = $("#requestURL").val();
		$.ajax({
			type: "post",
			url: "${ctx}/utils/redirectPost?"+$('#form').serialize()+"&requestURL="+$requestURL,
			data: $('#form').serialize(),
			success: function(data){
				console.log(data);
				$("#RawJson").val(data);
			},
			error: function(data){
				console.log("提示信息","请求数据失败!","error");
			}
		});
	  }
</script>
</body>
</html>
