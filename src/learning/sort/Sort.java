package learning.sort;

import sun.reflect.annotation.ExceptionProxy;

public class Sort {

    public static void main(String[] args) {
        //排序算法测试开始
//        int[] arr = {1, 3, 2, 5, 6, 8, 6, 9, 0, 2, 3, 5, 12, 34, 24, 56, 9, 8, 0};
//        bubbleSort(arr);
//       selectionSort(arr);
//        insertionSort(arr);
//        bubbleSortDesc(arr);
//        selectionSortDesc(arr);
//        insertionSortDesc(arr);
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[i]);
//        }
        //排序算法测试结束

        //递归算法测试开始
        int result2 = kthGrammar2(29, 16777517);
        int resultMy = kthGrammar(29, 16777517);
        System.out.print("  result2: " + result2);
        System.out.print("  resultMy:  " + resultMy);
        System.out.print("  2d :  "+(1<<24));
        //递归算法测试结束
    }


    /**
     * 递归开始
     *
     * @param N
     * @param K
     * @return
     */
    public static int kthGrammar2(int N, int K) {
        return recursion(N, K - 1);
    }

    public static int recursion(int N, int k) {
        if (N == 1) return 0;
        int res = 0;
        //首先要得到前N - 1排的那个数字
        int num = recursion(N - 1, k / 2);
        if (k % 2 == 0) {
            if (num == 1) res = 1;
            else res = 0;
        } else {
            if (num == 1) res = 0;
            else res = 1;
        }
        return res;
    }


    public static int kthGrammar(int N, int K) {
        if (N < 1 || K < 1 || K > 1 << (N - 1)) {
            return -1;
        }
        StringBuilder result = kthGrammarN(N);
        return Integer.valueOf(String.valueOf(result.charAt(K - 1)));
    }

    public static StringBuilder kthGrammarN(int N) {
        if (N == 1) {
            return new StringBuilder("0");
        }
        StringBuilder ahead = kthGrammarN(N - 1);
        StringBuilder sb = new StringBuilder();
        int len = ahead.length();
        for (int i = 0; i < len; i++) {
            char si = ahead.charAt(i);
            String sis = String.valueOf(si);
            if ("0".equals(sis)) {
                sb.append("01");
            } else if ("1".equals(sis)) {
                sb.append("10");
            }
        }
        return sb;
    }
    //递归结束


    public static void insertionSortDesc(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i; j >= 0; j--) {
                int next = arr[j + 1];
                int arrj = arr[j];
                if (next < arrj) {
                    break;
                }
                if (next > arrj) {
                    arr[j + 1] = arrj;
                    arr[j] = next;
                }
            }
        }
    }

    public static void selectionSortDesc(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i; j < len; j++) {
                int mix = arr[i];
                int arrj = arr[j];
                if (arrj > mix) {
                    arr[i] = arrj;
                    arr[j] = mix;
                }
            }
        }
    }

    public static void bubbleSortDesc(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                int arrj = arr[j];
                int next = arr[j + 1];
                if (arrj < next) {
                    arr[j + 1] = arrj;
                    arr[j] = next;
                }
            }
        }
    }


    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i; j >= 0; j--) {
                int arrj = arr[j];
                int next = arr[j + 1];
                if (next > arrj) {
                    break;
                }
                if (next < arrj) {
                    arr[j + 1] = arrj;
                    arr[j] = next;
                }
            }
        }
    }

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                int mix = arr[i];
                int arrj = arr[j];
                if (arrj < mix) {
                    arr[i] = arrj;
                    arr[j] = mix;
                }
            }
        }
    }

    public static int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j + i < len; j++) {
                if (j + i == len - 1) {
                    continue;
                }
                int thisInt = arr[j];
                int nextInt = arr[j + 1];
                if (thisInt > nextInt) {
                    int temp = thisInt;
                    arr[j] = nextInt;
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

}
