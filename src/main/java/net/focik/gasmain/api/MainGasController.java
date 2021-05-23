package net.focik.gasmain.api;

import lombok.AllArgsConstructor;
import net.focik.gasmain.domain.GasMainFacade;
import net.focik.gasmain.domain.dto.IGasMainDto;
import net.focik.gasmain.domain.share.DtoType;
import net.focik.gasmain.infrastructure.dto.GasMainDbDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
//@NoArgsConstructor
@RequestMapping("/api/gasmain")
class MainGasController {

    private GasMainFacade gasMainFacade;

    @PostMapping
    public Integer addGasMain(@RequestBody GasMainDbDto gasMainDbDto){
        return gasMainFacade.addGasMain(gasMainDbDto);
    }

    @GetMapping("/{id}")
    ResponseEntity<IGasMainDto> getGasMain(@PathVariable Integer id, @RequestParam(name = "type", defaultValue = "GAS_CONNECTION") DtoType dtoType){
        int i=0;
        IGasMainDto gasConnectionDto = gasMainFacade.getGasMainDto(id, dtoType);

        if(gasConnectionDto == null)
            return new ResponseEntity<>((IGasMainDto) null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(gasConnectionDto, HttpStatus.OK);
    }

}
