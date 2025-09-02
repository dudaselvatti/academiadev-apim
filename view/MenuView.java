package view;

import dto.CourseCatalogDTO;
import dto.EnrollmentDTO;
import dto.SupportTicketDTO;
import dto.UserSummaryDTO;
import model.CourseStatus;
import model.DifficultyLevel;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class MenuView {

    private final Scanner scanner = new Scanner(System.in);

    public void mostrarBoasVindas() {
        System.out.println("=== Olá! Bem-vindo à plataforma AcademiaDev! ===\n");
    }

    public DifficultyLevel selecionarDificuldade() {
        System.out.println("\n--- Escolha o nível de dificuldade ---");
        System.out.println("1. Iniciante");
        System.out.println("2. Intermediário");
        System.out.println("3. Avançado");
        System.out.print("Digite sua opção: ");
        String escolha = scanner.nextLine().trim();

        return switch (escolha) {
            case "1" -> DifficultyLevel.BEGINNER;
            case "2" -> DifficultyLevel.INTERMEDIATE;
            case "3" -> DifficultyLevel.ADVANCED;
            default -> {
                System.out.println("Opção inválida. Definindo como 'Iniciante'.");
                yield DifficultyLevel.BEGINNER;
            }
        };
    }

    public String lerEmail() {
        System.out.print("Informe seu e-mail (ou 'X' para sair): ");
        return scanner.nextLine().trim();
    }

    public void mostrarMensagemLoginSucesso(String nome) {
        System.out.println("Você entrou como: " + nome + "\n");
    }

    public void mostrarErro(String mensagem) {
        System.out.println("Erro: " + mensagem + "\n");
    }

    public void mostrarMensagemSistemaEncerrado() {
        System.out.println("Sistema finalizado. Até breve!");
    }

    // === MENU ADMIN ==
    public int mostrarMenuAdmin() {
        System.out.println("\n--- Painel do Administrador ---");
        System.out.println("1. Ver todos os cursos");
        System.out.println("2. Ativar ou desativar curso");
        System.out.println("3. Atender próximo chamado");
        System.out.println("4. Gerar relatórios");
        System.out.println("5. Exportar dados em CSV");
        System.out.println("6. Alterar plano de usuários");
        System.out.println("7. Encerrar sessão");
        System.out.println("8. Fechar o sistema");
        System.out.print("Digite sua escolha: ");
        return lerOpcao();
    }

    public List<String> selecionarCamposParaCurso() {
        System.out.println("\n--- Escolha os campos para exportar (Cursos) ---");
        System.out.println("1. Nome");
        System.out.println("2. Resumo");
        System.out.println("3. Instrutor");
        System.out.println("4. Carga Horária");
        System.out.println("5. Nível");
        System.out.println("6. Situação");
        System.out.print("Digite os números separados por vírgula (ex: 1,3,4): ");

        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return List.of();
        }

        Map<String, String> opcoes = Map.of(
            "1", "title",
            "2", "description",
            "3", "instructorName",
            "4", "durationInHours",
            "5", "difficultyLevel",
            "6", "status"
        );

        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(opcoes::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<String> selecionarCamposParaMatricula() {
        System.out.println("\n--- Escolha os campos para exportar (Matrículas) ---");
        System.out.println("1. Nome do aluno");
        System.out.println("2. E-mail do aluno");
        System.out.println("3. Curso");
        System.out.println("4. Progresso");
        System.out.print("Digite os números separados por vírgula (ex: 1,3,4): ");

        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return List.of();
        }

        Map<String, String> opcoes = Map.of(
            "1", "student",
            "2", "email",
            "3", "course",
            "4", "progress"
        );

        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(opcoes::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<String> selecionarCamposParaUsuarios() {
        System.out.println("\n--- Escolha os campos para exportar (Usuários) ---");
        System.out.println("1. Nome completo");
        System.out.println("2. E-mail");
        System.out.print("Digite os números separados por vírgula (ex: 1,2): ");

        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return List.of();
        }

        Map<String, String> opcoes = Map.of(
            "1", "name",
            "2", "email"
        );

        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(opcoes::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<String> selecionarCamposParaTickets() {
        System.out.println("\n--- Escolha os campos para exportar (Chamados) ---");
        System.out.println("1. Usuário");
        System.out.println("2. E-mail do usuário");
        System.out.println("3. Assunto");
        System.out.println("4. Descrição");
        System.out.print("Digite os números separados por vírgula (ex: 1,3,4): ");

        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return List.of();
        }

        Map<String, String> opcoes = Map.of(
            "1", "user",
            "2", "email",
            "3", "title",
            "4", "description"
        );

        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(opcoes::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public boolean confirmarAlteracaoPlano(String email, String atual, String novo) {
        System.out.println("Usuário: " + email);
        System.out.println("Plano atual: " + atual);
        System.out.println("Deseja alterar para: " + novo + "? (S/N)");
        String resposta = scanner.nextLine().trim();
        return resposta.equalsIgnoreCase("S");
    }

    public void mostrarPlanoAtualizado(String email, String novoStatus) {
        System.out.println("\nPlano atualizado com sucesso!");
        System.out.println("Usuário: " + email);
        System.out.println("Novo plano: " + novoStatus);
        System.out.println();
    }

    public String lerEmailAluno() {
        System.out.print("Informe o e-mail do aluno: ");
        return scanner.nextLine().trim();
    }

    public void mostrarTicketAtendido(Optional<SupportTicketDTO> optional) {
        if (optional.isPresent()) {
            SupportTicketDTO ticket = optional.get();
            mostrarTicket(ticket.getEmailAuthor(),
                    ticket.getTitle(),
                    ticket.getDescription());

        } else {
            System.out.println("Não há chamados pendentes.");
        }
    }

    public void mostrarAlunoComMaiorMatricula(Optional<UserSummaryDTO> aluno) {
        if (aluno.isPresent()) {
            System.out.println("\nAluno com maior número de matrículas:");
            System.out.println(aluno.get());
        } else {
            System.out.println("\nNenhum aluno localizado.\n");
        }
    }

    public void mostrarMediaProgresso(double media) {
        if (media == -1) {
            System.out.println("\nNenhuma matrícula registrada.\n");
        } else {
            System.out.println("\nMédia geral de progresso: " + media + "%\n");
        }
    }

    public void mostrarAlunoAgrupado(Map<String, List<UserSummaryDTO>> alunos) {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno registrado.\n");
        } else {
            System.out.println("\nAlunos agrupados por plano:");
            alunos.forEach((plano, alunosDoPlano) -> System.out.println(plano + ": " + alunosDoPlano));
        }
        System.out.println();
    }

    public void mostrarCursos(Collection<CourseCatalogDTO> cursos) {
        if (cursos.isEmpty()) {
            System.out.println("Não há cursos disponíveis.\n");
        } else {
            System.out.println("\nLista de cursos:");
            cursos.forEach(System.out::println);
        }
        System.out.println();
    }

    public String lerTituloCurso() {
        System.out.print("Digite o nome do curso: ");
        return scanner.nextLine().trim();
    }

    public boolean confirmarAlteracaoStatus(String curso, CourseStatus atual, CourseStatus novo) {
        System.out.println("Curso: " + curso);
        System.out.println("Status atual: " + atual);
        System.out.println("Alterar para: " + novo + "? (S/N)");
        String resposta = scanner.nextLine().trim();
        return resposta.equalsIgnoreCase("S");
    }

    public void mostrarStatusAtualizado(String curso, CourseStatus novoStatus) {
        System.out.println("\nStatus modificado com sucesso!");
        System.out.println("Curso: " + curso);
        System.out.println("Novo status: " + novoStatus);
        System.out.println();
    }

    public int mostrarMenuAluno() {
        System.out.println("\n--- Menu do Aluno ---");
        System.out.println("1. Ver cursos disponíveis");
        System.out.println("2. Inscrever-se em curso");
        System.out.println("3. Consultar minhas inscrições");
        System.out.println("4. Atualizar progresso");
        System.out.println("5. Cancelar inscrição");
        System.out.println("6. Abrir chamado de suporte");
        System.out.println("7. Encerrar sessão");
        System.out.println("8. Sair do sistema");
        System.out.print("Digite sua opção: ");
        return lerOpcao();
    }

    public void mostrarEnrollments(Collection<EnrollmentDTO> enrollments) {
        if (enrollments.isEmpty()) {
            System.out.println("Você não possui matrículas.\n");
        } else {
            System.out.println("\nSuas inscrições:");
            enrollments.forEach(System.out::println);
        }
        System.out.println();
    }

    public void mostrarInstrutores(Set<String> instructor) {
        if (instructor.isEmpty()) {
            System.out.println("Nenhum instrutor cadastrado.\n");
        } else {
            System.out.println("\nInstrutores disponíveis:");
            instructor.forEach(System.out::println);
        }
        System.out.println();
    }

    public int lerProgresso() {
        System.out.print("Digite o progresso atualizado (0 a 100%): ");
        String input = scanner.nextLine().trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("O progresso deve ser um número válido.");
        }
    }

    public void mostrarProgressoAtualizado(String curso, int progresso) {
        System.out.println("\nProgresso salvo com sucesso!");
        System.out.println("Curso: " + curso);
        System.out.println("Progresso atual: " + progresso + "%");
        if (progresso == 100) {
            System.out.println("Parabéns! Você concluiu este curso!");
        } else {
            System.out.println("Continue se dedicando!");
        }
        System.out.println();
    }

    public void mostrarMatriculaRealizada(String curso) {
        System.out.println("\nInscrição confirmada!");
        System.out.println("Curso: " + curso);
        System.out.println("Progresso inicial: 0%\n");
    }

    public void mostrarMatriculaCancelada(String curso) {
        System.out.println("\nSua inscrição no curso '" + curso + "' foi cancelada.\n");
    }

    public String lerTicketTitle() {
        System.out.print("Assunto do chamado: ");
        return scanner.nextLine().trim();
    }

    public String lerTicketMessage() {
        System.out.print("Descreva o problema: ");
        return scanner.nextLine().trim();
    }

    public void mostrarTicket(String author, String title, String message) {
        System.out.println("\nChamado registrado com sucesso!");
        System.out.println("=========== DETALHES DO CHAMADO ===========");
        System.out.println("Usuário: " + author);
        System.out.println("Assunto: " + title);
        System.out.println("Descrição: " + message);
        System.out.println("===========================================");
    }

    private int lerOpcao() {
        String input = scanner.nextLine().trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
