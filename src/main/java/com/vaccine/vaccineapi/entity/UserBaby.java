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
 * 宝宝用户关系
 * </p>
 *
 * @author admin
 * @since 2019-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("v_user_baby")
@ApiModel(value="UserBaby对象", description="宝宝用户关系")
public class UserBaby implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "宝宝ID")
    private Long babyId;

    @ApiModelProperty(value = "关系，1：父亲；2：母亲；3：爷爷；4：奶奶；5：外公；6：外婆；7：叔叔；8：婶婶；9：姑姑；10：姑父；11：舅舅；12：舅妈；13：姨姨；14：姨父；15：阿姨（保姆）")
    private Integer relationship;


}
