package org.baklansbalkan;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        File file = new File("Headache.txt");
        Map<LocalDate, Headache> allHeadache = reader(file);
        LocalDate today = LocalDate.now();

        int choiceInt = 0;
        while (choiceInt != 4) {
            mainMenu();
            String choice = scanner.nextLine();
            choiceInt = Integer.parseInt(choice);
            switch (choiceInt) {
                case 1:
                    System.out.println("Add an entry for today");
                    Headache headacheToday = new Headache();
                    headacheToday.setDate(today);
                    System.out.println("Today is: " + today);
                    newEntry(scanner, headacheToday);
                    allHeadache.put(headacheToday.getDate(), headacheToday);
                    writer(allHeadache, file);
                    break;
                case 2:
                    System.out.println("Add an entry for another day");
                    Headache otherDay = new Headache();
                    otherDay.setDate(setAnotherDate(scanner));
                    System.out.println("Add an entry for: " + otherDay.getDate());
                    newEntry(scanner, otherDay);
                    allHeadache.put(otherDay.getDate(), otherDay);
                    writer(allHeadache, file);
                    break;
                case 3:
                    viewStatistics(scanner, today, allHeadache);
                    break;
                case 4:
                    System.out.println("Exit...");
                    break;
                default:
                    System.out.println("Please type a number from 1 to 4");
            }
        }
    }

    private static void mainMenu() {
        System.out.println("I want:");
        System.out.println("1 - Add an entry for today");
        System.out.println("2 - Add an entry for another day");
        System.out.println("3 - View statistics");
        System.out.println("4 - Exit");
    }

    private static void newEntry(Scanner scanner, Headache headache) {
        headache.setHeadache(haveHeadache(scanner));
        if (headache.getHeadache()) {
            headache.setMedicine(haveMedicine(scanner));
            headache.setIntensity(howStrong(scanner));
            headache.setLocalisation(whereLocalized(scanner));
            headache.setPhysicalActivity(haveActivity(scanner));
            headache.setNausea(haveNausea(scanner));
            headache.setPhotophobia(havePhotophobia(scanner));
            headache.setPhonophobia(havePhonophobia(scanner));
            headache.setTimesOfDay(whenStarted(scanner));
        }
        headache.setComment(leaveComment(scanner));
        System.out.println("Done! \n" + headache);
    }

    private static LocalDate setAnotherDate(Scanner scanner) {
        while (true) {
            System.out.println("Set a date in format YYYY-MM-DD");
            String anotherDate = scanner.nextLine();
            if (anotherDate.matches("(((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]))")) {
                return LocalDate.parse(anotherDate);
            } else {
                System.out.println("Please use the format YYYY-MM-DD");
            }
        }
    }

    private static boolean haveHeadache(Scanner scanner) {
        String questionHeadache = "Have you had a headache? Y/N";
        return yesOrNo(questionHeadache, scanner);
    }

    public static String haveMedicine(Scanner scanner) {
        System.out.println("Have you taken any pills? Write the name, dosage");
        return scanner.nextLine();
    }

    public static int howStrong(Scanner scanner) {
        while (true) {
            System.out.println("How strong has the headache been? On a scale from 1 to 5");
            String intensity = scanner.nextLine();
            try {
                int intensityInt = Integer.parseInt(intensity);
                if (intensityInt > 0 && intensityInt < 6) {
                    return intensityInt;
                } else {
                    System.out.println("Please type a number from 1 to 5");
                }
            } catch (NumberFormatException e) {
                System.out.println("Not a number. Please type a number from 1 to 5");
            }
        }
    }

    public static String whereLocalized(Scanner scanner) {
        System.out.println("Where has the pain localized?");
        return scanner.nextLine();
    }

    private static boolean haveActivity(Scanner scanner) {
        String questionActivity = "Has it got worse with physical activity? For example, when you have stood up or bent over. Y/N";
        return yesOrNo(questionActivity, scanner);
    }

    private static boolean haveNausea(Scanner scanner) {
        String questionNausea = "Have you felt nausea? Y/N";
        return yesOrNo(questionNausea, scanner);
    }

    private static boolean havePhotophobia(Scanner scanner) {
        String questionPhotophobia = "Have you felt photophobia? Y/N";
        return yesOrNo(questionPhotophobia, scanner);
    }

    private static boolean havePhonophobia(Scanner scanner) {
        String questionPhonophobia = "Have you felt phonophobia? Y/N";
        return yesOrNo(questionPhonophobia, scanner);
    }

    private static String whenStarted(Scanner scanner) {
        System.out.println("What time of day has your headache started?");
        return scanner.nextLine();
    }

    private static String leaveComment(Scanner scanner) {
        System.out.println("Anything else? Leave a comment");
        return scanner.nextLine();
    }

    private static boolean yesOrNo(String question, Scanner scanner) {
        while (true) {
            System.out.println(question);
            char answer = scanner.nextLine().charAt(0);
            if (answer == 'Y' || answer == 'y')
                return true;
            else if (answer == 'N' || answer == 'n')
                return false;
            else System.out.println("Please type Y or N");
        }
    }

    private static void writer(Map<LocalDate, Headache> allHeadache, File file) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file, false)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(allHeadache);
        } catch (IOException e) {
            System.out.println("File error");
        }

    }

    private static Map<LocalDate, Headache> reader(File file) {
        Map<LocalDate, Headache> allHeadache = new TreeMap<>();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            allHeadache = (Map<LocalDate, Headache>) objectInputStream.readObject();
            System.out.println("I have successfully read all the previous entries");
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("File is empty. You have no previous entries");
        }
        return allHeadache;
    }

    private static void statisticsMenu() {
        System.out.println("View statistics for:");
        System.out.println("1 - This month");
        System.out.println("2 - Other month");
        System.out.println("3 - Return to maim menu");
    }

    private static void viewStatistics(Scanner scanner, LocalDate today, Map<LocalDate, Headache> allHeadache) {
        int choiceInt = 0;
        while (choiceInt != 3) {
            statisticsMenu();
            String choice = scanner.nextLine();
            choiceInt = Integer.parseInt(choice);
            switch (choiceInt) {
                case 1:
                    System.out.println("This month");
                    int thisMonth = today.getMonthValue();
                    statisticsMonth(thisMonth, allHeadache);
                    break;
                case 2:
                    System.out.println("Other month");
                    System.out.println("Please type the number of month (01 - 12)");
                    int otherMonth = Integer.parseInt(scanner.nextLine());
                    statisticsMonth(otherMonth, allHeadache);
                    break;
                case 3:
                    System.out.println("Return to maim menu");
                    break;
                default:
                    System.out.println("Please type a number from 1 to 3");
            }
        }
    }

    private static void statisticsMonth(int month, Map<LocalDate, Headache> allHeadache) {
        List<Headache> list = allHeadache.values().stream()
                .filter(element -> Objects.equals(element.getDate().getMonthValue(), month))
                .filter(element -> Objects.equals(element.getHeadache(), true))
                .toList();
        System.out.println("Days with headache: " + list.size());
        list.forEach(System.out::println);
    }
}









