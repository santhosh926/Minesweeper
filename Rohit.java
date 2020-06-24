import java.util.ArrayList;
import java.util.Collections;

public class Rohit
{
    static ArrayList<String> result;

    public static void addToResult(int[] arr, int max)
    {
        String s = "";
        for(int i = 0; i < max; i++)
        {
            s += arr[i];
        }
        //System.out.println(s);
        result.add(s);
    }

    public static void permutation(int[] nums, int length, int max)
    {
        if(length == 1)
        {
            addToResult(nums, max);
        }

        for(int i = 0; i < length; i++)
        {
            permutation(nums, length-1, max);
            int temp;
            if(length % 2 == 0)
            {
                temp = nums[i];
                nums[i] = nums[length - 1];
                nums[length - 1] = temp;
            }
            else
            {
                temp = nums[0];
                nums[0] = nums[length - 1];
                nums[length - 1] = temp;
            }
        }
    }

    public static void main(String[] args)
    {
        result = new ArrayList<>();
        int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        permutation(nums, nums.length, nums.length);
        Collections.sort(result);
        System.out.println("The millionth lexographic permutation of these numbers is " + result.get(1000000));
    }
}