package a.android.reservamob.model;

public class Cliente {

    private int id;
    private String nome;
    private String cpf;
    private String usuario;
    private String senha;


    public Cliente(int id, String nome, String cpf, String usuario, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Cliente() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}
