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
	public static void quickSort(int[] array) { 
		quickSort(array, 0, array.length - 1);
	}
	
	public static void quickSort(int[] array, int left, int right) {
		if (array == null || left >= right || array.length <= 1) {
			return;
		}
		
		int mid = partition(array, left, right);
		quickSort(array, left, mid);
		quickSort(array, mid + 1, right);
	}
	
	private static int partition(int[] array, int left, int right) {
		int temp = array[left];
		while (right > left) {
			
			while (temp <= array[right] && left < right) {
				--right;
			}
			if (left < right) {
				array[left] = array[right];
				++left;
			}
			while (temp >= array[left] && left < right) {
				++left;
			}
			if (left < right) {
				array[right] = array[left];
				--right;
			}
		}
		array[left] = temp;
		return left;
	}

}
