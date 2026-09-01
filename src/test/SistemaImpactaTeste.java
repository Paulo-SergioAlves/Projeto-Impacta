package test;

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

}
