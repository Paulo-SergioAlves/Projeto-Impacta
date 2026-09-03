package test;

import impacta.Ranking;
import impacta.Voluntario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SistemaImpactaTeste {

    @Test
    @DisplayName(" Deve ordenar os voluntários por pontuação ")
    public void deveOrdenarVoluntariosPorPontuacao() {

        Voluntario voluntario1 =
                new Voluntario("Daniel", "daniel@email.com", "001");

        Voluntario voluntario2 =
                new Voluntario("Lucas", "lucas@email.com", "002");

        Voluntario voluntario3 =
                new Voluntario("Paulo", "paulo@email.com", "003");

        voluntario1.adicionarParticipacao(20);
        voluntario2.adicionarParticipacao(50);
        voluntario3.adicionarParticipacao(30);

        java.util.List<Voluntario> voluntarios =
                new java.util.ArrayList<>();

        voluntarios.add(voluntario1);
        voluntarios.add(voluntario2);
        voluntarios.add(voluntario3);

        String[] resultado =
                Ranking.ordenarVoluntarios(voluntarios);

        assertEquals(
                "Lucas - 1 ações - 50 pontos",
                resultado[0]
        );

        assertEquals(
                "Paulo - 1 ações - 30 pontos",
                resultado[1]
        );

        assertEquals(
                "Daniel - 1 ações - 20 pontos",
                resultado[2]
        );
    }



}
