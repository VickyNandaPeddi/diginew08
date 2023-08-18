/**
 *
 */
package com.aashdit.digiverifier.vendorcheck.repository;


import com.aashdit.digiverifier.vendorcheck.model.ConventionalAttributesMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author ${ashwani}
 *
 */
public interface ConventionalAttributesMasterRepository extends JpaRepository<ConventionalAttributesMaster, Long> {
    @Query(value = "SELECT * FROM t_dgv_conventional_attributes_master WHERE source_id = :sourceId", nativeQuery = true)
    List<ConventionalAttributesMaster> findBySourceId(@Param("sourceId") Long sourceId);


}
