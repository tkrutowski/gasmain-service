package net.focik.gasmain.infrastructure.clients;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.focik.gasmain.domain.port.IAddressRepository;
import net.focik.gasmain.infrastructure.dto.AddressDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@AllArgsConstructor
@Log4j2
public class AddressRepositoryAdapter implements IAddressRepository {
    private RestTemplate restTemplate;
    //TODO dodać stałą z propertisów
    private static final String URI = "http://address-service/api/address/type/";

    @HystrixCommand(fallbackMethod = "getFallbackAddressDto",
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
    public Optional<AddressDto> findAddressById(Integer id) {
        log.info("GASMAIN-SERVICE: Try find address for id = " + id);
        AddressDto addressDto = null;
        try {
            addressDto = restTemplate.getForObject(URI + id + "?type=TASK_CALENDAR", AddressDto.class);
            log.info(addressDto != null ? "GASMAIN-SERVICE: Found address for id = " + id : "GASMAIN-SERVICE: Not found address for id = " + id);
        }catch (RestClientException ex){
            //TODO może rzucić wyjątek albo legger error
            log.error("GASMAIN-SERVICE: AddressRepositoryAdapter findAddressById. Message: "+ex.getMessage());

            return Optional.empty();
        }

        return Optional.ofNullable(addressDto);
    }

    
    public Optional<AddressDto> getFallbackAddressDto(Integer id) {
        AddressDto addressDto = new AddressDto();
        addressDto.setCity("fallback");
        addressDto.setCommune("fallback");
        addressDto.setStreet("fallback");

        return Optional.ofNullable(addressDto);
    }



}
