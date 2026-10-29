# 最小示例：查询 MySQL 用户

入口：`io.github.advance1997.learning.mybatis.FirstQueryApplication.main`。
示例只有启动类、Mapper 接口、用户对象和 MyBatis XML 配置，不使用 Spring、Docker 或运行脚本。

## 本机配置

将仓库根目录的 `env.conf.example` 复制为 `env.conf`，由你手动填写：

| 变量名 | 用途 |
| --- | --- |
| `MYSQL_URL` | JDBC 地址，例如 `jdbc:mysql://127.0.0.1:3306/mybatis_learning?connectionTimeZone=UTC` |
| `MYSQL_USER` | MySQL 用户名 |
| `MYSQL_PASSWORD` | MySQL 密码 |

`env.conf` 使用 UTF-8 的 Java Properties 格式（支持 UTF-8 BOM），每行 `变量名=变量值`，不要加引号，反斜杠写为 `\\`。不支持 shell 的 `export` 或变量插值。值不要加无意义的前后空格。

程序从当前工作目录向上查找 `env.conf`，读取后传给 MyBatis；XML 直接通过 `${MYSQL_URL}`、`${MYSQL_USER}`、`${MYSQL_PASSWORD}` 引用配置。不读取旧 `.env` 或系统中的同名环境变量。
`env.conf` 不打包进 JAR，也不提交到 Git；只有不含实际值的变量模板需要版本管理。后续框架可在同一文件增加独立命名的变量。

## 准备数据库并运行

1. 使用 JDK 21，在 IDE 打开仓库根 `pom.xml`，重新加载 Maven 模块。
2. 在自己的 MySQL 中创建 `mybatis_learning` 学习数据库，并在该库执行 `database/init.sql`。脚本不会创建数据库或账号。
3. 配置根目录 `env.conf`；运行配置的工作目录设为 `E:\advance-source-code-learn`，类路径使用 `mybatis-first-query` 模块。
4. 直接运行 `FirstQueryApplication.main`。

预期结果：`查询结果：id=1, name=advance`。

IDE 应将 `org.mybatis:mybatis` 关联到当前工程的 `source` 模块。如果需要先安装本地源码，在根目录 PowerShell 执行：

```powershell
.\mvnw.cmd -pl :mybatis-first-query -am -Dmaven.test.skip=true install
```

该命令编译源码及示例，跳过上游测试集。Maven 中的语言版本不能代替本机 JDK 安装，`JAVA_HOME` 和 IDE Maven JDK 仍需使用 21。
