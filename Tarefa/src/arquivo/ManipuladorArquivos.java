// Pacote para as classes relacionadas à manipulação de arquivos
package arquivo;

import tarefas.Tarefa;
import tarefas.TarefaRecorrente;
import tarefas.TarefaUnica;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// Classe para manipulação de arquivos
public class ManipuladorArquivos {
    public static List<Tarefa> lerArquivo(String nomeArquivo) throws IOException {
        
        List<Tarefa> tarefas = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
        
            String linha;
            reader.readLine(); // Ignorar a primeira linha, que contém os cabeçalhos
        
            while ((linha = reader.readLine()) != null) {
                
                String[] dados = linha.split(";");
                
                if (dados.length == 5) {
                
                    String descricao = dados[0];
                    String tipo      = dados[1];
                    String status    = dados[2];
                    String data      = dados[3];
                    String hora      = dados[4];
                    
                    Tarefa tarefa;

                    if ("Recorrente".equals(tipo)) {
                        tarefa = new TarefaRecorrente(descricao, status, data, hora);
                    } else if ("Única".equals(tipo)) {
                        tarefa = new TarefaUnica(descricao, status, data, hora);
                    } else {
                        System.out.println("Tipo de tarefa desconhecido: " + tipo);
                        continue; // Pular para a próxima iteração do loop
                    }

                    tarefas.add(tarefa);
                
                } else System.out.println("Formato inválido para a linha: " + linha);
            
            }
        
        }

        return tarefas;
    }

    public static void escreverArquivo(String nomeArquivo, List<Tarefa> tarefas) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
        
            writer.write("Descrição;Tipo;Status;Data;Hora"); // Escrever cabeçalhos
            writer.newLine();    
        
            for (Tarefa tarefa : tarefas) { // Escrever cada tarefa no arquivo
                String linha = formatarLinhaTarefa(tarefa);
                writer.write(linha);
                writer.newLine();
            }
        
        }
    
    }
    
    private static String formatarLinhaTarefa(Tarefa tarefa) {

        if (tarefa instanceof TarefaRecorrente) {
            TarefaRecorrente recorrente = (TarefaRecorrente) tarefa;
            return String.format("%s;Recorrente;%s;%s;%s", recorrente.getDescricao(), recorrente.getStatus(), recorrente.getDataString(), recorrente.getHora());
        } else if (tarefa instanceof TarefaUnica) {
            TarefaUnica unica = (TarefaUnica) tarefa;
            return String.format("%s;Única;%s;%s;%s", unica.getDescricao(), unica.getStatus(), unica.getDataString(), unica.getHora());
        }
        
        return ""; // Tratar outros tipos de tarefa, se necessário

    }

}
