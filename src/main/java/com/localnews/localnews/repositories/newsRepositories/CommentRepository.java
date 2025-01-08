package com.localnews.localnews.repositories.newsRepositories;

import com.localnews.localnews.models.userModels.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentModel, Long> {
}
