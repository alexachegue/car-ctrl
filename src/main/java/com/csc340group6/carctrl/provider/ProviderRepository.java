package com.csc340group6.carctrl.provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {

    Provider getProviderByProvidername(String providername);

    // active
    List<Provider> findByStatus(Provider.ProviderStatus status);

    @Query("SELECT p FROM Provider p JOIN p.services s WHERE s.serviceId = :serviceId")
    List<Provider> findByServiceId(@Param("serviceId") int serviceId);

    List<Provider> findByServices_ServiceId(int serviceId);

}
