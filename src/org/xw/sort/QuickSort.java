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
//		quickSort(list);
		quickSort2(list, 0, list.length - 1);
		// 打印排序之前的数组
		System.out.print("排序后：");
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
	
	/** 
	 * 参考1 https://blog.csdn.net/pengzonglu7292/article/details/84938910
	 * 参考2 https://blog.csdn.net/pengzonglu7292/article/details/84945563
	 * @param array
	 * @param low
	 * @param high
	 */
	public static void quickSort2(int[] array, int low, int high) { 
		// 递归返回条件
		if (low >= high) return;
		
		int i = low;
		int j = high;
		int key = array[i];
		
		while (i < j) {
			while (array[j] >= key && i < j) {
				j--;
			}
			while (array[i] <= key && i < j) {
				i++;
			}
			
			if (i != j) {
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			} else {
				int temp = array[j];
				array[j] = array[low];
				array[low] = temp;
			}
		}
		quickSort2(array, low, i - 1);
		quickSort2(array, i + 1, high);
	}
}
