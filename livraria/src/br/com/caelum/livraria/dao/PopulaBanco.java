package br.com.caelum.livraria.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Usuario;
import br.com.caelum.livraria.modelo.Venda;

public class PopulaBanco {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();
		
		Usuario usuario = new Usuario("lucas-machado@outlook.com", "123456");
		em.persist(usuario);

		Autor assis = geraAutor("Machado de Assis");
		Autor amado = geraAutor("Jorge Amado");
		Autor coelho = geraAutor("Paulo Coelho");
		Autor lobato = geraAutor("Monteiro Lobato");
		
		em.persist(assis);
		em.persist(amado);
		em.persist(coelho);
		em.persist(lobato);

		Livro casmurro = geraLivro("978-8-52-504464-8", "Dom Casmurro", "10/01/1899", 24.90, assis);
		Livro memorias = geraLivro("978-8-50-815415-9", "Memorias Postumas de Bras Cubas", "01/01/1881", 19.90, assis);
		Livro quincas = geraLivro("978-8-50-804084-1", "Quincas Borba", "01/01/1891", 16.90, assis);
		Livro alquemista = geraLivro("978-8-57-542758-3", "O Alquimista", "01/01/1988", 19.90, coelho);
		Livro brida = geraLivro("978-8-50-567258-7", "Brida", "01/01/1990", 12.90, coelho);
		Livro valkirias = geraLivro("978-8-52-812458-8", "As Valkirias", "01/01/1992", 29.90, coelho);
		Livro maao = geraLivro("978-8-51-892238-9", "O Diario de um Mago", "01/01/1987", 9.90, coelho);
		Livro capitaes = geraLivro("978-8-50-831169-1", "Capitaes da Areia", "01/01/1937", 6.90, amado);
		Livro flor = geraLivro("978-8-53-592569-9", "Dona Flor e Seus Dois Maridos", "01/01/1966", 18.90, amado);
		
		em.persist(casmurro);
		em.persist(memorias);
		em.persist(quincas);
		em.persist(alquemista);
		em.persist(brida);
		em.persist(valkirias);
		em.persist(maao);
		em.persist(capitaes);
		em.persist(flor);

		Random random = new Random(1234);

		Venda vendaCasmurro = new Venda(casmurro, random.nextInt(500));
		Venda vendaMemorias = new Venda(memorias, random.nextInt(500));
		Venda vendaQuincas = new Venda(quincas, random.nextInt(500));
		Venda vendaAlquimista = new Venda(alquemista, random.nextInt(500));
		Venda vendaBrida = new Venda(brida, random.nextInt(500));
		Venda vendaValkirias = new Venda(valkirias, random.nextInt(500));
		Venda vendaMaao = new Venda(maao, random.nextInt(500));
		Venda vendaCapitaes = new Venda(capitaes, random.nextInt(500));
		Venda vendaFlor = new Venda(flor, random.nextInt(500));

		em.persist(vendaCasmurro);
		em.persist(vendaMemorias);
		em.persist(vendaQuincas);
		em.persist(vendaAlquimista);
		em.persist(vendaBrida);
		em.persist(vendaValkirias);
		em.persist(vendaMaao);
		em.persist(vendaCapitaes);
		em.persist(vendaFlor);
		
		em.getTransaction().commit();
		em.close();
	}

	private static Autor geraAutor(String nome) {
		Autor autor = new Autor();
		autor.setNome(nome);
		
		return autor;
	}

	private static Livro geraLivro(String isbn, String titulo, String data, double preco, Autor autor) {
		Livro livro = new Livro();
		
		livro.setIsbn(isbn);
		livro.setTitulo(titulo);
		livro.setDataLancamento(parseData(data));
		livro.setPreco(preco);
		livro.adicionaAutor(autor);
		
		return livro;
	}

	private static Calendar parseData(String data) {
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			
			return calendar;
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
