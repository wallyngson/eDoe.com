package itens;

public class Doacao {
	
	private String data, nomeDoador, itemDoado, nomeReceptor, idDoador;
	
	public Doacao(String data, String nomeDoador, String itemDoado, String nomeReceptor, String idDoador) {
		this.data = data;
		this.nomeDoador = nomeDoador;
		this.itemDoado = itemDoado;
		this.nomeReceptor = nomeReceptor;
		this.idDoador = idDoador;
	}
	
	/**
	 * Transforma a data em um numero inteiro.
	 * 
	 * @return
	 */
	public int formataData() {
		String novaData = this.data.replace("/", "");
		
		return Integer.parseInt(novaData);
	}
	
	public String getItemDoado() {
		return this.itemDoado;
	}
	
	@Override
	public String toString() {
		return this.data + " - doador: " +  this.nomeDoador + "/" + this.idDoador + ", item: "
				+ this.itemDoado + this.nomeReceptor; 
	}
	
	
	
}
