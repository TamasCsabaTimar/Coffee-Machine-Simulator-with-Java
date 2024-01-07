import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        int[][] nums;

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        nums = new int[n][k];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                nums[i][j] = scanner.nextInt();
            }
        }

        int a = scanner.nextInt();
        int b = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                int x = j==a ? nums[i][b] : (j==b ? nums[i][a] : nums[i][j]);
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }
}