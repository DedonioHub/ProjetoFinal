package tarefas;

// Classe abstrata para representar uma tarefa genérica.
public abstract class Tarefa implements Agendavel {

    private String descricao;
    private String status;    // 1. Concluída | 2. Agendada | 3. Em andamento
    private String hora;

    public Tarefa(String descricao, String status, String hora) {
        this.descricao = descricao;
        this.status    = status;
        this.hora      = hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(int status) {
        switch (status) {
            case 1: this.status = "Concluída";    break;
            case 2: this.status = "Agendada";     break;
            case 3: this.status = "Em andamento"; break;
        }
    }

    public String getHora() {
        return hora;
    }

}
