package net.focik.gasmain.infrastructure.clients;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.focik.gasmain.domain.port.IScopeGasMainRepository;
import net.focik.gasmain.infrastructure.dto.ScopeGasMainDto;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@Log4j2
@Primary
@Profile("dev")
public class ScopeGasMainClient implements IScopeGasMainRepository {


    private RestTemplate restTemplate ;

    private static final String URI = "http://scope-gasmain-service/api/scopegasmain/task/";

    @HystrixCommand(fallbackMethod = "getFallbackListOfScopeGasMainDto",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "6"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
            },
            threadPoolKey = "scopePool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "20"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")
            })
    public List<ScopeGasMainDto> findScopeGasMainByIdTask(Integer idTask) {
        log.info("Try find scope-gasconnection for gasconnection id = " + idTask);
        List<ScopeGasMainDto> connectionDtos = new ArrayList<>();
        try {
            ResponseEntity<ScopeGasMainDto[]> response =
                    restTemplate.getForEntity(URI + idTask, ScopeGasMainDto[].class);

            connectionDtos = List.of(response.getBody());
            log.info("Found " + connectionDtos.size() + " scope-gasmain for gasmain id = " + idTask);
        } catch (RestClientException ex) {
            log.error("Error", ex.fillInStackTrace());            //TODO może rzucić wyjątek
            return connectionDtos;
        }

        return connectionDtos;
    }

    private List<ScopeGasMainDto> getFallbackListOfScopeGasMainDto(Integer idtask) {
        List<ScopeGasMainDto> connectionDtos = new ArrayList<>();

        return connectionDtos;
    }
}
