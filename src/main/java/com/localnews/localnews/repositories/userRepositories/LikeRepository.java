package com.localnews.localnews.repositories.userRepositories;

import com.localnews.localnews.models.userModels.LikeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikeModel, Long> {


    @Query("SELECT COUNT(l) FROM LikeModel l WHERE l.newsModel.id = :newsModelId")
    int countByNewsId(@Param("newsModelId") Long newsModelId);
}
