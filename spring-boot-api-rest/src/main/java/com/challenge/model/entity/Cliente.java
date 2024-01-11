package com.challenge.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{cliente.nombre.null}")
    private String nombres;

    @NotBlank(message = "{cliente.apellido.null}")
    private String apellido;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "{cliente.fecha_nac.null}")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @NotBlank(message = "{cliente.cuit.null}")
    @Pattern(regexp = "\\d{2}-\\d{8}-\\d{1}", message = "{cliente.cuit.format}")
    @Size(min = 13, max = 13, message = "{cliente.cuit.longitud}")
    private String cuit;

    @NotBlank(message = "{cliente.domicilio.null}")
    private String domicilio;

    @NotNull(message = "{cliente.celular.null}")
    private Integer celular;

    @NotBlank(message = "{cliente.email.null}")
    @Email(message = "{cliente.email.format}")
    private String email;

    @Override
    public String toString() {
        return
                "\n    id=" + id +
                        "\n    nombres='" + nombres + '\'' +
                        "\n    apellido='" + apellido + '\'' +
                        "\n    fechaNacimiento=" + fechaNacimiento +
                        "\n    cuit='" + cuit + '\'' +
                        "\n    domicilio='" + domicilio + '\'' +
                        "\n    celular=" + celular +
                        "\n    email='" + email + '\'' +
                        "\n}";
    }
}