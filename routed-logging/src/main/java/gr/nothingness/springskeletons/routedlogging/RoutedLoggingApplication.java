package gr.nothingness.springskeletons.routedlogging;

public class RoutedLoggingApplication {

  public static void main(String[] args) {
    new FirstRandomNumberFetcher("1st Random Thread 1").start();
    new FirstRandomNumberFetcher("1st Random Thread 2").start();
    new SecondRandomNumberFetcher("2nd Random Thread 1").start();
    new SecondRandomNumberFetcher("2nd Random Thread 2").start();
  }

}
