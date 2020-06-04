package com.company;

public class Main {
    static int[] crossing(int [] array, int low, int mid, int high){
        int max_left = 0, left_sum = 0;
        int sum = 0;
        for(int i = mid; i >= low; i--){  //левая половина
            sum += array[i];
            if(sum > left_sum){
                left_sum = sum;
                max_left = i;
            }
        }
        int right_sum = 0, max_right = 0;
        sum = 0;
        for(int j = mid + 1; j <= high; j++){   //правая половина
            sum += array[j];
            if(sum > right_sum){
                right_sum = sum;
                max_right = j;
            }
        }
        return new int[]{max_left, max_right, left_sum + right_sum};
    }


     static int[] max_subarray(int[] array, int low, int high) {
         int mid;
         if (high == low)
             return new int[]{low, high, array[low]};   //только один элемент
         else {
             mid = (low + high) / 2;

             int[] left = max_subarray(array, low, mid);  //рекурсивно макс подмассив в левой
             int left_low = left[0];
             int left_high = left[1];
             int left_sum = left[2];

             int[] right = max_subarray(array, mid + 1, high);   //в правой
             int right_low = right[0];
             int right_high = right[1];
             int right_sum = right[2];

             int[] cross = crossing(array, low, mid, high);
             int cross_low = cross[0];
             int cross_hight = cross[1];
             int cross_sum = cross[2];

             if (left_sum >= right_sum && left_sum >= cross_sum)
                 return new int[]{left_low, left_high, left_sum};
             else if (right_sum >= left_sum && right_sum >= cross_sum)
                 return new int[]{right_low, right_high, right_sum};
             else
                 return new int[]{cross_low, cross_hight, cross_sum};
         }
     }



    public static void main(String[] args) {
        int[] array = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        System.out.print("День:      ");
        for(int i = 0; i < 17; i ++)
            System.out.print(i + "    ");
        System.out.println("\nЦена:     100  113  110   85  105  102   86   63   81  101   94   106   101    79    94    90    97");
        System.out.print("Изменение:      ");
        for(int i = 0; i < 16; i ++)
            System.out.print(array[i] + "   ");

        int low = 0, high = array.length-1;
        int[] res = max_subarray(array, low, high);
        System.out.println("\nМаксимальный подмасссив: с " + (res[0]-1) + " до " + res[1] + "\nСумма = " + res[2]);

    }
}
