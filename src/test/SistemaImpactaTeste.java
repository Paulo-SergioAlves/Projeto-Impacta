package test;
import impacta.Mutirao; // importei a classe
import impacta.Oficina;
import impacta.PlantioMudas;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SistemaImpactaTeste {

    @Test
    @DisplayName("Deve calcular corretamente a pontuação do Plantio de mudas")
    public void deveCalcularPontuacaoPlantio() {
        PlantioMudas plantio = new PlantioMudas(
                1,"Plantio de arvores","Plantio de mudas",LocalDateTime.now(),20,10
                );
        assertEquals(25,plantio.calcularPontuacao());


    }
    @Test
    @DisplayName("Deve calcular corretamente a pontuacão do Mutirao")
    public void deveCalcularPontuacaoMutirao() {
        Mutirao mutirao = new Mutirao(2,"Mutirao de reciclagem","Coleta de material de reciclagem",LocalDateTime.now(),20,5
        );
        assertEquals(20, mutirao.calcularPontuacao());
    }


    @Test
    @DisplayName("Deve calcular corretamente a pontuação  da Oficina sem kit")
    public void deveCalcularPontuacaoOficina() {
        Oficina oficina = new Oficina(3,"Oficina ecológica","Reciclagem",LocalDateTime.now(),20,2,false);
        assertEquals(6,oficina.calcularPontuacao());
    }


}
