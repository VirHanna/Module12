import java.util.concurrent.*;

public class Task1 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Runnable timeTask = () -> {
            long startTime = System.currentTimeMillis();
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000);
                    long elapsed = (System.currentTimeMillis() - startTime) / 1000;
                    System.out.println("Час, що минув: " + elapsed + " секунд");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Переривання потоку
                    System.out.println("Часовий потік перервано.");
                }
            }
        };

        Runnable messageTask = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(5000);
                    System.out.println("Минуло 5 секунд");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Потік повідомлення перервано.");
                }
            }
        };

        executorService.submit(timeTask);
        executorService.submit(messageTask);

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        executorService.shutdownNow();
    }
}
