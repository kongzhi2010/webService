webService
==========

webService built by spring mvc for learning

xss漏洞实例:
http://localhost:8080/demo/xss/check/user/input?p=<script>e1=document.createElement('script');e1.src='http://localhost:8080/static/js/xss/xss.js';document.body.appendChild(e1);</script>

这个是因为系统没有对用户输入的参数进行检查,而直接在页面上显示了
用户输入,导致恶意用户构造了特殊的html代码,造成了恶意代码在用户浏览器的上下文环境执行.