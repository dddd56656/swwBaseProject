package com.sww.edu.ad.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sww.edu.ad.entity.PromotionAd;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sww
 * @since 2024-06-18
 */
public interface IPromotionAdService extends IService<PromotionAd> {

    List<PromotionAd> getByPromotionSpaceId(Integer promotionSpaceId);

}
