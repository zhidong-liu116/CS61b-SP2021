package flik;

public class HorribleSteve {
    public static final int MAX = 500;

    public static void main(String[] args) throws Exception {
        int i = 0;
        for (int j = 0; i < MAX; ++i, ++j) {
            if (!Flik.isSameNumber(i, j)) {
                throw new Exception(
                        String.format("i:%d not same as j:%d ??", i, j));
            }
        }
        System.out.println(STR."i is \{i}");
    }
}
