package org.xw.sort;

import java.util.Arrays;


/**
 * 冒泡排序
 * 优课达：https://zhuanlan.zhihu.com/p/128961788
 */
public class BubbleSort {

	public static void main(String[] args) {
		
		// 创建数组
		int[] array = {95, 45, 15, 78, 84, 51, 24, 12};
		
		// 打印排序之前的数组
		System.out.print(Arrays.toString(array));
		System.out.print("\n");
		
		// 调用冒泡排序方法
		bubbleSort(array);
		
		// 打印排序之后的数组
		System.out.print(Arrays.toString(array));
	}
	
	/**
	 * Description: 冒泡排序
	 * 
	 * @param array 需要排序的数组
	 */
	public static void bubbleSort(int[] array) {
		if (array == null || array.length <= 1) return;
		
		int length = array.length;
		int temp;
		
		// 外层循环控制比较轮数
		for (int i = 0; i < length; i++) {
			
			// 内层循环控制比较次数，每一个内循环都会找到一个较大值
			// (array.length - 1)防止索引越界，(array.length - 1 - i)较小比较次数
			for (int j = 0; j < length - 1 - i; j++) {
				
				// 如果前面的数大于后面的数，就交换两数
				if (array[j] > array[j + 1]) {
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}

}
