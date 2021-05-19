package net.focik.gasmain.api;

import lombok.AllArgsConstructor;
import net.focik.gasmain.domain.GasMainFacade;
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
@RequestMapping("/api/gasconnection")
class MainGasController {

    private GasMainFacade gasMainFacade;

    @PostMapping
    public Integer addGasConnection(@RequestBody GasConnectionDbDto gasConnectionDbDto){
        return gasMainFacade.addGasConnection(gasConnectionDbDto);
    }

    @GetMapping("/{id}")
    ResponseEntity<IGasConnectionDto> getGasConnection(@PathVariable Integer id, @RequestParam(name = "type", defaultValue = "GAS_CONNECTION") DtoType dtoType){
        int i=0;
        IGasConnectionDto gasConnectionDto = gasMainFacade.getGasConnectionDto(id, dtoType);

        if(gasConnectionDto == null)
            return new ResponseEntity<>((IGasConnectionDto) null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(gasConnectionDto, HttpStatus.OK);
    }

}
