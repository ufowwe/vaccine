package com.vaccine.vaccineapi.controller.vo.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 更新接种记录参数
 * </p>
 *
 * @author admin
 * @since 2019-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UpdateRecordReq对象", description="更新接种记录参数")
public class UpdateRecordReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "记录ID")
    @NotNull(message = "记录ID为空")
    private Long id;

    @ApiModelProperty(value = "接种日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date vaccinationDate;

    @ApiModelProperty(value = "实际接种日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date vaccinationDateActual;

    @ApiModelProperty(value = "接种状态，0：未选择；1：已选未接种；2：已选同效苗；3：已种同效苗；4：已部分接种；5：已完成接种；6：遗漏接种")
    private Integer vaccinationStatus;

}
