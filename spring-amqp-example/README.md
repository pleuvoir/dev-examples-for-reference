

## 下载与安装

### windows 

[Installing on Windows](https://www.rabbitmq.com/install-windows.html)

先安装 Erlang (必须以管理员身份运行)，然后安装 rabbit

安装好后，进入 rabbitMQ 安装目录下的 sbin 目录，在目录下 shift+右键 打开命令行
（C:\Program Files\RabbitMQ Server\rabbitmq_server-3.7.7\sbin）

使用 `rabbitmq-plugins.bat enable rabbitmq_management` 开启网页管理界面,然后重启 rabbitMQ 

在浏览器中输入 http://localhost:15672 成功开启web后台管理界面,输入用户名和密码（默认为guest）

配置文件位于 `C:\Users\pleuvoir\AppData\Roaming\RabbitMQ`

到此 rabbitMQ 的 window 安装就完成了

注意：如果重新安装 必须删除 C:\Users\pleuvoir\AppData\Roaming\RabbitMQ 文件夹，否则无法启动


## 示例

目前只有延迟队列和普通队列生产和消费示例
