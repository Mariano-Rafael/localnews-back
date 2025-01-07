package com.localnews.localnews.repositories.newsRepositories;

import com.localnews.localnews.models.newsModels.NewsModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<NewsModel, Long> {

    Page<NewsModel> findByTitleOrContentIgnoreCaseContaining(String title, String content, Pageable pageable);
}
