package br.com.guilherme.elevator;

import br.com.guilherme.elevator.dto.ElevatorUsageDTO;
import br.com.guilherme.elevator.dto.ElavetorOutputDTO;
import br.com.guilherme.elevator.services.ElevatorService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Lê o arquivo input.json
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = Main.class.getResourceAsStream("/input.json");

            if (inputStream == null) {
                System.err.println("Arquivo input.json não encontrado em /resources.");
                return;
            }

            List<ElevatorUsageDTO> usageList = mapper.readValue(inputStream, new TypeReference<List<ElevatorUsageDTO>>() {});

            // Processa os dados
            ElevatorService service = new ElevatorService();
            ElavetorOutputDTO output = service.analyze(usageList);

            // Imprime o resultado
            System.out.println("Resultado da análise:");
            System.out.println(output);

        } catch (Exception e) {
            System.err.println("Erro ao processar os dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}