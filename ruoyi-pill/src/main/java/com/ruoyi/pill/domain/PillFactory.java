package com.ruoyi.pill.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName PillFactory
 * @Description TODO
 * @Author JiangXiong
 * @Date 2023/6/30 17:23
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PillFactory extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 厂商Id */
    private Long factoryId;

    /** 厂商名称 */
    private String factoryName;

    /** 厂商编号 */
    private String factoryCode;

    /** 联系人 */
    private String contact;

    /** 电话 */
    private String phone;

    /** 关键字 */
    private String keyword;

    /** 状态 */
    private String status;
}
