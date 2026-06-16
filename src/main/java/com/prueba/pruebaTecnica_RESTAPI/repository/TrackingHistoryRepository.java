package com.prueba.pruebaTecnica_RESTAPI.repository;

import com.prueba.pruebaTecnica_RESTAPI.entity.TrackingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackingHistoryRepository
        extends JpaRepository<TrackingHistory, Long> {
}