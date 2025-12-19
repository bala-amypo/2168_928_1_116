package com.example.demo.controller;

import com.example.demo.entity.DeliveryRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery-records")
public class DeliveryRecordController {

    @PostMapping
    public ResponseEntity<DeliveryRecord> logDelivery(
            @RequestBody DeliveryRecord record) {
        return ResponseEntity.ok(record);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getRecord(@PathVariable Long id) {
        return ResponseEntity.ok("Get delivery record " + id);
    }

    @GetMapping("/contract/{contractId}")
    public ResponseEntity<String> listRecords(@PathVariable Long contractId) {
        return ResponseEntity.ok(
                "List delivery records for contract " + contractId
        );
    }

    @GetMapping("/contract/{contractId}/latest")
    public ResponseEntity<String> getLatest(@PathVariable Long contractId) {
        return ResponseEntity.ok(
                "Latest delivery record for contract " + contractId
        );
    }
}
