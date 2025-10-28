package com.bip.sistema.controller;

import com.bip.sistema.model.ConsumptionRecord;
import com.bip.sistema.service.ConsumptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumptions")
public class ConsumptionController {

    private final ConsumptionService consumptionService;

    public ConsumptionController(ConsumptionService consumptionService) {
        this.consumptionService = consumptionService;
    }

    @GetMapping
    public List<ConsumptionRecord> getAll() {
        return consumptionService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsumptionRecord> getById(@PathVariable Long id) {
        ConsumptionRecord record = consumptionService.findById(id);
        if (record == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(record);
    }

    @PostMapping
    public ResponseEntity<ConsumptionRecord> recordConsumption(
            @RequestParam Long userId,
            @RequestParam Long itemId,
            @RequestParam(required = false) Integer quantity
    ) {
        if (quantity == null) quantity = 1;
        ConsumptionRecord record = consumptionService.recordConsumption(userId, itemId, quantity);
        return ResponseEntity.ok(record);
    }
}
