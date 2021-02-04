package org.xw.tree;

// 接口
public interface BinaryTreeInfo {
	/**
	 * 获取跟节点
	 */
	Object root();
	
	/**
	 * 获取节点的左child
	 * @param node
	 * @return
	 */
	Object left(Object node);
	
	/**
	 * 获取节点的右child
	 * @param node
	 * @return
	 */
	Object right(Object node);
	
	/**
	 * 打印节点
	 * @param node
	 * @return
	 */
	Object string(Object node);
}
