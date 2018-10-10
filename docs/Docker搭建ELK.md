## 设置网络
```bash
docker network create --driver bridge --subnet 172.98.0.0/16 elk-network
```

## 准备镜像
6.0之后官方开始自己维护镜像版本:www.docker.elastic.co。找到需要的ELK镜像地址，pull下来就好了。
需要下载elasticsearch, kibana, filebeat, logstash。下以elasticsearch 6.4为例：
```bash
docker pull docker.elastic.co/elasticsearch/elasticsearch:6.4.0
docker pull docker.elastic.co/kibana/kibana:6.4.0
docker pull docker.elastic.co/beats/filebeat:6.4.0
docker pull docker.elastic.co/logstash/logstash:6.4.0
docker pull docker.elastic.co/beats/packetbeat:6.4.0
```

官方pull下来之后镜像名太长了，将镜像全部重新打了tag：
```bash
docker tag docker.elastic.co/elasticsearch/elasticsearch:6.4.0 elasticsearch
docker tag docker.elastic.co/kibana/kibana:6.4.0 kibana
docker tag docker.elastic.co/beats/filebeat:6.4.0 filebeat
docker tag docker.elastic.co/logstash/logstash:6.4.0 logstash
```
    
## elasticsearch
- 设置vm.max_map_count

在elasticsearch的docker版本文档中，官方提到了vm.max_map_count的值在生产环境最少要设置成262144。设置的方式有两种：

1.永久性的修改,在/etc/sysctl.conf文件中添加一行：
```bash
grep vm.max_map_count /etc/sysctl.conf # 查找当前的值
vm.max_map_count=262144 # 修改或者新增
```

2.正在运行的机器：
```bash
sysctl -w vm.max_map_count=262144
```

- 主节点

配置文件elasticsearch.yml
```yaml
cluster.name: elas-cluster
node.name: node-master
node.master: true
node.data: true
http.port: 9200
network.host: 0.0.0.0
network.publish_host: 172.98.0.31
xpack.security.enabled: false
```

```bash
docker run --name elas1 --restart always -d  \
    -p 9200:9200  \
    -p 9300:9300  \
    --network=elk-network --ip=172.98.0.31 \
    -e ES_JAVA_OPTS="-Xms512m -Xmx512m"  \
    -v /home/minstrel/elk/elas1/data:/usr/share/elasticsearch/data  \
    -v /home/minstrel/elk/elas1/elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml  \
    elasticsearch
```

- 子节点

配置文件elasticsearch.yml
```yaml
cluster.name: elas-cluster
node.name: node-data-1
node.master: false
node.data: true
http.port: 9200
network.host: 0.0.0.0
network.publish_host: 172.98.0.32
discovery.zen.ping.unicast.hosts: [172.98.0.31]
xpack.security.enabled: false
```

```bash
docker run --name elas2 --restart always -d  \
    --network=elk-network --ip=172.98.0.32  \
    -e ES_JAVA_OPTS="-Xms512m -Xmx512m"  \
    -v /home/minstrel/elk/elas2/data:/usr/share/elasticsearch/data  \
    -v /home/minstrel/elk/elas2/elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml  \
    elasticsearch
```

### kibana
```yaml
server.name: kibana
server.host: "0"
elasticsearch.url: http://172.98.0.31:9200
xpack.monitoring.ui.container.elasticsearch.enabled: true
```

```bash
docker run --name kibana --restart always -d  \
    --network=elk-network --ip=172.98.0.33  \
    -p 5601:5601  \
    -v /home/minstrel/elk/kibana/kibana.yml:/usr/share/kibana/config/kibana.yml  \
    kibana
```

### logstash
配置文件logstash.conf

