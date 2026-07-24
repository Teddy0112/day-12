import java.util.*;

class Solution {
    public int countPairs(int[] nums) {
        Arrays.sort(nums);

        int ans = 0;
        Map<Integer, Integer> count = new HashMap<>();

        for (int num : nums) {
            Set<Integer> possible = new HashSet<>();
            String s = String.valueOf(num);

            // No swap
            possible.add(num);

            // Try every possible digit swap
            char[] arr = s.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    swap(arr, i, j);
                    possible.add(Integer.parseInt(new String(arr)));
                    swap(arr, i, j); // restore
                }
            }

            // Count previous numbers that match
            for (int x : possible) {
                ans += count.getOrDefault(x, 0);
            }

            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        return ans;
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
