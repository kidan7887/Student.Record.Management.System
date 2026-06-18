import java.io.Serializable;

public class Student implements Serializable{
    private int studentId;
    private String name;
    private String department;
    private double gpa;

    public Student(int studentId, String name, String departement, double gpa){
        this.studentId=studentId;
        this.name=name;
        this.department=departement;
        this.gpa=gpa;
    }

    public int getStudentId(){
        return studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public double getGpa() {
        return gpa;
    }

    public String toString() {
        return "ID: " +studentId+
                ", NAME: "+ name+
                ", DEPARTEMMENT: "+ department+
                ", GPA: "+ gpa ;
    }
}
