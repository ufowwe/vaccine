package com.vaccine.vaccineapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vaccine.vaccineapi.controller.vo.baby.BabyInfoRes;
import com.vaccine.vaccineapi.entity.Baby;

import java.util.List;

/**
 * <p>
 * 宝宝表 服务类
 * </p>
 *
 * @author admin
 * @since 2019-10-31
 */
public interface IBabyService extends IService<Baby> {

    boolean saveBaby(Baby bean);

    boolean updateBaby(Baby bean);

    boolean updateTop(Long id);

    List<BabyInfoRes> selectList();

}
