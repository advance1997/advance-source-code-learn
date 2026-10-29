#!/usr/bin/env bash
set -euo pipefail

example_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
repo_dir="$(cd "$example_dir/../../../../.." && pwd)"
cd "$repo_dir"

# 仅首次运行时生成本机学习数据库密码；已有配置保持不变。
if [[ ! -f "$example_dir/.env" ]]; then
    command -v openssl >/dev/null
    (
        umask 077
        printf 'MYSQL_ROOT_PASSWORD=%s\nMYSQL_PASSWORD=%s\n' \
            "$(openssl rand -hex 24)" "$(openssl rand -hex 24)" > "$example_dir/.env"
    )
fi

# 只读取所需配置，不执行 .env 中的内容。
while IFS='=' read -r key value; do
    case "$key" in
        MYSQL_PASSWORD|MYSQL_ROOT_PASSWORD) export "$key=$value" ;;
    esac
done < "$example_dir/.env"
export MYSQL_USER=learning
export MYSQL_URL='jdbc:mysql://127.0.0.1:13306/mybatis_learning?sslMode=PREFERRED&connectionTimeZone=UTC'

chmod a+r "$example_dir/database/init.sql"
docker compose -f "$example_dir/compose.yaml" up -d --wait --wait-timeout 180

maven="${MVN:-$repo_dir/mvnw}"
maven_args=()
if [[ -n "${MAVEN_SETTINGS:-}" ]]; then
    maven_args+=(-s "$MAVEN_SETTINGS")
fi
"$maven" "${maven_args[@]}" -B -ntp -pl :mybatis-first-query -am -Dmaven.test.skip=true install
"$maven" "${maven_args[@]}" -B -ntp -f "$example_dir/pom.xml" dependency:build-classpath \
    -DexcludeArtifactIds=mybatis -Dmdep.outputFile=target/dependencies.classpath

# 明确使用 source/ 刚构建的 JAR，不通过远程发布包替代本地源码。
mybatis_jar="$repo_dir/frameworks/mybatis/3.6.0/source/target/mybatis-3.6.0.jar"
classpath="$example_dir/target/classes:$mybatis_jar:$(cat "$example_dir/target/dependencies.classpath")"
java -cp "$classpath" io.github.advance1997.learning.mybatis.FirstQueryApplication
