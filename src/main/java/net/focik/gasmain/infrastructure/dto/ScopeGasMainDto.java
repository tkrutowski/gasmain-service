package net.focik.gasmain.infrastructure.dto;

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
public class ScopeGasMainDto {
    private Integer id;
    private Integer idTask;
    private String message;
    private Integer diameter;
    private float lenghtWar;
}
