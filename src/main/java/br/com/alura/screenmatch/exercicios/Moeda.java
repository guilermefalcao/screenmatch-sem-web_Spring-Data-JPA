package br.com.alura.screenmatch.exercicios;

public enum Moeda {
    DOLAR(5.10),
    EURO(5.50),
    REAL(1.0);

    private double taxaConversao;

    Moeda(double taxaConversao) {
        this.taxaConversao = taxaConversao;
    }

    public double converterPara(double valorEmReais) {
        return valorEmReais / taxaConversao;
    }
}
