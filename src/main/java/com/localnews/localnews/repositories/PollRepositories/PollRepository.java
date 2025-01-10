package com.localnews.localnews.repositories.PollRepositories;

import com.localnews.localnews.models.PollModels.PollModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends JpaRepository<PollModel, Long> {
}
