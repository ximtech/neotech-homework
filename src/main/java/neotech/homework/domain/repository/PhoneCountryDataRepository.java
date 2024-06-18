package neotech.homework.domain.repository;

import neotech.homework.domain.entity.PhoneCountryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhoneCountryDataRepository extends JpaRepository<PhoneCountryData, Long> {
    
    @Query(value = """
        SELECT * FROM neotech.phone_country_data AS country_data
        WHERE country_data.dialing_code = (
            SELECT MAX(cd.dialing_code)
            FROM neotech.phone_country_data AS cd
                     JOIN neotech.phone_world_zone phone_world_zone ON phone_world_zone.id = cd.phone_world_zone_id
            WHERE phone_world_zone.zone_id = :countryCode
              AND cd.dialing_code = SUBSTRING(:phoneNumber, 0, LENGTH(cd.dialing_code) + 1))
        """, nativeQuery = true)
    List<PhoneCountryData> findCountryData(@Param("countryCode") String countryCode, @Param("phoneNumber") String phoneNumber);
    
}
