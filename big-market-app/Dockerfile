# 基础镜像
FROM openjdk:8-jre-slim

# 作者
MAINTAINER linzexu

# 配置
ENV PARAMS=""

# 添加应用
ADD target/big-market-app.jar /big-market-app.jar

ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /big-market-app-app.jar $PARAMS"]