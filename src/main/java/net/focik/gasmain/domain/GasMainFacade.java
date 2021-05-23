package net.focik.gasmain.domain;

import lombok.AllArgsConstructor;
import net.focik.gasmain.domain.dto.IGasMainDto;
import net.focik.gasmain.domain.port.IGasMainRepository;
import net.focik.gasmain.domain.share.DtoType;
import net.focik.gasmain.infrastructure.dto.GasMainDbDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GasMainFacade {

    private IGasMainRepository gasMainRepository;
    private GasMainFactory gasMainFactory;

    public Integer addGasMain(GasMainDbDto customerDbDto) {
         return gasMainRepository.add(customerDbDto);
    }


    public IGasMainDto getGasMainDto(Integer id, DtoType dtoType) {
        int i=0;
        Optional<GasMainDbDto> byId = gasMainRepository.findById(id);

        if(byId.isEmpty())
            return null;

        return gasMainFactory.createGasMainByDtoType(byId.get(), dtoType);

    }
}
