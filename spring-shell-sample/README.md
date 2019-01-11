```java

# 帮助
shell:>help

AVAILABLE COMMANDS
Built-In Commands
        clear: Clear the shell screen.
        exit, quit: Exit the shell.
        help: Display help about available commands.
        history: Display or save the history of previously run commands
        script: Read and execute commands from a file.
        stacktrace: Display the full stacktrace of the last error.

多命令组操作示例
        delete: 文件删除
        download: 文件下载
        option: 部署工作流
        upload: 文件上传
        
# 查看命令具体帮助     
shell:>help option

NAME
	option - 部署工作流

SYNOPSYS
	option [[bpmn] string[]]  

OPTIONS
	bpmn  string[]
		部署指定的流程文件名（可选）
		[Optional, default = <none>]
 
```

```java
启动命令：
java -jar spring-shell-sample-1.0.0.jar --spring.profiles.active=prod
shell:>upload d://test.txt
shell:>delete 88250
shell:>download 88250 d://test.txt
shell:>option after_sale.bpmn,waiting_pay.bpmn
```

