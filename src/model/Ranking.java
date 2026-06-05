package model;

public class Ranking {
    private Jogador[] jogadores = new Jogador[10];

    public Ranking() {
        // Dados de teste
        cadastrarJogador(new Jogador("Mauricio", 50));
        cadastrarJogador(new Jogador("Anna", 35));
        cadastrarJogador(new Jogador("João", 42));
    }

    // percorre o vetor e na primeira posição nula que encontra, adiciona o novo jogador
    public void cadastrarJogador(Jogador jogador){
        for(int i = 0; i < jogadores.length; i++){
            if(jogadores[i] == null){
                jogadores[i] = jogador  ;
                break;
            }
        }
    }

    /* usando o insertion sort e mudando a logica para decrescente,
    para exibir o ranking do jogador com a maior pontuação até a menor */
    private void ordenarRanking(){
        int i, j;
        Jogador aux;

        for(i = 1; i < jogadores.length; i++){
            if(jogadores[i] != null){
                aux = jogadores[i]; // guarda o jogador atual que será inserido na pos correta
                j = i - 1; // começa comparando com o anterior

                while(j >= 0  && jogadores[j] != null && jogadores[j].getMaiorPontuacao() < aux.getMaiorPontuacao()){ // enquanto houver elementos menores qua aux
                    jogadores[j + 1] = jogadores[j]; // move para a direita (sentido fim do vetor)
                    j--; // vai para o elemento anterior
                }

                jogadores[j + 1] = aux; // insere o jogador na posição correta
            }
        }
    }

    public boolean editarNomeJogador(int posicao, String novoNome){
        if(posicao >= 0 && posicao < jogadores.length && jogadores[posicao] != null){
            jogadores[posicao].setNome(novoNome);
            return true;
        }
        return false;
    }

    public boolean removerJogador(int posicao){
        if(posicao >= 0 && posicao < jogadores.length && jogadores[posicao] != null){
            jogadores[posicao] = null;

            // reorganizando o vetor
            for(int i = posicao; i < jogadores.length - 1; i++){
                jogadores[i] = jogadores[i + 1];
            }
            jogadores[9] = null;
            return true;
        }
        return false;
    }

    // percorre o vetor e seta todos os jogadores como nulos
    public void resetarRanking(){
        for(int i = 0; jogadores.length > i; i++){
            jogadores[i] = null;
        }
    }

    public void exibirRanking(){
        // antes de exibir o ranking, ordena ele
        ordenarRanking();
        for(int i = 0; jogadores.length > i; i++){
            if(jogadores[i] != null){
                System.out.println("Nome: " + jogadores[i].getNome() + "Pontuação: " + jogadores[i].getMaiorPontuacao());
            }
        }
    }

    // método de exibir o ranking como string (pro optionpane conseguir receber)
    public String exibirRankingString(){
        ordenarRanking();;

        String textoRanking = "Placar de Recordes\n\n";
        boolean vazio = true;

        for (int i = 0; i < jogadores.length; i++){
            if(jogadores[i] != null){
                vazio = false;
                // mostra a posição, o nome e a pontuação do jogador
                textoRanking = textoRanking + "[" + i + "] Nome: " + jogadores[i].getNome() + " - " + jogadores[i].getMaiorPontuacao() + " pontos\n";
            }
        }

        if (vazio){
            textoRanking = textoRanking + "Nenhum recorde registrado ainda!";
        }

        return textoRanking;
    }
}