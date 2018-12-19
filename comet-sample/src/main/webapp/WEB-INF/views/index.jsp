<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding= "UTF-8" %>
<! DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd" >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>服务器推送技术</title>
</head>
<body>
<h1>服务器推送技术演示</h1>

<ul>
    <li><h2><a href="time">查看服务器时间（ajax 定时器短轮询）</a></h2></li>
    <li><h2><a href="pushnews">Servlet3 异步-推送实时新闻（基于 spring 提供的 DeferedResult 长轮询）</a></h2></li>
    <li><h2><a href="nobleMetal">SSE-贵金属期货价格实时查询（原生 sse）</a></h2></li>
    <li><h2><a href="weChatPay">在线支付（基于 spring 提供的 SseEmitter）</a></h2></li>
</ul>
</body>
</html>