# 使用官方的 JDK 17 运行时作为基础镜像
FROM registry.cn-hangzhou.aliyuncs.com/library/openjdk:17-jdk-alpine

# 设置工作目录
WORKDIR /app

# 将构建好的 JAR 文件复制到容器中
COPY target/zhy-gateway.jar /app/zhy-gateway.jar

# 暴露应用的端口
EXPOSE 9000

# 设置环境变量
ENV SPRING_PROFILES_ACTIVE=dev

# 启动应用
ENTRYPOINT ["java", "-jar", "zhy-gateway.jar"]