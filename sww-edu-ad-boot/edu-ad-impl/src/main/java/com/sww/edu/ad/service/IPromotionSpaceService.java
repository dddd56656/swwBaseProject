package com.sww.edu.ad.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sww.edu.ad.entity.PromotionSpace;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sww
 * @since 2024-06-18
 */

public interface IPromotionSpaceService extends IService<PromotionSpace> {

    PromotionSpace getBySpaceKey(String spaceKey);

}
