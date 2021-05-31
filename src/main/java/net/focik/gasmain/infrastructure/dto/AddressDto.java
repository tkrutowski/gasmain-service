package net.focik.gasmain.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDto {
    private String commune;
    private String city;
    private String street;

    public String getFullAddress(){
        return String.format("%s, %s, ul. %s", commune,city, street);
    }
}
