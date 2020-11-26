package org.xw.sort;

import java.util.Arrays;

/**
 * 参考链接：https://zhuanlan.zhihu.com/p/126723908
 *
 */
public class SelectionSort {

	public static void main(String[] args) {

		// 创建数组
		int[] array = { 95, 45, 15, 78, 84, 51, 24, 12 };

		// 打印排序之前的数组
		System.out.print(Arrays.toString(array));
		System.out.print("\n");

		// 调用选择排序方法
		selectionSort(array);

		// 打印排序之后的数组
		System.out.print(Arrays.toString(array));
	}
	
	public static void selectionSort(int[] array) {
		if (array == null || array.length <= 1) return;
		
		int length = array.length;
		
		for (int i = 0; i < length; i++) {
			// 保存最小数的索引
			int minIndex = i;
			for (int j = i + 1; j < length; j++) {
				// 找到最小的数
				if (array[j] < array[minIndex]) minIndex = j;
			}
			
			// 交换元素位置
			if (i != minIndex) {
				swap(array, minIndex, i);
			}
		}
	}
	
	private static void swap(int[] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}

}
