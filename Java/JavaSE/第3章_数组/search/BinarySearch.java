package search;

/**
 * 二分查找法
 */
public class BinarySearch {
    public static void main(String[] args) {
        //二分法查找：要求此数组必须是有序的。
        int[] arr = new int[]{-99,-54,-2,0,2,33,43,256,999};
        System.out.println(binarySearch(arr, 0));

    }

    static int binarySearch(int[] arr, int number){
        // 起始位置
        int begin = 0;
        // 结束位置
        int end = arr.length - 1;
        // 目标位置
        int targer = -1;
        // 中间位置
        int middle;
        // 中间值
        int midNum;
        /** 此处为 <= 而不是 <，避免出现begin == end， 并且arr[begin] == 目标值，导致不能输出正确位置的情况出现 */
        while (begin <= end){
            middle = (begin + end) >> 1;
            midNum = arr[middle];
            if(midNum == number){
                targer = middle;
                break;
            }else if(midNum < number){
                /** 此处注意 +1 */
                begin = middle + 1;
            }else{
                end = middle - 1;
            }
        }
        return targer;
    }
}
