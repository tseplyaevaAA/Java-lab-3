//Java-lab-3
//Efremov Pavel
//

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Students {

    private Integer ID;                         //ID Studenta
    private String FIO;                         //FIO student
    private Group groupStudent;                 //группа студента
    private ArrayList<Integer> Marks;           //список оценок студента
    private int AVM = 0;                        //средняя оценка студента
    private int Num = 0;                        //количество оценок studenta

    Students(Integer id, String fio){            //конструктор
        this.ID = id;
        this.FIO = fio;
        this.groupStudent = null;                //пока не пренадлежит никакой группе
        Marks = new ArrayList<Integer>();
        Date date = new Date();
        System.out.println("Создан студент с именем: " + FIO + ", ID = " + ID);
        Dekanat.writeToFile(date + " Создан студент: " + this.FIO, Dekanat.logPath);

    }

    public void createMarks(){
        Num = (int)(Math.random()*6+5);                     //кол-во оценок студента от 5 до 10

        for(int i = 0; i < Num; i++){
            int temp = (int)(Math.random()*5+1);             //оценки от 1 до 5
            addMark(temp);
        }
    }

    public void addMark(int num){                            //добавление оценки
        Marks.add(num);
        Date date = new Date();
        System.out.println("Студенту " + FIO + " добавлена оценка" + num );
        Dekanat.writeToFile(date + " Студенту " + FIO + " добавлена оценка " + num,Dekanat.logPath);
        avarageMark();

    }

    public void avarageMark(){                               //среднее оценка
        int sum = 0;
        for (int i : Marks){
            sum += i;
        }
        AVM = sum/Num;
    }

//-------------------------------get-----------------------------------
    public String getFIO() {
        return FIO;
    }

    public Integer getID() {
        return ID;
    }

    public Group getGroupStudent() {
        return groupStudent;
    }

    public int getAVM() {
        return AVM;
    }

    public int getNum() {
        return Num;
    }

    public ArrayList<Integer> getMarks() {
        return Marks;
    }



    //---------------------------------------------------------------------

//------------------------------set------------------------------------
    public void setGroupStudent(Group groupstudent) {
        this.groupStudent = groupstudent;
    }
//---------------------------------------------------------------------
}
