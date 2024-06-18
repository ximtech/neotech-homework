package neotech.homework.domain.repository;

import neotech.homework.domain.entity.PhoneWorldZone;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface PhoneWorldZoneRepository extends JpaRepository<PhoneWorldZone, Long> {
    
    default Map<String, PhoneWorldZone> mapAllWorldZoneIds() {
        return findAll().stream()   // small amount of data in the table, so can fetch all content
                .collect(Collectors.toMap(PhoneWorldZone::getZoneId, Function.identity()));
    }
}
