# 第一个示例：MySQL 主键查询

目标：调用 `UserMapper.selectById(1L)`，从 MySQL 读取用户并输出 `查询结果：id=1, name=advance`。
仅包含一个启动类、一个 Mapper 接口、一个用户对象和一份 MyBatis XML 配置。

## 一条命令运行

依赖 JDK 21、Docker Compose v2、Bash 和 OpenSSL。在仓库根目录执行：

```bash
./frameworks/mybatis/3.6.0/examples/first-query/run.sh
```

脚本生成被 Git 忽略的 `.env`，启动 MySQL 8.4（仅绑定 `127.0.0.1:13306`），等待查询健康检查通过，构建源码和示例，再调用启动类。MySQL 数据保存在独立命名卷中。重复运行会复用密码、容器和数据。

运行时明确把 `source/target/mybatis-3.6.0.jar` 放入类路径，并排除依赖列表里的其他 MyBatis JAR。不会用远程发布包替代本地构建产物。
构建使用 `-Dmaven.test.skip=true`：本次只验证源码构建与真实 MySQL 查询，不执行上游完整测试集。

若需要使用已安装的 Maven，可设置 `MVN=/绝对路径/mvn`。代理等本机参数通过 `MAVEN_SETTINGS=/绝对路径/settings.xml` 提供，不写入仓库。

## IDE 启动

1. 打开仓库根 `pom.xml`，使用 JDK 21 导入所有 Maven 模块。
2. 首次执行上述脚本，完成数据库初始化。
3. 打开 `FirstQueryApplication`，在运行配置中设置 `MYSQL_PASSWORD`，值取自本机 `.env`，不要将密码提交到 Git。
4. 直接运行 `main`；可选环境变量 `MYSQL_URL`、`MYSQL_USER` 用于覆盖连接参数。

IDE 运行配置的 classpath 使用 `mybatis-first-query` 模块，MyBatis 依赖应关联同一聚合工程内的 `source` 模块。

## 使用自己的 MySQL

自行创建独立的 `mybatis_learning` 学习数据库和用户，在该库执行 `database/init.sql`，然后在 IDE 中设置：

- `MYSQL_URL`：你的 JDBC 连接地址。
- `MYSQL_USER`：具备查询权限的学习用户。
- `MYSQL_PASSWORD`：该用户密码。

直接运行 `FirstQueryApplication.main`。`run.sh` 专门用于本地容器验证，会使用其自身连接配置。

## 停止数据库

在仓库根目录执行（保留数据）：

```bash
docker compose -f frameworks/mybatis/3.6.0/examples/first-query/compose.yaml down
```

初始化 SQL 只在新数据卷首次启动时自动执行。已有数据卷的密码不会因修改 `.env` 自动更新；请保留与其匹配的本机配置。
