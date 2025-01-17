package com.localnews.localnews.services.busServices;

import com.localnews.localnews.models.busModels.BusModel;
import com.localnews.localnews.repositories.busRepositories.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    public BusModel createBus(BusModel busModel) {
        return busRepository.save(busModel);
    }

    public Optional<BusModel> getBusById(String route) {
        return busRepository.findByRoute(route);
    }

    public List<BusModel> getBusByRouteAndDayOfWeek(String route, String dayOfWeek) {
        return busRepository.findByRouteAndDayOfWeek(route, dayOfWeek);
    }
}
