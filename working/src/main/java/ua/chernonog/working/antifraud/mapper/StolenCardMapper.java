package ua.chernonog.working.antifraud.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ua.chernonog.working.antifraud.entity.StolenCardEntity;
import ua.chernonog.working.antifraud.model.respons.StolenCardResponse;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StolenCardMapper {

    StolenCardResponse stolenEntityToStolenResponse(StolenCardEntity savedStolenCard);
}
