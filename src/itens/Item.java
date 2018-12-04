package itens;

public interface Item extends Comparable<Item>{

	public int getIdItem();
	
	public String descricaoCompleta();
	
	public String atualizaItem(int qtd, String tags);

	public String getNome();

	public int getQtdItem();

	public void setQtdItem(int qtdItem);

	@Override
	public String toString();

	@Override
	public int compareTo(Item other);
	
	public int compareToNome(Item i);
	
	public String getUsuarioVinculado();

}
