package org.xw.sort;

public class InsertSort {

	public static void main(String[] args) {

		// 创建数组
		int[] list = { 95, 45, 15, 78, 84, 51, 24, 12 };

		// 打印排序之前的数组
		System.out.print("排序前：");
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + ", ");
		}
		System.out.print("\n");

		// 使用快速排序
		insertSort(list);
		// 打印排序之前的数组
		System.out.print("排序前：");
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + ", ");
		}
		System.out.print("\n");
	}
	/**
	 * 插入排序的思路：每次将一个待排序的记录，插入到（前面已经排好序的子序列）的合适位置。直到所有元素都插入完。
	 */
	public static void insertSort(int[] list) {
		// 初始化变量
		int i, j, k;
		for (int i = 1; i < list.length; i++) {
			// 为当前元素在（前面已经排好序的子序列）中找合适的位置
			for (int j = i - 1; j > 0; j--) {
				if (list[j] < list[i]) {
					break;
				}
				
				if (j != i - 1) {
					// 将比list[i]大的元素 往后移动
				}
			}
		}
	}

}
