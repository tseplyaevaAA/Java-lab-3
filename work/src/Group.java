import java.util.*;

public class Group {
    String title;
    int num;
    Student head;
    List<Student> list;
    Dekanat d;

    Group(String _title) {
        this.title = _title;
        this.num = 0;
        this.list = new ArrayList<Student>();
        this.head = null;
    }

    void addStud(Student a) {
        int m = a.id_stud;
        String abc = Integer.toString(m);

        if (this.findStud(abc) == true) {
            System.out.println("This student already exists");
        } else {

            this.list.add(a);
            this.num++;
          a.addToGroup(this);
            this.findHead();
        }
    }

    void addStudById(int id) {
        String abc = Integer.toString(id);
        if (this.findStud(abc) == true) {
            System.out.println("This student already exists");
        } else {
        if ((id<= d.students.size()&&(id>0))) {
         Student person = new Student(0, "");
         person = d.dekRequestStud(id);

         this.list.add(person);
         this.num++;
          person.addToGroup(this);
          this.findHead();
        }
        }
    }

   protected void findHead() {
        Student temp;
        float max_mark = 0;

        for (int i = 0; i < list.size(); i++) {
            temp = list.get(i);
            if (temp.midMark() > max_mark) {
                this.head = temp;
            }

        }
    }

    float midMarkGroup() {
        Student temp;
        float mid_mark = 0;
        for (int i = 0; i < list.size(); i++) {
            temp = list.get(i);
            mid_mark += temp.midMark();
        }

        mid_mark = mid_mark / this.num;
        return mid_mark;
    }

    boolean findStud(String abc) {

        Student temp;
        int id1 = -1;
        String fio1 = "";
        int flag = 0;
        if ((abc.charAt(0) == '0') || (abc.charAt(0) == '1') || (abc.charAt(0) == '2') || (abc.charAt(0) == '3') || (abc.charAt(0) == '4') || (abc.charAt(0) == '5') || (abc.charAt(0) == '6') || (abc.charAt(0) == '7') || (abc.charAt(0) == '8') || (abc.charAt(0) == '9')) {
            flag = 1;
            id1 = Integer.parseInt(abc);
        } else {
            fio1 = abc;
        }
        // if a ID  flag =1;
        if (flag == 1) {
            for (int i = 0; i < list.size(); i++) {
                temp = list.get(i);
                int id2 = temp.id_stud;

                if (id2 == id1) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < list.size(); i++) {
                temp = list.get(i);
                String fio2 = temp.fio;

                if (fio2.equals(fio1)) {
                    return true;
                }
            }
        }

        return false;
    }

    void expellStud(String abc) {
        Student temp;
        for (int i = 0; i < list.size(); i++) {
            temp = list.get(i);
            String fio2 = temp.fio;

            if (fio2.equals(abc)) {
                list.get(i).group= null;
                list.remove(i);
                break;
            }
        }
    }

    void showGroup() {
        Student temp;
        for (int i = 0; i < list.size(); i++) {
            temp = list.get(i);
            String fio2 = temp.fio;
            int m = temp.id_stud;
            System.out.println(m + " " + fio2);

        }
    }
}