//Java-lab-3
//Efremov Pavel
//


import java.nio.Buffer;
import java.util.ArrayList;
import java.io.*;
import java.util.Date;

public class Dekanat {

    private ArrayList<Integer> ID = new ArrayList<Integer>();               //ID студентов из файла
    private ArrayList<String> FIO = new ArrayList<String>();                //список FIO студентов из файла

    private ArrayList<Group> allGroups = new ArrayList<Group>();            //количество групп
    private ArrayList<String> GroupsTitle = new ArrayList<String>();        //Список названий групп, указанных в файле
    private ArrayList<Students> allStudents = new ArrayList<Students>();    //список всех студентов

    public static boolean aceptToDelete = false;                      //разрешение на удаление

    public String pathStatistics = "D:\\java_work\\javalab3\\src\\main\\resources\\Statistics.txt";
    public static String logPath = "D:\\java_work\\javalab3\\src\\main\\resources\\Log.txt";
    public String finalFile = "D:\\java_work\\javalab3\\src\\main\\resources\\finalFile.txt";


    public void FileReading(String path) {
        try {
            File file = new File(path);                                  //!mb error
            FileReader reader = new FileReader(file);
            BufferedReader buff = new BufferedReader(reader);

            String line;
            String[] temp;

            while ((line=buff.readLine()) != null) {
                temp = line.split("/");     //В начало строки файла залетает символ начала строки!
                ID.add(Integer.parseInt(temp[1]));
                FIO.add(temp[2]);

            }
            reader.close();
        }
        catch(IOException ex){
                System.out.println("File not exist");
        }
    }                        //чтение из файла Students

    public void ReadFileToCreateTitle(String path){
        try {
            File file = new File(path);                                  //!mb error
            FileReader reader = new FileReader(file);
            BufferedReader buff = new BufferedReader(reader);

            String line;

            while ((line=buff.readLine()) != null) {
                GroupsTitle.add(line);
            }
            reader.close();
        }
        catch(IOException ex){
            System.out.println("File not exist");
        }
    }               //чтение из файла GroupsTitle

    public void createStudents(){
        for (int i = 0; i < FIO.size(); i++ ){
            Students student = new Students(ID.get(i),FIO.get(i));
            allStudents.add(student);
            student.createMarks();                  //создаются оценки
            student.avarageMark();                  //вычисляется средний балл
            //System.out.println(student.getFIO()+" "+student.getID()+" "+student.getGroupStudent()+" "+student.getAVM());

        }
    }                                 //создание студентов

    public void createGroups(){
        for (String temp: GroupsTitle){
            Group group = new Group(temp);
            allGroups.add(group);
        }
    }                                   //создание групп студентов

    public void addAllStudentsToGroup(){
        if ((allGroups.size() != 0) && (allStudents.size() != 0)) {
            for (int i = 0; i < allStudents.size(); i++) {
                int temp =(int)(Math.random()*3);       //0,1,2
                transferStudent(allStudents.get(i), allGroups.get(temp));
              //  System.out.println(this.allStudents.get(i).getGroupStudent().getTitle());
            }
        }
    }                          //Заполнение 3x груп студентами

    public void transferStudent(Students students, Group group){
        aceptToDelete = true;
        int indexStudent = allStudents.indexOf(students);
        int indexGroup = allGroups.indexOf(group);

        if (students.getGroupStudent() == group){
            System.out.println("Студент уже состоит в этой группе");
        }
        else {
            allStudents.get(indexStudent).setGroupStudent(group);
            allGroups.get(indexGroup).addStudent(allStudents.get(indexStudent));
           // allGroups.get(indexGroup).deleteStudent(students);

            Date date = new Date();
            System.out.println("Студент " + students.getFIO() +" переведен в группу: "+ group.getTitle());
            writeToFile(date + " Студент " + students.getFIO() +" переведен в группу: "+ group.getTitle(), logPath);
        }
    }  //перевод студента в групп


