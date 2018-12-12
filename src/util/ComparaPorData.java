package util;

import java.util.Comparator;

import itens.Doacao;

public class ComparaPorData implements Comparator<Doacao> {

	@Override
	public int compare(Doacao o1, Doacao o2) {
		if (o1.formataData() > o2.formataData())
			return -1;
		
		if (o1.formataData() < o2.formataData())
			return 1;
		
		return o1.getItemDoado().compareTo(o2.getItemDoado());
	}

}
