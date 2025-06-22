package com.sww.edu.comment.service.impl;

import com.sww.edu.comment.entity.CourseCommentFavorite;
import com.sww.edu.comment.repository.CourseCommentFavoriteRepository;
import com.sww.edu.comment.service.ICourseCommentFavoriteService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:   mkp
 * Date:     2020/7/6 18:10
 * Description:
 */
@Service
public class ICourseCommentFavoriteServiceImpl implements ICourseCommentFavoriteService {
    @Autowired
    CourseCommentFavoriteRepository courseCommentFavoriteRepository;
    @Override
    public List<CourseCommentFavorite> getCommentFavoriteRecordList(Integer userId, List<String> commentIds) {
        if (userId == null || CollectionUtils.isEmpty(commentIds)) {
            return null;
        }

        List<CourseCommentFavorite> favoriteRecords = courseCommentFavoriteRepository.getCommentFavoriteList(userId,commentIds);
        return favoriteRecords;
    }
}
