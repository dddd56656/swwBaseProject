package com.sww.edu.pay.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sww.edu.pay.entity.PayOrderRecord;
import com.sww.edu.pay.mapper.PayOrderRecordMapper;
import com.sww.edu.pay.service.IPayOrderRecordService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: ma wei long
 * @date:   2020年6月22日 上午12:26:35
 */
@Slf4j
@Service
public class PayOrderRecordServiceImpl extends ServiceImpl<PayOrderRecordMapper, PayOrderRecord> implements IPayOrderRecordService {
}
