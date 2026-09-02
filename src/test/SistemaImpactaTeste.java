package test;
import impacta.Mutirao; // importei a classe
import impacta.PlantioMudas;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SistemaImpactaTeste {

    @Test
    @DisplayName("Deve Calcular corretamente a Pontuação do Plantio de mudas")
    public void deveCalcularPontuacaoPlantio() {
        PlantioMudas plantio = new PlantioMudas(
                1,"Plantio de arvores","Plantio de mudas",LocalDateTime.now(),20,10
                );
        assertEquals(25,plantio.calcularPontuacao());


    }
    @Test
    @DisplayName("Deve Calcular corretamente a Pontuacão do Mutirao")
    public void deveCalcularPontuacaoMutirao() {
        Mutirao mutirao = new Mutirao(2,"Mutirao de reciclagem","Coleta de material de reciclagem",LocalDateTime.now(),20,5
        );
        assertEquals(20, mutirao.calcularPontuacao());
    }

}
