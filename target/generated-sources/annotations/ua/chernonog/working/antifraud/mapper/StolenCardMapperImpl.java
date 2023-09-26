package ua.chernonog.working.antifraud.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ua.chernonog.working.antifraud.entity.StolenCardEntity;
import ua.chernonog.working.antifraud.model.respons.StolenCardResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-26T13:39:29+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (JetBrains s.r.o.)"
)
@Component
public class StolenCardMapperImpl implements StolenCardMapper {

    @Override
    public StolenCardResponse stolenEntityToStolenResponse(StolenCardEntity savedStolenCard) {
        if ( savedStolenCard == null ) {
            return null;
        }

        StolenCardResponse.StolenCardResponseBuilder stolenCardResponse = StolenCardResponse.builder();

        if ( savedStolenCard.getId() != null ) {
            stolenCardResponse.id( savedStolenCard.getId() );
        }
        stolenCardResponse.number( savedStolenCard.getNumber() );

        return stolenCardResponse.build();
    }
}
