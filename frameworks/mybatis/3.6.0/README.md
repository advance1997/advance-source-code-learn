# MyBatis 3.6.0 学习模块

- `source/`：完整官方源码，保留官方父 POM、模块结构与许可证；主代码和测试编译版本适配为 JDK 21。
- `examples/first-query/`：直接运行 main 的最小 MySQL 查询示例。
- `pom.xml`：聚合源码与示例，不覆盖官方父 POM。
- `UPSTREAM.md`：记录源码标签、提交及本地构建差异。

所有模块统一使用 JDK 21；在 IDE 中打开仓库根 `pom.xml`，将项目 SDK、Maven 导入和运行 JDK 均设为 21。
根 `.mvn/maven.config` 关闭上游自动格式化、许可证头改写和 Git hook 安装。请从仓库根执行构建。

[运行说明](examples/first-query/README.md) · [官方源码来源](UPSTREAM.md)