支持的input plugins: https://www.elastic.co/guide/en/logstash/current/input-plugins.html
```
input {
  beats { # 定义接受日志ip和端口
    host => "172.98.0.34"
    port => "5043"
  }
}

filter {
  if [fields][doc_type] == 'order' { #[fields][doc_type]在filebeat中配置
    grok {
      match => { "message" => "%{TIMESTAMP_ISO8601:timestamp} %{LOGLEVEL:level} %{JAVALOGMESSAGE:msg}" }
    }
  }

  if [fields][doc_type] == 'customer' {
    grok {
      match => { "message" => "%{TIMESTAMP_ISO8601:timestamp} %{LOGLEVEL:level} %{JAVALOGMESSAGE:msg}" }
    }
  }
}

output {
  stdout { codec => rubydebug }
  elasticsearch { # 输出到elas中
    hosts => [ "172.98.0.31:9200" ]
    index => "%{[fields][doc_type]}-%{+YYYY.MM.dd}"
  }
}
```

```bash
docker run --name logstash -it --restart always -d  \
    --network=elk-network --ip=172.98.0.34  \
    -v /home/minstrel/elk/logstash/cfg/logstash.conf:/usr/share/logstash/pipeline/logstash.conf  \
    logstash
```

## filebeat
- 准备两个日志文件作测试用，日志文件要卷关联到容器中

customer.log
```
2017-08-26 10:05:56,476 INFO ConfigClusterResolver:43 - Resolving eureka endpoints via configuration
2017-08-26 10:07:23,529 INFO WarehouseController:271 - findWarehouseList,json{"formJSON":{"userId":"885769620971720708"},"requestParameterMap":{},"requestAttrMap":{"name":"asdf","user":"8857696","ip":"183.63.112.1","source":"asdfa","customerId":"885768861337128965","IMEI":"863267033748196","sessionId":"xm1cile2bcmb15wtqmjno7tgz","sfUSCSsadDDD":"asdf/10069&ADR&1080&1920&OPPO R9s Plus&Android6.0.1","URI":"/warehouse-service/appWarehouse/findByCustomerId.apec","encryptType":"2","requestStartTime":3450671468321405}}
2017-08-26 10:07:23,650 INFO WarehouseServiceImpl:325 - warehouse list:8,warehouse str:[{"addressDetail":"nnnnnnnn","areaId":"210624","areaNa":""}]
2017-08-26 10:10:56,477 INFO ConfigClusterResolver:43 - Resolving eureka endpoints via configuration
2017-08-26 10:15:56,477 INFO ConfigClusterResolver:43 - Resolving eureka endpoints via configuration
2017-08-26 10:20:56,478 INFO ConfigClusterResolver:43 - Resolving eureka endpoints via configuration
2017-08-26 10:05:56,476 INFO ConfigClusterResolver:43 - Resolving eureka endpoints via configuration
2017-08-26 10:07:23,529 INFO WarehouseController:271 - findWarehouseList,json{"formJSON":{"userId":"885769620971720708"}}]
2017-08-26 10:10:56,477 INFO ConfigClusterResolver:43 - Resolving eureka endpoints via configuration
2017-08-26 10:15:56,477 INFO ConfigClusterResolver:43 - Resolving eureka endpoints via configuration
2017-08-26 10:20:56,478 INFO ConfigClusterResolver:43 - Resolving eureka endpoints via configuration
```

