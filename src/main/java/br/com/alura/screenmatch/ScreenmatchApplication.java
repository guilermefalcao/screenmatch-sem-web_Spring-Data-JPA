package br.com.alura.screenmatch;

import br.com.alura.screenmatch.principal.Principal;
import br.com.alura.screenmatch.repository.SerieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {
	
	// @Autowired: Injeção de dependência do Spring
	// O Spring cria automaticamente uma instância de SerieRepository e injeta aqui
	// Não precisamos fazer "new SerieRepository()" manualmente!
	@Autowired
	private SerieRepository repositorio;

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	// Método run() é executado automaticamente após a aplicação iniciar
	@Override
	public void run(String... args) throws Exception {
		// Cria a classe Principal passando o repositório como parâmetro
		// Isso permite que Principal acesse o banco de dados
		Principal principal = new Principal(repositorio);
		principal.exibeMenu();
	}
}
