package model;

import model.listaSimplesEncadeada.EstruturaCobra;
import model.listaSimplesEncadeada.No;

public class Cobrinha {
    private static final int LARGURA_TELA = 35;
    private static final int ALTURA_TELA = 35;

    private EstruturaCobra corpo;
    private boolean viva;

    public Cobrinha() {
        this.corpo = new EstruturaCobra();
        this.viva = true;
        corpo.adicionarNoFim(10, 10);
    }

    public void mover(int direcao, boolean comeuComida) {
        No cabecaAtual = corpo.get(corpo.getTamanho() - 1);
        int proximoX = cabecaAtual.getX();
        int proximoY = cabecaAtual.getY();

        switch (direcao) {
            case 0 -> proximoY--; //cima
            case 1 -> proximoX++; //direita
            case 2 -> proximoY++; //baixo
            case 3 -> proximoX--; //esquerda
        }

        if (colidiuComParede(proximoX, proximoY) || colidiuComCorpo(proximoX, proximoY)) {
            viva = false;
            return;
        }

        corpo.adicionarNoFim(proximoX, proximoY);

        if (!comeuComida) {
            corpo.removerDoInicio();
        }
    }

    private boolean colidiuComParede(int x, int y) {
        return x < 0 || x >= LARGURA_TELA || y < 0 || y >= ALTURA_TELA;
    }

    private boolean colidiuComCorpo(int x, int y) {
        int limite = corpo.getTamanho() - 1;
        for (int i = 0; i < limite; i++) {
            No no = corpo.get(i);
            if (no.getX() == x && no.getY() == y) {
                return true;
            }
        }
        return false;
    }

    public boolean isViva() {
        return viva;
    }

    public EstruturaCobra getCorpo() {
        return corpo;
    }
}