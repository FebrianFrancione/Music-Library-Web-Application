package Soap.utils;

public final class StudentData {
    public String first_name ;
    public String course_name;
    public String server_message;

    public StudentData(){
    }

    public StudentData(String first_name, String course_name, String res){
        this.first_name = first_name;
        this.course_name = course_name;
        this.server_message = res;
    }

    public String GetFirstName(){
        return this.first_name;
    }
    public String GetCourseName(){
        return this.course_name;
    }
    public String GetServerMessage(){
        return this.server_message;
    }

    public void SetFirstName(String first_name){
        this.first_name = first_name;
    }
    public void SetCourseName(String course_name){
        this.course_name = course_name;
    }
    public void SetServerMessage(String server_message){
        this.server_message = server_message;
    }
}
