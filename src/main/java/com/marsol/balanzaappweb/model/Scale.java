package com.marsol.balanzaappweb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "scale")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Scale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "tienda")
    private int store;

    @Column(nullable = false)
    private String formato;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private int departamento;

    @NotBlank(message = "La IP no puede ser nula o vacía")
    @NotNull
    @Pattern(regexp = "^\\d{1,3}(\\.\\d{1,3}){3}$", message = "Formato de IP no válido")
    @Column(nullable = false, name = "ip_balanza")
    private String ipBalanza;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false, name = "estado")
    private boolean status;

    @Column(nullable = false, name = "last_update")
    private String lastUpdate;

    @Column(nullable = false, name = "carga_layout")
    private boolean isCargaLayout;

    @Column(nullable = false, name = "es_dual")
    private boolean isEsDual;

    @Column(nullable = false, name = "carga_maestra")
    private boolean isCargaMaestra;

    @Column(nullable = false, name="es_autoservicio")
    private boolean isEsAutoservicio;
}
