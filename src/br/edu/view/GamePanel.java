package br.edu.view;

import ModeloCobra.Cobrinha;
import ModeloCobra.Comida;
import CobraListaEncadeada.No;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class GamePanel extends JPanel {

    private final int TAMANHO_BLOCO = 20;
    private Cobrinha cobrinha;
    private Comida comida;

    public GamePanel(Cobrinha cobrinha, Comida comida) {
        this.cobrinha = cobrinha;
        this.comida = comida;

        this.setPreferredSize(new Dimension(700, 700));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(0, 20, 40));

        //linhas verticais
        for (int i = 0; i < getWidth() / TAMANHO_BLOCO; i++) {
            g.drawLine(i * TAMANHO_BLOCO, 0, i * TAMANHO_BLOCO, getHeight());
        }

        //linhas horizontais
        for (int i = 0; i < getHeight() / TAMANHO_BLOCO; i++) {
            g.drawLine(0, i * TAMANHO_BLOCO, getWidth(), i * TAMANHO_BLOCO);
        }

        if (cobrinha.isViva()) {
            g.setColor(Color.RED);
            g.fillOval(comida.getX() * TAMANHO_BLOCO, comida.getY() * TAMANHO_BLOCO, TAMANHO_BLOCO, TAMANHO_BLOCO);

            int totalNos = cobrinha.getCorpo().getTamanho();
            for (int i = 0; i < totalNos; i++) {
                No atual = cobrinha.getCorpo().get(i);
                if (atual != null) {
                    if (i == totalNos - 1) {
                        g.setColor(new Color(0, 93, 200)); //cabeça
                    } else {
                        g.setColor(new Color(0, 82, 140)); //corpo
                    }
                    g.fillRect(atual.getX() * TAMANHO_BLOCO, atual.getY() * TAMANHO_BLOCO, TAMANHO_BLOCO, TAMANHO_BLOCO);
                }
            }
        } else {
            //telinha de game over
            int larguraQuadrado = 300;
            int alturaQuadrado = 150;

            int xQuadrado = (700 - larguraQuadrado) / 2;
            int yQuadrado = (700 - alturaQuadrado) / 2;

            g.setColor(Color.BLACK);
            g.fillRect(xQuadrado, yQuadrado, larguraQuadrado, alturaQuadrado);

            g.setColor(new Color(0, 93, 200));
            g.drawRect(xQuadrado, yQuadrado, larguraQuadrado, alturaQuadrado);

            g.setColor(Color.RED);
            g.drawString("GAME OVER", xQuadrado + 110, yQuadrado + 60);

            g.setColor(Color.WHITE);
            g.drawString("Pontos: " + cobrinha.getCorpo().getTamanho(), xQuadrado + 115, yQuadrado + 100);
        }
    }
}
