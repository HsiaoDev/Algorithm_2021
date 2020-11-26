package org.xw.sort;

import java.util.Arrays;

/**
 * 参考链接：https://zhuanlan.zhihu.com/p/127839844
 */
public class InsertSort {

	public static void main(String[] args) {

		// 创建数组
		int[] array = { 95, 45, 15, 78, 84, 51, 24, 12 };

		// 打印排序之前的数组
		System.out.print(Arrays.toString(array));
		System.out.print("\n");

		// 调用插入排序方法
		insertSort(array);

		// 打印排序之后的数组
		System.out.print(Arrays.toString(array));
	}
	/**
	 * 插入排序的思路：每次将一个待排序的记录，插入到（前面已经排好序的子序列）的合适位置。直到所有元素都插入完。
	 */
	public static void insertSort(int[] array) {
		if (array == null || array.length <= 1) return;
		
		int length = array.length;
		
		// 要处理的元素
		int insertNum;
		
		// 从第2个数（索引=1）开始处理
		for (int i = 1; i < length; i++) {
			insertNum = array[i];
			// 已经排好序的数组 元素个数（i前面的元素）
			int j = i - 1;
			
			// 从后往前循环，将大于insertNum的数往后移动一格
			while (j >= 0 && array[j] > insertNum) {
				array[j + 1] = array[j];
				j--;
			}
			// 将需要插入的数放在要插入的位置
			array[j + 1] = insertNum;
		}
	}

}
