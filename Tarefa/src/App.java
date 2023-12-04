import arquivo.ManipuladorArquivos;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import tarefas.*;

public class App {
    public static void main(String[] args) {
        
        String caminhoTarefas = "src/arquivo/tarefas.csv";

        // Leitura do arquivo CSV
        try {

            List<Tarefa> tarefas = ManipuladorArquivos.lerArquivo(caminhoTarefas);
            System.out.println("Tarefas lidas do arquivo:");

            // Supõe que Tarefa tem um método toString()
            for (Tarefa tarefa : tarefas) System.out.println(tarefa);

            // Exemplo de operações com as tarefas lidas
            if (!tarefas.isEmpty()) {

                System.out.println("\nSimulação de operações:");

                // Agendar a primeira tarefa para daqui a 2 dias
                Tarefa primeiraTarefa = tarefas.get(0);
                Date novaData = new Date(System.currentTimeMillis() + 2 * 86400000L); // 2 dias
                primeiraTarefa.agendar(novaData);

                // Concluir a segunda tarefa
                Tarefa segundaTarefa = tarefas.get(1);
                segundaTarefa.setStatus(1); // 1. Concluida | 2. Agendada | 3. Em andamento

                // Mostrar o estado atual das tarefas após as operações
                System.out.println("\nEstado atual das tarefas:");
                for (Tarefa tarefa : tarefas) System.out.println(tarefa);

            }

            if (tarefas.isEmpty()) {

                System.out.println("\nA lista de tarefas está vazia.");
                System.out.println("\nNão há nada para escrever no arquivo.\n");
                
            } else {

                try {
                    ManipuladorArquivos.escreverArquivo(caminhoTarefas, tarefas);
                    System.out.println("\nTarefas foram escritas no arquivo com sucesso.\n");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("\nErro ao escrever as tarefas no arquivo.\n");
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
