package com.sww.ad.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sww.ad.entity.PromotionSpace;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author su wei wei
 * @since 2024-06-18
 */
public interface IPromotionSpaceService extends IService<PromotionSpace> {

    PromotionSpace getBySpaceKey(String spaceKey);

}
