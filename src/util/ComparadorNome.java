package util;

import java.util.Comparator;

import itens.Item;


public class ComparadorNome implements Comparator<Item>{
		public int compare(Item i1, Item i2) {
			return i1.getNome().compareTo(i2.getNome());
		}
}