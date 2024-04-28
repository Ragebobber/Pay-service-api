package org.acquiring.service.repository;

import jakarta.validation.constraints.NotNull;
import org.acquiring.service.entity.Customer;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.UUID;
public interface CustomerRepository extends Repository<Customer, UUID> {
    void save(@NotNull Customer customer);
    Optional<Customer> findCustomerByExternalId(String externalId);
}
