package com.vaccine.vaccineapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vaccine.vaccineapi.controller.vo.baby.BabyInfoRes;
import com.vaccine.vaccineapi.domain.BabyInfoDTO;
import com.vaccine.vaccineapi.entity.Baby;
import com.vaccine.vaccineapi.entity.User;
import com.vaccine.vaccineapi.entity.UserBaby;
import com.vaccine.vaccineapi.exception.BusinessException;
import com.vaccine.vaccineapi.mapper.BabyMapper;
import com.vaccine.vaccineapi.service.IBabyService;
import com.vaccine.vaccineapi.service.IUserBabyService;
import com.vaccine.vaccineapi.service.IUserService;
import com.vaccine.vaccineapi.utils.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 宝宝表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2019-10-31
 */
@Service
public class BabyServiceImpl extends ServiceImpl<BabyMapper, Baby> implements IBabyService {

    public static final String[] nameArr = {"大宝儿", "二宝儿", "三宝儿", "四宝儿", "五宝儿", "六宝儿", "七宝儿", "八宝儿", "九宝儿", "十宝儿", };

    @Resource
    private IUserService userService;

    @Resource
    private IUserBabyService userBabyService;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean saveBaby(Baby bean) {
        Long userId = userService.getUserId();
        //如果宝宝名为空，则查询已存在几个宝宝，从固定名称数组中获取默认名称
        if (StringUtils.isBlank(bean.getNickname())) {
            QueryWrapper<UserBaby> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(UserBaby::getUserId, userId);
            Integer integer = userBabyService.getBaseMapper().selectCount(queryWrapper);
            bean.setNickname(nameArr[integer]);
        }

        bean.setTopStatus(0);
        bean.setCreateUser(userId);
        bean.setCreateDate(LocalDateTime.now());
        boolean saveRs = save(bean);

        //保存用户和宝宝关系
        UserBaby userBaby = new UserBaby();
        userBaby.setUserId(userId);
        userBaby.setBabyId(bean.getId());
        //查询用户关系
        User user = userService.getById(userId);
        userBaby.setRelationship(user.getRelationship());
        boolean userBabyRs = userBabyService.save(userBaby);
        if (saveRs && userBabyRs) {
            return true;
        } else {
            throw new BusinessException("保存失败");
        }
    }

    @Override
    public boolean updateBaby(Baby bean) {
        Long userId = userService.getUserId();
        bean.setUpdateDate(LocalDateTime.now());
        bean.setUpdateUser(userId);
        return updateById(bean);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deleteBaby(Long id) {
        Long userId = userService.getUserId();
        //删除宝宝
        boolean rs = removeById(id);
        //删除关系
        QueryWrapper<UserBaby> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserBaby::getUserId, userId).eq(UserBaby::getBabyId, id);
        boolean removeRs = userBabyService.remove(queryWrapper);
        if (rs && removeRs) {
            return true;
        } else {
            throw new BusinessException("删除失败");
        }
    }

