public class HelloNumbers {
    public static void main(String[] args) {
        int x = 1;
        int total = 0;
        while (x <= 10) {
            System.out.print(total + " "); // 0, 1, 3, 6, 10
            total = total + x; // 1, 1+2=3, 3+3=6, 6+4=10, 15
            x = x + 1; // 2, 2+1, 3+1,4+1
        }
	}
} 
