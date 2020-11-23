package org.xw;

@SuppressWarnings("unchecked")
public class LinkedList<E> extends AbstractList<E> {
	
	// 成员变量
	private Node<E> firstNode;
	private Node<E> lastNode;
	
	/**
	 * 内部类
	 */
	private static class Node<E> {
		E e;
		Node<E> preNode;
		Node<E> nextNode;
		
		/**
		 * 构造方法
		 * @param preNode 前驱节点
		 * @param e 当前节点的元素
		 * @param nextNode 后继节点
		 */
		public Node(Node<E> preNode, E e, Node<E> nextNode) {
			this.e = e;
			this.preNode = preNode;
			this.nextNode = nextNode;
		}
		
		@Override
		public String toString() {
			StringBuilder stringBuilder = new StringBuilder();
			if (preNode != null) {
				stringBuilder.append(preNode.e);
			} else {
				stringBuilder.append("null");
			}
			
			stringBuilder.append("_").append(e).append("_");
			
			if (nextNode != null) {
				stringBuilder.append(nextNode.e);
			} else {
				stringBuilder.append("null");
			}
			
			return stringBuilder.toString();
		}
	}

	@Override
	public void clear() {
		size = 0;
		firstNode = null;
		lastNode = null;
		
	}

	@Override
	public E getAt(int index) {
		return nodeAt(index).e;
	}

	@Override
	public E replaceAt(int index, E e) {
		Node<E> targetNode = nodeAt(index);
		E oldE = targetNode.e;
		targetNode.e = e;
		return oldE;
	}

	@Override
	public void insertAt(int index, E e) {
		rangeCheckForAdd(index);
		
		if (index == size) {
			// 在末尾添加元素
			Node<E> oldLastNode = lastNode;
			lastNode = new Node<>(oldLastNode, e, null);
			
			if (oldLastNode == null) {
				// 当前链表为空
				firstNode = lastNode;
			} else {
				oldLastNode.nextNode = lastNode;
			}
		} else {
			// 在内部添加元素
			Node<E> nextNode = nodeAt(index);
			Node<E> preNode = nextNode.preNode;
			Node<E> newNode = new Node<>(preNode, e, nextNode);
			nextNode.preNode = newNode;
			
			if (preNode == null) {
				// 在首部插入
				firstNode = newNode;
			} else {
				preNode.nextNode = newNode;
			}
		}
		size++;
	}

	@Override
	public E removeAt(int index) {
		// 调用抽象类的方法确保将要移除的索引没有越界
		rangeCheckForIndex(index);
		
		Node<E> node = nodeAt(index);
		Node<E> preNode = node.preNode;
		Node<E> nextNode = node.nextNode;
		
		if (preNode == null) {
			// 将要被删除的是首节点
			firstNode = nextNode;
		} else {
			preNode.nextNode = nextNode;
		}
		
		if (nextNode == null) {
			// 将要被删除的是尾节点
			lastNode = preNode;
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
	
	/**
	 * 获取index位置对应的节点对象
	 * @param index
	 * @return
	 */
	private Node<E> nodeAt(int index) {
		// 调用抽象类AbstractList的方法确保index没有越界
		rangeCheckForIndex(index);
		
		if (index < (size >> 1)) {
			// index在前半部分
			Node<E> node = firstNode;
			for (int i = 0; i < size; i++) {
				node = node.nextNode;
			}
			return node;
		} else {
			// index在后半部分，从后往前找
			Node<E> node = lastNode;
			for (int i = size - 1; i > index; i--) {
				node = node.preNode;
			}
			return node;		
		}
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
