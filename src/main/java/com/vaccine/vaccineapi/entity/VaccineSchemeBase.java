package com.vaccine.vaccineapi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 疫苗基础方案
 * </p>
 *
 * @author admin
 * @since 2019-11-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("v_vaccine_scheme_base")
@ApiModel(value="VaccineSchemeBase对象", description="疫苗基础方案")
public class VaccineSchemeBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "疫苗明细ID")
    private Long vaccineDetailId;

    @ApiModelProperty(value = "剂次ID")
    private Long vaccineTimesId;

    @ApiModelProperty(value = "接种状态，0：未选择；1：已选未接种；2：已选同效苗；3：已种同效苗；4：已部分接种；5：已完成接种；6：遗漏接种")
    private Integer status;

    @ApiModelProperty(value = "方案类型，1：国家免费方案；2：常规推荐方案；3：最优推荐方案；4：自定义方案")
    private Integer schemeType;


}
