package net.focik.gasmain.domain.port;

import net.focik.gasmain.infrastructure.dto.AddressDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface IAddressRepository {
    Optional<AddressDto> findAddressById(Integer id);
}