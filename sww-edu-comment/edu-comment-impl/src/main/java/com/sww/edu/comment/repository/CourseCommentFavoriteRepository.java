package com.sww.edu.comment.repository;

import com.sww.edu.comment.entity.CourseCommentFavorite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CourseCommentFavoriteRepository extends MongoRepository<CourseCommentFavorite, Long> {
    @Query(value = "{'$and':[{'isDel':false},{'userId':?0},{'commentId':{'$in':?1}}]}")
    List<CourseCommentFavorite> getCommentFavoriteList(Integer userId, List<String> commentIds);
}
