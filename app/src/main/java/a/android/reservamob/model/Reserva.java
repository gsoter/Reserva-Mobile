package a.android.reservamob.model;


public class Reserva {

    private int id;
    private String dt_chegada;
    private String dt_saida;
    private Double valor_total;

    public Reserva(int id, String dt_chegada, String dt_saida, Double valor_total) {
        this.id = id;
        this.dt_chegada = dt_chegada;
        this.dt_saida = dt_saida;
        this.valor_total = valor_total;
    }

    public Reserva() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDt_chegada() {
        return dt_chegada;
    }

    public void setDt_chegada(String dt_chegada) {
        this.dt_chegada = dt_chegada;
    }

    public String getDt_saida() {
        return dt_saida;
    }

    public void setDt_saida(String dt_saida) {
        this.dt_saida = dt_saida;
    }

    public Double getValor_total() {
        return valor_total;
    }

    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }
}
