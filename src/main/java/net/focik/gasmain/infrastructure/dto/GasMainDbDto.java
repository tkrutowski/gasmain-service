package net.focik.gasmain.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "gas_main_view")
public class GasMainDbDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTask;
    private Integer idCustomer;
    private Integer idSurveyor;
    private String taskNo;
    private Integer idAddress;

}
