# 开源框架源码学习

每个框架独立管理，不同版本分别保存；官方源码与运行示例分开维护。

| 框架 | 版本 | 模块 | 示例 |
| --- | --- | --- | --- |
| MyBatis | 3.6.0 | [模块说明](frameworks/mybatis/3.6.0/README.md) | [MySQL 最小查询](frameworks/mybatis/3.6.0/examples/first-query/README.md) |

所有 Maven 模块使用 **JDK 21**。根项目负责聚合，框架版本模块下分别管理官方源码和示例。

## Windows 本地启动

1. 在 IDE 中打开 `E:\advance-source-code-learn\pom.xml`，将项目 SDK、Maven 导入和运行 JDK 均设置为 21，然后重新加载 Maven 项目。
2. 直接在根目录 `env.conf` 中填写 `MYSQL_URL`、`MYSQL_USER`、`MYSQL_PASSWORD`。本机文件不存在时新建这一个文件即可，格式见示例运行说明。`env.conf` 已被 Git 忽略。
3. 在你自己的 MySQL 中创建 `mybatis_learning` 学习数据库，并执行示例的 `database/init.sql`。
4. 直接运行 `FirstQueryApplication.main`，使用 `mybatis-first-query` 模块的类路径。工作目录设为 `E:\advance-source-code-learn` 或该目录下的子模块。

预期输出：`查询结果：id=1, name=advance`。

命令行构建（在根目录 PowerShell 执行；`JAVA_HOME` 必须指向 JDK 21）：

```powershell
.\mvnw.cmd -pl :mybatis-first-query -am -Dmaven.test.skip=true install
```

构建命令跳过上游测试。示例不需要 Docker、Compose 或运行脚本。
