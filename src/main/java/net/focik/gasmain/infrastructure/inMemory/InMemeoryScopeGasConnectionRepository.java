package net.focik.gasmain.infrastructure.inMemory;

import net.focik.gasconnection.domain.port.IScopeGasConnectionRepository;
import net.focik.gasconnection.infrastructure.dto.ScopeGasConnectionDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Profile("test")
public class InMemeoryScopeGasConnectionRepository implements IScopeGasConnectionRepository {
    @Override
    public List<ScopeGasConnectionDto> findScopeGasConnectionByIdTask(Integer idtask) {
        return  List.of(new ScopeGasConnectionDto(1, 1, "GAS_CONNECTION", "brak", 23, 12.3f, "brak", "SDR 11", 12.5f, "Klient", LocalDate.of(2020, 05, 04)));

        //return new ArrayList<>();
    }
}
