package br.edu.view;

import Modelo.Cobrinha;
import Modelo.Comida;
import Modelo.Ranking;
import br.edu.control.GerenciadorJogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {
    private Ranking ranking;

    private JButton btnJogar;
    private JButton btnVerRanking;
    private JButton btnAreaAdm;

    // Construtor que inicializa a tela
    public MenuPrincipal(Ranking ranking){
        this.ranking = ranking;

        setTitle("Jogo da Cobrinha - Menu Principal");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centraliza o menu
        setResizable(false); // bloqueia o redirecionamento da janela

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));

        JLabel lblTitulo = new JLabel();
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnJogar = new JButton("Jogar");
        btnVerRanking = new JButton("Ver Ranking");
        btnAreaAdm = new JButton("Área do ADM");

        btnJogar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVerRanking.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAreaAdm.setAlignmentX(Component.CENTER_ALIGNMENT);

        Dimension tamanhoBotao = new Dimension(200,40);
        btnJogar.setMaximumSize(tamanhoBotao);
        btnVerRanking.setMaximumSize(tamanhoBotao);
        btnAreaAdm.setMaximumSize(tamanhoBotao);

        painel.add(lblTitulo);
        painel.add(Box.createRigidArea(new Dimension(0, 30)));
        painel.add(btnJogar);
        painel.add(Box.createRigidArea(new Dimension(0, 30)));
        painel.add(btnVerRanking);
        painel.add(Box.createRigidArea(new Dimension(0, 30)));
        painel.add(btnAreaAdm);

        add(painel);

        configuracaoCliquesBotoes();
    }

    // método para configurar os botões
    private void configuracaoCliquesBotoes(){

        // botão jogar - redireciona para a tela do jogo
        btnJogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // esconde o menu

                // montando a janela q vai exibir o jogo
                JFrame janelaDoJogo = new JFrame();

                // instanciando os elementos essenciais do jogo, passando o corpo da cobra para a comida
                Cobrinha cobrinha = new Cobrinha();
                Comida comida = new Comida(cobrinha.getCorpo());

                // criando o painel visual da partida
                GamePanel painelJogo = new GamePanel(cobrinha, comida);

                // gerenciador do jogo com todos os parãmetros necessários
                GerenciadorJogo gerenciadorJogo = new GerenciadorJogo(cobrinha, comida, painelJogo, ranking, janelaDoJogo, MenuPrincipal.this);

                // conectando o teclado ao painel do jogo
                painelJogo.addKeyListener(gerenciadorJogo);

                janelaDoJogo.add(painelJogo);
                janelaDoJogo.setTitle("Snake Game");
                janelaDoJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                janelaDoJogo.pack();
                janelaDoJogo.setLocationRelativeTo(null);
                janelaDoJogo.setVisible(true);

                // captura das teclas do teclado
                painelJogo.requestFocusInWindow();
            }
        });

        btnVerRanking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, ranking.exibirRankingString());
            }
        });

        btnAreaAdm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // campo para pedir a senha e deixar (***)
                JPasswordField campoSenha = new JPasswordField();

                /* agrupa o texto e o campo de senha em um array de objetos, permitindo que fiquem empilhados
                dentro do joptionpane
                 */
                Object[] mensagemECampoSenha = {
                        "Digite a senha do Administrador: ",
                        campoSenha
                };

                int janelaSenhaAdm = JOptionPane.showOptionDialog(
                        null,
                        mensagemECampoSenha,
                        "Área Restrita",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null, null, null
                );

                // se fechar a janela ou cancelar, interrompe o acesso
                if (janelaSenhaAdm != JOptionPane.OK_OPTION) { return; }

                // pegando a senha digitada pelo usuário
                String senha = new String(campoSenha.getPassword());

                // validação da senha
                if("adminjogo123".equals(senha)){
                    // se a senha estiver correta, exibe o menu de administrador
                    String opcao = JOptionPane.showInputDialog(null,
                            """
                                    Bem- vindo Adm! Escolha uma opção:
                                    1 - Editar Nome de um Jogador
                                    2 - Remover Jogador do Ranking
                                    3 - Resetar Ranking
                                    4 - Visualizar Ranking
                                    """,
                            "Menu do Administrador", JOptionPane.QUESTION_MESSAGE);
                    if(opcao == null){ return; }

                    // 1 - editar o nome do usuário
                    if("1".equals(opcao)){
                        String posicaoSt = JOptionPane.showInputDialog(null, "Digite a posição do jogador (0 a 9): ");
                        if(posicaoSt == null) { return; }

                        String novoNome = JOptionPane.showInputDialog("Digite o novo nome: ");
                        if(novoNome == null) return;

                        try{
                            // convertendo de string para int
                            int posicao = Integer.parseInt(posicaoSt);

                            // chama o método e passa os parametros já salvos acima, alterando o nome do jogaodor
                            if (ranking.editarNomeJogador(posicao, novoNome)){
                                JOptionPane.showMessageDialog(null, "Nome alterado com sucesso!");
                            } else {
                                JOptionPane.showMessageDialog(null, "Erro ao salvar alterações! Verifique a posição digitada.");
                            }

                        } catch (NumberFormatException ex){
                            JOptionPane.showMessageDialog(null, "Digite um número válido na posição.");
                        }
                    }

                    // 2 - Remover jogador
                    if("2".equals(opcao)){
                        String posicaoSt = JOptionPane.showInputDialog(null, "Digite a posição do jogador (0 a 9): ");
                        if (posicaoSt == null){ return; }

                        try{
                            int posicao = Integer.parseInt(posicaoSt);
                            // chama o método e passa a posicao do jogaodor para exclui-lo
                            if (ranking.removerJogador(posicao)){
                                JOptionPane.showMessageDialog(null,"Jogador deletado com sucesso!");
                            } else {
                                JOptionPane.showMessageDialog(null,"Erro ao excluir jogador. Verifique a posição digitada");
                            }
                        } catch (NumberFormatException ex){
                            JOptionPane.showMessageDialog(null, "Digite um número válido na posição.");
                        }
                    }

                    // 3 - resetar o ranking
                    if("3".equals(opcao)){
                        int confirmacaoExclusao = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja resetar o ranking?");
                        if(confirmacaoExclusao == JOptionPane.YES_OPTION){
                            ranking.resetarRanking();
                            JOptionPane.showMessageDialog(null,"Ranking resetado com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Opção inválida!");
                        }
                    }

                    // 4 - ver ranking
                    if("4".equals(opcao)){
                        JOptionPane.showMessageDialog(null, ranking.exibirRankingString());
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Senha Incorreta! Acesso negado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}