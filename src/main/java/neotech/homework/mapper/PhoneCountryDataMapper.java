package neotech.homework.mapper;

import neotech.homework.domain.entity.PhoneCountryData;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface PhoneCountryDataMapper {
    
    default List<String> mapToCountryNames(List<PhoneCountryData> phoneCountryDataList) {
        return phoneCountryDataList.stream()
                .map(PhoneCountryData::getCountryName)
                .collect(Collectors.toList());
    }
}
