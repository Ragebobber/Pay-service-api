package org.acquiring.service.repository;

import jakarta.validation.constraints.NotNull;
import org.acquiring.service.entity.Payment;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends Repository<Payment, UUID> {
    void save(@NotNull Payment payment);
    Optional<Payment> findPaymentByBankId(String id);
}
