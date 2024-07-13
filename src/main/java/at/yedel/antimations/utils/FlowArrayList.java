package at.yedel.antimations.utils;



import java.util.ArrayList;



public class FlowArrayList<E> extends ArrayList<E> {
	public E getPreviousElement(E element) {
		int previousIndex = (indexOf(element) - 1 + size()) % size();
		return get(previousIndex);
	}

	public E getNextElement(E element) {
		int nextIndex = (indexOf(element) + 1) % size();
		return get(nextIndex);
	}
}
