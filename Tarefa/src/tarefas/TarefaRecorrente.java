package tarefas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Subclasse de Tarefa que representa uma tarefa recorrente
public class TarefaRecorrente extends Tarefa {

    private String data; // Representa a data como uma String

    public TarefaRecorrente(String descricao, String status, String data, String hora) {
        super(descricao, status, hora);
        this.data = data;
    }

    public String getDataString() {
        return data;
    }

    // Método para configurar a data a partir de uma String
    public void setDataString(String data) {
        this.data = data;
    }

    // Método para obter a data como um objeto Date
    public Date getData() {

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace(); // Lidar com a exceção ou simplesmente retornar null
            return null;
        }

    }

    // Método para configurar a data a partir de um objeto Date
    public void setData(Date data) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.data = dateFormat.format(data);
    }

    // Método toString para fornecer uma representação de string da TarefaRecorrente
    @Override
    public String toString() {

        String resposta  = "";
               resposta += "\nTarefa Recorrente:";
               resposta += "\n------------------";
               resposta += "\nDescrição = '" + getDescricao()  + "'";
               resposta += "\nStatus    = '" + getStatus()     + "'";
               resposta += "\nData      = '" + getDataString() + "'";
               resposta += "\nHora      = '" + getHora()       + "'";
        return resposta;
        
    }

    @Override
    public void agendar(Date novaData) {
        System.out.println("Tarefa recorrente agendada para: " + novaData);
        setData(novaData);
    }

}
