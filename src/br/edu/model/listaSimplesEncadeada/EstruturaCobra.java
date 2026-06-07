package br.edu.model.listaSimplesEncadeada;

public class EstruturaCobra {
    private No cabeca;
    private No cauda;
    private int tamanho;

    public EstruturaCobra() {
        this.cabeca = null;
        this.cauda = null;
        this.tamanho = 0;
    }

    public void adicionarNoFim(int x, int y) {
        No novo = new No(x, y);
        if (cauda == null) {
            cabeca = novo;
            cauda = novo;
        }else {
            cauda.setProx(novo);
            cauda = novo;
        }
        tamanho++;
    }

    public No removerDoInicio() {
        if (cabeca == null) return null;
        No removido = cabeca;
        cabeca = cabeca.getProx();
        if (cabeca == null) cauda = null;
        tamanho--;
        return removido;
    }

    public int getTamanho() {
        return tamanho;
    }

    public No get(int index) {
        if (index < 0 || index >= tamanho) return null;
        No atual = cabeca;
        for (int i = 0; i < index; i++) {
            atual = atual.getProx();
        }
        return atual;
    }
}