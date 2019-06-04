package root;

public class BasicStuentInfoBlob {
    private String Name_;
    private String ID_;
    private String Gender_;
    private String Class_;
    private String Collage_;

    public BasicStuentInfoBlob() {
    }

    public BasicStuentInfoBlob(String name_, String ID_, String gender_, String class_, String collage_) {
        Name_ = name_;
        this.ID_ = ID_;
        Gender_ = gender_;
        Class_ = class_;
        Collage_ = collage_;
    }

    public String getName_() {
        return Name_;
    }

    public void setName_(String name_) {
        Name_ = name_;
    }

    public String getID_() {
        return ID_;
    }

    public void setID_(String ID_) {
        this.ID_ = ID_;
    }

    public String getGender_() {
        return Gender_;
    }

    public void setGender_(String gender_) {
        Gender_ = gender_;
    }

    public String getClass_() {
        return Class_;
    }

    public void setClass_(String class_) {
        Class_ = class_;
    }

    public String getCollage_() {
        return Collage_;
    }

    public void setCollage_(String collage_) {
        Collage_ = collage_;
    }
}
