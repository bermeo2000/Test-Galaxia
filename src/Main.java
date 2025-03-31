import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        TransactionProcessor processor = new TransactionProcessor();

        System.out.println("Ingrese el dato indicado:");

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) break;
            processor.processInput(input);
        }
        scanner.close();
    }
}