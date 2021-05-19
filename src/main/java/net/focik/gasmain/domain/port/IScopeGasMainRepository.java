package net.focik.gasmain.domain.port;

import net.focik.gasmain.infrastructure.dto.ScopeGasMainDto;

import java.util.List;

public interface IScopeGasMainRepository {
    List<ScopeGasMainDto> findScopeGasMainByIdTask(Integer idtask);
}
