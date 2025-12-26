// package com.example.demo.service.impl;

// import com.example.demo.entity.DeliveryRecord;
// import com.example.demo.repository.DeliveryRecordRepository;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class DeliveryRecordServiceImpl {

//     private final DeliveryRecordRepository deliveryRecordRepository;

//     public DeliveryRecordServiceImpl(DeliveryRecordRepository deliveryRecordRepository) {
//         this.deliveryRecordRepository = deliveryRecordRepository;
//     }

//     public DeliveryRecord save(DeliveryRecord record) {
//         return deliveryRecordRepository.save(record);
//     }

//     public List<DeliveryRecord> getByContractId(Long contractId) {
//         return deliveryRecordRepository
//                 .findByContractIdOrderByDeliveryDateAsc(contractId);
//     }
// }
