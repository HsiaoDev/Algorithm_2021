package org.xw;

import org.xw.list.List;

/**
 * 新建抽象类实现List接口，抽象类中可以没有抽象方法
 */
public abstract class AbstractList<E> implements List<E> {
	/**
	 * 元素的数量
	 */
	protected int size;
	
	/**
	 * 获取元素的数量
	 */
	public int size() {
		return size;
	}
	
	/**
	 * 是否为空
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * 是否包含某个元素
	 */
	public boolean contains(E e) {
		// 调用List接口中的indexOf方法，该方法需要有实现
		return indexOf(e) != ELEMENT_NOT_FOUND;
	}
	
	/**
	 * 添加元素到尾部
	 */
	public void append(E e) {
		// 调用List接口中的插入方法，插入的索引=size，即插入在末尾
		insertAt(size, e);
	}
	
	/**
	 * index越界
	 * @param index
	 */
	protected void outOfBounds(int index) {
		throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
	}
	
	/**
	 * index越界（查找 替换）
	 * @param index
	 */
	protected void rangeCheckForIndex(int index) {
		if (index < 0 || index >= size) {
			outOfBounds(index);
		}	
	}
	
	/**
	 * index越界 （添加）
	 * @param index
	 */
	protected void rangeCheckForAdd(int index) {
		if (index < 0 || index > size) {
			outOfBounds(index);
		}
	}
}
