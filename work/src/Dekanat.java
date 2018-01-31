import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;



public class Dekanat {

    List<Student> students;
    List<Group> groups;

    Dekanat(){
        students = new ArrayList<Student>();
        groups = new ArrayList<Group>();
    }

    void readGroups(){
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("B:\\dekanat_proj\\src\\groups.txt"), StandardCharsets.UTF_8))){
            String line;

            while ((line = reader.readLine()) != null) {
             //   System.out.println(line);
               Group a = new Group(line);
               a.d = this;
                groups.add(a);
            }
        } catch (IOException e) {
            // log error
        }
    }

    void readStudents(){
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("B:\\dekanat_proj\\src\\students.txt"), StandardCharsets.UTF_8))){
            String line;
            int id=1;

            while ((line = reader.readLine()) != null) {
                //   System.out.println(line);

                Student a = new Student(id,line);
                students.add(a);
                id++;
            }
        } catch (IOException e) {
            // log error
        }
    }

    void readBoth(){
            List<String> lines = new ArrayList<String>();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("B:\\dekanat_proj\\src\\studgroups.txt"), StandardCharsets.UTF_8))){
                String line;
                int id=1;

                while ((line = reader.readLine()) != null) {
                    //   System.out.println(line);

                    line+=" ";
                    lines.add(line);
                }
            } catch (IOException e) {
                // log error
            }
        int id=1;
        for (String line : lines){
                String abc = line;
                String[] s = abc.split(" ");

                String stud = s[0]+" "+ s[1];

                String group = s[2];
                boolean fl = this.findGroup(group);
                  if (fl==false)
                { Group g = new Group(group);
                  g.d = this;
                  groups.add(g);
                }

                 Student a = new Student(id, stud);
                students.add(a);
                String id_s = String.valueOf(id);
                this.addToGroup(group, id_s);
                id++;
                }
    }

    void updateInfo(){

            File file = new File("B:\\dekanat_proj\\src\\studgroups.txt");
            FileWriter fr = null;

            try {
                fr = new FileWriter(file);

                for(Student a : students) {

                    if (a.group!=null){
                    String fio = a.fio;
                    String group = a.group.title;
                    fr.write(fio+" "+group+" "+System.lineSeparator());
                    }else{
                        String fio = a.fio;
                        fr.write(fio+" "+ System.lineSeparator());
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    boolean findGroup(String abc){
        boolean m = false;
        Group temp  = new Group("");
        for (int i = 0; i < groups.size(); i++) {
            temp =groups.get(i);
            String fio2 = temp.title;

            if (fio2.equals(abc)) {

               m= true;
                break;

            }
        }
       return m;
    }

    void addToGroup(String abc, String whom ){

      //  whom+=" ";
        Student temp;
        int id1 = -1;
        String fio1 = "";
        int flag = 0;
        if ((whom.charAt(0) == '0') || (whom.charAt(0) == '1') || (whom.charAt(0) == '2') || (whom.charAt(0) == '3') || (whom.charAt(0) == '4') || (whom.charAt(0) == '5') || (whom.charAt(0) == '6') || (whom.charAt(0) == '7') || (whom.charAt(0) == '8') || (whom.charAt(0) == '9')) {
            flag = 1;
            id1 = Integer.parseInt(whom);
        } else {
            fio1 = whom;
        }

        if (flag==1) {
            String gname = "";
            Group temp1 = new Group("");
            for (int i = 0; i < groups.size(); i++) {
                temp1 = groups.get(i);
                gname = temp1.title;

                if (gname.equals(abc)) {
                    groups.get(i).addStudById(id1);
                    break;
                }
            }
        }else
        {
            String gname = "";
            Group temp1 = new Group("");
            for (int i = 0; i < groups.size(); i++) {
                temp1 = groups.get(i);
                gname = temp1.title;
                int flaq=-1;
                if (gname.equals(abc)) {
                    if ( groups.get(i).findStud(whom)==false){

                        for (Student a: students){

                            if (a.fio.equals(whom)){
                                groups.get(i).addStud(a);
                                flaq = 1;
                                break;
                            }

                        }

                        if(flaq<0) {
                            //если нет в списке
                            int new_id = students.size() + 1;
                            Student a = new Student(new_id, whom);
                            students.add(a);
                            groups.get(i).addStud(a);
                            this.updateInfo();
                            break;
                        }
                    }

                }
            }

        }


    }

    void getStudInfo(int id){
        if ((id<= students.size()&&(id>0))) {

            String gname = "";
            int id2 = 0;
            Student temp1 = new Student(0, "");
            for (int i = 0; i < students.size(); i++) {
                temp1 = students.get(i);
                id2 = temp1.id_stud;

                if (id2 == id) {
                    System.out.println(temp1.id_stud + "  " + temp1.fio + "  from group :" + temp1.group.title);
                    System.out.println("Marks are : " + temp1.marks);
                    break;
                }
            }
        }
        else
        {System.out.println("Student with the specified ID does not exist.");}
        System.out.println();
    }

    void showGroups(){
        Group temp;
        for (int i = 0; i < groups.size(); i++) {
            temp = groups.get(i);
            String fio2 = temp.title;
            System.out.println(fio2);

        }
        System.out.println();
    }

    Student dekRequestStud(int id){
        Student temp = new Student(0,"");
     //   int id1 = -1;
        String fio1 = "";
         for (int i = 0; i < students.size(); i++) {
            temp =students.get(i);
            int id2 = temp.id_stud;

            if (id2 == id) {
                temp = students.get(i);
                break;
            }
        }
        return temp;
    }

    void showStudents(){
        Student temp;
        int id;
        for (int i = 0; i < students.size(); i++) {
            temp = students.get(i);
            id = temp.id_stud;
            String fio2 = temp.fio;
            System.out.println(id + " " +fio2);

        }
        System.out.println();
    }

    void groupInfo(String abc){
        String gname = "";
        Group temp1 = new Group("");
        for (int i = 0; i < groups.size(); i++) {
            temp1 =groups.get(i);
            gname = temp1.title;

            if (gname.equals(abc)) {
                System.out.println("Group "+ groups.get(i).title+" :");
                groups.get(i).showGroup();
            }
        }
        System.out.println();
    }

    void addRandMarks(){
        for (int i = 0; i < students.size(); i++) {
            students.get(i).addRandomMarks();
        }

        for (int i = 0; i < groups.size(); i++) {
            groups.get(i).findHead();
        }
    }

    void getStudStat(){
        System.out.println("Average marks are:");
        for (int i = 0; i < students.size(); i++) {

            System.out.println(students.get(i).id_stud+" "+ students.get(i).fio+ " "+ students.get(i).midMark());
        }
        System.out.println();
    }

    void getGroupStat(){
        System.out.println("Average marks in groups are:");
        int k=0;
        Group p = new Group("");
        for (int i = 0; i < groups.size(); i++) {
            float midmark =0;
            p = groups.get(i);
            for (int j = 0; j < p.list.size(); j++) {
                k++;
                midmark += p.list.get(i).midMark();
            }
            midmark = midmark/k;
            System.out.println(groups.get(i).title+ " "+midmark);
        }
        System.out.println();
    }

    void expellStud(float steps){
        System.out.println("Students to expel: ");
        Group temp = new Group("");
        String abc="";
        for (int i = 0; i < students.size(); i++) {
            float z = students.get(i).midMark();
            if (z < steps ){
                temp = students.get(i).group;
                abc = students.get(i).fio;
                System.out.println(students.get(i).fio + " from group"+ temp.title);

                temp.expellStud(abc);
                students.remove(i);
                i--;
            }
          }
        this.updateInfo();
        System.out.println();
    }

    void expellByName(String _abc){
        System.out.println("A student to expel: ");
        Group temp = new Group("");

        Student temps = new Student(0,"");
        for (int i = 0; i < students.size(); i++) {
             temps = students.get(i);

            if (temps.fio.equals(_abc) ){
                temp = students.get(i).group;

                System.out.println(students.get(i).fio + " from group"+ temp.title);
                temp.expellStud(_abc);
                students.remove(i);
                i--;
            }
        }

        this.updateInfo();
        System.out.println();
    }

    void addMark(String abc, float n){

        Group temp = new Group("");
        Student temps = new Student(0,"");
        for (int i = 0; i < students.size(); i++) {
            temps = students.get(i);

            if (temps.fio.equals(abc) ){
                temp = students.get(i).group;
                temps.addMark(n);
                temp.findHead();
                break;
            }
        }
        System.out.println();
    }

    void showHeadInGroup(String abc){
        String gname = "";
        Group temp1 = new Group("");
        for (int i = 0; i < groups.size(); i++) {
            temp1 =groups.get(i);
            gname = temp1.title;

            if (gname.equals(abc)) {
               System.out.println(groups.get(i).head.fio);
                break;
            }
        }
    }

    void changeGroup(String whom, String where){
        int id=0;
        String abc="";
        for (int i = 0; i < students.size(); i++) {

           abc = students.get(i).fio;
            if (abc.equals(whom) ){
                id = students.get(i).id_stud;
                students.get(i).group.expellStud(abc);
                break;
            }
        }

        String gname = "";
        Group temp1 = new Group("");
        for (int i = 0; i < groups.size(); i++) {
            temp1 =groups.get(i);
            gname = temp1.title;

            if (gname.equals(where)) {
                String str = String.valueOf(id);
               this.addToGroup(where, str);
                break;
            }
        }
      this.updateInfo();
    }

}
