public class Program {
    public static void main(String args[]){
        Dekanat d = new Dekanat();

   /*   d.readGroups();
        d.showGroups();
        d.readStudents();
        d.showStudents();

        d.addToGroup("1", "Kirillov Ruslan");
        d.addToGroup("1", "Shestkov Oleg");
        d.addToGroup("1", "Knyazev Vitaliy");
        d.addToGroup("1", "Alexandrov Sergey");*/
      //  d.getStudInfo(20);
      //  d.getGroupStat();
    //  d.expellStud(3.5f);
   //   d.expellByName("Kirillov Ruslan");
        d.readBoth();
        d.addRandMarks();
        d.getStudStat();
     // d.groupInfo("1");
      d.groupInfo("2");
     // d.groupInfo("3");

        d.expellByName("Panova Masha");
     // d.groupInfo("3");
     // d.groupInfo("2");
     // d.showHeadInGroup("1");
     // d.changeGroup("Knyazev Vitaliy", "3");
      //  d.groupInfo("1");
      //  d.groupInfo("2");
       d.addToGroup("2", "Semyonova Olya");

        //    d.showStudents();
    //    d.groupInfo("1");
      //  d.addToGroup("2", "Panova Masha");
        d.groupInfo("2");

    }
}
