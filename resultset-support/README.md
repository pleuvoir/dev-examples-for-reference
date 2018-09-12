
### :fire:

 一个SQL生成查询返回值

#### 使用

`config.properties` 文件中输入要查询的 sql 

```
querySql = SELECT * FROM emp e left join dept on e.deptno = dept.deptno
```

运行程序后，会自动生成结果集 `pojo` 对象

注意： sql 在文件中必须是一行。
