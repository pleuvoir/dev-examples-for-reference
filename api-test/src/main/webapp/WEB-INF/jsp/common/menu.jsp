<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-inverse">
<div class="container">
		
  <div class="navbar-header">
          <a href="${ctx}" class="navbar-brand">api-test</a>
          <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
  </div>
  
	<div id="navbar-main" class="collapse navbar-collapse">
		 <ul class="nav navbar-nav">
		 
				<li class="active"><a href="${ctx}/">设置</a></li>
 			    
			 	<li><a href="${ctx}/Merchantregister">商户报备</a></li>
				
				<li><a href="${ctx}/QrCode">扫码</a></li>
				
				<li class="dropdown">
					<a href="##" data-toggle="dropdown" class="dropdown-toggle">代付<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${ctx}/Defray">代付申请</a></li>
						<li><a href="${ctx}/Defrayquery">代付查询</a></li>
					</ul> 
				</li>
				
				<li class="dropdown">
					<a href="##" data-toggle="dropdown" class="dropdown-toggle">支付查询<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${ctx}/Querysingle">订单单笔查询</a></li>
						<li><a href="${ctx}/Querymulti">订单批量查询</a></li>
					</ul> 
				</li>
				
		</ul>
	</div>
</div>
</nav>


<script type="text/javascript">
 $(function(){
	 //自动展开
	$('li.dropdown').mouseover(function() {
			$(this).addClass('open');
		}).mouseout(function() {
			$(this).removeClass('open');
		});
	})
</script>