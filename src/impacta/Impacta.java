package impacta;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Impacta {

    private ArrayList<Voluntario> voluntarios;
    private ArrayList<Acao> acoes;
    private Map<Integer, ArrayList<Voluntario>> inscritos;
    private int proximoId;

    public Impacta() {
        voluntarios = new ArrayList<>();
        acoes = new ArrayList<>();
        inscritos = new HashMap<>();
        proximoId = 1;
    }

    public boolean cadastrarVoluntario(String nome, String email, String matricula) {
        for (Voluntario voluntario : voluntarios) {

            if (voluntario.getEmail().equalsIgnoreCase(email)) {
                throw new EmailDuplicadoException(
                        "Já existe um voluntário cadastrado com este e-mail."
                );
            }
        }

        voluntarios.add(new Voluntario(nome, email, matricula));
        return true;
    }

    public String exibirVoluntario(String email) {
        for (Voluntario voluntario : voluntarios) {

            if (voluntario.getEmail().equalsIgnoreCase(email)) {
                return "Nome: " + voluntario.getNome()
                        + "\nE-mail: " + voluntario.getEmail()
                        + "\nMatrícula: " + voluntario.getMatricula()
                        + "\nQuantidade de ações: " + voluntario.getQuantidadeAcoes()
                        + "\nPontuação de impacto: " + voluntario.getPontuacaoImpacto();
            }
        }

        return null;
    }

    public String[] listarVoluntarios() {
        return Ranking.ordenarVoluntarios(voluntarios);
    }

    public int cadastrarPlantio(String titulo, String descricao,
                                String data, int maxParticipantes,
                                int qtdMudas) {

        LocalDateTime dataConvertida = converterData(data);

        PlantioMudas plantio = new PlantioMudas(
                proximoId,
                titulo,
                descricao,
                dataConvertida,
                maxParticipantes,
                qtdMudas
        );

        acoes.add(plantio);
        inscritos.put(proximoId, new ArrayList<>());
        proximoId++;

        return plantio.getId();
    }

    public int cadastrarMutirao(String titulo, String descricao,
                                String data, int maxParticipantes,
                                int duracaoHoras) {

        LocalDateTime dataConvertida = converterData(data);

        Mutirao mutirao = new Mutirao(
                proximoId,
                titulo,
                descricao,
                dataConvertida,
                maxParticipantes,
                duracaoHoras
        );

        acoes.add(mutirao);
        inscritos.put(proximoId, new ArrayList<>());
        proximoId++;

        return mutirao.getId();
    }

    public int cadastrarOficina(String titulo, String descricao,
                                String data, int maxParticipantes,
                                int duracaoHoras, boolean kitMaterial) {

        LocalDateTime dataConvertida = converterData(data);

        Oficina oficina = new Oficina(
                proximoId,
                titulo,
                descricao,
                dataConvertida,
                maxParticipantes,
                duracaoHoras,
                kitMaterial
        );

        acoes.add(oficina);
        inscritos.put(proximoId, new ArrayList<>());
        proximoId++;

        return oficina.getId();
    }

    public boolean inscreverVoluntario(String emailVoluntario, int idAcao) {

        Voluntario voluntario = encontrarVoluntario(emailVoluntario);
        Acao acao = encontrarAcao(idAcao);

        if (voluntario == null) {
            return false;
        }

        if (acao == null) {
            return false;
        }

        ArrayList<Voluntario> listaInscritos = inscritos.get(idAcao);

        if (listaInscritos.size() >= acao.getMaxParticipantes()) {
            throw new AcaoLotadaException(
                    "A ação já atingiu o número máximo de participantes."
            );
        }

        for (Voluntario inscrito : listaInscritos) {

            if (inscrito.getEmail().equalsIgnoreCase(emailVoluntario)) {
                throw new InscricaoDuplicadaException(
                        "O voluntário já está inscrito nesta ação."
                );
            }
        }

        listaInscritos.add(voluntario);

        voluntario.adicionarParticipacao(
                acao.calcularPontuacao()
        );

        return true;
    }

    public String exibirDetalhesAcao(int idAcao) {

        Acao acao = encontrarAcao(idAcao);

        if (acao == null) {
            return null;
        }

        ArrayList<Voluntario> listaInscritos = inscritos.get(idAcao);

        StringBuilder detalhes = new StringBuilder();

        detalhes.append("Título: ")
                .append(acao.getTitulo())
                .append("\n");

        detalhes.append("Descrição: ")
                .append(acao.getDescricao())
                .append("\n");

        detalhes.append("Data: ")
                .append(acao.getData())
                .append("\n");

        detalhes.append("Pontuação: ")
                .append(acao.calcularPontuacao())
                .append("\n");

        detalhes.append("Participantes: ")
                .append(listaInscritos.size())
                .append("/")
                .append(acao.getMaxParticipantes())
                .append("\n");

        if (acao instanceof PlantioMudas) {

            PlantioMudas plantio = (PlantioMudas) acao;

            detalhes.append("Quantidade de mudas: ")
                    .append(plantio.getQtdMudas())
                    .append("\n");

        } else if (acao instanceof Mutirao) {

            Mutirao mutirao = (Mutirao) acao;

            detalhes.append("Duração em horas: ")
                    .append(mutirao.getDuracaoHoras())
                    .append("\n");

        } else if (acao instanceof Oficina) {

            Oficina oficina = (Oficina) acao;

            detalhes.append("Duração em horas: ")
                    .append(oficina.getDuracaoHoras())
                    .append("\n");

            detalhes.append("Kit de material educativo: ")
                    .append(oficina.isKitMaterial())
                    .append("\n");
        }

        detalhes.append("Voluntários inscritos:\n");

        for (Voluntario voluntario : listaInscritos) {
            detalhes.append("- ")
                    .append(voluntario.getNome())
                    .append("\n");
        }

        return detalhes.toString();
    }

    private Voluntario encontrarVoluntario(String email) {

        for (Voluntario voluntario : voluntarios) {

            if (voluntario.getEmail().equalsIgnoreCase(email)) {
                return voluntario;
            }
        }

        return null;
    }

    private Acao encontrarAcao(int idAcao) {

        for (Acao acao : acoes) {

            if (acao.getId() == idAcao) {
                return acao;
            }
        }

        return null;
    }

    private LocalDateTime converterData(String data) {

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return LocalDateTime.parse(data, formato);
    }
}
