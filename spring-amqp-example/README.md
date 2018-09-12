

## 下载与安装

### [Installing on Windows](https://www.rabbitmq.com/install-windows.html)

先安装 Erlang (必须以管理员身份运行)，然后安装 rabbit

####  启动

启动 `rabbitmq-server.bat`

#### 插件

安装好后，如果需要安装插件，进入 rabbitMQ 安装目录下的 sbin 目录，在目录下 shift+右键 打开命令行
（大概是这个位置 C:\Program Files\RabbitMQ Server\rabbitmq_server-3.7.7\sbin）

1. 使用 `rabbitmq-plugins.bat enable rabbitmq_management` 开启网页管理界面，然后重启 rabbitMQ 

	在浏览器中输入 http://localhost:15672 成功开启web后台管理界面，输入用户名和密码（默认为guest）

2. 如果需要做推送可以使用 `rabbitmq-plugins enable rabbitmq_web_stomp` 开启 stomp 代理

#### 注意

1. 如果重新安装 必须删除 C:\Users\pleuvoir\AppData\Roaming\RabbitMQ 文件夹，否则无法启动
2. 配置文件位于 `C:\Users\pleuvoir\AppData\Roaming\RabbitMQ`


## 示例

该项目基于 springboot 提供了延迟队列、普通队列生产者及消费者示例
