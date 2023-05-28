package problems;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		int left = 0;
		int right = array.length - 1;
		Integer result = null;

		while (left <= right) {
			int mid = left + (right - left) / 2;

			if (array[mid] == x) {
				return x;
			} else if (array[mid] < x) {
				result = array[mid];
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return result;
	}

}
