package 算法;

public class _3_插入排序 {
    public static void insertSort(int[] arr) {
        int insertVal;
        int insertIndex;
        //使用for循环来把代码简化
        for (int i = 1; i < arr.length; i++) {
            //定义待插入的数
            insertVal = arr[i];
            insertIndex = i - 1;// 即arr[ 1 ]的前面这个数的下标
            // 给insertVal 找到插入的位置
            // 1. insertIndex >= 0 保证在给insertVal 找插入位置，不越界
            // 2. insertVal<arr[insertIndex] 待插入的数，还没有找到插入位置
            // 3. 就需要将 arr[insertIndex] 后移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];//arr[insertIndex]
                insertIndex--;
            }
            // 当退出while循环时，说明插入的位置找到，为 insertIndex + 1
            //这里我们判断是否需要赋值
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
//            System.out.println("第" + i + "轮插入");
//            System.out.println(Arrays.toString(arr));
        }
    }
}