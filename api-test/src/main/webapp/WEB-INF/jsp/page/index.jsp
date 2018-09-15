<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%> 
<html>
<head>
<title>api-test</title>
</head>
<body>

  <jsp:include page="../common/menu.jsp"/>

  <div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
		
		<button  class="btn btn-default" id="open">设置公共参数环境</button>
 
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog">
		        <div class="modal-content">
		          <div class="modal-header">
		            <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
		            <h4 class="modal-title">设置</h4>
		          </div>
		          <div class="modal-body">
		            	<div class="form-group">
							 商户号:<input class="form-control" id="mid" value="${mid}"  placeholder="商户号"/>
						</div>
						<div class="form-group">
							  密钥:<input class="form-control"  id="key"  name="key" value="${key}"  placeholder="密钥"/>
						</div>
						
						
						<div class="form-group">
							环境:
							<select id="env"  class="form-control">
								<option  value="----">请选择环境</option>
								<option endPoint="https://devapi.tfcpay.com/v2" <c:if test="${environment eq 'MER_TEST'}">selected</c:if> value="MER_TEST">商户测试环境</option>
								<option endPoint="https://api.tfcpay.com/v2"    <c:if test="${environment eq 'PRODUCT'}">selected</c:if> value="PRODUCT">生产环境</option>
							</select>
						</div>
						
						<div class="form-group">
							请求地址:<input class="form-control" id="endPoint" value="${endPoint}"  placeholder="请求地址"/>
						</div>
		          </div>
		          
		          <div class="modal-footer">
		            <button class="btn btn-primary" id="submit" type="button">提交</button>
		            <button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
		          </div>
		        </div>
		  </div>
		  </div>
		</div>
	</div>
 </div>

<script>
$(function(){
	$("#env").change(function(){
	    $("#endPoint").val($(this).find("option:selected").attr("endPoint"));
	});
	
   $("#open").click(function(){
      $("#myModal").modal('show');
   });
   $("#submit").click(function(){
	   var mid = $("#mid").val();
	   var key = $("#key").val();
	   var env = $("#env").val();
	   var endPoint = $("#endPoint").val();
		$.ajax({
			type: "post",
			url: "${ctx}/utils/reset",
			data: "mid=" + mid + "&key=" + key + "&env=" + env + "&endPoint=" + endPoint,
			success: function(data){
      		$("#myModal").modal('hide');
		},
		error: function(data){
			console.log("提示信息","请求数据失败!","error");
			$("#myModal").modal('hide');
		}
	  });
   });
});
</script>
</body>

</html>
