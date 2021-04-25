package strings.common.sorting;

public class InsertionSort {

	public static void main(String[] args) {
		int[] arr = { 12, 11, 13, 5, 6 };
System.out.println(System.currentTimeMillis());
		
		for (int i = 1; i < arr.length; i++) {
			int index =i;
			for (int j = i-1; j >= 0; j--) {
				int value = arr[index];
				if (arr[index] < arr[j]) {
					int temp = arr[j];
					arr[j] = value;
					arr[index] = temp;
					index--;
				}else{
					continue;
				}
			}
		}
		System.out.println(System.currentTimeMillis());

		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

}
