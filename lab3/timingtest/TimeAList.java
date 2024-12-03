package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        AList<Integer> Ns = new AList<Integer>();
        AList<Double> times = new AList<Double>();
        AList<Integer> opCounts = new AList<Integer>();

        // test different size
        int[] sizes = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};

        for(int size: sizes){
            // create a test Alist
            AList<Integer> testList = new AList<>();

            // create a stopwatch
            Stopwatch sw = new Stopwatch();

            // add element to this Alist to its capacity -> (in sizes array "loop through different size")
            for(int i = 0; i < size; i++){
                testList.addLast(2);
            }

            // record time
            double timeInSeconds = sw.elapsedTime();

            // store the data
            Ns.addLast(size);
            times.addLast(timeInSeconds);
            opCounts.addLast(size);  // number of operations equal to size
        }

        printTimingTable(Ns, times, opCounts);
    }
}
