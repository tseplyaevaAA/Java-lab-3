//Java-lab-3
//Efremov Pavel
//

import java.util.ArrayList;
import java.util.Date;

public class Group {

    private String Title = "";                                          //название группы
    private ArrayList<Students> Students_FIO;                           //массив из ссылок на студентов
    private int Num = 0;                                                //количество студентов в группе
    private Students Head;                                              //ссылка на старосту (из членов группы)
    private int AVMG = 0;                                               //Среднее значение оцентки в группе

     Group(String title){                 //конструктор групп (String title)
        this.Title = title;
        Students_FIO = new ArrayList<Students>();
        Date date = new Date();
        System.out.println("Создана группа: " + this.Title );
        Dekanat.writeToFile(date + " Создана группа: " + this.Title, Dekanat.logPath);

    }

    public void addStudent(Students students){
        this.Students_FIO.add(students);
        this.setNum();
        this.AVMGroup();
        Date date = new Date();
        System.out.println("В группу " + this.Title + " добавлен студлент: " + students.getFIO() );
        Dekanat.writeToFile(date + " В группу " + this.Title + " добавлен студлент: " + students.getFIO(), Dekanat.logPath);
    }

    public void setHead(){
        this.setNum();
        if (this.Num != 0) {
            this.Head = null;
            int random = (int) (Math.random() * Num + 1);
            this.Head = Students_FIO.get(random - 1);
            Date date = new Date();
            System.out.println("Студент " + this.Head.getFIO() + " избран старостой");
            Dekanat.writeToFile(date + " Студент " + this.Head.getFIO() + " избран старостой ", Dekanat.logPath);
        }
        else System.out.println("Группа пуста");
    }                           //выбор старосты

    public Students search (int id) {
        Students student = null;
        for (int i = 0; i < this.Students_FIO.size(); i++) {
            if (this.Students_FIO.get(i).getID().equals(id)) {
                student = this.Students_FIO.get(i);
                break;
            }
        }
        return student;
    }                //поиск студента по ID

    public Students search(String fio){
        Students student = null;
            for (int i = 0; i < this.Students_FIO.size() ; i++) {
                if (this.Students_FIO.get(i).getFIO().equals(fio)){
                    student = this.Students_FIO.get(i);
                    break;
                }
            }
        return student;
    }              //поиск студента по FIO

    public void AVMGroup(){
        int sum = 0;

        for (int i = 0; i < this.Students_FIO.size(); i++){
            sum +=  this.Students_FIO.get(i).getAVM();
        }
        AVMG = sum/this.Students_FIO.size();

    }                          //средний балл группы

    public void deleteStudent(Students str){             //входной параметр - студент
        Date date = new Date();

        if ((search(str.getID()) != null) && (Dekanat.aceptToDelete == true)) {
            System.out.println("Удален студент " + str.getFIO() + " из группы " + this.getTitle());
            Dekanat.writeToFile(date + " Удален студент " + str.getFIO() + " из группы " + this.getTitle(), Dekanat.logPath);
            int index = this.Students_FIO.indexOf(str);
            if (this.getHead() != this.Students_FIO.get(index))
                this.Students_FIO.remove(index);
            else {
                this.Students_FIO.remove(index);
                this.setHead();
            }
        }
        else {
            System.out.println("Студент " + str.getFIO() + " не найден");
            Dekanat.setAceptToDelete(false);
        }

    }



    //-----------------------------------get------------------------------------

    public int getNum() {
        return Num;
    }

    public String getTitle() {
        return Title;
    }

    public int getAVMG() {
        return AVMG;
    }

    public ArrayList<Students> getStudents_FIO() {
        return Students_FIO;
    }

    public Students getHead() {
        return Head;
    }

//-------------------------------------------------------------------------------
//------------------------------------set----------------------------------------

    public void setNum() {
        Num = Students_FIO.size();
    }

//-------------------------------------------------------------------------------

}
