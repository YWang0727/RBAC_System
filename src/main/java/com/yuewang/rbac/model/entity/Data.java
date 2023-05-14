package com.yuewang.rbac.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
 * @TableName data
 */
@TableName(value ="data")
@lombok.Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Data extends BaseEntity {
    /**
     * data ID, unique
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * data name, unique
     */
    @TableField(value = "name")
    private String name;

    /**
     * company ID
     */
    @TableField(value = "company_id")
    private Long companyId;

    /**
     * createdTime
     */
    @TableField(value = "created_time")
    private Date createdTime;

    /**
     * updatedTime
     */
    @TableField(value = "updated_time")
    private Date updatedTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}