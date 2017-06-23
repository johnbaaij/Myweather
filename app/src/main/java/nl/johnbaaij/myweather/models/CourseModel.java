package nl.johnbaaij.myweather.models;

/**
 * Created by John on 16/06/2017.
 */

public class CourseModel {

    public String name;
    public int ects;
    public int grade;
    public int period;

    public CourseModel(String name, int ects, int grade, int period) {
        this.name = name;
        this.ects = ects;
        this.grade = grade;
        this.period = period;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
// ADD GETTERS AND SETTERS - ONLY IF NEEDED !! }

