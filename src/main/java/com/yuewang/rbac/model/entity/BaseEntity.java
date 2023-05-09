package com.yuewang.rbac.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @ClassName BaseEntity
 * @Description inherited by all other entity classes
 * @Author Yue Wang
 * @Date 2023/5/9 19:50
 **/
@Data
public abstract class BaseEntity {
    /**
     * Primary key
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
}
