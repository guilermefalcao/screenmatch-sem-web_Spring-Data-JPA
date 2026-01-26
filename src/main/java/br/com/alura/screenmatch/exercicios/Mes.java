package br.com.alura.screenmatch.exercicios;

public enum Mes {
    JANEIRO(31),
    FEVEREIRO(28),
    MARCO(31),
    ABRIL(30),
    MAIO(31),
    JUNHO(30),
    JULHO(31),
    AGOSTO(31),
    SETEMBRO(30),
    OUTUBRO(31),
    NOVEMBRO(30),
    DEZEMBRO(31);

    private int numeroDeDias;

    Mes(int numeroDeDias) {
        this.numeroDeDias = numeroDeDias;
    }

    public int getNumeroDeDias() {
        return numeroDeDias;
    }
}
