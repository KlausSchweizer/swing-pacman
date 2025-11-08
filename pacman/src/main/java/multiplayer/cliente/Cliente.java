package multiplayer.cliente;

import personagem.Personagem;

import java.io.Serializable;

public class Cliente implements Serializable {
    private String nome;
    private Personagem personagem;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }
}
