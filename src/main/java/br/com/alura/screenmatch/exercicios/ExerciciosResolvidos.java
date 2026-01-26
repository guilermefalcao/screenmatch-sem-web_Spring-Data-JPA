package br.com.alura.screenmatch.exercicios;

import java.util.*;
import java.util.stream.Collectors;

public class ExerciciosResolvidos {

    public static void executarTodos() {
        System.out.println("\n========== EXERCÍCIOS RESOLVIDOS ==========\n");
        
        exercicio1();
        exercicio2();
        exercicio3();
        exercicio4();
        exercicio5();
        exercicio6();
        exercicio7();
        exercicio8();
        
        System.out.println("\n========== FIM DOS EXERCÍCIOS ==========\n");
    }

    // Exercício 1: Converter lista de strings para números, ignorando valores inválidos
    private static void exercicio1() {
        System.out.println("--- Exercício 1: Conversão de Strings para Números ---");
        List<String> input = Arrays.asList("10", "abc", "20", "30x");
        
        List<Integer> numeros = input.stream()
                .map(s -> {
                    try {
                        return Optional.of(Integer.parseInt(s));
                    } catch (NumberFormatException e) {
                        return Optional.<Integer>empty();
                    }
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        
        System.out.println("Input: " + input);
        System.out.println("Output: " + numeros);
        System.out.println();
    }

    // Exercício 2: Processar número em Optional
    private static void exercicio2() {
        System.out.println("--- Exercício 2: Processar Número em Optional ---");
        System.out.println("processaNumero(Optional.of(5)): " + processaNumero(Optional.of(5)));
        System.out.println("processaNumero(Optional.of(-3)): " + processaNumero(Optional.of(-3)));
        System.out.println("processaNumero(Optional.empty()): " + processaNumero(Optional.empty()));
        System.out.println();
    }

    public static Optional<Integer> processaNumero(Optional<Integer> numero) {
        return numero.filter(n -> n > 0).map(n -> n * n);
    }

    // Exercício 3: Obter primeiro e último nome
    private static void exercicio3() {
        System.out.println("--- Exercício 3: Primeiro e Último Nome ---");
        System.out.println("Input: '  João Carlos Silva   ' -> Output: '" + 
                obterPrimeiroEUltimoNome("  João Carlos Silva   ") + "'");
        System.out.println("Input: 'Maria   ' -> Output: '" + 
                obterPrimeiroEUltimoNome("Maria   ") + "'");
        System.out.println();
    }

    public static String obterPrimeiroEUltimoNome(String nomeCompleto) {
        String[] nomes = nomeCompleto.trim().split("\\s+");
        if (nomes.length == 1) {
            return nomes[0];
        }
        return nomes[0] + " " + nomes[nomes.length - 1];
    }

    // Exercício 4: Verificar palíndromo
    private static void exercicio4() {
        System.out.println("--- Exercício 4: Verificar Palíndromo ---");
        System.out.println("'socorram me subi no onibus em marrocos': " + 
                ehPalindromo("socorram me subi no onibus em marrocos"));
        System.out.println("'Java': " + ehPalindromo("Java"));
        System.out.println();
    }

    public static boolean ehPalindromo(String palavra) {
        String limpa = palavra.replaceAll("\\s+", "").toLowerCase();
        String reversa = new StringBuilder(limpa).reverse().toString();
        return limpa.equals(reversa);
    }

    // Exercício 5: Converter emails para minúsculas
    private static void exercicio5() {
        System.out.println("--- Exercício 5: Converter Emails ---");
        List<String> emails = Arrays.asList("TESTE@EXEMPLO.COM", "exemplo@Java.com ", "Usuario@teste.Com");
        System.out.println("Input: " + emails);
        System.out.println("Output: " + converterEmails(emails));
        System.out.println();
    }

    public static List<String> converterEmails(List<String> emails) {
        return emails.stream()
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }

    // Exercício 6: Enum Mes
    private static void exercicio6() {
        System.out.println("--- Exercício 6: Enum Mes ---");
        System.out.println("Dias em FEVEREIRO: " + Mes.FEVEREIRO.getNumeroDeDias());
        System.out.println("Dias em JULHO: " + Mes.JULHO.getNumeroDeDias());
        System.out.println();
    }

    // Exercício 7: Enum Moeda
    private static void exercicio7() {
        System.out.println("--- Exercício 7: Enum Moeda ---");
        System.out.println("100 reais em DOLAR: $" + String.format("%.2f", Moeda.DOLAR.converterPara(100)));
        System.out.println("100 reais em EURO: €" + String.format("%.2f", Moeda.EURO.converterPara(100)));
        System.out.println();
    }

    // Exercício 8: Enum CodigoErro
    private static void exercicio8() {
        System.out.println("--- Exercício 8: Enum CodigoErro ---");
        System.out.println("NOT_FOUND - Código: " + CodigoErro.NOT_FOUND.getCodigo() + 
                " - Descrição: " + CodigoErro.NOT_FOUND.getDescricao());
        System.out.println("BAD_REQUEST - Código: " + CodigoErro.BAD_REQUEST.getCodigo() + 
                " - Descrição: " + CodigoErro.BAD_REQUEST.getDescricao());
        System.out.println("INTERNAL_SERVER_ERROR - Código: " + CodigoErro.INTERNAL_SERVER_ERROR.getCodigo() + 
                " - Descrição: " + CodigoErro.INTERNAL_SERVER_ERROR.getDescricao());
        System.out.println();
    }
}
