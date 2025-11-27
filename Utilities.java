package sysImplementation;

public class Utilities {

	public static java.lang.String getArrayString(int[] arr, char separator) {

		// first check if array is null
		if (arr == null) {
			throw new IllegalArgumentException("Cannot perform method on null array!");
		}
		
		// create String to return
		String elements = "";

		
		// if array is empty, return empty string
		if (arr.length == 0) {
			return elements;
		}

		// if not, then loop through every element of array except the last one
		// and add seperator after adding each character
		for (int i = 0; i < arr.length - 1; i++) {
			elements += arr[i] + "" + separator;
		}

		// add last character with no seperator after
		elements += arr[arr.length - 1];

		return elements;
	}

	public static int getInstances​(int[] array, int lowerLimit, int upperLimit) {

		// first case, can't perform on null array
		if (array == null) {
			throw new IllegalArgumentException("Cannot perform method on null array!");
		}

		// if lower limit is greater than upper limit, cannot compute
		if (lowerLimit > upperLimit) {
			throw new IllegalArgumentException("Lower limit greater than upper limit.");
		}

		int numOfInstances = 0;
		for (int i = 0; i < array.length; i++) {
			// if element is between lower limit (inclusive) and upper limit (inclusive)
			if (array[i] >= lowerLimit && array[i] <= upperLimit) {
				// add to count
				numOfInstances++;
			}
		}

		return numOfInstances;

	}

	public static int[] filter​(int[] array, int lowerLimit, int upperLimit) {

		// first case, can't perform on null array
		if (array == null) {
			throw new IllegalArgumentException("Cannot perform method on null array!");
		}

		// cannot computer if lower limit is greater than the upper limit
		if (lowerLimit > upperLimit) {
			throw new IllegalArgumentException("Lower limit greater than upper limit.");
		}

		
		// first find number of elements to be in new array
		int numOfInstances = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] >= lowerLimit && array[i] <= upperLimit) {
				numOfInstances++;
			}
		}

		// create index array to track elements in new array
		int index = 0;
		// create array of proper length
		int[] values = new int[numOfInstances];
		for (int i = 0; i < array.length; i++) {
			// if element in array is between
			// lower limit (inclusive) and upper limit (inclusive)
			if (array[i] >= lowerLimit && array[i] <= upperLimit) {
				// update new array
				values[index] = array[i];
				index++;
			}
		}

		return values;

	}

	public static void rotate​(int[] array, boolean leftRotation, int positions) {

		// first case, can't perform on null array
		if (array == null) {
			throw new IllegalArgumentException("Cannot perform method on null array!");
		}

		// if length of array is less than 2, return 
		if (array.length < 2) {
			return;
		}

		// checks how many positions to move after full rotations
		positions = positions % array.length;

		// if positions = 0, then that means the positions to move 
		// is the same of full rotations, so array doesn't actually move
		// retuen normal array
		if (positions == 0) {
			return;
		}

		// if rotate is to the left
		if (leftRotation == true) {
			for (int i = 0; i < positions; i++) {
				// rotate left "positions" times
				rotateLeft(array);
			}
		}

		// if rotate is to the right
		if (leftRotation == false) {
			for (int i = 0; i < positions; i++) {
				// rotate right "positions" times
				rotateRight(array);
			}
		}

	}

	private static void rotateLeft(int[] array) {
		// set a temp variable to keep the first variable in array
		int first = array[0];
		// rotate all elements except the last to the left
		for (int i = 0; i < array.length - 1; i++) {
			array[i] = array[i + 1];
		}
		
		// set the last element to previous first element
		array[array.length - 1] = first;
	}

	private static void rotateRight(int[] array) {
		// set a temp variable to keep the last variable in the array
		int last = array[array.length - 1];
		// rotate all elements except the first to the right
		for (int i = array.length - 1; i > 0; i--) {
			array[i] = array[i - 1];
		} 
		// set the first element to previous last element
		array[0] = last;
	}

	public static java.lang.StringBuffer[] getArrayStringsLongerThan​(java.lang.StringBuffer[] array, int length) {
		
		// first check null
		if (array == null) {
			throw new IllegalArgumentException("Cannot perform method on null array!");
		}

		// create count to find number of elements in new array
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i].length() > length) {
				count++;
			}
		}

		// create new array with appropriate length
		StringBuffer[] copies = new StringBuffer[count];

		// create index array to track elements in new array
		int index = 0;
		for (int i = 0; i < array.length; i++) {
			// if length of element is greater than parameter
			if (array[i].length() > length) {
				// deep copy into new array
				copies[index] = new StringBuffer(array[i]);
				index++;
			}
		}

		return copies;

	}

}