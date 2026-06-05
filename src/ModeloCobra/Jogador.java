package ModeloCobra;

public class Jogador {
    private String nome;
    private int maiorPontuacao;

    public Jogador(){

    }

    public Jogador(String nome, int maiorPontuacao){
        this.nome = nome;
        this.maiorPontuacao = maiorPontuacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMaiorPontuacao() {
        return maiorPontuacao;
    }

    public void setMaiorPontuacao(int maiorPontuacao) {
        this.maiorPontuacao = maiorPontuacao;
    }
}
