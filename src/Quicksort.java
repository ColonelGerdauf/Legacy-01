public class Quicksort 
{
	private String[] numbers;
	private int number;

	protected void sort(String[] values) 
	{
		// Check for empty or null array
		if (values ==null || values.length==0)
		{
			return;
		}
		this.numbers = values;
		number = values.length;
		quicksort(0, number - 1);
	}

	private void quicksort(int low, int high)
	{
		int i = low, j = high;
		String pivot = numbers [low + (high-low)/2];

		while (i <= j) 
		{
			while (numbers[i].compareTo(pivot) < 0) 
			{
				i++;
			}
			while (numbers[j].compareTo(pivot) > 0) 
			{
				j--;
			}
			if (i <= j)
			{
				exchange(i, j);
				i++;
				j--;
			}
		}
		if (low < j)
			quicksort(low, j);
		if (i < high)
			quicksort(i, high);
	}

	private void exchange(int i, int j) 
	{
		String temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
}