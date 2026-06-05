package Modelo;

import CobraListaEncadeada.EstruturaCobra;
import CobraListaEncadeada.No;

import java.util.Random;

public class Comida {
    private static final int LARGURA_TELA = 35;
    private static final int ALTURA_TELA = 35;

    private int x;
    private int y;
    private final Random random = new Random();

    public Comida(EstruturaCobra cobra) {
        gerarNovaPosicao(cobra);
    }

    public void gerarNovaPosicao(EstruturaCobra cobra) {
        int novoX, novoY;

        do {
            novoX = random.nextInt(LARGURA_TELA);
            novoY = random.nextInt(ALTURA_TELA);
        } while (colidiuComCobra(novoX, novoY, cobra));

        this.x = novoX;
        this.y = novoY;
    }

    private boolean colidiuComCobra(int x, int y, EstruturaCobra cobra) {
        for (int i = 0; i < cobra.getTamanho(); i++) {
            No no = cobra.get(i);
            if (no != null && no.getX() == x && no.getY() == y) {
                return true;
            }
        }
        return false;
    }

    public int getX() { return x; }
    public int getY() { return y; }
}