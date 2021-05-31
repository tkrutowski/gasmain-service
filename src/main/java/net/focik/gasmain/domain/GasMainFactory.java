package net.focik.gasmain.domain;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import net.focik.gasmain.domain.dto.GasMainTaskCalendarDto;
import net.focik.gasmain.domain.dto.IGasMainDto;
import net.focik.gasmain.domain.exceptions.AddressNotExistException;
import net.focik.gasmain.domain.port.IAddressRepository;
import net.focik.gasmain.domain.port.IScopeGasConnectionRepository;
import net.focik.gasmain.domain.port.IScopeGasMainRepository;
import net.focik.gasmain.domain.share.DtoType;
import net.focik.gasmain.infrastructure.dto.AddressDto;
import net.focik.gasmain.infrastructure.dto.GasMainDbDto;
import net.focik.gasmain.infrastructure.dto.ScopeGasMainDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Log
class GasMainFactory {

    private ModelMapper mapper;

//    private IScopeGasConnectionRepository scopeGasConnectionRepository;
    //private IScopeGasMainRepository scopeGasMainRepository;
IAddressRepository addressRepository;
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

        Optional<AddressDto> addressById = getAddressDto(dbDto.getIdAddress());

        mappedDto.setAddress(addressById.get().getFullAddress());
        //List<ScopeGasMainDto> scopeGasMainList = scopeGasMainRepository.findScopeGasMainByIdTask(dbDto.getIdTask());

        return mappedDto;

    }
    private Optional<AddressDto> getAddressDto(Integer idAddress) {
        Optional<AddressDto> addressById = addressRepository.findAddressById(idAddress);
        if (addressById.isEmpty())
            throw new AddressNotExistException(idAddress);
        return addressById;
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
