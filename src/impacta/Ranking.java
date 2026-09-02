package impacta;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Ranking {

    public static String[] ordenarVoluntarios(List<Voluntario> voluntarios) {

        List<Voluntario> ranking = new ArrayList<>(voluntarios);

        ranking.sort(
                Comparator.comparingInt(Voluntario::getPontuacaoImpacto)
                        .reversed()
                        .thenComparing(Voluntario::getNome)
        );

        String[] resultado = new String[ranking.size()];

        for (int i = 0; i < ranking.size(); i++) {
            Voluntario voluntario = ranking.get(i);

            resultado[i] =
                    voluntario.getNome()
                            + " - "
                            + voluntario.getQuantidadeAcoes()
                            + " ações - "
                            + voluntario.getPontuacaoImpacto()
                            + " pontos";
        }

        return resultado;
    }
}

