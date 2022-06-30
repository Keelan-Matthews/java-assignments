// Name:
// Student number:
import java.util.Arrays;
public class Sort
{
	
	////// Implement the functions below this line //////
	
	/********** MERGE **********/
	public static <T extends Comparable<? super T>> void mergesort(T[] data, int first, int last, boolean debug)
	{
		if (first < last) {
			int mid = (first+last) / 2;
			mergesort(data, first, mid, debug);
			mergesort(data, mid+1, last, debug);
			merge(data, first, last, debug);
		}
	}
     
	private static <T extends Comparable<? super T>> void merge(T[] data, int first, int last, boolean debug)
	{
		int mid = (first+last) / 2;

		Object[] temp = new Object[last-first+1];
		int it1 = first;
		int it2 = mid+1;
		int it3 = 0;

		while (it1 <= mid && it2 <= last) {
			if (data[it1].compareTo(data[it2]) <= 0)
				temp[it3] = data[it1++];
			else
				temp[it3] = data[it2++];
			it3++;
		}
		if (it1 <= mid && it2 > last) {
			while (it1 <= mid)
				temp[it3++] = data[it1++];
		} else {
			while (it2 <= last)
				temp[it3++] = data[it2++];
		}
		for (it3 = 0; it3 < temp.length; it3++) {
			data[it3+first] = (T)temp[it3];
		}
        
		//DO NOT MOVE OR REMOVE!
		if (debug)
			System.out.println(Arrays.toString(data));
	}
     
	/********** COUNTING **********/
	public static void countingsort(int[] data, boolean debug)
	{
		int length = data.length;
		int sorted[] = new int[length+1];

		//Find the max value in the given array
		int max = data[0];
		for (int i = 1; i < length; i++)
			if (data[i] > max)
				max = data[i];

		//Initialize the count array
		int[] count = new int[max+1];
		for (int i = 0; i < max; ++i) count[i] = 0;

		//Store the count for each element in the given array
		for (int datum : data) count[datum]++;

		//Add the count for each element
		for (int i = 1; i <= max; i++)
			count[i] += count[i-1];

		//Execute sort
		for (int i = length-1; i >= 0; i--) {
			sorted[count[data[i]] - 1] = data[i];
			count[data[i]]--;
		}

		//Copy into original array
		for (int i = 0; i < length; i++) data[i] = sorted[i];

		//DO NOT MOVE OR REMOVE!
		if (debug)
			System.out.println(Arrays.toString(data));
	}

}