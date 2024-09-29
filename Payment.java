class Payment {
    public static boolean processPayment(User user, double amount) {
        // Simulating a payment process
        System.out.println("Processing payment for " + user.getName() + " of amount $" + amount);
        return true; // Assume payment is always successful for simplicity
    }
}
