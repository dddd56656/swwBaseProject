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
