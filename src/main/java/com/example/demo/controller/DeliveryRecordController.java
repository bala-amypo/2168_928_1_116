package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery-records")
public class DeliveryRecordController {

    @PostMapping
    public String logDelivery() {
        return "Delivery logged";
    }

    @GetMapping("/{id}")
    public String getRecord(@PathVariable Long id) {
        return "Get delivery record " + id;
    }

    @GetMapping("/contract/{contractId}")
    public String listRecords(@PathVariable Long contractId) {
        return "List delivery records for contract " + contractId;
    }

    @GetMapping("/contract/{contractId}/latest")
    public String getLatest(@PathVariable Long contractId) {
        return "Latest delivery record for contract " + contractId;
    }
}
