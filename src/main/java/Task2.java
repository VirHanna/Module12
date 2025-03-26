import java.util.concurrent.*;

public class Task2 {

    private static final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        int n = 15;
        ExecutorService executor = Executors.newFixedThreadPool(4);

        executor.submit(() -> fizz(n));
        executor.submit(() -> buzz(n));
        executor.submit(() -> fizzBuzz(n));
        executor.submit(() -> number(n));
        executor.shutdown();

    }

    public static void fizz(int n) {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                try {
                    queue.put("fizz_" + i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void buzz(int n) {
        for (int i = 1; i <= n; i++) {
            if (i % 5 == 0 && i % 3 != 0) {
                try {
                    queue.put("buzz_" + i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void fizzBuzz(int n) {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                try {
                    queue.put("fizzbuzz_" + i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void number(int n) {
        String[] results = new String[n];

        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 != 0) {
                results[i - 1] = String.valueOf(i);
            } else {
                try {
                    String item = queue.take();
                    int index = Integer.parseInt(item.split("_")[1]) - 1;
                    results[index] = item.split("_")[0];
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(results[i]);
            if (i < n - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}