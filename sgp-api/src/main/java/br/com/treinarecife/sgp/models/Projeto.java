package br.com.treinarecife.sgp.models;

import br.com.treinarecife.sgp.types.enums.StatusProjeto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private LocalDate dataInicio;

    private LocalDate dataConclusao;

    @Enumerated(EnumType.STRING)
    private StatusProjeto status;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "projeto")
    private List<Tarefa> tarefas;

}
