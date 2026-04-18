package br.com.treinarecife.sgp.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.treinarecife.sgp.models.Projeto;
import br.com.treinarecife.sgp.models.Tarefa;
import br.com.treinarecife.sgp.models.Usuario;
import br.com.treinarecife.sgp.repository.ProjetoRepository;
import br.com.treinarecife.sgp.repository.TarefaRepository;
import br.com.treinarecife.sgp.repository.UsuarioRepository;
import br.com.treinarecife.sgp.types.enums.Prioridade;
import br.com.treinarecife.sgp.types.enums.StatusProjeto;
import br.com.treinarecife.sgp.types.enums.StatusTarefa;
import br.com.treinarecife.sgp.types.enums.StatusUsuario;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(ProjetoRepository projetoRepository, UsuarioRepository usuarioRepository, TarefaRepository tarefaRepository) {

        Usuario admin = new Usuario();
        Projeto projeto = new Projeto();
        Tarefa tarefa = new Tarefa();

        return args -> {

            String emailAdmin = "admin@sgp.com";
            if (usuarioRepository.findByEmail(emailAdmin).isEmpty()) {
                admin.setNome("Administrador");
                admin.setEmail(emailAdmin);
                admin.setCpf("00000000000");
                admin.setSenha("123456");
                admin.setDataNascimento(java.time.LocalDate.of(1990, 1, 1));
                admin.setStatus(StatusUsuario.ATIVO);

                usuarioRepository.save(admin);
                System.out.println("Usuário administrador criado.");
            }


            if (projetoRepository.count() == 0) {
                projeto.setNome("Projeto de exemplo");
                projeto.setDescricao(
                        "Este é um projeto de exemplo criado na inicialização do banco de dados");
                projeto.setDataInicio(java.time.LocalDate.now());
                projeto.setDataConclusao(java.time.LocalDate.now().plusMonths(1));
                projeto.setStatus(StatusProjeto.ATIVO);
                projeto.setUsuario(Usuario.builder().id(1L).build());

                projetoRepository.save(projeto);
                System.out.println("Projeto de exemplo criado.");
            }

            if (tarefaRepository.count() == 0) {
                tarefa.setTitulo("Tarefa de exemplo");
                tarefa.setDescricao(
                        "Esta é uma tarefa de exemplo criada na inicialização do banco de dados.");
                tarefa.setDataCriacao(java.time.LocalDate.now());
                tarefa.setDataConclusao(java.time.LocalDate.now().plusDays(7));
                tarefa.setPrioridade(Prioridade.MEDIA);
                tarefa.setStatus(StatusTarefa.PENDENTE);
                tarefa.setProjeto(Projeto.builder().id(1L).build());
                tarefa.setUsuario(Usuario.builder().id(1L).build());

                tarefaRepository.save(tarefa);
                System.out.println("Tarefa de exemplo criada.");
            }

        };
    }

}
