package com.example.APIprovider.provider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {

    Provider getProviderByProvidername(String providername);

    // active
    List<Provider> findByStatus(Provider.ProviderStatus status);

}
