package com.yuewang.rbac.model.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName DataPageVO
 * @Description
 * @Author Yue Wang
 * @Date 2023/5/14 16:06
 **/
@Data
public class DataPageVO {

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

}
