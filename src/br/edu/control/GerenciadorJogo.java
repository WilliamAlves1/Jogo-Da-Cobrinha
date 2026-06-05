package br.edu.control;

import Modelo.Cobrinha;
import Modelo.Comida;
import CobraListaEncadeada.No;
import Modelo.Jogador;
import Modelo.Ranking;
import br.edu.view.GamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GerenciadorJogo extends KeyAdapter implements ActionListener {

    private Ranking ranking;
    private Cobrinha cobrinha;
    private Comida comida;
    private GamePanel gamePanel;
    private Timer timer;
    private int direcaoAtual = 1;
    private JFrame janelaDoJogo;
    private JFrame menuPrincipal;

    public GerenciadorJogo(Cobrinha cobrinha, Comida comida, GamePanel gamePanel, Ranking ranking, JFrame janelaDoJogo, JFrame menuPrincipal) {
        this.cobrinha = cobrinha;
        this.comida = comida;
        this.gamePanel = gamePanel;
        this.ranking = ranking;
        this.janelaDoJogo = janelaDoJogo;
        this.menuPrincipal = menuPrincipal;

        this.gamePanel.getBtnVoltarMenu().addActionListener(e -> {
            this.janelaDoJogo.dispose();
            this.menuPrincipal.setVisible(true);
        });

        this.timer = new Timer(130, this);
        this.timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (cobrinha.isViva()) {
            int indexCabeca = cobrinha.getCorpo().getTamanho() - 1;
            No cabeca = cobrinha.getCorpo().get(indexCabeca);

            boolean comeuComida = (cabeca.getX() == comida.getX() && cabeca.getY() == comida.getY());

            cobrinha.mover(direcaoAtual, comeuComida);

            if (comeuComida) {
                comida.gerarNovaPosicao(cobrinha.getCorpo());
            }

            if (!cobrinha.isViva()) {
                timer.stop();

                gamePanel.getBtnVoltarMenu().setVisible(true);
                gamePanel.repaint();

                // cadastrando o usuário e salvando a pontuação dele assim que o jogo acaba
                SwingUtilities.invokeLater(() -> {
                    try{
                        String nome = JOptionPane.showInputDialog(null, "GAME OVER!\nDigite seu nome: ");

                        if(nome == null){ return; }

                        if(nome.trim().isEmpty()){
                            throw new IllegalArgumentException("Nome Inválido!");
                        }

                        // pegando a pontuação do usuário
                        int maiorPontuacao = cobrinha.getCorpo().getTamanho();

                        // cadastrando o jogador
                        Jogador jogador = new Jogador(nome, maiorPontuacao);
                        ranking.cadastrarJogador(jogador);
                        JOptionPane.showMessageDialog(null,"Recorde salvo com sucesso!");
                    } catch (IllegalArgumentException erro) {
                        JOptionPane.showMessageDialog(null, "Erro: " + erro.getMessage());
                    }
                });

                return;
            }
        }

        gamePanel.repaint();
    }

    //pega as setas do teclado para mudar a direção da cobrinha
    @Override
    public void keyPressed(KeyEvent e) {
        int codigoTecla = e.getKeyCode();

        if ((codigoTecla == KeyEvent.VK_UP) && (direcaoAtual != 2)) {
            direcaoAtual = 0;
        }
        if ((codigoTecla == KeyEvent.VK_RIGHT) && (direcaoAtual != 3)) {
            direcaoAtual = 1;
        }
        if ((codigoTecla == KeyEvent.VK_DOWN) && (direcaoAtual != 0)) {
            direcaoAtual = 2;
        }
        if ((codigoTecla == KeyEvent.VK_LEFT) && (direcaoAtual != 1)) {
            direcaoAtual = 3;
        }
    }
}