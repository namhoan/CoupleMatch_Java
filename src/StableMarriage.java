/**
 Namho An
 3/25/2015
 PA05
 */
import java.io.*;
import java.util.*;

   public class StableMarriage {
      public static final String LIST_END = "END";
//The main method that accept the input file and includes the methods for sorting and output
      public static void main(String[] args) throws FileNotFoundException {
         Scanner scan = new Scanner(System.in);
         System.out.print("What is the input file? ");
         String fileName = scan.nextLine();
         Scanner input = new Scanner(new File(fileName));
         System.out.println();

         List<Person> men = scanHalf(input);
         List<Person> women = scanHalf(input);

         makeMatches(men, women);
         resultList(men, women, "Matches for men");
         resultList(women, men, "Matches for women");
    }//end of method
//method that read each person 
    public static Person scanPerson(String line) {
        int index = line.indexOf(":");
        Person result = new Person(line.substring(0, index));
        Scanner scan = new Scanner(line.substring(index + 1));
        while (scan.hasNextInt()) {
            result.addChoice(scan.nextInt());
        }
        return result;
    }//end of method
//method that add the the List of people in the ArrayList
    public static List<Person> scanHalf(Scanner input) {
        ArrayList<Person> result = new ArrayList<Person>();
        String line = input.nextLine();
        while (!line.equals(LIST_END)) {
            result.add(scanPerson(line));
            line = input.nextLine();
        }
        return result;
    }//end of method
//Method that matches the list of man and women
    public static void makeMatches(List<Person> list1, List<Person> list2) {
       for (Person eachMan :  list1){
            eachMan.setPartner(-1);
        }
        for (Person eachWoman : list2){
            eachWoman.setPartner(-1);
        }
        for (Person man : list1){
           for (Person woman : list2){
           while (man.hasChoices() && !man.hasPartner()){
              int takenWoman = man.getFirstChoice(); 
              for (Person p : list1){
                 if (p.getPartner()== takenWoman){
                    p.setPartner(-1);
                 }
              }
              man.setPartner(takenWoman);
              list2.get(takenWoman).setPartner(list1.indexOf(man));

              List<Integer> manList= man.getChoices();
              List<Integer> womanList = woman.getChoices();
              for (int q = womanList.size()-1; q >= 0; q--){
                 if(q > womanList.indexOf(list1.indexOf(man)))
                    manList.remove(manList.indexOf(man.getFirstChoice()));
                    womanList.remove(q);
              }//end of for loop that is inside of while loop  
           }//end of while 
           }//end of for loop inside
        }//end of for loop outside
    }//end of method
//Method that prints out the result of matching men and women
    public static void resultList(List<Person> list1, List<Person> list2, String title) {
        System.out.println(title);
        System.out.println("Name/t/t   Choice  Partner");
        System.out.println("-----------------------------------------");
        int sum = 0;
        int count = 0;
        for (Person p : list1) {
            System.out.printf("%-15s", p.getName());
            if (!p.hasPartner()) {
                System.out.println("  --    nobody");
            } else {
                int rank = p.getPartnerRank();
                sum += rank;
                count++;
                System.out.printf("%4d    %s\n", rank,
                                  list2.get(p.getPartner()).getName());
            }
        }
        System.out.println("Mean choice = " + (double) sum / count);
        System.out.println();
    }//end of method
}//end of class