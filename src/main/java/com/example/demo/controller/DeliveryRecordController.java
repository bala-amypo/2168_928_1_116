package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery-records")
public class DeliveryRecordController {

    // POST / - Log delivery
    @PostMapping
    public String logDelivery() {
        return "Delivery logged";
    }

    // GET /{id} - Get record
    @GetMapping("/{id}")
    public String getRecord(@PathVariable Long id) {
        return "Get delivery record " + id;
    }

    @GetMapping("/contract/{contractId}")
    public String listRecords(@PathVariable Long contractId) {
        return "List delivery records for contract " + contractId;
    }

    // GET /contract/{contractId}/latest - Get latest
    @GetMapping("/contract/{contractId}/latest")
    public String getLatest(@PathVariable Long contractId) {
        return "Latest delivery record for contract " + contractId;
    }
}
