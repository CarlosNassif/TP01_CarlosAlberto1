package com.example.tp01_carlosalberto;

public class Pessoa {

    private static String nome;
    private static String email;
    private static String numero;

    public Pessoa(String nome, String email, String numero) {
        setNome(nome);
        setEmail(email);
        setNumero(numero);
    }

    // Sets
    private void setNome(String nome) {
        this.nome = nome;
    }
    private void setEmail(String email) {
        this.email = email;
    }
    private void setNumero(String numero) { this.numero = numero; }

    // Gets
    public String getNome() {
        return this.nome;
    }
    public String getEmail() {
        return this.email;
    }
    public String getNumero() { return this.numero; }
}
