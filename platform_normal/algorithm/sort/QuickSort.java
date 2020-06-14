package algorithm.sort;

/**
 * 快速排序
 */
public class QuickSort {
	/**
	 * Tips：排序需要掌握Bubble,Quick,Heap,Merge
	 * 
	 * 快排为什么是不稳定的？
	 * 排序bai之前du,有两个数相等，在排序结束之后,它们两个有可能bai改变顺序
	 * 例 {2, 1, 1, 3} 以2为基准向下分治，2就要和第二个1交换，这就不稳定了
	 * 
	 */
	public static void main(String[] args) {
		int[] arr = new int[]{1,5,9,6,2,5,6,8,5,0};
		quickSort(arr, 0, 9);
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

	public static void quickSort(int a[], int left, int right) {
		/**步骤一*/
		if (left >= right)
			return;
		/**步骤二*/
		int i = left;
		int j = right;
		int key = a[left];// 选择第一个数为key
		/**步骤三*/
		while (i < j) {
			while (i < j && a[j] >= key)
				j--;
			if (i < j) {
				a[i] = a[j];
				i++;
			}
			while (i < j && a[i] < key)
				i++;
			if (i < j) {
				a[j] = a[i];
				j--;
			}
		}
		/**步骤四*/
		a[i] = key;
		/**步骤五*/
		quickSort(a, left, i - 1);// 递归调用
		quickSort(a, i + 1, right);// 递归调用
	}
}