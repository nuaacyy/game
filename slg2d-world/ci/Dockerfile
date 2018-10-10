FROM sgrio/java-oracle:jdk_8

RUN cd /; mkdir -p data/app;

# 开放对外的端口
EXPOSE 4454

# 开放工作目录
VOLUME ["/data/app"]

WORKDIR /data/app

# 启动服务器，注意不要在最后附加&符号
CMD sh bin/run_in_docker.sh