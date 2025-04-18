package com.localnews.localnews.repositories.pollRepositories;

import com.localnews.localnews.models.PollModels.PollModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PollRepository extends JpaRepository<PollModel, Long> {

}
