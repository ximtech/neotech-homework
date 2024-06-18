package neotech.homework.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "phone_country_data", schema = BaseEntity.DEFAULT_SCHEMA)
public class PhoneCountryData extends BaseEntity {

    @Column(nullable = false)
    String countryName;

    @Column(nullable = false)
    String dialingCode;
    
    @ManyToOne
    @JoinColumn(name = "phone_world_zone_id", nullable = false)
    PhoneWorldZone phoneWorldZone;
    
}
