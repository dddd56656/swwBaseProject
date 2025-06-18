package com.sww.ad.api;

import com.sww.ad.api.dto.PromotionAdDTO;
import com.sww.ad.api.dto.PromotionSpaceDTO;
import com.sww.common.result.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * @author su wei wei
 * @version 1.0
 * @date 2024/6/18 15:17
 */
@FeignClient(name = "${remote.feign.ad.name:ad}", path = "/ad")
public interface AdRemoteService {

    @GetMapping("/getAllAds")
    default List<PromotionSpaceDTO> getAllAds(@RequestParam("spaceKeys") String[] spaceKeys) {
        return null;
    }


    //广告位的新增或者修改
    @RequestMapping(value = "/space/saveOrUpdate",method = RequestMethod.POST)
    public ResponseDTO saveOrUpdate(@RequestBody PromotionSpaceDTO promotionSpaceDTO);

    //获取单个广告位
    @RequestMapping("/space/getSpaceById")
    public PromotionSpaceDTO getPromotionSpaceById(@RequestParam("id") Integer id);

    //获取所有广告位
    @RequestMapping("/space/getAllSpaces")
    public List<PromotionSpaceDTO> getAllSpaces();


    //新增或者修改广告信息
    @RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
    public ResponseDTO saveOrUpdate(@RequestBody PromotionAdDTO promotionAdDTO);


    //根据ID获取对应的广告
    @RequestMapping("/getAdById")
    public PromotionAdDTO getAdById(@RequestParam("id") Integer id);


    //获取所有的广告
    @RequestMapping("/getAdList")
    public List<PromotionAdDTO> getAdList();

    //获取所有的广告
    @RequestMapping("/updateStatus")
    public ResponseDTO updateStatus(@RequestParam("id") Integer id,
                                    @RequestParam("status") Integer status);

}
