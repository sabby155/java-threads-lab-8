import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        //Create a future that grabs an integer
        Future<Integer> futureInteger = executor.submit(getCallableInteger());
        System.out.println("Result is number: " + futureInteger.get());

        //Create a future that grabs a string
        Future<String> futureString = executor.submit(getCallableString());
        System.out.println("Result is string: " + futureString.get());


        //Create a list of futures and add futures
        List<Future> futuresList = new ArrayList<>();
        futuresList.add(futureInteger);
        futuresList.add(futureString);

        System.out.println(countFinishedFutures(futuresList));

    }

    public static Callable<Integer> getCallableInteger() {
        int num = 3;

        Callable<Integer> callable = () -> {
            System.out.println(Thread.currentThread().getName()+ " is responsible for this call");
            return num;
        };
        return callable;
    }
    public static Callable<String> getCallableString() {
        String string = "Hi!";

        Callable<String> callable = () -> {
            System.out.println(Thread.currentThread().getName()+ " is responsible for this call");
            return string;
        };
        return callable;
    }

    public static AtomicInteger countFinishedFutures(List<Future> futures) {
        // your code here
        AtomicInteger counter = new AtomicInteger();
        futures.forEach(future -> counter.getAndIncrement());
        return counter;
    }
}