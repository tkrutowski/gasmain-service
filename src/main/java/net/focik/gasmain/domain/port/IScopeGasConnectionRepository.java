package net.focik.gasmain.domain.port;

import net.focik.gasconnection.infrastructure.dto.ScopeGasConnectionDto;

import java.util.List;

public interface IScopeGasConnectionRepository {
    List<ScopeGasConnectionDto> findScopeGasConnectionByIdTask(Integer idtask);
}
