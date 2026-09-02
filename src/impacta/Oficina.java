package impacta;

import java.time.LocalDateTime;

public class Oficina extends Acao {

    //duracao da oficina em horas
    private int duracaoHoras;
    //indica se a oficina oferece kit medico educativo
    private boolean kitMaterial;

    public Oficina(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes, int duracaoHoras, boolean kitMaterial){

        super(id, titulo, descricao, data, maxParticipantes);
        this.duracaoHoras = duracaoHoras;
        this.kitMaterial = kitMaterial;
    }

    public int getDuracaoHoras(){
        return duracaoHoras;
    }

    public void setDuracaoHoras(int duracaoHoras) {
        this.duracaoHoras = duracaoHoras;
    }

    public boolean isKitMaterial(){
        return kitMaterial;
    }

    public void setKitMaterial(boolean kitMaterial) {
        this.kitMaterial = kitMaterial;
    }
    @Override
    public int calcularPontuacao(){
        int pontuacao = duracaoHoras *3;

        if (kitMaterial){
            pontuacao +=10;
        }
        return pontuacao;
    }
}
