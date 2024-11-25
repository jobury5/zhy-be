#!/bin/bash

# 定义 JAR 文件路径
JAR_FILE="zhy-gateway.jar"

# 定义日志文件路径
LOG_FILE="app.log"

# 检查 JAR 文件是否存在
if [ ! -f "$JAR_FILE" ]; then
  echo "JAR file not found: $JAR_FILE"
  exit 1
fi

# 启动 Spring Boot 应用程序
nohup java -jar $JAR_FILE > $LOG_FILE 2>&1 &

# 输出启动信息
echo "Application started with PID $!"