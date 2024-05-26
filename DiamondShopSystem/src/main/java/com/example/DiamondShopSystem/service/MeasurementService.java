package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.Measurement;
import com.example.DiamondShopSystem.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {
    @Autowired
    private MeasurementRepository measurementRepository;

    public List<Measurement> findAllMeasurements() {
        return measurementRepository.findAll();
    }

    public Measurement findMeasurementById(Long id) {
        Optional<Measurement> measurement = measurementRepository.findById(id);
        return measurement.orElse(null);
    }

    public Measurement saveMeasurement(Measurement measurement) {
        return measurementRepository.save(measurement);
    }

    public Measurement updateMeasurement(Long id, Measurement newMeasurement) {
        return measurementRepository.findById(id)
                .map(existingMeasurement -> {
                    existingMeasurement.setLength(newMeasurement.getLength());
                    existingMeasurement.setWidth(newMeasurement.getWidth());
                    existingMeasurement.setHeight(newMeasurement.getHeight());
                    return measurementRepository.save(existingMeasurement);
                }).orElseGet(() -> {
                    newMeasurement.setMeasurementId(id);
                    return measurementRepository.save(newMeasurement);
                });
    }

    public void deleteMeasurement(Long id) {
        measurementRepository.deleteById(id);
    }
}
