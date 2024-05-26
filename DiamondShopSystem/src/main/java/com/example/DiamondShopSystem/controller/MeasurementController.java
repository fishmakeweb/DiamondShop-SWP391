package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.service.MeasurementService;
import com.example.DiamondShopSystem.model.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/measurements")
@CrossOrigin(origins = "http://localhost:3000")
public class MeasurementController {
    @Autowired
    private MeasurementService measurementService;

    @GetMapping
    public List<Measurement> getAllMeasurements() {
        return measurementService.findAllMeasurements();
    }

    @GetMapping("/{id}")
    public Measurement getMeasurementById(@PathVariable Long id) {
        return measurementService.findMeasurementById(id);
    }

    @PostMapping
    public Measurement createMeasurement(@RequestBody Measurement measurement) {
        return measurementService.saveMeasurement(measurement);
    }

    @PutMapping("/{id}")
    public Measurement updateMeasurement(@PathVariable Long id, @RequestBody Measurement measurement) {
        return measurementService.updateMeasurement(id, measurement);
    }

    @DeleteMapping("/{id}")
    public void deleteMeasurement(@PathVariable Long id) {
        measurementService.deleteMeasurement(id);
    }
}
