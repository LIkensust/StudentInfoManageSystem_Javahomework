package root;

public class GradeInfoBlob {
    //"学号","姓名","学院","班级","课程","成绩"
    private String ID;
    private String StudentName;
    private String College;
    private String Class_;
    private String Course;
    private String Grade;

    public GradeInfoBlob() {
    }

    public String getID() {
        return ID;
    }

    public String getStudentName() {
        return StudentName;
    }

    public String getCollege() {
        return College;
    }

    public String getClass_() {
        return Class_;
    }

    public String getCourse() {
        return Course;
    }

    public String getGrade() {
        return Grade;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public void setCollege(String college) {
        College = college;
    }

    public void setClass_(String class_) {
        Class_ = class_;
    }

    public void setCourse(String course) {
        Course = course;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }
}
