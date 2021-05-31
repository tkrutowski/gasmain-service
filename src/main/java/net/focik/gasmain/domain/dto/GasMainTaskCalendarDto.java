package net.focik.gasmain.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import net.focik.customers.domain.ClientType;

//
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@Entity
//@Table(name = "userbooks")
public class GasMainTaskCalendarDto implements IGasMainDto {
    private Integer idTask;
    private Integer idCustomer;
    private Integer idSurveyor;
    private String taskNo;
    private String address;
}
