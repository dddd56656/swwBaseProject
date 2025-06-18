package com.sww.ad.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sww.ad.entity.PromotionAd;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author su wei wei
 * @since 2024-06-18
 */
public interface IPromotionAdService extends IService<PromotionAd> {

    List<PromotionAd> getByPromotionSpaceId(Integer promotionSpaceId);

}
