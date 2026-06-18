import java.io.*;
import java.util.*;

public class StudentManager {
    private ArrayList<Student> students = new ArrayList<>();

    private final File folder = new File("StudentRecords");
    private final File textFile = new File(folder, "students.txt");
    private final File binaryFile = new File(folder, "students.dat");
    private final File objectFile = new File(folder, "students.ser");
    private final File backupFile = new File(folder, "backup_students.txt");

    public StudentManager() {
        createFiles();
    }

    public void createFiles() {
        try {
            if (!folder.exists()) {
                folder.mkdir();
            }

            textFile.createNewFile();
            binaryFile.createNewFile();
            objectFile.createNewFile();
            backupFile.createNewFile();

        } catch (IOException e) {
            System.out.println("Error creating files: " + e.getMessage());
        }
    }

    public void addStudent(Student student) {
        students.add(student);
        saveToTextFile();
        saveToBinaryFile();
        saveToObjectFile();
        backupRecords();
        System.out.println("Student added successfully.");
    }

    public void searchStudent(int id) {
        for (Student s : students) {
            if (s.getStudentId() == id) {
                System.out.println("Student Found:");
                System.out.println(s);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void updateStudent(int id, String name, String department, double gpa) {
        for (Student s : students) {
            if (s.getStudentId() == id) {
                s.setName(name);
                s.setDepartment(department);
                s.setGpa(gpa);

                saveToTextFile();
                saveToBinaryFile();
                saveToObjectFile();
                backupRecords();

                System.out.println("Student updated successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void deleteStudent(int id) {
        Iterator<Student> iterator = students.iterator();

        while (iterator.hasNext()) {
            Student s = iterator.next();

            if (s.getStudentId() == id) {
                iterator.remove();

                saveToTextFile();
                saveToBinaryFile();
                saveToObjectFile();
                backupRecords();

                System.out.println("Student deleted successfully.");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    public void generateReport() {
        if (students.isEmpty()) {
            System.out.println("No students to generate report.");
            return;
        }

        double highest = students.get(0).getGpa();
        double lowest = students.get(0).getGpa();
        double total = 0;

        for (Student s : students) {
            double gpa = s.getGpa();

            if (gpa > highest) {
                highest = gpa;
            }

            if (gpa < lowest) {
                lowest = gpa;
            }

            total += gpa;
        }

        double average = total / students.size();

        System.out.println("===== Student Report =====");
        System.out.println("Total Students: " + students.size());
        System.out.println("Highest GPA: " + highest);
        System.out.println("Lowest GPA: " + lowest);
        System.out.println("Average GPA: " + average);
    }

    public void saveToTextFile() {
        try (PrintWriter writer = new PrintWriter(textFile)) {
            for (Student s : students) {
                writer.println(s);
            }
        } catch (IOException e) {
            System.out.println("Text file error: " + e.getMessage());
        }
    }

    public void readTextFile() {
        try (Scanner scanner = new Scanner(textFile)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Read text file error: " + e.getMessage());
        }
    }

    public void saveToBinaryFile() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(binaryFile))) {
            dos.writeInt(students.size());

            for (Student s : students) {
                dos.writeUTF(s.toString());
            }

        } catch (IOException e) {
            System.out.println("Binary file error: " + e.getMessage());
        }
    }
    public void readBinaryFile() {
        try (DataInputStream dis = new DataInputStream(
                new FileInputStream(binaryFile))) {

            int count = dis.readInt();

            System.out.println("Students stored in Binary File:");

            for (int i = 0; i < count; i++) {
                System.out.println(dis.readUTF());
            }

        } catch (IOException e) {
            System.out.println("Read Binary File Error: " + e.getMessage());
        }
    }

    public void saveToObjectFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(objectFile))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Object file error: " + e.getMessage());
        }
    }
    public void readObjectFile() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(
                             new FileInputStream(objectFile))) {

            ArrayList<Student> loadedStudents =
                    (ArrayList<Student>) ois.readObject();

            System.out.println("Students stored in Object File:");

            for (Student s : loadedStudents) {
                System.out.println(s);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Object Read Error: " + e.getMessage());
        }
    }

    public void backupRecords() {
        try (
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(textFile));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(backupFile))
        ) {
            int data;

            while ((data = bis.read()) != -1) {
                bos.write(data);
            }

        } catch (IOException e) {
            System.out.println("Backup error: " + e.getMessage());
        }
    }

    public void displayFileProperties() {
        displayProperties(textFile);
        displayProperties(binaryFile);
        displayProperties(objectFile);
        displayProperties(backupFile);
    }

    private void displayProperties(File file) {
        System.out.println("===== File Properties =====");
        System.out.println("Name: " + file.getName());
        System.out.println("Path: " + file.getAbsolutePath());
        System.out.println("Size: " + file.length() + " bytes");
        System.out.println("Last Modified: " + new Date(file.lastModified()));
    }
}
