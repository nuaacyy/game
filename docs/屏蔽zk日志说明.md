## 设置org.apache.zookeeper这个包的日志级别
    <logger name="org.apache.zookeeper" level="WARN"/>
## 分析日志输出
    日志打印集中于org.apache.zookeeper.ZooKeeper和org.apache.zookeeper.ClientCnxn
    这两个类中，查看源码可看到其中的Log，因此只需要在日志配置文件logback.xml中指定
    org.apache.zookeeper这个包的日志级别即可
## 参考文章
    https://blog.csdn.net/qbw2010/article/details/72841316
    https://www.cnblogs.com/cb0327/p/5759441.html