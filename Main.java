import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        int choice;

        do {
            System.out.println("\n===== Student Record Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Search Student by ID");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Generate Report");
            System.out.println("7. Display File Properties");
            System.out.println("8. Read Text File");
            System.out.println("9. Read Binary File");
            System.out.println("10. Read Object File");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = input.nextInt();
            input.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Student ID: ");
                        int id = input.nextInt();
                        input.nextLine();

                        System.out.print("Enter Name: ");
                        String name = input.nextLine();

                        System.out.print("Enter Department: ");
                        String department = input.nextLine();

                        System.out.print("Enter GPA: ");
                        double gpa = input.nextDouble();

                        Student student = new Student(id, name, department, gpa);
                        manager.addStudent(student);
                        break;

                    case 2:
                        System.out.print("Enter Student ID to search: ");
                        int searchId = input.nextInt();
                        manager.searchStudent(searchId);
                        break;

                    case 3:
                        System.out.print("Enter Student ID to update: ");
                        int updateId = input.nextInt();
                        input.nextLine();

                        System.out.print("Enter New Name: ");
                        String newName = input.nextLine();

                        System.out.print("Enter New Department: ");
                        String newDepartment = input.nextLine();

                        System.out.print("Enter New GPA: ");
                        double newGpa = input.nextDouble();

                        manager.updateStudent(updateId, newName, newDepartment, newGpa);
                        break;

                    case 4:
                        System.out.print("Enter Student ID to delete: ");
                        int deleteId = input.nextInt();
                        manager.deleteStudent(deleteId);
                        break;

                    case 5:
                        manager.displayAllStudents();
                        break;

                    case 6:
                        manager.generateReport();
                        break;

                    case 7:
                        manager.displayFileProperties();
                        break;

                    case 8:
                        manager.readTextFile();
                        break;
                    case 9:
                        manager.readBinaryFile();
                        break;

                    case 10:
                        manager.readObjectFile();
                        break;

                    case 0:
                        System.out.println("Program exited.");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                input.nextLine();
            }

        } while (choice != 0);

        input.close();
    }
}
