import java.util.InputMismatchException;
import java.util.Scanner;

public class DaysOfTheYear {

    public static void main(String[] args) {
        daysOfTheYear(1, 1);

    }

    static void daysOfTheYear(int day, int month) {
        // * Variables initialised outside of the while Loop to ensure they dont reset
        // every loop */

        String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December" };

        String ordinals = "";
        int PTurn = 4;
        int PNumber = 0;

        // while loop to ensure the game runs untill the end

        while (!((day == 31) && (month == 12))) {

            // * If statement to ensure the correct ordinal is used */
            if ((day % 10 == 1) && (day != 11)) {
                ordinals = "st";
            } else if ((day % 10 == 2) && (day != 12)) {
                ordinals = "nd";
            } else if ((day % 10 == 3) && (day != 13)) {
                ordinals = "rd";
            } else {
                ordinals = "th";
            }

            // * Equation to alternate player number */

            PNumber = ((PTurn % 2) + 1);
            Scanner in = new Scanner(System.in);

            // * System outputs */

            System.out.print("The current date is: " + day + ordinals + " of " + months[month - 1] + "\n");
            System.out.print("It is Player " + PNumber + "'s turn!\n");
            System.out.print("Do you want to increase the day or the month? (day or month): ");

            String DayorMonth = in.nextLine();

            // * While loop to check validity of user input */

            while ((!(DayorMonth.equalsIgnoreCase("month"))) && (!(DayorMonth.equalsIgnoreCase("day")))
                    || (DayorMonth.equalsIgnoreCase("month")) && (month == 12)
                    || ((DayorMonth.equalsIgnoreCase("day")) && (!isLegalDate((day + 1), month)))) {
                System.out.print("Input invalid, please try again!\n");
                DayorMonth = in.nextLine();

            }

            if ((DayorMonth.equalsIgnoreCase("month")) && (month != 12)) {
                int monthCheck = 0;
                System.out.print("Which month do you want to pick: ");

                // * while loop to detect if input is correct
                while (true) {
                    try {
                        monthCheck = in.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.print("Input invalid, please try again!\n");
                        in.nextLine();
                    }
                }

                // while loop to determine the validity of the month selected (real date &&
                // allowed within the game)
                while ((monthCheck <= month) || (!isLegalDate(day, monthCheck))) {
                    System.out.print("Input invalid, please try again!\n");
                    while (true) {
                        try {
                            monthCheck = in.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.print("Input invalid, please try again!\n");
                            in.nextLine();
                        }
                    }
                }
                month = monthCheck;
                PTurn++;

                // while loop to determine the validity of the day selected (real date &&
                // allowed within the game)
            } else if ((DayorMonth.equalsIgnoreCase("day")) && (day != 31)) {
                int dayCheck = 0;
                System.out.print("Which day do you want to pick: ");
                while (true) {
                    try {
                        dayCheck = in.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.print("Input invalid, please try again!\n");
                        in.nextLine();
                    }
                }
                while ((!isLegalDate(dayCheck, month)) || (dayCheck <= day)) {
                    System.out.print("Input invalid, please try again!\n");
                    while (true) {
                        try {
                            dayCheck = in.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.print("Input invalid, please try again!\n");
                            in.nextLine();
                        }
                    }

                }
                day = dayCheck;
                // increase the player turn integer so that the value will alternate
                PTurn++;
            }

        }

        System.out.print("The current date is: " + day + "st" + " of " + months[month - 1] + "\n");
        System.out.print("Player " + PNumber + " is the winner of the game!\n");
    }

    static boolean isLegalDate(int Legalday, int Legalmonth) {
        // boolean function to ensure the date given is valid
        if (Legalday > 31 || Legalmonth > 12 || Legalday < 1 || Legalmonth < 1) {
            return false;

        } else if ((Legalmonth == 2) & (Legalday > 28)) {
            return false;

        } else if ((Legalmonth == 4 || Legalmonth == 6 || Legalmonth == 9 || Legalmonth == 11) & Legalday <= 30) {
            return true;

        } else if (Legalmonth != 4 && Legalmonth != 6 && Legalmonth != 9 && Legalmonth != 11) {
            return true;
        }
        return false;
    }
}