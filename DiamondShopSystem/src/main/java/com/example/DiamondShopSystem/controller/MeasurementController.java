package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.Measurement;
import com.example.DiamondShopSystem.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MeasurementController {
    @Autowired
    private MeasurementService measurementService;

    @GetMapping("/adminsale/measurements")
    public List<Measurement> getAllMeasurements() {
        return measurementService.findAllMeasurements();
    }

    @GetMapping("/adminsale/measurements/{id}")
    public Measurement getMeasurementById(@PathVariable Long id) {
        return measurementService.findMeasurementById(id);
    }

    @PostMapping("/admin/measurements")
    public Measurement createMeasurement(@RequestBody Measurement measurement) {
        return measurementService.saveMeasurement(measurement);
    }

    @PutMapping("/admin/measurements/{id}")
    public Measurement updateMeasurement(@PathVariable Long id, @RequestBody Measurement measurement) {
        return measurementService.updateMeasurement(id, measurement);
    }

    @DeleteMapping("/admin/measurements/{id}")
    public void deleteMeasurement(@PathVariable Long id) {
        measurementService.deleteMeasurement(id);
    }
}
