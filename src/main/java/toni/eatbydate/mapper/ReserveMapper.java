package toni.eatbydate.mapper;


import org.mapstruct.*;
import toni.eatbydate.dto.ReserveDto;
import toni.eatbydate.entity.Reserve;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReserveMapper {

    Reserve toEntity(ReserveDto reserveDto);

    ReserveDto toDto(Reserve reserve);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract Reserve partialUpdate(ReserveDto reserveDto, @MappingTarget Reserve reserve);
}
