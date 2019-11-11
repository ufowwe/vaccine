package com.vaccine.vaccineapi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 宝宝
 * </p>
 *
 * @author admin
 * @since 2019-11-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("v_baby")
@ApiModel(value="Baby对象", description="宝宝")
public class Baby implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "置顶状态，0：未置顶；1：置顶")
    private Integer topStatus;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别，0：未知，1：男；2：女")
    private Integer sex;

    @ApiModelProperty(value = "预产期")
    private LocalDateTime edc;

    @ApiModelProperty(value = "分娩方式")
    private Integer deliveryMode;

    @ApiModelProperty(value = "出生日期")
    private LocalDateTime birthday;

    @ApiModelProperty(value = "出生体重")
    private BigDecimal birthWeight;

    @ApiModelProperty(value = "出生身高")
    private BigDecimal birthHeight;

    @ApiModelProperty(value = "出生头围")
    private BigDecimal birthHc;

    @ApiModelProperty(value = "监护人，user_id")
    private Long primaryGuardian;

    @ApiModelProperty(value = "所在地")
    private String homeAddress;

    @ApiModelProperty(value = "接种点")
    private String vaccineAddress;

    @ApiModelProperty(value = "接种单位")
    private Long vaccineStationId;

    @ApiModelProperty(value = "已选疫苗，对应一系列疫苗表vaccine_id")
    private String selectedVaccines;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "创建人")
    private Long createUser;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateDate;

    @ApiModelProperty(value = "修改人")
    private Long updateUser;


}
