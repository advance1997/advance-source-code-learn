package io.github.advance1997.learning.mybatis;

/**
 * 学习用户数据对象，属性分别对应 learning_user 表中的 id 和 name 字段。
 *
 * @author advance
 * @since 2026-10-29 10:08:04
 */
public class LearningUser {

    /**
     * 用户主键，由数据库结果集映射赋值。
     */
    private Long id;

    /**
     * 用户名称。
     */
    private String name;

    /**
     * 创建空用户对象，供 MyBatis 实例化后填充查询结果。
     */
    public LearningUser() {
    }

    /**
     * 获取用户主键。
     *
     * @return 用户主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置用户主键。
     *
     * @param id 用户主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户名称。
     *
     * @return 用户名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户名称。
     *
     * @param name 用户名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 返回用户对象的可读表示，便于检查数据库查询后的字段映射结果。
     *
     * @return 包含用户主键和名称的字符串
     */
    @Override
    public String toString() {
        return "LearningUser{id=" + id + ", name='" + name + "'}";
    }
}
