package net.focik.gasmain.infrastructure.jpa;

import net.focik.gasmain.infrastructure.dto.GasMainDbDto;
import org.springframework.data.jpa.repository.JpaRepository;

interface IGasMainDtoRepository extends JpaRepository<GasMainDbDto, Integer> {
}
