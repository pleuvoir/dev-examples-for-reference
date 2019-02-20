

## Zookeeper 安装

ZooKeeper的安装模式分为三种，分别为：单机模式（stand-alone）、集群模式和集群伪分布模式。

### windows 安装

jdk 是必须的。

[下载](https://www.apache.org/dyn/closer.cgi/zookeeper/) 

完成后解压。

如解压的后的目录是 `D:\01 dev\zookeeper-3.4.13` ，建议在 `D:\01 dev` 新建 data 及 log 文件夹。

复制 `D:\01 dev\zookeeper-3.4.13\conf\zoo_sample.cfg` 文件到当前目录下，并改名为 `zoo.cfg`。

修改 `zoo.cfg` 文件，改为如下配置：

```
## 快照文件位置
dataDir=D:/01 dev/data
## 事务日志位置
dataLogDir=D:/01 dev/log
```

此时安装已经完成了。

### Linux 安装

```shell
## 下载
wget mirrors.shu.edu.cn/apache/zookeeper/zookeeper-3.4.13/zookeeper-3.4.13.tar.gz
## 解压文件
tar -xzvf zookeeper-3.4.13.tar.gz

cd zookeeper-3.4.13/conf
cp zoo_sample.cfg zoo.cfg

## 此时配置不配也可以启动
./zkServer.sh start
```

### 目录结构

```
bin            存放系统脚本
conf           存放配置文件
contrib        zk附加功能支持
dist-maven     maven仓库文件
docs           zk文档
lib            依赖的第三方库
recipes        经典场景样例代码
src            zk源码
```

其中 bin 和 conf 是非常重要的两个目录，平时也是经常使用的。


#### bin

![](https://i.loli.net/2019/02/20/5c6d009de9df4.jpg)

其中 `zkServer` 为启动服务端，默认端口为 2181；`zkCli` 是客户端命令行工具。

#### conf 

conf 目录为配置文件存放的目录，zoo.cfg 为核心的配置文件

![](https://i.loli.net/2019/02/20/5c6d00acbabf9.png)
![](https://i.loli.net/2019/02/20/5c6d00c51cdeb.png)
![](https://i.loli.net/2019/02/20/5c6d00cd6252a.png)
![](https://i.loli.net/2019/02/20/5c6d00d3da7bc.png)
![](https://i.loli.net/2019/02/20/5c6d00db36d50.png)


## ZK 特性

### 数据节点

在 Zookeeper 中，数据结构类似于树，每一个节点叫 `ZNode`,每一个节点都是有值的。

我们可以在 Windows 下使用 `ZooInspector` 客户端工具查看。

![ZooInspector](https://i.imgur.com/jLRsYQX.png)

#### 节点类型

1）临时节点

  当与客户端断开连接会 ZNode 自动删除

2）持久节点
  
  当与客户端断开连接会 ZNode 还在

3） 顺序节点

  当两个同样的命令执行会自增，`create /node1 0000001`； `create /node1 0000002`；非顺序节点会报错

当节点名称带序号时，创建同样的节点会自增，如果不带序号创建同名节点会报错。也就是说，<b>对于持久节点和临时节点，同一个 Znode 下，节点名称是唯一的。<b>



### Watcher



// todo

