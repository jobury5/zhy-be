# 使用官方的 JDK 17 运行时作为基础镜像
FROM registry.cn-hangzhou.aliyuncs.com/library/openjdk:17-jdk-alpine

# 设置国内镜像源(中国科技大学镜像源)，修改容器时区(alpine镜像需安装tzdata来设置时区)，安装字体库(验证码)
RUN echo "https://mirrors.ustc.edu.cn/alpine/v3.7/main/" > /etc/apk/repositories && \
    apk --no-cache add tzdata && \
    cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    echo "Asia/Shanghai" > /etc/timezone && \
    apk --no-cache add ttf-dejavu fontconfig

# /tmp 目录作为容器数据卷目录，SpringBoot内嵌Tomcat容器默认使用/tmp作为工作目录，任何向 /tmp 中写入的信息不会记录进容器存储层，从而保证容器存储层的无状态化
# 在宿主机的/var/lib/docker目录下创建一个临时文件并把它链接到容器中的/tmp目录
VOLUME /tmp

# 设置工作目录
WORKDIR /app

# 将构建好的 JAR 文件复制到容器中
COPY target/zhy-web.jar /app/zhy-web.jar

# 暴露应用的端口
EXPOSE 8000

# 设置环境变量
ENV SPRING_PROFILES_ACTIVE=dev

# 启动应用
ENTRYPOINT ["java", "-jar", "zhy-web.jar"]