package com.localnews.localnews.repositories.busRepositories;

import com.localnews.localnews.models.busModels.BusModel;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface BusRepository extends JpaRepository<BusModel, Long> {

    Optional<BusModel> findByRoute(String route);

    List<BusModel> findByRouteAndDayOfWeek(String route, String dayOfWeek);
}
