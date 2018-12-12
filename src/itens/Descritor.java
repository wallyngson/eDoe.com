package itens;
import java.io.Serializable;
public class Descritor implements Comparable<Descritor>, Serializable {
	
	private int qtdItens = 0;
	private String descritor;
	private static final long serialVersionUID = 3L;
	
	public Descritor(String descritor) {
		this.parametroInvalido(descritor);
		
		this.descritor = descritor;
	}
	
	/**
	 * Verifica se o descritor eh invalido.
	 * 
	 * @param descritor
	 */
	private void parametroInvalido(String descritor) {
		if (descritor == null || descritor.trim().isEmpty())
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
	}
	
	public void setQtdItens(int qtd) {
		this.qtdItens = qtd;
	}

	public String nomeDescritor() {
		return this.descritor;
	}

	@Override
	public String toString() {
		return this.qtdItens + " - " + this.descritor;
	}

	@Override
	public int compareTo(Descritor other) {
		return this.descritor.compareTo(other.nomeDescritor());
	}

}
