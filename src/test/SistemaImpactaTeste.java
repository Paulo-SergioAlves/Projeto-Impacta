package test;

import impacta.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    @DisplayName("Deve desempatar os volúntarios por nome")
    public void deveDesempatarPorNome() {

        Voluntario voluntario1 =
                new Voluntario("Daniel", "daniel2@email.com", "004");

        Voluntario voluntario2 =
                new Voluntario("Lucas", "lucas2@email.com", "005");

        Voluntario voluntario3 =
                new Voluntario("Paulo", "paulo2@email.com", "006");

        voluntario1.adicionarParticipacao(60);
        voluntario2.adicionarParticipacao(60);
        voluntario3.adicionarParticipacao(10);

        java.util.List<Voluntario> voluntarios =
                new java.util.ArrayList<>();

        voluntarios.add(voluntario1);
        voluntarios.add(voluntario2);
        voluntarios.add(voluntario3);

        String[] resultado =
                Ranking.ordenarVoluntarios(voluntarios);

        assertEquals(
                "Daniel - 1 ações - 60 pontos",
                resultado[0]
        );

        assertEquals(
                "Lucas - 1 ações - 60 pontos",
                resultado[1]
        );

        assertEquals(
                "Paulo - 1 ações - 10 pontos",
                resultado[2]
        );
    }

    @Test
    @DisplayName("Deve Calcular corretamente a Pontuação do Plantio de mudas")
    public void deveCalcularPontuacaoPlantio() {
        PlantioMudas plantio = new PlantioMudas(
                1,"Plantio de arvores","Plantio de mudas", LocalDateTime.now(),20,10
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

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar email duplicado ")
    public void develancarExcecaoEmailDuplicado() {

        Impacta sistema = new Impacta();

        sistema.cadastrarVoluntario(
                "Daniel",
                "daniel@email.com",
                "001");


        assertThrows(
                EmailDuplicadoException.class,
                () -> sistema.cadastrarVoluntario(
                        "Lucas",
                        "daniel@email.com",
                        "002")

        );
    }

    @Test
    @DisplayName("Deve lançar exceção ao inscrever voluntário em ação lotada")
    public void deveLancarExcecaoAcaoLotada() {

        Impacta sistema = new Impacta();

        sistema.cadastrarVoluntario(
                "Daniel",
                "daniel@gmail.com",
                "001"
        );

        sistema.cadastrarVoluntario(
                "Lucas",
                "lucas@email.com",
                "002"


        );

        int idAcao = sistema.cadastrarPlantio(
                "Plantio de arvores",
                "Plantio de mudas",
                "05/09/2026 14:00",
                1,
                10


        );

        sistema.inscreverVoluntario("daniel@gmail.com" , idAcao);

        assertThrows(
                AcaoLotadaException.class,
                () -> sistema.inscreverVoluntario("lucas@email.com", idAcao)
        );
    }

    @Test
    @DisplayName("Deve lançar exceção ao inscrever voluntário duas vezes na mesma ação")
    public void deveLancarExcecaoInscricaoDuplicada() {
        Impacta sistema = new Impacta();

        sistema.cadastrarVoluntario(
                "Daniel",
                "daniel@gmail.com",
                "001"


        );

        int idAcao = sistema.cadastrarPlantio(
                "Plantio de arvores",
                "Plantio de mudas",
                "05/09/2026 14:00",
                5,
                10



        );

        sistema.inscreverVoluntario("daniel@gmail.com", idAcao);

        assertThrows(
                InscricaoDuplicadaException.class,
                () -> sistema.inscreverVoluntario("daniel@gmail.com", idAcao)
        );
    }


}