    @Override
    public boolean updateTop(Long id) {
        Long userId = userService.getUserId();
        QueryWrapper<Baby> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Baby::getCreateUser, userId);
        Baby entity = new Baby();
        entity.setTopStatus(0);
        boolean updateRs = update(entity, queryWrapper);
        Baby temp = new Baby();
        temp.setId(id);
        temp.setTopStatus(1);
        return updateBaby(temp);
    }

    @Override
    public List<BabyInfoRes> selectList() {
        //查询所有宝宝
        Long userId = userService.getUserId();
        QueryWrapper<Baby> query = new QueryWrapper<>();
        query.lambda().eq(Baby::getCreateUser, userId).orderByDesc(Baby::getTopStatus).orderByAsc(Baby::getBirthday);
        List<BabyInfoDTO> babyList = getBaseMapper().getBabyInfoDTO(userId);
        List<BabyInfoRes> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(babyList)) {
            BabyInfoRes temp = null;
            for (BabyInfoDTO baby : babyList) {
                temp = new BabyInfoRes();
                BeanUtil.copyProperties(baby, temp);
                //计算年龄，2岁1月5天
                temp.setAge(computeAge(baby.getBirthday()));
                list.add(temp);
            }
        }
        return list;
    }

    public static String computeAge(LocalDateTime birthdayDate) {
        //生日数据
        int birthdayYear = birthdayDate.getYear();
        int birthdayMonth = birthdayDate.getMonthValue();
        int birthdayDay = birthdayDate.getDayOfMonth();
        //当前时间数据
        LocalDateTime currDate = LocalDateTime.now();
        int currYear = currDate.getYear();
        int currMonth = currDate.getMonthValue();
        int currDay = currDate.getDayOfMonth();
        //结果数据
        int year = currYear - birthdayYear;//计算几岁
        long month = 0;
        long day = 0;
        StringBuilder timeStr = new StringBuilder();
        //计算今年生日日期
        LocalDateTime birthdayDateTemp = birthdayDate;
        LocalDateTime currDateTemp = null;
        int yearTemp = 0;
        for (int i = 0; i < 100; i++) {
            if (currYear == birthdayYear) {
                break;
            }
            birthdayDateTemp = birthdayDateTemp.plusYears(1);
            yearTemp = birthdayDateTemp.getYear();
            if (yearTemp == currYear) {
                break;
            }
        }
        //当前时间已过今年生日
        if (currDate.isAfter(birthdayDateTemp)) {
            month = currMonth - birthdayDateTemp.getMonthValue();
            birthdayDateTemp = birthdayDateTemp.plusMonths(month);
//            day = currDate.toLocalDate().toEpochDay() - birthdayDateTemp.toLocalDate().toEpochDay();
            if (currDate.isAfter(birthdayDateTemp)) {
                day= Duration.between(birthdayDateTemp, currDate).toDays();
            } else {
                day= Duration.between(currDate, birthdayDateTemp).toDays();
            }
        } else {
            birthdayDateTemp = birthdayDateTemp.minusYears(1);
            currDateTemp = currDate.minusYears(1);
            //计算几年
            if (currDateTemp.isBefore(birthdayDateTemp)) {
                //计算几个月
                month = birthdayDateTemp.toLocalDate().until(currDate.toLocalDate(), ChronoUnit.MONTHS);
                birthdayDateTemp = birthdayDateTemp.plusMonths(month);
                if (currDate.isAfter(birthdayDateTemp)) {
                    day= Duration.between(birthdayDateTemp, currDate).toDays();
                } else {
                    day= Duration.between(currDate, birthdayDateTemp).toDays();
                }
            } else {
                //计算几个月
                month = birthdayDateTemp.toLocalDate().until(currDate.toLocalDate(), ChronoUnit.MONTHS);
                birthdayDateTemp = birthdayDateTemp.plusMonths(month);
                if (currDate.isAfter(birthdayDateTemp)) {
                    day= Duration.between(birthdayDateTemp, currDate).toDays();
                } else {
                    day= Duration.between(currDate, birthdayDateTemp).toDays();
                }
            }
        }
        timeStr.append(year).append("岁").append(month).append("月").append(day).append("天");
        return timeStr.toString();
    }

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.getYear());
        System.out.println(localDateTime.getMonthValue());
        System.out.println(localDateTime.getDayOfMonth());

        System.out.println(LocalDate.now().toEpochDay() - LocalDate.now().minusDays(5).toEpochDay());
        System.out.println(LocalDate.now().toEpochDay());
        System.out.println(LocalDate.now().minusDays(5).toEpochDay());

        System.out.println(LocalDate.now().until(LocalDate.now().minusDays(5), ChronoUnit.MONTHS));

        System.out.println("=======");
        LocalDateTime tempDate = LocalDateTime.of(2017, 12, 8, 0, 0, 0);
        String s = computeAge(tempDate);
        System.out.println(s);
        System.out.println("=======");
        LocalDateTime tempDate1 = LocalDateTime.of(2017, 2, 28, 0, 0, 0);
        System.out.println(tempDate1);
        tempDate1 = LocalDateTime.of(2018, 2, 28, 0, 0, 0);
        System.out.println(tempDate1);
        tempDate1 = LocalDateTime.of(2019, 2, 28, 0, 0, 0);
        System.out.println(tempDate1);
        tempDate1 = LocalDateTime.of(2020, 2, 29, 0, 0, 0);
        System.out.println(tempDate1);
        System.out.println(tempDate1.minusYears(1));
        System.out.println("=======11111");
        int days= (int) Duration.between(tempDate1.minusYears(1),tempDate1).toDays();
        int days1= (int) Duration.between(tempDate1,tempDate1.minusYears(1)).toDays();
        System.out.println(days + "====" + days1);
        LocalDateTime tempDate2 = LocalDateTime.of(2017, 2, 28, 0, 0, 0);

        System.out.println(localDateTime.isAfter(tempDate2));
    }

}
