package com.sww.edu.comment.service;

import com.sww.edu.comment.entity.CourseCommentFavorite;

import java.util.List;

public interface ICourseCommentFavoriteService {
    List<CourseCommentFavorite> getCommentFavoriteRecordList(Integer userId, List<String> parentIds);
}
