package com.vaccine.vaccineapi.controller;


import com.vaccine.vaccineapi.controller.vo.baby.*;
import com.vaccine.vaccineapi.domain.BaseResponse;
import com.vaccine.vaccineapi.entity.Baby;
import com.vaccine.vaccineapi.service.IBabyService;
import com.vaccine.vaccineapi.utils.BeanUtil;
import com.vaccine.vaccineapi.utils.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 宝宝 前端控制器
 * </p>
 *
 * @author admin
 * @since 2019-10-31
 */
@Slf4j
@Api(tags = {"宝宝"})
@RestController
@RequestMapping("/api/baby")
public class BabyController {

    @Resource
    private IBabyService service;

    @ApiOperation("新增")
    @PostMapping("/save")
    public BaseResponse save(@Valid @RequestBody SaveBabyReq req) {
        Baby baby = new Baby();
        BeanUtil.copyProperties(req, baby);
        baby.setBirthday(DateUtil.dateToLocalDateTime(req.getBirthday()));
        boolean rs = service.saveBaby(baby);
        if (rs) {
            return BaseResponse.success("保存成功");
        } else {
            return BaseResponse.failed("保存失败");
        }
    }

    @ApiOperation("更新")
    @PostMapping("/update")
    public BaseResponse update(@Valid @RequestBody UpdateBabyReq req) {
        Baby baby = new Baby();
        BeanUtil.copyProperties(req, baby);
        baby.setBirthday(DateUtil.dateToLocalDateTime(req.getBirthday()));
        boolean rs = service.updateBaby(baby);
        if (rs) {
            return BaseResponse.success("保存成功");
        } else {
            return BaseResponse.failed("保存失败");
        }
    }

    @ApiOperation("删除")
    @PostMapping("/delete")
    public BaseResponse delete(@Valid @RequestBody DeleteBabyReq req) {
        boolean rs = service.removeById(req.getId());
        if (rs) {
            return BaseResponse.success("删除成功");
        } else {
            return BaseResponse.failed("删除失败");
        }
    }

    @ApiOperation("更新置顶状态")
    @PostMapping("/updateTop")
    public BaseResponse updateTop(@Valid @RequestBody UpdateTopReq req) {
        boolean rs = service.updateTop(req.getId());
        if (rs) {
            return BaseResponse.success("更新成功");
        } else {
            return BaseResponse.failed("更新失败");
        }
    }

    @ApiOperation("宝宝集合")
    @PostMapping("/selectList")
    public BaseResponse selectList() {
        List<BabyInfoRes> babyInfoList = service.selectList();
        return BaseResponse.success("查询成功", babyInfoList);
    }

}
