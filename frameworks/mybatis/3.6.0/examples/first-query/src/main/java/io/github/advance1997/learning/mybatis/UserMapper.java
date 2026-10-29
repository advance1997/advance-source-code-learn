package io.github.advance1997.learning.mybatis;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 学习用户查询接口，由 MyBatis 创建实现代理并完成 SQL 执行和结果映射。
 *
 * @author advance
 * @since 2026-10-29 10:08:04
 */
public interface UserMapper {

    /**
     * 根据主键查询用户，使用参数占位符绑定主键值。
     *
     * @param id 用户主键
     * @return 匹配的用户；不存在时返回 {@code null}
     */
    @Select("SELECT id, name FROM learning_user WHERE id = #{id}")
    LearningUser selectById(@Param("id") Long id);
}
