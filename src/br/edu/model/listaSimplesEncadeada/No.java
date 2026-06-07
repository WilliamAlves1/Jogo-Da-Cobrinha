package br.edu.model.listaSimplesEncadeada;

public class No {

    private int x;
    private int y;
    private No prox;

    public No(int x, int y) {
        this.x = x;
        this.y = y;
        this.prox = null;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }
}