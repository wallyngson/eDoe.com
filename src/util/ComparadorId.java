package util;

import java.util.Comparator;

import itens.Item;

public class ComparadorId implements Comparator<Item>{

	@Override
	public int compare(Item arg0, Item arg1) {
		if (arg0.getIdItem() < arg1.getIdItem())
			return -1;
		
		if (arg0.getIdItem() > arg1.getQtdItem())
			return 1;
		
		return 0;
		
	}

}
