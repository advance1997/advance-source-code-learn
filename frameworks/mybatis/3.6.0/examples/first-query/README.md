# 最小示例：查询 MySQL 用户

入口：`io.github.advance1997.learning.mybatis.FirstQueryApplication.main`。
示例只有启动类、Mapper 接口、用户对象和 MyBatis XML 配置，不使用 Spring、Docker 或运行脚本。

## 本机配置

直接编辑仓库根目录的 `env.conf`，由你手动填写：

| 变量名 | 用途 |
| --- | --- |
| `MYSQL_URL` | JDBC 地址，例如 `jdbc:mysql://127.0.0.1:3306/mybatis_learning?connectionTimeZone=UTC` |
| `MYSQL_USER` | MySQL 用户名 |
| `MYSQL_PASSWORD` | MySQL 密码 |

`env.conf` 使用 UTF-8 的 Java Properties 格式（支持 UTF-8 BOM），每行 `变量名=变量值`，不要加引号，反斜杠写为 `\\`。不支持 shell 的 `export` 或变量插值。值不要加无意义的前后空格。

程序从当前工作目录向上查找 `env.conf`，读取后传给 MyBatis；XML 直接通过 `${MYSQL_URL}`、`${MYSQL_USER}`、`${MYSQL_PASSWORD}` 引用配置。不读取旧 `.env` 或系统中的同名环境变量。
`env.conf` 不打包进 JAR，也不提交到 Git；不提供模板文件。由于该文件被 Git 忽略，首次克隆后需在本机根目录新建一次：

```properties
MYSQL_URL=
MYSQL_USER=
MYSQL_PASSWORD=
```

后续框架可在同一文件增加独立命名的变量。

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

## IDE 无法解析 SuppressModernizer

该注解来自 `org.gaul:modernizer-maven-annotations:3.5.0`，不是 JDK 自带的类型。
源码模块已经直接声明这个编译依赖，不要手写同名注解或删除源码上的注解。

更新代码后，从仓库根 `pom.xml` 重新加载所有 Maven 项目，确认 IDE 的 `source` 模块依赖中存在该 JAR。
如果仍然报错，在根目录 PowerShell 执行：

```powershell
.\mvnw.cmd -U -pl :mybatis-first-query -am -Dmaven.test.skip=true clean install
```

若命令行构建成功而 IDE 仍标红，请核对 IDE 的 Maven 本地仓库、settings.xml 和导入模块是否与命令行一致。
若依赖下载失败，请依据 Maven 的具体错误检查镜像或代理设置；仅更换 JDK 不会补齐缺失的第三方依赖。
