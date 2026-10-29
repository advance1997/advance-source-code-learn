CREATE TABLE IF NOT EXISTS learning_user (
    id BIGINT UNSIGNED NOT NULL COMMENT '用户主键',
    name VARCHAR(64) NOT NULL COMMENT '用户名称',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='MyBatis 最小查询示例用户表';

INSERT INTO learning_user (id, name)
SELECT 1, 'advance'
WHERE NOT EXISTS (SELECT 1 FROM learning_user WHERE id = 1);
