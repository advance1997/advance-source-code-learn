# 开源框架源码学习

每个框架独立管理，不同版本分别保存；官方源码与运行示例分开维护。

| 框架 | 版本 | 学习模块 | 第一个示例 |
| --- | --- | --- | --- |
| MyBatis | 3.6.0 | [模块说明](frameworks/mybatis/3.6.0/README.md) | [MySQL 主键查询](frameworks/mybatis/3.6.0/examples/first-query/README.md) |

当前 Maven 聚合层级：根项目 → MyBatis 版本模块 → 官方源码与示例。
后续框架可以使用自己的构建体系，不要求全部采用 Maven。

## 快速运行

准备 **JDK 21**、Docker（含 Compose v2）、Bash 和 OpenSSL，在仓库根目录执行：

```bash
./frameworks/mybatis/3.6.0/examples/first-query/run.sh
```

脚本使用 Maven Wrapper 3.10.0，首次运行需要下载依赖。它会生成仅保存在本机的数据库密码、启动 MySQL、构建本地 MyBatis 源码并执行查询。

预期输出：

```text
查询结果：id=1, name=advance
```

示例入口：`io.github.advance1997.learning.mybatis.FirstQueryApplication.main`。
源码注释、断点指南和流程复习总结在明确学习目标后另行开展。
