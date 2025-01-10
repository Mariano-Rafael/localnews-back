package com.localnews.localnews.repositories.PollRepositories;

import com.localnews.localnews.models.PollModels.PollOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollOptionRepository extends JpaRepository<PollOption, Long> {
}
