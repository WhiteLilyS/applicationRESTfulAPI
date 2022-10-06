package com.example.applicationrestfulapi.modelRequesterForm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RequesterFormRepository extends JpaRepository <RequesterFormTable, Long> {
    RequesterFormTable findByRequesterId(Long id);
    Boolean existsRequesterFormTableByGatewayId(Long id);

    RequesterFormTable findRequesterFormTableByGatewayId(Long id);

    @Query(value = "SELECT max(requester_form.gateway_id) FROM requester_form",nativeQuery = true)
    Long getMaxId();
}
