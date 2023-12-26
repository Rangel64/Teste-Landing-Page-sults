package br.com.rangeltestesults.landingpagesults.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Conta {

    @Id
    @SequenceGenerator(name="geradorConta", sequenceName="conta_codigo_seq",allocationSize = 1)
    @GeneratedValue(generator="geradorConta", strategy=GenerationType.SEQUENCE)

    private Long id;

    @NotBlank(message = "O nome é obrigatorio")
    private String nome;

    @NotBlank(message = "O nome da empresa é obrigatorio")
    private String empresa;

    @NotBlank(message = "O email é obrigatorio")
    private String email;

    @NotBlank(message = "O celular é obrigatorio")
    private String celular;
    
}
