import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import org.w3c.dom.ls.LSOutput;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.left);
        Table t = new Table(1, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.SURROUND_HEADER_AND_COLUMNS);
        Scanner sc = new Scanner(System.in);
        String option = null;
        BussinessRules bussinessRules = new BussinessRules();

       List <StaffMember> staffMemberList = new ArrayList<>();
       staffMemberList.add(new Volunteer(1,"Ching","BMC",150));
       staffMemberList.add(new SalariedEmployee(2,"Kanika","LA",550,20));
       staffMemberList.add(new HourlySalaryEmployee(3,"Nann","PP",8,2));
        t.setColumnWidth(0,23, 23);
        t.addCell("STAFF MANAGEMENT SYSTEM", cellStyle);
        t.addCell("1. Insert Employee", cellStyle);
        t.addCell("2. Update Employee", cellStyle);
        t.addCell("3. Display Employee", cellStyle);
        t.addCell("4. Remove Employee", cellStyle);
        t.addCell("5. Exit", cellStyle);

       do {
           System.out.println(t.render());
           System.out.println("Enter your option : ");
           option = sc.nextLine();
           switch (option) {
               case "1":{
                   bussinessRules.Insert(staffMemberList);
                   break;
               }
               case "2":{
                   bussinessRules.Update(staffMemberList);
                   break;
               }
               case "3":{
                   bussinessRules.Display(staffMemberList);
                   break;
               }
               case "4":{
                   bussinessRules.Remove(staffMemberList);
                   break;
               }
               case "5":{
                   System.out.println("Have a nice day :)");
                   break;
               }
               default:{
                   System.out.println("Invalid option");
                   break;
               }
           }
       }while (!option.equals("5"));

    }
}