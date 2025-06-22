#注意!!!!!
容器全都没有挂载数据卷，下面命令仅限开发环境使用

#docker镜像的地址推荐
{
"features": {
"buildkit": true
},
"registry-mirrors": [
"https://registry.cn-hangzhou.aliyuncs.com",
"https://dockerproxy.net"
]
}


#起一下队列

docker run -d \
--name rabbitmq \
-p 5672:5672 \
-p 15672:15672 \
-e RABBITMQ_DEFAULT_USER=root \
-e RABBITMQ_DEFAULT_PASS=123456 \
rabbitmq:3.13-management

#起一下redis

docker run -d \
--name redis \
-p 6379:6379 \
redis:7.2.5

#起一下数据库

docker run -d \
--name mysql8 \
-p 3306:3306 \
-e MYSQL_ROOT_PASSWORD=Root@2024 \
mysql:8.0

#起一下rocketmq

# 拉取镜像
docker pull apache/rocketmq:5.3.2

# 创建网络（已存在忽略报错）
docker network create rocketmq || true

# 启动 NameServer
docker run -d \
--name rmqnamesrv \
-p 9876:9876 \
--network rocketmq \
apache/rocketmq:5.3.2 sh mqnamesrv

# 等待启动日志
docker logs -f rmqnamesrv

# Configure the broker's IP address
echo "brokerIP1=127.0.0.1" > broker.conf

# Start the Broker and Proxy
docker run -d `
  --name rmqbroker `
--network rocketmq `
  -p 10912:10912 -p 10911:10911 -p 10909:10909 `
-p 8080:8080 -p 8081:8081 `
  -e "NAMESRV_ADDR=rmqnamesrv:9876" `
-v "${PWD}\broker.conf:/home/rocketmq/rocketmq-5.3.2/conf/broker.conf" `
apache/rocketmq:5.3.2 sh mqbroker --enable-proxy -c /home/rocketmq/rocketmq-5.3.2/conf/broker.conf


# Verify if Broker started successfully
docker exec -it rmqbroker bash -c "tail -n 10 /home/rocketmq/logs/rocketmqlogs/proxy.log"




#起一下 mongodb

# 拉取官方MongoDB镜像（默认最新版）
docker pull mongo

# 运行MongoDB容器，映射本地27017端口
docker run -d --name mongodb -p 27017:27017 mongo

# （可选）进入容器查看日志确认启动成功
docker logs -f mongodb


#起一下zookeeper

# 拉取官方 ZooKeeper 镜像
docker pull zookeeper

# 启动 ZooKeeper 容器（监听本地 2181 端口）
docker run -d \
--name zookeeper \
-p 2181:2181 \
-e ZOO_4LW_COMMANDS_WHITELIST=ruok,stat,conf \
-e ZOO_MY_ID=1 \
-e ZOO_SERVERS=server.1=0.0.0.0:2888:3888 \
zookeeper

# 查看运行状态（确认输出中有 'binding to port 0.0.0.0/0.0.0.0:2181'）
docker logs -f zookeeper


#起一下sentinel

docker run -d --name sentinel-dashboard \
-p 30918:8080 \
-e JAVA_OPTS="-Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080" \
bladex/sentinel-dashboard:1.8.6