    public void transferStudent(Integer id, String title){

        int indexStudent = -1;
        int indexGroup = -1;
        Group deleteIndexGroup;
        for (Group Gr: allGroups){
            if (Gr.getTitle().equals(title)) indexGroup = allGroups.indexOf(Gr);
        }
        for(Students stud: allStudents){
            if (stud.getID().equals(id)) {
                indexStudent = allStudents.indexOf(stud);
                deleteIndexGroup = allStudents.get(indexStudent).getGroupStudent();
            }

        }
        transferStudent(allStudents.get(indexStudent),allGroups.get(indexGroup));

    }

    public void addMarksStudents(Integer id, int mark){
        int index = -1;
        for (Integer i : ID){
            if (i.equals(id)) index = ID.indexOf(i);
        }
        Students student = allStudents.get(index);
        student.addMark(mark);
    }

    public void addMarksStudents(String fio, int mark){
        int index = -1;
        for (String str : FIO){
            if (str.equals(fio)) index = FIO.indexOf(str);
        }
        Students student = allStudents.get(index);
        student.addMark(mark);
    }

    public void statistics(){
        String str;

        for (Students stud : allStudents){
            str = ("Средний бал студента " + stud.getFIO() + " = " + stud.getAVM() + "\n");
            writeToFile(str,pathStatistics);
        }
        for (Group Gr : allGroups){
            str = ("Средний бал группы " + Gr.getTitle() + " = " + Gr.getAVMG() + "\n");
            writeToFile(str,pathStatistics);
        }
    }           //запись статистики в файл

    public static void writeToFile(String str, String pathToWrite) {
        try {
            Writer file = new FileWriter(pathToWrite,true);
            file.write(str + "\r\n");
            file.close();
        }
        catch (IOException ex) {
            System.out.println("File can not be created");
        }

    }

    public void deleteStudent(Students student) {
        aceptToDelete = true;
        int index = allStudents.indexOf(student);
        Group group = allStudents.get(index).getGroupStudent();
        allStudents.remove(index);

        Date date = new Date();
        System.out.println("Удален студент с именем " + student.getFIO());
        writeToFile(date + " Удален студент с именем " + student.getFIO() + "\n", logPath);

        if (group != null) {
            group.deleteStudent(student);
        }

    }

    public void deleteStudent(String fio) {
        int index;
        aceptToDelete = true;
        for (Students stud: allStudents){
            if (stud.getFIO().equals(fio)) {
                index = allStudents.indexOf(stud);
                deleteStudent(allStudents.get(index));
                break;
            }

        }
    }

    public void deleteStudent(int id) {
        int index;
        aceptToDelete = true;
        for (Students stud: allStudents){
            if (stud.getID().equals(id)) {
                index = allStudents.indexOf(stud);
                deleteStudent(allStudents.get(index));
                break;
            }
        }
    }

    public void deleteBecauseLowMark(int number){
        ArrayList<Integer> index = new ArrayList<Integer>();
        setAceptToDelete(true);
        for (Students stud : allStudents){
            if (stud.getAVM() <= number){
                index.add(allStudents.indexOf(stud));
            }
        }
        for (int i = 0; i < allStudents.size(); i++){
            deleteStudent(allStudents.get(i));
                       
        }


    }

    public void saveToNewFile(){
       String str ;

        for (Students stud: allStudents) {
            str = ("/" + Integer.toString(stud.getID()) + "/" + stud.getFIO() + "/" + stud.getGroupStudent().getTitle());
            writeToFile(str,finalFile);
        }

    }

//---------------------------------get------------------------------------------

    public ArrayList<String> getGroupsTitle() {
        return GroupsTitle;
    }

    public boolean isAceptToDelete() {
        return aceptToDelete;
    }

    //------------------------------------------------------------------------------

    //----------------------------------set----------------------------------------

    public static void setAceptToDelete(boolean aceptToDelete) {
        Dekanat.aceptToDelete = aceptToDelete;
    }

    //-----------------------------------------------------------------------------
}
