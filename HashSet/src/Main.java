import java.util.HashSet;
import java.util.Set;

public class Main {
    public static boolean sochiahetcho3(int x) {
        while (x >= 3) {
            x -= 3;
        }
        return x == 0;
    }

    public static void main(String[] args) {
        Set<Integer> newSet = new HashSet<>();
        newSet.add(1);
        newSet.add(2);
        newSet.add(3);
        newSet.add(4);
        newSet.add(5);
        newSet.add(6);
        newSet.add(7);
        newSet.add(8);
        newSet.add(9);
        newSet.add(10);
        newSet.add(12);
        newSet.add(15);
        newSet.add(18);

        Set<Integer> newHashSet = new HashSet<>();

        for (Integer number : newSet) {
            if (sochiahetcho3(number)) {
                newHashSet.add(number);
            }
        }

        System.out.println("Danh sách các số chia hết cho 3 là: " + newHashSet);
    }
}
