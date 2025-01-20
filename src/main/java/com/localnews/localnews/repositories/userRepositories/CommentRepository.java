package com.localnews.localnews.repositories.userRepositories;

import com.localnews.localnews.models.userModels.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentModel, Long> {
    List<CommentModel> findByPollModel_IdOrderByCreatedAtAsc(Long pollId);
}
