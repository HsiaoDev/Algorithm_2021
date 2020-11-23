package org.xw;

import com.sun.org.apache.bcel.internal.generic.NEW;

@SuppressWarnings("unchecked")
public class DynamicList<E> {
	private int size;// 动态数组中元素的数量
	private E[] elements;// 动态数组内部的数组
	
	private static final int DEFAULT_CAPACITY = 10;// 默认容量
	private static final int ELEMENT_NOT_FOUND = -1;// 未找到元素
	
	// 带容量的初始化方法
	public DynamicList(int capacity) {
		capacity = (capacity > DEFAULT_CAPACITY) ? capacity : DEFAULT_CAPACITY;
		elements = (E[]) new Object[capacity];
	}
	
	// 默认初始化方法
	public DynamicList() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * 清除所有元素
	 */
	public void clearElements() {
		// 1. 遍历数组将每个元素设为空
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		// 2. 将成员变量的值设置为0
		size = 0;
	}
	
	/**
	 * 获取该动态数组的元素数量
	 */
	public int size() {
		return size;
	}
	
	/**
	 * 动态数组是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * 获取某个元素在动态数组中的索引
	 * @param e 目标元素
	 * @return 目标元素的索引
	 */
	public int indexOf(E e) {
		if (e == null) {
			for (int i = 0; i < size; i++) {
				if (elements[i] == null) return i;
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (e.equals(elements[i])) return i;
			}
		}
		return ELEMENT_NOT_FOUND;
	}
	
	/**
	 * 动态数组是否包含了某个元素
	 * @param e 目标元素
	 * @return 是否被包含在数组中
	 */
	public boolean contains(E e) {
		return indexOf(e) != ELEMENT_NOT_FOUND;
	}
	
	public void rangeCheck(int index) {
		if (index < 0 || index >= size) {
			outOfBounds(index);
		}
	}
	
	public void rangeCheckForAdd(int index) {
		if (index < 0 || index > size) {
			outOfBounds(index);
		}
	}
	
	/**
	 * 抛出索引越界的异常
	 * @param index 目标索引
	 */
	private void outOfBounds(int index) {
		throw new IndexOutOfBoundsException("索引越界，Index =" + index + ", Size = " + size);
	}
	
	/**
	 * 返回指定索引的元素
	 * @param index
	 * @return
	 */
	public E getIndex(int index) {
		rangeCheck(index);
		return elements[index];
	}
	
	/**
	 * 替换指定索引的元素
	 * @param index
	 * @param newE
	 * @return 目标索引处的旧元素
	 */
	public E replaceIndex(int index, E newE) {
		rangeCheck(index);
		E oldE = elements[index];
		elements[index] = newE;
		return oldE;
	}
	
	public void insert(int index, E newE) {
		rangeCheckForAdd(index);
		ensureCapacity(size + 1);
		for (int i = size; i > index; i--) {
			elements[i] = elements[i - 1];
		}
		elements[index] = newE;
		size++;
	}
	
	/**
	 * 移除指定索引的元素
	 * @param index 目标索引
	 * @return 目标索引处的旧值
	 */
	public E removeIndex(int index) {
		rangeCheck(index);
		E oldE = elements[index];
		for (int i = index + 1; i < size; i++) {
			elements[i - 1] = elements[i];
		}
		elements[--size] = null;
		return oldE;
	}
	
	/**
	 * 添加元素到数组末尾，index = size
	 * @param newE
	 */
	public void append(E newE) {
		insert(size, newE);
	}
	
	/**
	 * 插入元素之前先确保动态数组能再装一个元素
	 * @param capacity
	 */
	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (oldCapacity >= capacity) return;
		
		// 新建一个数组，容量为旧容量的1.5倍
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
		System.out.println(oldCapacity + "扩容为" + newCapacity);;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("size = ").append(size).append(", [");
		for (int i = 0; i < size; i++) {
			if (i != 0) stringBuilder.append(", ");
			stringBuilder.append(elements[i]);
		}
		stringBuilder.append("]");
		return stringBuilder.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("这行代码是在MBP写的");
		DynamicList<Object> list = new DynamicList<Object>();
//		list.append(new Person(10, "xw"));
//		list.append(new Person(12, "lxl"));
		list.append(2);
		list.append(99);
		System.out.println(list.toString());
	}

}
