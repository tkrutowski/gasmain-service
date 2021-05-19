package net.focik.gasconnection.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

//
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScopeGasConnectionDto {
    private Integer id;
    private Integer idTask;
    private String typeOfTask;
    private String message;
    private Integer diameter;
    private float lenghtWar;
    private String material;
    private String sdr;
    private float lenghtProj;
    private String gasCabinetProvider;
    private LocalDate gasCabinetPickupDate;
}
