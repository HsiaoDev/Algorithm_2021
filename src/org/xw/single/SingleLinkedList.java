package org.xw.single;

import org.xw.AbstractList;

@SuppressWarnings("unused")
public class SingleLinkedList<E> extends AbstractList<E> {

	// 成员变量 指向第一个节点 
	private Node<E> firstNode;
	
	/**
	 * 内部类
	 */
	private static class Node<E> {
		E e;
		Node<E> nextNode;
		
		public Node(E e, Node<E> nextNode) {
			this.e = e;
			this.nextNode = nextNode;
		}
	}
	
	@Override
	public void clear() {
		size = 0;
		firstNode = null;
		
	}

	@Override
	public E getAt(int index) {
		return nodeAt(index).e;
	}

	@Override
	public E replaceAt(int index, E e) {
		Node<E> node = nodeAt(index);
		E oldE = node.e;
		node.e = e;
		return oldE;
	}

	@Override
	public void insertAt(int index, E e) {
		rangeCheckForAdd(index);
		
		if (index == 0) {
			// 在首部添加
			firstNode = new Node<>(e, firstNode);
		} else {
			Node<E> preNode = nodeAt(index - 1);
			preNode.nextNode = new Node<>(e, preNode.nextNode);
		}
		size++;
	}

	@Override
	public E removeAt(int index) {
		// 调用抽象类的方法判断目标索引是否越界
		rangeCheckForIndex(index);
		
		Node<E> node = firstNode;
		if (index == 0) {
			firstNode = firstNode.nextNode;
		} else {
			Node<E> preNode = nodeAt(index - 1);
			node = preNode.nextNode;
			preNode.nextNode = node.nextNode;
		}
		size--;
		return node.e;
	}

	@Override
	public int indexOf(E e) {
		Node<E> node = firstNode;
		if (e == null) {
			for (int i = 0; i < size; i++) {
				if (node.e == null) return i;
				node = node.nextNode;
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (e.equals(node.e)) return i;
				node = node.nextNode;
			}
		}
		
		return ELEMENT_NOT_FOUND;
	}

	private Node<E> nodeAt(int index) {
		rangeCheckForIndex(index);
		
		Node<E> node = firstNode;
		for (int i = 0; i < index; i++) {
			node = node.nextNode;
		}
		return node;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Size=").append(size).append(", [");
		Node<E> node = firstNode;
		for (int i = 0; i < size; i++) {
			if (i != 0) stringBuilder.append(", ");
			stringBuilder.append(node);
			
			node = node.nextNode;
		}
		stringBuilder.append("]");
		return stringBuilder.toString();
	}

}
