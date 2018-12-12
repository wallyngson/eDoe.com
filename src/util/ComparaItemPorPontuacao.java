package util;

import java.util.Comparator;

import itens.Item;

public class ComparaItemPorPontuacao implements Comparator<Item> {

	@Override
	public int compare(Item o1, Item o2) {
		return transforma(o2.getPontuacao()).compareTo(transforma(o1.getPontuacao()));
	}

	private Integer transforma(int pontuacao) {
		return new Integer(pontuacao);
	}

}
