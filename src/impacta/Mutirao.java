package impacta;

import java.time.LocalDateTime;

public class Mutirao extends Acao {

    //duração do mutirão em horas
    private int duracaoHoras;

    public Mutirao(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes, int duracaoHoras){
        super(id, titulo, descricao, data, maxParticipantes);
        this.duracaoHoras = duracaoHoras;
    }

    public int getDuracaoHoras(){
        return  duracaoHoras;
    }

    public void setDuracaoHoras(int duracaoHoras){
        this.duracaoHoras = duracaoHoras;
    }

    //subscreve o metodo da classe ação
    @Override
    public int calcularPontuacao(){
        return duracaoHoras *4;
    }
}

