import java.util.Scanner;

public class Canteen {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] foods = {"Burger", "Pizza", "Pasta", "Sandwich", "Milk Tea", "Golden Chicken Nugget"};
        double[] costs = {80.00, 120.00, 100.00, 70.00, 90.00, 500.00};

        int totalQuantity = 0;
        double subTotal = 0.0; 
        double totalDiscount = 0.0;

        char continueOrder = 'Y';

        System.out.println("===== MENU =====");
        for (int i = 0; i < foods.length; i++) {
            System.out.printf("%d. %s - $%.2f%n", (i + 1), foods[i], costs[i]);        
        }
        System.out.println();

        while (continueOrder == 'Y' || continueOrder == 'y') {
            System.out.print("Enter item number: ");
            int itemChoice = input.nextInt();

            System.out.print("Enter quantity: ");
            int orderQty = input.nextInt();

            boolean itemIsValid = (itemChoice >= 1 && itemChoice <= foods.length);
            boolean qtyIsValid = (orderQty >= 1 && orderQty <= 10);

            if (!itemIsValid || !qtyIsValid) {
                System.out.println("\nInvalid order! Please enter a valid item and quantity.\n");
            } 
            else {
                System.out.print("Are you a student? (Y/N): ");
                char studentStatus = input.next().charAt(0);
                boolean isStudent = (studentStatus == 'Y' || studentStatus == 'y');

                double unitCost = costs[itemChoice - 1];
                double currentSubtotal = unitCost * orderQty;

                double discountRate = 0.0;
                if (isStudent && currentSubtotal >= 500.0) {
                    discountRate = 0.15;
                } else if (isStudent) {
                    discountRate = 0.10;
                } else if (currentSubtotal >= 500.0) {
                    discountRate = 0.05;
                }

                double deduct = currentSubtotal * discountRate;
                double currentTotal = currentSubtotal - deduct;

                totalQuantity = totalQuantity + orderQty;
                subTotal = subTotal + currentSubtotal;
                totalDiscount = totalDiscount + deduct;

                System.out.println();
                System.out.printf("Subtotal: $%.2f%n", currentSubtotal);
                System.out.printf("Discount: $%.2f%n", deduct);
                System.out.printf("Order total: $%.2f%n%n", currentTotal);
            }

            System.out.print("Do you want to order again? (Y/N): ");
            continueOrder = input.next().charAt(0);
            System.out.println();
        }

        double finalBill = subTotal - totalDiscount;

        System.out.println("===== ORDER SUMMARY =====");
        System.out.println("Total items: " + totalQuantity);
        System.out.printf("Total before discount: $%.2f%n", subTotal);
        System.out.printf("Total discount: $%.2f%n", totalDiscount);
        System.out.printf("Final amount: $%.2f%n", finalBill);
        System.out.println("Thank you for ordering!");
        
        input.close();
    }
}