package net.focik.gasmain.infrastructure.clients;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.focik.gasmain.domain.port.IScopeGasConnectionRepository;
import net.focik.gasconnection.infrastructure.dto.ScopeGasConnectionDto;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ScopeGasConnectionClient implements IScopeGasConnectionRepository {


    private RestTemplate restTemplate ;

    private static final String URI = "http://scope-gasconnection-service/api/scopegasconnection/task/";

    @HystrixCommand(fallbackMethod = "getFallbackListOfScopeGasConnectionDto",
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
    public List<ScopeGasConnectionDto> findScopeGasConnectionByIdTask(Integer idTask) {
        log.info("GASMAIN-SERVICE: Try find scope-gasconnection for gasconnection id = " + idTask);
        List<ScopeGasConnectionDto> connectionDtos = new ArrayList<>();
        try {
            ResponseEntity<ScopeGasConnectionDto[]> response =
                    restTemplate.getForEntity(URI + idTask, ScopeGasConnectionDto[].class);

            connectionDtos = List.of(response.getBody());
            log.info("GASMAIN-SERVICE: Found " + connectionDtos.size() + " scope-gasconnection for gasconnection id = " + idTask);
        } catch (RestClientException ex) {
            log.error("GASMAIN-SERVICE: Error", ex.fillInStackTrace());            //TODO może rzucić wyjątek
            return connectionDtos;
        }

        return connectionDtos;
    }

    private List<ScopeGasConnectionDto> getFallbackListOfScopeGasConnectionDto(Integer idtask) {
        List<ScopeGasConnectionDto> connectionDtos = new ArrayList<>();

        return connectionDtos;
    }
}
