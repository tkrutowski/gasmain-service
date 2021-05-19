package net.focik.gasmain.domain;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import net.focik.gasmain.domain.dto.GasMainTaskCalendarDto;
import net.focik.gasmain.domain.dto.IGasMainDto;
import net.focik.gasmain.domain.port.IScopeGasConnectionRepository;
import net.focik.gasmain.domain.port.IScopeGasMainRepository;
import net.focik.gasmain.domain.share.DtoType;
import net.focik.gasmain.infrastructure.dto.GasMainDbDto;
import net.focik.gasmain.infrastructure.dto.ScopeGasMainDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Log
class GasMainFactory {

    private ModelMapper mapper;

//    private IScopeGasConnectionRepository scopeGasConnectionRepository;
    private IScopeGasMainRepository scopeGasMainRepository;

    IGasMainDto createGasMainByDtoType(GasMainDbDto dbDto, DtoType dtoType) {
        IGasMainDto gasMainDto = null;

        switch (dtoType) {
            case TASK_CALENDAR:
                gasMainDto = createGasMainTaskCalendarDto(dbDto);
                break;
            case GAS_CONNECTION:
                break;
        }


        return gasMainDto;
    }

    private GasMainTaskCalendarDto createGasMainTaskCalendarDto(GasMainDbDto dbDto) {
        GasMainTaskCalendarDto mappedDto = mapper.map(dbDto, GasMainTaskCalendarDto.class);

        List<ScopeGasMainDto> scopeGasMainList = scopeGasMainRepository.findScopeGasMainByIdTask(dbDto.getIdTask());

        return mappedDto;
    }

//    private GasMainTaskCalendarDto createGasMainTaskCalendarDto(GasMainDbDto dbDto) {
//        GasConnectionTaskCalendarDto mappedDto = mapper.map(dbDto, GasMainTaskCalendarDto.class);
//
//        List<ScopeGasConnectionDto> scopeGasConnectionList = scopeGasConnectionRepository.findScopeGasConnectionByIdTask(dbDto.getIdTask());
//
//        if(scopeGasConnectionList.size() == 1){
//            mappedDto.setGasCabinetProvider(scopeGasConnectionList.get(0).getGasCabinetProvider());
//        }
//        else
//            mappedDto.setGasCabinetProvider("Brak danych.");
//
//        return mappedDto;
//    }
}
