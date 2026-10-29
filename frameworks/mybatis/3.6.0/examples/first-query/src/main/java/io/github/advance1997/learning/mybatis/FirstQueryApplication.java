package io.github.advance1997.learning.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * MyBatis 最小查询示例，通过独立启动方法读取 MySQL 中的学习用户。
 *
 * <p>运行前需要初始化数据库，并通过环境变量 MYSQL_PASSWORD 提供数据库密码。
 * 连接地址和用户名可以分别通过 MYSQL_URL、MYSQL_USER 覆盖默认值。</p>
 *
 * @author advance
 * @since 2026-10-29 10:08:04
 */
public final class FirstQueryApplication {

    /** 本次示例查询的用户主键，与初始化脚本中的记录保持一致。 */
    private static final long SAMPLE_USER_ID = 1L;

    private FirstQueryApplication() {
    }

    /**
     * 加载配置、创建会话并执行一次 Mapper 查询；退出作用域时自动关闭会话。
     *
     * @param args 命令行参数，本示例不使用
     * @throws IOException 配置资源无法读取时抛出
     * @throws IllegalStateException 数据库密码缺失或初始化数据不存在时抛出
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
     * 读取数据库连接参数，密码只从运行环境获取，不写入源码或日志。
     *
     * @return 用于替换 MyBatis 配置占位符的数据库属性
     * @throws IllegalStateException 密码没有配置或为空白时抛出
     */
    private static Properties createDatabaseProperties() {
        String password = System.getenv("MYSQL_PASSWORD");
        if (password == null || password.isBlank()) {
            throw new IllegalStateException("请设置环境变量 MYSQL_PASSWORD");
        }

        Properties properties = new Properties();
        properties.setProperty("jdbc.url", System.getenv().getOrDefault("MYSQL_URL",
                "jdbc:mysql://127.0.0.1:13306/mybatis_learning?sslMode=PREFERRED&connectionTimeZone=UTC"));
        properties.setProperty("jdbc.username", System.getenv().getOrDefault("MYSQL_USER", "learning"));
        properties.setProperty("jdbc.password", password);
        return properties;
    }
}
