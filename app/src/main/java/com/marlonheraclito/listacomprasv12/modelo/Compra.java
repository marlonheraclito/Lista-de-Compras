package com.marlonheraclito.listacomprasv12.modelo;

public class Compra {

    private int id;
    private String nome;
    private int quant;
    private float preco;
    private float total;

    public Compra(String nome, int quant, float preco, float total) {
        this.nome = nome;
        this.quant = quant;
        this.preco = preco;
        this.total = total;
    }

    public Compra(int id, String nome, int quant, float preco, float total) {
        this.id = id;
        this.nome = nome;
        this.quant = quant;
        this.preco = preco;
        this.total = total;
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

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
