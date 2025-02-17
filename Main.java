import java.util.ArrayList;
import java.util.Scanner;
import org.json.simple.JSONObject;

/*
 * @author Isaac
 * @author Natalie
 */
public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int choice;
    JSONObject user;

    do {
      System.out.println("""1. Login");
     2. Add user
     3. Search user by ID
     4. Print all users
     5. Exit
     Enter your choice: """
      choice = scanner.nextInt();

      switch (choice) {
        case 1:
          login(scanner);
          Game.start();

          break;
        case 2:
          addUser(scanner);
          break;
        case 3:
          searchUserById(scanner);
          break;
        case 4:
          UserRepository.printAllUsers();
          break;
        case 9:
          System.out.println("Goodbye!");
          scanner.close();
          break;
        default:
          System.out.println("Invalid choice. Please try again.");
      }
    } while (choice != 9);

    scanner.close();
  }

  /*
   * @author Isaac
   *
   * @param scanner - user input
   *
   * @return User - the user if they logged in
   */
  private static User login(Scanner scanner) {
    System.out.print("Enter username: ");
    String username = scanner.next();

    System.out.print("Enter password: ");
    String password = encryptPassword(scanner.next());

    User user = Login.authenticateUser(username, password);
    if (user.getId() == -1) {
      System.out.println("Invalid username or password.");
      return null;
    }

    User currentUser = UserRepository.searchUserById(user.getId());
    if (currentUser == null) {
      System.out.println("User not found.");
      return null;
    }

    System.out.println("Login successful. Hello " + currentUser.getFirstName());
    return currentUser;
  }

  /*
   * @author Isaac
   *
   * @param scanner - user input
   *
   * @return
   */
  private static void loggedIn(Scanner scanner, User currentUser) {
    int loggedInChoice;
    do {
      System.out.println("1. View current user");
      System.out.println("2. Play MathIsFun");
      System.out.println("9. Logout");
      System.out.print("Enter your choice: ");
      loggedInChoice = scanner.nextInt();

      switch (loggedInChoice) {
        case 1:
          UserRepository.printSearchedUser(currentUser.getId());
          break;
        case 2:
          MathIsFun(scanner);
          break;
        case 9:
          System.out.println("Logging out...");
          break;
        default:
          System.out.println("Invalid choice. Please try again.");
      }
    } while (loggedInChoice != 9);
  }

  /*
   * @author Natalie
   * @author Isaac
   *
   * @param scanner - user input
   */
  private static void MathIsFun(Scanner scanner) {
    int playAgain = 1;
    while (playAgain != 0) {
      System.out.println(
          "***********************************\n"
              + "*				  *\n"
              + "*    MATH IS FUN!                 *\n"
              + "*                                 *\n"
              + "***********************************");

      System.out.println(
          "Select a game below:\n"
              + "1. Addition     2. Subtraction     3. Multiplication   4.Division");

      switch (scanner.nextInt()) {
        case 1: // addition
          ArrayList<Double> grades = MathFunctions.Addition(scanner);
          break;
        case 2: // subtraction
          ArrayList<Double> gradesSub = MathFunctions.Subtraction(scanner);
          break;
        case 3: // multiplication
          MathFunctions.Multiplication(scanner);
          break;
        case 4: // division
          break;
      }
      System.out.println("Would you like to play again?");
      System.out.println("press 0 to quit, or 1 to continue");
      playAgain = scanner.nextInt();
    }
  }

  /*
   * @author Isaac
   *
   * @param scanner - user input
   */
  private static void addUser(Scanner scanner) {
    System.out.print("Enter email: ");
    String email = scanner.next();
    
    System.out.print("Enter gameLevel: ");
    String level = scanner.next();

    private static void addUser(Scanner scanner) {
        System.out.print("Enter email: ");
        String email = scanner.next();

        int level = 0;

        do {
            System.out.print("Enter level(1-4): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a number (1-4): ");
                scanner.next();
            }
            level = scanner.nextInt();
        } while (level > 4 || level < 1);

        System.out.print("Enter first name: ");
        String firstName = scanner.next();

    Login.addUser(email, level, firstName, lastName, username, password);

  }

  /*
   * @author Isaac
   *
   * @param scanner - user input
   *
   */
  private static void searchUserById(Scanner scanner) {
    System.out.print("Enter user ID: ");
    int id = scanner.nextInt();
    UserRepository.printSearchedUser(id);
  }
}
