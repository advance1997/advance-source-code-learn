package io.github.advance1997.learning.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * MyBatis 最小查询示例，通过独立启动方法读取 MySQL 中的学习用户。
 *
 * <p>运行前需要初始化 MySQL，并在仓库根目录的 env.conf 中填写连接信息。
 * 程序从当前工作目录向上查找配置文件，不依赖 Docker 或操作系统环境变量。</p>
 *
 * @author advance
 * @since 2026-10-29 10:08:04
 */
public final class FirstQueryApplication {

    /**
     * 本次示例查询的用户主键，与初始化脚本中的记录保持一致。
     */
    private static final long SAMPLE_USER_ID = 1L;

    /**
     * 隐藏构造方法，启动类只通过静态入口运行，无需创建实例。
     */
    private FirstQueryApplication() {
    }

    /**
     * 加载配置、创建会话并执行一次 Mapper 查询；退出作用域时自动关闭会话。
     *
     * @param args 命令行参数，本示例不使用
     * @throws IOException 本机配置或 MyBatis 配置无法读取时抛出
     * @throws IllegalStateException 必填连接配置缺失或初始化数据不存在时抛出
     */
    public static void main(String[] args) throws IOException {
        Properties properties = createDatabaseProperties();

        SqlSessionFactory sessionFactory;
        try (Reader reader = Resources.getResourceAsReader("mybatis-config.xml")) {
            sessionFactory = new SqlSessionFactoryBuilder().build(reader, properties);
        }

        try (SqlSession session = sessionFactory.openSession()) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            LearningUser user = userMapper.selectById(SAMPLE_USER_ID);

            if (user == null) {
                throw new IllegalStateException("未找到示例用户，请先执行数据库初始化脚本");
            }

            System.out.printf("查询结果：id=%d, name=%s%n", user.getId(), user.getName());
        }
    }

    /**
     * 从当前目录向上查找 env.conf，并以 UTF-8 读取数据库连接参数。
     *
     * <p>支持从仓库根目录或其子模块启动，也兼容带 BOM 的 UTF-8 文件。
     * 只校验变量是否已填写，不在异常或日志中输出账号密码。</p>
     *
     * @return 供 MyBatis XML 直接引用的本机配置变量
     * @throws IOException 配置文件不存在或无法读取时抛出
     * @throws IllegalStateException 必填变量缺失或为空白时抛出
     */
    private static Properties createDatabaseProperties() throws IOException {
        Path directory = Path.of("").toAbsolutePath();
        while (directory != null && !Files.isRegularFile(directory.resolve("env.conf"))) {
            directory = directory.getParent();
        }
        if (directory == null) {
            throw new IOException("未找到 env.conf，请在仓库根目录创建该文件，并从仓库或其子目录启动");
        }

        String content = Files.readString(directory.resolve("env.conf"), StandardCharsets.UTF_8);
        if (content.startsWith("\uFEFF")) {
            content = content.substring(1);
        }

        Properties properties = new Properties();
        try (Reader reader = new StringReader(content)) {
            properties.load(reader);
        }

        for (String key : new String[] {"MYSQL_URL", "MYSQL_USER", "MYSQL_PASSWORD"}) {
            String value = properties.getProperty(key);
            if (value == null || value.isBlank()) {
                throw new IllegalStateException("请在 env.conf 中填写变量：" + key);
            }
        }

        return properties;
    }
}