order.log
```
2017-08-26 11:29:19,374 INFO WebLogAspect:53 -- 请求:18,SPEND TIME:0
2017-08-26 11:38:20,404 INFO NoticeServiceApplication:664 -- The following profiles are active: test
2017-08-26 11:41:07,754 INFO NoticeServiceApplication:664 -- The following profiles are active: test
2017-08-26 12:38:58,683 INFO RedisClusterConfig:107 -- //// --- 启动单点Redis ---
2017-08-26 12:39:00,325 DEBUG ApplicationContextRegister:26 --
2017-08-26 12:39:06,961 INFO NoticeServiceApplication:57 -- Started NoticeServiceApplication in 17.667 seconds (JVM running for 18.377)
2017-08-26 11:27:56,577 INFO WebLogAspect:51 -- 请求:19,RESPONSE:"{\"data\":null,\"errorCode\":\"\",\"errorMsg\":\"\",\"repeatAct\":\"\",\"succeed\":true}"
2017-08-26 11:27:56,577 INFO WebLogAspect:53 -- 请求:19,SPEND TIME:1
2017-08-26 11:28:09,829 INFO WebLogAspect:42 -- 请求:20,URL:http://192.168.7.203:30004/sr/flushCache
2017-08-26 11:28:09,830 INFO WebLogAspect:43 -- 请求:20,HTTP_METHOD:POST
2017-08-26 11:28:09,830 INFO WebLogAspect:44 -- 请求:20,IP:192.168.7.98
2017-08-26 11:28:09,830 INFO WebLogAspect:45 -- 请求:20,CLASS_METHOD:com.notice.web.estrictController
2017-08-26 11:28:09,830 INFO WebLogAspect:46 -- 请求:20,METHOD:flushRestrict
2017-08-26 11:28:09,830 INFO WebLogAspect:47 -- 请求:20,ARGS:["{\n}"]
2017-08-26 11:28:09,830 DEBUG SystemRestrictController:231 -- 刷新权限限制链
2017-08-26 11:38:20,404 INFO NoticeServiceApplication:664 -- The following profiles are active: test
2017-08-26 11:41:07,754 INFO NoticeServiceApplication:664 -- The following profiles are active: test
2017-08-26 11:41:40,664 INFO NoticeServiceApplication:664 -- The following profiles are active: test
2017-08-26 11:43:38,224 INFO NoticeServiceApplication:664 -- The following profiles are active: test
2017-08-26 11:47:49,141 INFO NoticeServiceApplication:664 -- The following profiles are active: test
2017-08-26 11:51:02,525 INFO NoticeServiceApplication:664 -- The following profiles are active: test
2017-08-26 11:52:28,726 INFO NoticeServiceApplication:664 -- The following profiles are active: test
2017-08-26 11:53:55,301 INFO NoticeServiceApplication:664 -- The following profiles are active: test
2017-08-26 11:54:26,717 INFO NoticeServiceApplication:664 -- The following profiles are active: test
2017-08-26 11:58:48,834 INFO NoticeServiceApplication:664 -- The following profiles are active: test
2017-08-26 12:38:51,126 INFO NoticeServiceApplication:664 -- The following profiles are active: test
2017-08-26 12:38:58,683 INFO RedisClusterConfig:107 -- //// --- 启动单点Redis ---
2017-08-26 12:39:00,325 DEBUG ApplicationContextRegister:26 -- ApplicationContextRegister.setApplicationContext:applicationContextorg.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@5f150435: startup date [Tue Dec 26 12:38:51 CST 2017]; parent: org.springframework.context.annotation.AnnotationConfigApplicationContext@63c12fb0
2017-08-26 12:39:06,961 INFO NoticeServiceApplication:57 -- Started NoticeServiceApplication in 17.667 seconds (JVM running for 18.377)  
```

- 容器创建

配置文件filebeat.yml
```yaml
filebeat.inputs:
- type: log
  paths:
    - /home/logs/customer/*.log
  multiline:
    pattern: ^\d{4}
    negate: true
    match: after
  fields:
    doc_type: customer
- paths:
    - /home/logs/order/*.log
  multiline:
    pattern: ^\d{4}
    negate: true
    match: after
  fields:
    doc_type: order
output.logstash:
  hosts: ["172.98.0.34:5043"]
```

```bash
docker run --name filebeat --restart always -d  \
    --network=elk-network --ip=172.98.0.35  \
    -v /home/minstrel/elk/filebeat/cfg/filebeat.yml:/usr/share/filebeat/filebeat.yml  \
    -v /home/minstrel/logs/:/home/logs/  \
    filebeat
```