package com.sww.common.web.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.sww.common.util.UserContextHolder;
import com.sww.common.web.entity.po.BasePo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.reflection.MetaObject;

import java.time.ZonedDateTime;
import java.util.Date;

@Slf4j
public class PoMetaObjectHandler implements MetaObjectHandler {
    /**
     * 获取当前交易的用户，为空返回默认system
     *
     * @return
     */
    private String getCurrentUsername() {
        return StringUtils.defaultIfBlank(UserContextHolder.getInstance().getUsername(), BasePo.DEFAULT_USERNAME);
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createdBy", String.class, getCurrentUsername());
        this.strictInsertFill(metaObject, "createdTime", Date.class, Date.from(ZonedDateTime.now().toInstant()));
       this.updateFill(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updatedBy", String.class, getCurrentUsername());
        this.strictUpdateFill(metaObject, "updatedTime", Date.class, Date.from(ZonedDateTime.now().toInstant()));
    }
}