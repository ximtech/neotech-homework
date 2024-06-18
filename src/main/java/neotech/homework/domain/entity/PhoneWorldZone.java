package neotech.homework.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "phone_world_zone", schema = BaseEntity.DEFAULT_SCHEMA)
public class PhoneWorldZone extends BaseEntity {

    @Column(nullable = false)
    String zoneId;

    @Column(nullable = false)
    String zoneName;

    @OneToMany
    private List<PhoneCountryData> phoneCountryDataList;
    
}
