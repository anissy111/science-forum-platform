# Science Forum Platform Backend

Java Spring Boot 后端应用程序

## 项目结构

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/scienceforum/
│   │   │   ├── ScienceForumApplication.java      # 主应用入口
│   │   │   ├── config/                           # 配置类
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   ├── JwtConfig.java
│   │   │   │   └── CorsConfig.java
│   │   │   ├── controller/                       # REST 控制器
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── UserController.java
│   │   │   │   ├── PostController.java
│   │   │   │   ├── CommentController.java
│   │   │   │   └── NotificationController.java
│   │   │   ├── service/                          # 业务逻辑
│   │   │   │   ├── UserService.java
│   │   │   │   ├── PostService.java
│   │   │   │   ├── CommentService.java
│   │   │   │   ├── AuthService.java
│   │   │   │   └── NotificationService.java
│   │   │   ├── repository/                       # 数据访问层
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── PostRepository.java
│   │   │   │   ├── CommentRepository.java
│   │   │   │   └── NotificationRepository.java
│   │   │   ├── model/                            # 数据模型/实体
│   │   │   │   ├── entity/
│   │   │   │   │   ├── User.java
│   │   │   │   │   ├── Post.java
│   │   │   │   │   ├── Comment.java
│   │   │   │   │   └── Notification.java
│   │   │   │   ├── dto/                          # 数据传输对象
│   │   │   │   │   ├── UserDTO.java
│   │   │   │   │   ├── PostDTO.java
│   │   │   │   │   └── CommentDTO.java
│   │   │   │   └── request/                      # 请求对象
│   │   │   │       ├── LoginRequest.java
│   │   │   │       └── RegisterRequest.java
│   │   │   ├── security/                         # 安全相关
│   │   │   │   ├── JwtTokenProvider.java
│   │   │   │   ├── JwtAuthenticationFilter.java
│   │   │   │   └── CustomUserDetailsService.java
│   │   │   ├── exception/                        # 异常处理
│   │   │   │   ├── ApiException.java
│   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── util/                             # 工具类
│   │   │   │   ├── DateUtil.java
│   │   │   │   ├── ValidationUtil.java
│   │   │   │   └── StringUtil.java
│   │   │   └── websocket/                        # WebSocket 处理
│   │   │       ├── WebSocketConfig.java
│   │   │       └── NotificationWebSocketHandler.java
│   │   └── resources/
│   │       ├── application.properties             # 主配置文件
│   │       ├── application-dev.properties         # 开发环境配置
│   │       ├── application-prod.properties        # 生产环境配置
│   │       └── logback-spring.xml                 # 日志配置
│   └── test/
│       └── java/com/scienceforum/
│           ├── controller/
│           ├── service/
│           └── repository/
├── pom.xml                                        # Maven 配置
└── Dockerfile                                     # Docker 配置
```

## 快速开始

### 1. 构建项目
```bash
cd backend
mvn clean install
```

### 2. 运行应用
```bash
mvn spring-boot:run
```

应用将在 `http://localhost:8081` 启动

### 3. 查看 API 文档
访问 `http://localhost:8081/swagger-ui.html`

## 关键依赖

- **Spring Boot 3.1.5** - Web 框架
- **Spring Data JPA** - 数据库操作
- **Spring Security** - 身份验证和授权
- **JWT** - Token 认证
- **MySQL** - 数据库
- **Lombok** - 减少代码

更多详情见 `pom.xml`