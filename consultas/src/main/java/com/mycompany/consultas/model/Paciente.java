package com.mycompany.consultas.model;

public class Paciente {
    private int id;
    private String nome;
    private String cpf;
    private String telefone;

    // Construtor vazio (necessário para o DAO funcionar)
    public Paciente() {}

    // Construtor completo
    public Paciente(int id, String nome, String cpf, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    // Getters e Setters
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

    public String getTelefone() { 
        return telefone; 
    }
    public void setTelefone(String telefone) { 
        this.telefone = telefone; 
    }
    
    public String toString() {
        return nome;
    }
}
