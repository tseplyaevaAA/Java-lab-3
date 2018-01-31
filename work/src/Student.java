import java.util.*;

public class Student {


    int id_stud;
    String fio;
    Group group;
    List<Float> marks;
    float mid_mark;


    Student(int _id, String _fio) {
        this.marks = new ArrayList<Float>();
        this.id_stud = _id;
        this.fio = _fio;
        this.mid_mark = 0;
    }


    void addMark(float _n) {
        marks.add(_n);
    }

    float midMark() {
        float mid = 0;
        int k = 0;
        for (int i = 0; i < marks.size(); i++) {
            mid += marks.get(i);
            k++;
        }
        mid = mid / k;
        return mid;
    }

    protected void addToGroup(Group _P) {
        int m = this.id_stud;
        String abc = Integer.toString(m);

        if (_P.findStud(abc) == true) {
            this.group = _P;
        }
        //   }
    }

    void addRandomMarks() {
            for (int i = 0; i < 5; i++) {
            float mark = 2 + ((float) Math.random() * 3);

            float ost = (float) (mark % 0.5);
            mark = mark - ost;
            if (mark == 2.5 ){ mark =5;}
            this.addMark(mark);
        }
    }
}

