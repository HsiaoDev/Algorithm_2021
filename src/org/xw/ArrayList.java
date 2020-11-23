package org.xw;

@SuppressWarnings("unchecked")
public class ArrayList<E> extends AbstractList<E> {

	// 存放元素的数组
	private E[] elements;
	
	// 构造函数的默认容量
	private static final int DEFAULT_CAPACITY = 10;
	
	/**
	 * 带参数的构造方法
	 * @param capacity 容量
	 */
	public ArrayList(int capacity) {
		capacity = capacity > DEFAULT_CAPACITY ? capacity : DEFAULT_CAPACITY;
		elements = (E[]) new Object[capacity];
	}
	
	/**
	 * 不带参数的构造方法
	 */
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	/**
	 * 清除所有元素
	 */
	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}

	@Override
	public E getAt(int index) {
		// 调用抽象类中的方法判断目标索引是否越界
		rangeCheckForIndex(index);
		return elements[index];
	}

	@Override
	public E replaceAt(int index, E e) {
		// 调用抽象类中的方法判断目标索引是否越界
		rangeCheckForIndex(index);
		E oldE = elements[index];
		elements[index] = e;
		return oldE;
	}

	@Override
	public void insertAt(int index, E e) {
		// 调用抽象类中的方法判断需要插入的位置是否越界
		rangeCheckForIndex(index);
		// 确保当前数组的容量足以新增一个元素
		ensureCapacity(index + 1);
		
		for (int i = size; i > index; i--) {
			elements[i] = elements[i - 1];
		}
		elements[index] = e;
		size++;
	}

	@Override
	public E removeAt(int index) {
		// 调用抽象方法判断待删除的位置是否越界
		rangeCheckForIndex(index);
		
		E oldE = elements[index];
		for (int i = index + 1; i < size; i++) {
			elements[i - 1] = elements[i];
		}
		// 原本最末尾的元素设为空
		elements[--size] = null;
		return oldE;
		
	}

	@Override
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
	 * 确保当前数组的容量足够装下目标容量
	 * @param capacity
	 */
	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (oldCapacity >= capacity) return;
		
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
		
		System.out.println(oldCapacity + "扩容为" + newCapacity);
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("size=").append(size).append(", [");
		
		for (int i = 0; i < size; i++) {
			if (i != 0) stringBuilder.append(", ");
			stringBuilder.append(elements[i]);
		}
		stringBuilder.append("]");
		
		return stringBuilder.toString();
	}

}
