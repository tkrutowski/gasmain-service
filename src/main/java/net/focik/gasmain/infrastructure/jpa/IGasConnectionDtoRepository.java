package net.focik.gasmain.infrastructure.jpa;

import net.focik.gasconnection.infrastructure.dto.GasConnectionDbDto;
import org.springframework.data.jpa.repository.JpaRepository;

interface IGasConnectionDtoRepository extends JpaRepository<GasConnectionDbDto, Integer> {
}
