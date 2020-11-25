package org.xw.sort;

/**
 * 冒泡排序
 */
public class BubbleSort {

	public static void main(String[] args) {
		
		// 创建数组
		int[] list = {95, 45, 15, 78, 84, 51, 24, 12};
		
		// 打印排序之前的数组
		System.out.print("排序前：");
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + ", ");
		}
		System.out.print("\n");
		
		// 调用冒泡排序方法
		bubbleSort(list);
		
		// 打印排序之后的数组
		System.out.print("排序后：");
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + ", ");
		}
		System.out.print("\n");
	}
	
	public static void bubbleSort(int[] list) {
		int i, j, temp;
		for (i = 0; i < (list.length - 1); i++) {
			for (j = 0; j < ((list.length - 1) - i); j++) {
				// 1、先写内层循环
				if (list[j] > list[j + 1]) {
					// 如果相邻两个数前面的数大于后面的数，则交换两个数
					temp = list[j];
					list[j] = list[j + 1];
					list[j + 1] = temp;
				}
			}
		}
	}

}
