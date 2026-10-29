# MyBatis 官方源码来源

- 仓库：https://github.com/mybatis/mybatis-3
- 正式发布标签：`mybatis-3.6.0`
- 提交：`73a0c5d4d403efe0875c824a18717037966309ad`
- 导入方式：从该标签检出后，使用 `git archive HEAD` 导出完整受版本管理的文件。
- 最新版本核验：官方 Git 标签与 Maven Central 的 `org.mybatis:mybatis` 元数据均指向 3.6.0。
- `source/` 保留官方源码、测试、构建配置、LICENSE 和 NOTICE，不包含上游 `.git` 历史。
- 官方源码未添加学习注释。根聚合项目通过 Maven 参数跳过上游 Git hook 安装，避免影响学习仓库。

## 本地构建适配

按学习工程要求，将 source/pom.xml 的主代码和测试 Java 版本统一为 21。Modernizer 的 API 检查基线保持为官方原来的 11，避免要求重写源码以采用 Java 21 新 API；这不影响 Java 21 编译目标。其余官方源码保持原样。
