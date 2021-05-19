package net.focik.gasmain.domain.port;

import net.focik.gasmain.infrastructure.dto.GasMainDbDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface IGasMainRepository {

    Integer add(GasMainDbDto gasMainDbDto);

    Optional<GasMainDbDto> findById(Integer id);
//
//    List<Client> findAll();
//
//    void delete(Long id);
//
//    Optional<Client> edit(Client author);
//
//    Optional<Client> findByNip(String nip);
}
