package impacta;

public class Voluntario {

    private String nome;
    private String email;
    private String matricula;
    private int quantidadeAcoes;
    private int pontuacaoImpacto;

    public Voluntario(String nome, String email, String matricula) {
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.quantidadeAcoes = 0;
        this.pontuacaoImpacto = 0;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getQuantidadeAcoes() {
        return quantidadeAcoes;
    }

    public int getPontuacaoImpacto() {
        return pontuacaoImpacto;
    }

    public void adicionarParticipacao(int pontos) {
        quantidadeAcoes++;
        pontuacaoImpacto += pontos;
    }
}
