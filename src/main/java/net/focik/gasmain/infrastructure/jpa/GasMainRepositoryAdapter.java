package net.focik.gasmain.infrastructure.jpa;

import lombok.AllArgsConstructor;
import net.focik.gasmain.domain.port.IGasMainRepository;
import net.focik.gasmain.infrastructure.dto.GasMainDbDto;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
@Primary
class GasMainRepositoryAdapter implements IGasMainRepository {

    private IGasMainDtoRepository gasMainRepository;

    @Override
    public Integer add(GasMainDbDto gasMainDbDto) {
        return gasMainRepository.save(gasMainDbDto).getIdTask();
    }

    @Override
    public Optional<GasMainDbDto> findById(Integer id) {
        return gasMainRepository.findById(id);
    }
}
