package br.jus.projetobd2.Beans;

public class Produto {
    private String nome;
    private float preco;
    private String descricao;
    private int id;

    public Produto(int id, String nome, float preco, String desc) {
        setNome(nome);
        setPreco(preco);
        setDescricao(desc);
        setId(id);//Quando o banco de dados retorna o id
    }

    public Produto(String nome, float preco, String desc) {
        setNome(nome);
        setPreco(preco);
        setDescricao(desc);
        setId(0); //Quando não tem o id
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(!nome.isEmpty()) {
            this.nome = nome;
        }
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        if(preco >= 0) {
            this.preco = preco;
        } else {
            this.preco = 0;
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
