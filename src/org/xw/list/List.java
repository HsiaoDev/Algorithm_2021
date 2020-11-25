package org.xw.list;

/**
 * 新建一个接口interface 需要有类实现这个接口中声明的方法
 */
public interface List<E> {
	static final int ELEMENT_NOT_FOUND = -1;
	
	/**
	 * 清除所有元素
	 */
	void clear();
	
	/**
	 * 元素的数量
	 * @return
	 */
	int size();
	
	/**
	 * 是否为空
	 * @return
	 */
	boolean isEmpty();
	
	/**
	 * 是否包含某个元素
	 * @param e
	 * @return
	 */
	boolean contains(E e);
	
	/**
	 * 在尾部添加一个元素
	 * @param e
	 */
	void append(E e);
	
	/**
	 * 获取index位置的元素
	 * @param index
	 * @return 目标位置的元素
	 */
	E getAt(int index);
	
	/**
	 * 替换index位置的元素
	 * @param index 目标位置
	 * @param e 新元素
	 * @return 目标位置原本的元素
	 */
	 E replaceAt(int index, E e);
	
	/**
	 * 在index位置插入一个元素
	 * @param index
	 * @param e
	 */
	void insertAt(int index, E e);
	
	/**
	 * 删除index位置的元素
	 * @param index
	 */
	E removeAt(int index);
	
	/**
	 * 获取元素的索引
	 * @param e
	 * @return
	 */
	int indexOf(E e);
}
