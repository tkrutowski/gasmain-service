package net.focik.gasmain.infrastructure.inMemory;

import lombok.extern.java.Log;
import net.focik.gasmain.domain.port.IGasMainRepository;
import net.focik.gasmain.infrastructure.dto.GasMainDbDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

@Component()
@Profile("dev")
@Log
class InMemoryGasMainRepositoryAdapter implements IGasMainRepository {
    private HashMap<Integer, GasMainDbDto> gasMainDbDtoHashMap = new HashMap<>();


    @Override
    public Integer add(GasMainDbDto gasMainDbDto) {
        int i=0;
    log.info("Try add into inMemoryDb clientDbDto: "+ gasMainDbDto.toString());
        if(gasMainDbDto == null)
            throw new NullPointerException("ClientDbDto cannot be null");
        Integer id = gasMainDbDtoHashMap.size() + 1;
        gasMainDbDto.setIdTask(id);
        gasMainDbDtoHashMap.put(id, gasMainDbDto);
        log.info("Succssec id = " + id);
        return id;
    }


    @Override
    public Optional<GasMainDbDto> findById(Integer id) {
        return Optional.ofNullable(gasMainDbDtoHashMap.get(id));
    }
}
