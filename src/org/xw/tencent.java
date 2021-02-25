package org.xw; 
/** 
* @Author fufu
* @Time Feb 25, 2021 2:23:18 PM 
* @Version 1.0
* <p>Description:</p>
*/
public class tencent {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/// 斐波那契数列 - 递归
	public static int fib1(int n) {
		// 边界条件
		if (n <= 1) return n;
		
		/// 递归条件
		return fib1(n - 1) + fib1(n -2);
	}
	
	/// 斐波那契数列 - 循环
	public static int fib2(int n) {
		if (n <= 1) return n;
		
		int first = 0;
		int second = 1;
		
		for (int i = 0; i < n-1; i++) {
			int sum = first + second;
			first = second;
			second = sum;
		}
		return second;
	}
	
	// 斐波那契数列 - 循环优化版
	public static int fib3(int n) {
		if (n <= 1) return n;
		
		int first = 0;
		int second = 1;
		 while (n-- > 1) {
			second += first;
			first = second - first;
		}
		 return second;
	}

}
