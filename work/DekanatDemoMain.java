//Java-lab-3
//Efremov Pavel
//main




public class DekanatDemoMain {

    public static void main(String args[]){

        String fullpath = "D:\\java_work\\javalab3\\src\\main\\resources\\students.txt" ;
        String pathTitle ="D:\\java_work\\javalab3\\src\\main\\resources\\GroupsTitle.txt";

        Dekanat dekanat = new Dekanat();

        dekanat.FileReading(fullpath);                  //прочел файл Groups
        dekanat.ReadFileToCreateTitle(pathTitle);       //прочел файл GroupsTitle
        dekanat.createStudents();                       //создал студентов
        dekanat.createGroups();                         //создал группы
        dekanat.addAllStudentsToGroup();                //распределение всех студентов по группам
        dekanat.addMarksStudents(2,5);        //добавили смтуденту с индексом 2 оценку 5
        dekanat.transferStudent(2,"Люди");      //перевод студента с id 2 в группу Люди
        dekanat.deleteStudent(17);                  //удаляем студента с индексом 17
        dekanat.deleteBecauseLowMark(3);        //удаляем студенвтов со средней оценкой 2
        dekanat.statistics();                       //создание статистики студентов
        dekanat.saveToNewFile();        //создаем новый файл студентов с указанием id, ФИО и группы
        //System.out.println("Test");
    }
}


