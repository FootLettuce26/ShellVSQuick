package shellANDquick;

import java.util.Random;
import java.lang.Math;
import java.util.Arrays;

public class Driver {

	public static void main(String[] args) 
	{
		int[] srcArray = RandomArrayGenerator(10000);
		int[] destArray;
		
		//System.out.println("Unsorted array: " + Arrays.toString(srcArray));
		
		ShellSort(srcArray);
		destArray = srcArray.clone();
		
		long start = System.nanoTime();
		QuickSort(destArray, 0, destArray.length-1);
		long finish = System.nanoTime();
		long timeElapsed = finish-start;
		System.out.println("QuickSort sorted an array of " + destArray.length + " in " + timeElapsed + " nanoseconds.");
		
		//System.out.println(Arrays.toString(srcArray));
		//System.out.println(Arrays.toString(destArray));

	}
	
	//generates the random array of a given size
	public static int[] RandomArrayGenerator(int size)
	{
		int[] result = new int[size];
		Random r = new Random();
		
		//filling each position in array with index number
		//0, 1, 2, 3, 4, 5...
		for(int i = 0; i < size; i++)
		{
			result[i] = i;
		}
		
		//randomly swapping numbers in the array
		for(int i = 0; i < size; i++)
		{
			int pos = r.nextInt(size); //selecting a random position in array
			int temp = result[i]; //storing value in arr[i] to temp
			result[i] = result[pos]; //taking value in pos and moving it to i
			result[pos] = temp; //setting pos to temp... i
		}
		return result; 
	}
	
	//round 1
		//1     4     7     10
		//  2     5     8
		//    3     6     9
	    //round 2
		//1   3   5   7   9
		//  2   4   6   8
	
	public static void ShellSortPartition(int[] arr, int startIndex, int interval)
	{
		for(int i = startIndex; i < arr.length; i = i + interval)
		{
			//setting i to whatever startIndex is
			int pos = i;
			
			while(pos - interval >= startIndex && arr[pos - interval] > arr[pos])
			{
				//swapping two elements
				int temp = arr[pos];
				arr[pos] = arr[pos-interval];
				arr[pos-interval] = temp;
				pos = pos-interval;
			}
		}
	}
	
	public static void ShellSort(int[] arr)
	{
		long beginning = System.nanoTime();
		//interval 2^n -1 , 2^(n-1) - 1, 2^(n-2) -1, ..., 2, 1
		//interval reducing by a power of 2 until gap value is one
		int length = arr.length;
		
		//determines how many digits needed to represent 1 to length
		//n is NOT the length of the array
		int n = (int)Math.floor(Math.log(length + 1));
		
		//decrements i by 1 which is originally set to n
		for(int i = n; i > 0; i--)
		{
			//adjusts the gap value (2^i - 1)
			int interval = (int)Math.pow(2, i) - 1;
			//separating the array into different portions tracked
			//by start based on interval size
			for(int start = 0; start < interval; start++)
			{
				ShellSortPartition(arr, start, interval);
			}
		}
		long finish = System.nanoTime();
		long timeElapsed = finish - beginning;
		System.out.println("ShellSort sorted an array of " + arr.length + " in " + timeElapsed + " nanoseconds.");
	}
	
	//log calculator?
	public static int log2nlz(int bits)
	{
	    if(bits == 0)
	        return 0; // or throw exception
	    return 31 - Integer.numberOfLeadingZeros(bits);
	}
	
	
	public static int Partition(int[] arr, int lowIndex, int highIndex) 
	{
		int low = lowIndex;
		int high = highIndex;
		
		int pivot = arr[(low + high)/2]; //this is integer division
		
		boolean done = false;
		while(!done) {
			//check the item at the low index, and stop when this item is larger
			//or equal to the pivot
			while(arr[low] < pivot && low <= highIndex) 
			{
				low = low + 1;//low += 1;
			}
			//check the item at the high index, and stop when item is smaller
			//than the pivot
			while(arr[high] >= pivot && high > lowIndex) 
			{
				high = high-1;
			}
			
			if (high > low) 
			{
				int temp = arr[low];
				arr[low] = arr[high];
				arr[high] = temp;
			} else 
			{
				done = true;
			}
		}
		return high;
	}
	
	public static void QuickSort(int[] arr, int low, int high) 
	{
		//when quicksort stop?
		//when there is only one item is past into the quicksort method
		//when low == high
		if (low >= high) 
		{
			
			return;
		}else 
		{
			//partition
			int mid = Partition(arr, low, high);
			//recursively call quicksort
			QuickSort(arr, low, mid);
			QuickSort(arr, mid + 1, high);
		}
		
	}
		

}
