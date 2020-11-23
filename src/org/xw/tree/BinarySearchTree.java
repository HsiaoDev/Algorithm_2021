package org.xw.tree;

import java.util.Comparator;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.CompareGenerator;

@SuppressWarnings("unchecked")
public class BinarySearchTree<E> implements BinaryTreeInfo {

	// 二叉排序树的元素数量
	private int size;
	// 二叉树的根节点
	private Node<E> root;
	// 二叉树内部的比较器
	private Comparator<E> comparator;
	
	/**
	 * 不带参数的构造方法
	 */
	public BinarySearchTree() {
		this(null);
	}
	
	/**
	 * 带参数的构造方法
	 * @param comparator
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}
	
	/**
	 * 内部类
	 */
	public static class Node<E> {
		// 元素
		E e;
		// 左 子节点
		Node<E> leftChildNode;
		// 右 子节点
		Node<E> rightChildNode;
		// 父节点
		Node<E> parentNode;
		
		/**
		 * 构造方法
		 * @param e 新节点的元素
		 * @param parentNode 新节点的父节点
		 */
		private Node(E e, Node<E> parentNode) {
			this.e = e;
			this.parentNode = parentNode;
		}
		
		/**
		 * 当前节点是否叶子节点
		 * @return
		 */
		public boolean isLeafNode() {
			return leftChildNode == null && rightChildNode == null;
		}
		
		/**
		 * 当前节点是否包含两个子节点
		 * @return
		 */
		public boolean hasTwoChildren() {
			return leftChildNode != null && rightChildNode != null;
		}
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		size = 0;
		root = null;
	}
	
	private int compare(E e1, E e2) {
		if (comparator != null) {
			return comparator.compare(e1, e2);
		}
		return ((Comparable<E>)e1).compareTo(e2);
	}
	
	/**
	 * 将新元素添加到BST中
	 * @param e
	 */
	public void add(E e) {
		// 判断新元素是否为空
		
		// 如果当前BST是空树
		if (root == null) {
			root = new Node<>(e, null);
			size++;
			return;
		}
		
		//当前BST不为空
		Node<E> parentNode = root;
		Node<E> node = root;
		int cmpResult = 0;
		
		do {
			cmpResult = compare(e, node.e);
			parentNode = node;
			if (cmpResult > 0) {
				node = node.rightChildNode;
			} else if (cmpResult < 0) {
				node = node.leftChildNode;
			} else {
				node.e = e;
			}
		} while (node != null);
		
		// 创建新节点
		Node<E> newNode = new Node<>(e, parentNode);
		if (cmpResult > 0) {
			parentNode.rightChildNode = newNode;
		} else {
			parentNode.leftChildNode = newNode;
		}
		size++;
	}
	
	@Override
	public Object root() {
		return root;
	}

	@Override
	public Object left(Object node) {
		return ((Node<E>)node).leftChildNode;
	}

	@Override
	public Object right(Object node) {
		return ((Node<E>)node).rightChildNode;
	}

	@Override
	public Object string(Object node) {
		// TODO Auto-generated method stub
		return null;
	}

}
