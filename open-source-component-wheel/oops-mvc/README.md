
## :fire: oops-mvc

:sob:  乞丐版MVC容器

### 思路

在 servlet 容器 `init` 时扫描所有需要注册的请求 保存进 `map` 中，`map` 中 key 即为请求路径，value 为反射需要调用的方法。如下：


```java

/**
 * 保存 servlet 请求路径和  反射调用的方法 <br>
 * Example： /github/password public void io.github.pleuvoir.controller.HelloWorldController.password()
 */
private static Map<String, Method> routers = new ConcurrentHashMap<>();

```

整个代码思路都是围绕这一点展开的。

