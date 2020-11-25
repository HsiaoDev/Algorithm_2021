package org.xw.tree;

import java.util.Comparator;

//@SuppressWarnings("unchecked")
@SuppressWarnings("unused")
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
	 * @param comparator 比较器：插入元素需要通过对比找到合适的位置
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}
	
	/**
	 * 内部类：节点
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
		 * @param parentNode 新节点的父节点 节点一定有父节点，不一定有子节点
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
	
	
	/**
	 * 抽象类
	 */
	private static abstract class Visitor<E> {
		boolean stop;
		
		/**
		 * 抽象方法:比较某个元素
		 */
		public abstract boolean viosit(E e);
	}
	
	/**
	 * 获取当前BST的元素数量
	 * @return
	 */
	public int size() {
		return size;
	}
	
	/**
	 * 当前BST是否为空树
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * 删除/销毁BST
	 */
	public void clear() {
		size = 0;
		root = null;
	}
	
	/**
	 * 将新元素添加到BST中
	 * @param e
	 */
	public void add(E e) {
		// 判断新元素是否为空
		elementNotNullCheck(e);
		
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
	
	
	/**
	 * 删除一个元素
	 * @param e 待删除的元素
	 * @return 返回包含该元素的节点
	 */
	public void removeE(E e) {
		// 删除 包含指定元素 的节点
		removeNode(nodeWithElement(e));
	}
	
	/**
	 * 删除节点
	 * @param node
	 */
	public void removeNode(Node<E> node) {
		// 如果被删除的节点是空节点，直接返回
		if (node == null) return;
		// 删除一个节点后，元素的数量减少1
		size--;
		
		// 如果被删除的节点同时含有左右子节点（度为2）
		if (node.hasTwoChildren()) {
			// 找到后继节点
			Node<E> successorNode = successorNodeOf(node);
			// 将要被删除的节点内的元素 替换为后继节点内的元素
			node.e = successorNode.e;
			// 删除后继节点
			node = successorNode;
		} 
		
		// 被删除的节点只有左子节点，或没有子节点（叶子节点）
		Node<E> replaceNode = node.leftChildNode != null ? node.leftChildNode : node.rightChildNode;
		if (replaceNode != null) {
			// node是度为1的节点
			
			// 修改父节点
			replaceNode.parentNode = node.parentNode;
			// 修改父节点的左右子节点
			if (node.parentNode == null) {
				// node的度为1，且没有父节点 == 是只有一个子节点的根节点
				root = replaceNode;
			} else if (node == node.parentNode.leftChildNode) {
				// 被删除的节点是某个左节点
				node.parentNode.leftChildNode = replaceNode;
			} else {
				// 被删除的是某个右节点
				node.parentNode.rightChildNode = replaceNode;
			}
		} else if (node.parentNode == null) {
			// node没有子节点，并且没有父节点 == 没有子节点的根节点
			root = null;
		} else {
			// node没有子节点，并且父节点不为空 == 叶子节点
			if (node == node.parentNode.leftChildNode) {
				// 被删除的节点是某个左叶子节点
				node.parentNode.leftChildNode = null;
			} else {
				//  被删除的是某个右叶子节点
				node.parentNode.rightChildNode = null;
			}
		}
	}
	
	/**
	 * 获取某个节点的前驱节点
	 * @param node
	 * @return
	 */
	private Node<E> predecessor(Node<E> node) {
		if (node == null) return null;
		
		// 前驱是左子树的最右子节点
		Node<E> pNode = node.leftChildNode;
		if (pNode != null) {
			while (pNode.rightChildNode != null) {
				pNode = pNode.rightChildNode;
			}
			return pNode;
		}
		
		// 前驱在父节点中
		while (node.parentNode != null && node == node.parentNode.leftChildNode) {
			node = node.parentNode;
		}

		return node.parentNode;
	}
	
	/**
	 * 获取某个节点的后继节点 中序遍历的下一个节点
	 * https://blog.csdn.net/zhou_209/article/details/79440355
	 * 分两种情况
	 * 1:当前节点有右子树，则其后继是右子树中的最左节点
	 * 2:当前节点没有右子树
	 * 	a: 当前节点是一个左子节点，其后继是当前节点的父节点
	 *  b: 当前节点是一个右子节点，沿着其父节点一直往上找，若有一个节点是终点节点的左子节点，则该终点节点就是目标节点的后继
	 * @param node 目标节点
	 * @return 目标节点的后继节点 可能是左子节点或右子节点
	 */
	private Node<E> successorNodeOf(Node<E> node) {
		// A: 如果目标节点是空节点 返回空节点
		if (node == null) return null;
		
		// B: 目标节点不是空节点，且是当前节点的右子节点
		Node<E> pNode = node.rightChildNode;
		if (pNode != null) {
			while (pNode.leftChildNode != null) {
				pNode = pNode.leftChildNode;
			}
			// 后继节点是当前节点右子树的最左节点
			return pNode;
		}
		
		// C: 目标节点不是空节点，且是当前节点的左子节点
		while (node.parentNode != null && node == node.parentNode.rightChildNode) {
			node = node.parentNode;
		}
		return node.parentNode;
	}
	
	/**
	 * 工具方法：返回包含某个元素的节点
	 * @param e
	 * @return
	 */
	private Node<E> nodeWithElement(E e) {
		Node<E> node = root;
		
		while (node != null) {
			// 比较目标元素和当前节点中元素的大小
			int cmpResult = compare(e, node.e);
			// 如果目标元素与当前节点的元素相等，则直接返回当前节点
			if (cmpResult == 0) return node;
			if (cmpResult > 0) {
				// 如果目标元素比当前节点中的元素大，则继续用当前节点的右子节点来比较
				node = node.rightChildNode;
			} else {
				// 如果目标元素比当前节点中的元素小，则继续用当前节点的左子节点来比较
				node = node.leftChildNode;
			}
		}
		// 代码走到这里表示没有找到包含目标元素的节点，返回null
		return null;
	}
	
	/**
	 * 是否包含某个元素
	 * @param e
	 */
	private boolean contains(E e) {
		return nodeWithElement(e) != null;

	}
	
	/**
	 * 工具方法：比较两个元素的大小，如果构造方法传入了比较器，则使用该比较器，否则使用自带比较器。
	 * @param e1
	 * @param e2
	 * @return
	 */
	private int compare(E e1, E e2) {
		if (comparator != null) {
			return comparator.compare(e1, e2);
		}
		return ((Comparable<E>)e1).compareTo(e2);
	}
	
	
	/**
	 * 内部方法：判断元素是否为空
	 * @param e
	 */
	private void elementNotNullCheck(E e) {
		if (e == null) {
			throw new IllegalArgumentException("element must not be null");
		}
	}
	
	// MARK - 前序遍历
	private void preOrder(Node<E> node, Visitor<E> visitor) {
		// 边界条件
		if (visitor == null || visitor.stop) return;
		
		// 访问当前节点的元素
		visitor.stop = visitor.viosit(node.e);
		// 调用当前方法访问左子节点
		preOrder(node.leftChildNode, visitor);
		// 调用当前方法访问右子节点
		preOrder(node.rightChildNode, visitor);
	}
	
	private void preOrder(Visitor<E> visitor) {
		if (visitor == null) return;
		preOrder(root, visitor);
	}
	
	// MARK - 中序遍历
	public void inOrder(Visitor<E> visitor) {
		if (visitor == null) return;
	}
	
	private void inOrder(Node<E> node, Visitor<E> visitor) {
		// 边界条件
		if (visitor == null || visitor.stop) return;
		
		// 调用当前方法访问左子节点
		inOrder(node.leftChildNode, visitor);
		if (visitor.stop) return;
		// 调用当前方法访问根节点
		visitor.stop = visitor.viosit(node.e);
		// 调用当前方法访问右子节点
		inOrder(node.rightChildNode, visitor);

	}
	
	// MARK - 后序遍历
	public void postOrder(Visitor<E> visitor) {
		if (visitor == null || visitor.stop) return;
		postOrder(root, visitor);
	}
	
	private void postOrder(Node<E> node, Visitor<E> visitor) {
		// 边界条件
		if (visitor == null || visitor.stop) return;
		
		// 调用当前方法访问左子节点
		postOrder(node.leftChildNode, visitor);
		// 调用当前方法访问右子节点
		postOrder(node.rightChildNode, visitor);
		
		if (visitor.stop) return;
		// 访问根节点
		visitor.stop = visitor.viosit(node.e);

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
