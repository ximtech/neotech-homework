package neotech.homework.mapper;

import java.math.BigDecimal;
import java.util.UUID;
import javax.annotation.processing.Generated;

import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-25T02:14:27+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ClientTransactionMapperImpl implements ClientTransactionMapper {

    @Override
    public ClientTransactionDTO mapToTransaction(ClientTransaction entity) {
        if ( entity == null ) {
            return null;
        }

        UUID targetClientAccountId = null;
        BigDecimal amountTransferred = null;
        TransactionStatus status = null;
        String currency = null;

        targetClientAccountId = entityTargetAccountId( entity );
        amountTransferred = entity.getAmount();
        status = entity.getStatus();
        currency = entity.getCurrency();

        ClientTransactionDTO clientTransactionDTO = new ClientTransactionDTO( targetClientAccountId, amountTransferred, status, currency );

        return clientTransactionDTO;
    }

    private UUID entityTargetAccountId(ClientTransaction clientTransaction) {
        if ( clientTransaction == null ) {
            return null;
        }
        ClientAccount targetAccount = clientTransaction.getTargetAccount();
        if ( targetAccount == null ) {
            return null;
        }
        UUID id = targetAccount.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
