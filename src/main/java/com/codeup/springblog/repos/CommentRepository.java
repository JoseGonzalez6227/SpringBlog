package com.codeup.springblog.repos;

import com.codeup.springblog.models.Ad;
import com.codeup.springblog.models.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    //    Comment findCommentsByAd(Ad ad);

    @Query("from Comment where ad_id like %:term%")
    List<Comment> findCommentsByAd(@Param("term") long term);
}
