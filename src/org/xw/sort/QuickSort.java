package org.xw.sort;

public class QuickSort {

	public static void main(String[] args) {
		// 创建数组
		int[] list = {95, 45, 15, 78, 84, 51, 24, 12};
				
		// 打印排序之前的数组
		System.out.print("排序前：");
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + ", ");
		}
		System.out.print("\n");
		
		// 使用快速排序
		quickSort(list);
		// 打印排序之前的数组
		System.out.print("排序前：");
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + ", ");
		}
		System.out.print("\n");

	}
	
	public static void quickSort(int[] list) {
		
	}

}
