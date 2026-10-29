# MyBatis 3.6.0 学习模块

```text
3.6.0/
├── pom.xml                   # 聚合官方源码与学习示例
├── UPSTREAM.md               # 源码标签、提交和来源
├── source/                   # 完整官方源码，保留原有父 POM 和许可证
└── examples/first-query/      # 独立的 MySQL 最小查询示例
```

使用 JDK 21 和 Maven Wrapper 3.10.0。官方源码的 Java 目标版本保留为 11，学习示例目标版本为 17。
请在 IDE 中打开仓库根 `pom.xml`，将项目与 Maven 运行 JDK 设为 21。

根 `.mvn/maven.config` 关闭上游自动格式化、许可证头重写和 Git hook 安装，保护源码原貌；没有关闭编译检查。
推荐从仓库根运行构建，不单独进入 `source/` 执行未带这些参数的构建命令。

[运行第一个示例](examples/first-query/README.md) · [官方源码来源](UPSTREAM.md)
