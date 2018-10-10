## 部署最小结构
* elk-master：1台
* elk-data：1台
* kafka：3台
* kibana：1台
* kafka-connect：1台

## kafka相关
### kafka主题维护
```bash
# 主题创建
## 分区3个
## 复制2个
./kafka-topics.sh --zookeeper zookeeper:2181 --create --replication-factor 2 --partitions 3 --topic dclog1

# 主题删除
./kafka-topics.sh --zookeeper zookeeper:2181 --delete --topic dclog1

# 查询特定消费者组的相关信息
./kafka-consumer-groups.sh --bootstrap-server 127.0.0.1:9092 --describe --group connect-dclog1-sink 

# 查询所有的消费者组
./kafka-consumer-groups.sh --bootstrap-server 127.0.0.1:9092 --list

```

## kafka-connect-elasticsearch相关
### 相关操作
```bash
# 查询connector插件所在进程的信息
curl 'localhost:8083'

# 查询所有connector的信息，可能的返回["elasticsearch-sink"]
curl 'localhost:8083/connectors'

# 查询所有connector插件
curl 'localhost:8083/connector-plugins'

# 查询特定connector配置的信息
curl 'localhost:8083/connectors/elasticsearch-sink/config'

# 查询特定connector配置的状态
curl 'localhost:8083/connectors/elasticsearch-sink/status'

# 查询特定connector运行中的任务
curl 'localhost:8083/connectors/elasticsearch-sink/tasks'

# 删除指定的connector
curl -XDELETE '172.18.3.203:8083/connectors/dclog11-sink'

# 暂停、恢复、重启配置
curl -XPUT 'localhost:8083/connectors/elasticsearch-sink/pause'
curl -XPUT 'localhost:8083/connectors/elasticsearch-sink/resume'
curl -XPOST 'localhost:8083/connectors/elasticsearch-sink/restart'

# 创建配置
curl -X POST -H "Content-Type: application/json" --data '{"name": "dclog1-sink", "config": {"connector.class":"io.confluent.connect.elasticsearch.ElasticsearchSinkConnector", "tasks.max":"3", "topics":"dclog1", "topic.index.map":"dclog1:dclog1_index", "connection.url": "http://172.18.3.203:9200", "type.name": "log", "key.ignore": "true", "schema.ignore": "true" }}' http://172.18.3.203:8083/connectors
```