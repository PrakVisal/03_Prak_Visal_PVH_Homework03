import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class BussinessRules {
    String optionString = null;
    String option = null;
    int id = 3;
    static int rowSet = 0;
    Scanner sc = new Scanner(System.in);
    public void Insert(List<StaffMember> staffMembers) {
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        Table t = new Table(4, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);
        t.setColumnWidth(0,30,30);
        t.setColumnWidth(1,30,30);
        t.setColumnWidth(2,30,30);
        t.setColumnWidth(3,10,10);
        t.addCell("1. Volunteer",cellStyle);
        t.addCell("2. Salaried Employee",cellStyle);
        t.addCell("3. Hourly Employee",cellStyle);
        t.addCell("4. Back",cellStyle);
        do {
            System.out.println("=== Insert ===");
            System.out.println("Choose Type :");
            System.out.println(t.render());
            System.out.print("Enter Type : ");
            option = sc.nextLine();
            switch (option){
                case "1":{
                    InputData(staffMembers,"Volunteer");
                    break;
                }
                case "2":{
                    InputData(staffMembers,"Salaried Employee");
                    break;
                }
                case "3":{
                    InputData(staffMembers,"Hourly Employee");
                    break;
                }
                case "4":{
                    System.out.println("Exit Program...");
                    break;
                }
                default:{
                    System.out.println("Invalid Option");
                    break;
                }
            }
        }while (!option.equals("4") );



    }
    public void Update(List <StaffMember> staffMembers) {
        String selectColumn = null;
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        Table t = new Table(9, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);

        t.setColumnWidth(0, 30, 30);
        t.setColumnWidth(1, 5, 5);
        t.setColumnWidth(2, 20, 20);
        t.setColumnWidth(3, 10, 10);
        t.setColumnWidth(4, 20, 20);
        t.setColumnWidth(5, 15, 15);

        t.addCell("TYPE"  ,  cellStyle);
        t.addCell( "ID"  , cellStyle);
        t.addCell( "NAME"  , cellStyle);
        t.addCell( "ADDRESS" , cellStyle);
        t.addCell( "SALARY"  , cellStyle);
        t.addCell("BONUS"  , cellStyle);
        t.addCell( "HOUR"  , cellStyle);
        t.addCell( "RATE"  , cellStyle);
        t.addCell( "PAY"  , cellStyle);
        try {
            System.out.println("=== Update ===");
            System.out.println("Enter ID to update : ");
            String searchId = sc.nextLine();
            int inputId = Integer.parseInt(searchId);
            List<StaffMember> findType = staffMembers.stream().filter(staffMember -> staffMember.id == inputId).toList();
            boolean found = staffMembers.stream().anyMatch(staffMember -> staffMember.id == inputId);
            if(!found){
                System.out.println("Invalid ID");
                return;
            }
            findType.forEach(staffMember -> {
                if(staffMember instanceof Volunteer volunteer){
                    t.addCell("Volunteer",cellStyle);
                    t.addCell(String.valueOf(volunteer.id), cellStyle);
                    t.addCell(String.valueOf(volunteer.name),cellStyle);
                    t.addCell(String.valueOf(volunteer.address), cellStyle);
                    t.addCell(String.valueOf(volunteer.getSalary()),cellStyle);
                    t.addCell("---",cellStyle);
                    t.addCell("---",cellStyle);
                    t.addCell("---",cellStyle);
                    t.addCell(String.valueOf(volunteer.pay()),cellStyle);
                } else if (staffMember instanceof SalariedEmployee salariedEmployee) {
                    t.addCell("Salaried Employee",cellStyle);
                    t.addCell(String.valueOf(salariedEmployee.id), cellStyle);
                    t.addCell(String.valueOf(salariedEmployee.name),cellStyle);
                    t.addCell(String.valueOf(salariedEmployee.address),cellStyle);
                    t.addCell(String.valueOf(salariedEmployee.getSalary()),cellStyle);
                    t.addCell(String.valueOf(salariedEmployee.getBonus()),cellStyle);
                    t.addCell("---",cellStyle);
                    t.addCell("---",cellStyle);
                    t.addCell(String.valueOf(salariedEmployee.pay()),cellStyle);
                }else if (staffMember instanceof HourlySalaryEmployee hourlyEmployee) {
                    t.addCell("Hourly Employee",cellStyle);
                    t.addCell(String.valueOf(hourlyEmployee.id), cellStyle);
                    t.addCell(String.valueOf(hourlyEmployee.name),cellStyle);
                    t.addCell(String.valueOf(hourlyEmployee.address),cellStyle);
                    t.addCell("---",cellStyle);
                    t.addCell("---",cellStyle);
                    t.addCell(String.valueOf(hourlyEmployee.getHourWorked()),cellStyle);
                    t.addCell(String.valueOf(hourlyEmployee.getRate()),cellStyle);
                    t.addCell(String.valueOf(hourlyEmployee.pay()),cellStyle);
                }
            });
            System.out.println(t.render());
            do {
                System.out.println("Choose one column to update");
                findType = staffMembers.stream().filter(staffMember -> staffMember.id == inputId).toList();
                findType.forEach(staffMember -> {
                    if(staffMember instanceof HourlySalaryEmployee) {
                        System.out.println("1. Name\t 2. Address\t 3. Hour\t 4. Rate\t 0. Cancel");
                    }else if(staffMember instanceof SalariedEmployee) {
                        System.out.println("1. Name\t 2. Address\t 3. Salary\t 4.Bonus\t 0. Cancel");
                    }else {
                        System.out.println("1. Name\t 2. Address\t 3. Salary\t 0. Cancel");
                    }
                });
                System.out.println("=> Select column to update : ");
                selectColumn = sc.nextLine();
                switch (selectColumn){
                    case "1":{
                        System.out.println("New Name : ");
                        String newName = sc.nextLine();
                        findType.forEach(staffName ->{
                            if(staffName instanceof Volunteer volunteer){
                                volunteer.name = newName;
                            }else if(staffName instanceof SalariedEmployee salariedEmployee){
                                salariedEmployee.name = newName;
                            }else if(staffName instanceof HourlySalaryEmployee hourlyEmployee){
                                hourlyEmployee.name = newName;
                            }
                        });
                        Display(staffMembers.stream().filter(staffMember -> staffMember.id == inputId).toList());
                        break;
                    }
                    case "2":{
                        System.out.println("New Address : ");
                        String newAddress = sc.nextLine();
                        List<StaffMember> findData = staffMembers.stream().filter(staffMember -> staffMember.id == inputId).toList();
                        findData.forEach(staffName ->{
                            if(staffName instanceof Volunteer volunteer){
                                volunteer.address = newAddress;
                            }else if(staffName instanceof SalariedEmployee salariedEmployee){
                                salariedEmployee.address = newAddress;
                            }else if(staffName instanceof HourlySalaryEmployee hourlyEmployee){
                                hourlyEmployee.address = newAddress;
                            }
                        });
                        Display(staffMembers.stream().filter(staffMember -> staffMember.id == inputId).toList());
                        break;
                    }
                    case "3":{
                        findType.forEach(staffMember -> {
                            System.out.println((staffMember instanceof HourlySalaryEmployee)?"New Hour : ":"New Salary : ");
                        });
                        String data = sc.nextLine();
                        double newSalary = Double.parseDouble(data);
                        int newHourWorked = Integer.parseInt(data);
                        List<StaffMember> findData = staffMembers.stream().filter(staffMember -> staffMember.id == inputId).toList();
                        findData.forEach(staffName ->{
                            if(staffName instanceof Volunteer volunteer){
                                volunteer.setSalary(newSalary);
                            }else if(staffName instanceof SalariedEmployee salariedEmployee){
                                salariedEmployee.setSalary(newSalary);
                            }else if(staffName instanceof HourlySalaryEmployee hourlyEmployee){
                                hourlyEmployee.setHourWorked(newHourWorked);
                            }
                        });
                        Display(staffMembers.stream().filter(staffMember -> staffMember.id == inputId).toList());
                        break;
                    }
                    case "4":{
                        findType.forEach(staffMember -> {
                            if(staffMember instanceof HourlySalaryEmployee hourlyEmployee){
                                System.out.println("New Rate : ");
                                String data = sc.nextLine();
                                double newRate = Double.parseDouble(data);
                                if(newRate<1){
                                    System.out.println("New Rate can't be negative");
                                    return;
                                }
                                hourlyEmployee.setRate(newRate);
                            }else if(staffMember instanceof SalariedEmployee salariedEmployee){
                                System.out.println("New Bonus : ");
                                String data = sc.nextLine();
                                double newBonus = Double.parseDouble(data);
                                salariedEmployee.setBonus(newBonus);
                            }else {
                                System.out.println("Invalid Selection!");
                            }
                        });
                        Display(staffMembers.stream().filter(staffMember -> staffMember.id == inputId).toList());
                        break;
                    }
                    case "0":{
                        System.out.println("Exit Program...");
                        break;
                    }
                    default:{
                        System.out.println("Invalid selection");
                        break;
                    }
                }
            }while (!selectColumn.equals("0"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Display(List<StaffMember> staffMember) {
        final int PAGE_SIZE = 3;  // Number of records per page
        Scanner sc = new Scanner(System.in);
        int totalPages = (int) Math.ceil((double) staffMember.size() / PAGE_SIZE);
        int currentPage = 1;

        while (true) {
            // Create table
            CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
            Table t = new Table(9, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);

            // Set column widths
            t.setColumnWidth(0, 30, 30);
            t.setColumnWidth(1, 5, 5);
            t.setColumnWidth(2, 20, 20);
            t.setColumnWidth(3, 10, 10);
            t.setColumnWidth(4, 20, 20);
            t.setColumnWidth(5, 15, 15);
            t.setColumnWidth(6, 10, 10);
            t.setColumnWidth(7, 10, 10);
            t.setColumnWidth(8, 20, 20);

            // Add header
            t.addCell("TYPE", cellStyle);
            t.addCell("ID", cellStyle);
            t.addCell("NAME", cellStyle);
            t.addCell("ADDRESS", cellStyle);
            t.addCell("SALARY", cellStyle);
            t.addCell("BONUS", cellStyle);
            t.addCell("HOUR", cellStyle);
            t.addCell("RATE", cellStyle);
            t.addCell("PAY", cellStyle);

            // Get sublist for the current page
            int startIndex = (currentPage - 1) * PAGE_SIZE;
            int endIndex = Math.min(startIndex + PAGE_SIZE, staffMember.size());
            List<StaffMember> pageData = staffMember.subList(startIndex, endIndex);

            // Populate table with page data
            pageData.forEach(staffData -> {
                if (staffData instanceof SalariedEmployee salariedEmployee) {
                    t.addCell("Salaried Employee", cellStyle);
                    t.addCell(String.valueOf(salariedEmployee.id), cellStyle);
                    t.addCell(String.valueOf(salariedEmployee.name), cellStyle);
                    t.addCell(String.valueOf(salariedEmployee.address), cellStyle);
                    t.addCell(String.valueOf(salariedEmployee.getSalary()), cellStyle);
                    t.addCell(String.valueOf(salariedEmployee.getBonus()), cellStyle);
                    t.addCell("---", cellStyle);
                    t.addCell("---", cellStyle);
                    t.addCell(String.valueOf(salariedEmployee.pay()), cellStyle);
                } else if (staffData instanceof Volunteer volunteer) {
                    t.addCell("Volunteer", cellStyle);
                    t.addCell(String.valueOf(volunteer.id), cellStyle);
                    t.addCell(String.valueOf(volunteer.name), cellStyle);
                    t.addCell(String.valueOf(volunteer.address), cellStyle);
                    t.addCell(String.valueOf(volunteer.getSalary()), cellStyle);
                    t.addCell("---", cellStyle);
                    t.addCell("---", cellStyle);
                    t.addCell("---", cellStyle);
                    t.addCell(String.valueOf(volunteer.pay()), cellStyle);
                } else if (staffData instanceof HourlySalaryEmployee hourlyEmployee) {
                    t.addCell("Hourly Employee", cellStyle);
                    t.addCell(String.valueOf(hourlyEmployee.id), cellStyle);
                    t.addCell(String.valueOf(hourlyEmployee.name), cellStyle);
                    t.addCell(String.valueOf(hourlyEmployee.address), cellStyle);
                    t.addCell("---", cellStyle);
                    t.addCell("---", cellStyle);
                    t.addCell(String.valueOf(hourlyEmployee.getHourWorked()), cellStyle);
                    t.addCell(String.valueOf(hourlyEmployee.getRate()), cellStyle);
                    t.addCell(String.valueOf(hourlyEmployee.pay()), cellStyle);
                }
            });

            // Render table
            System.out.println(t.render());

            // Pagination info
            System.out.println("Page " + currentPage + " of " + totalPages);
            System.out.println("[N]ext | [P]revious | [E]xit");
            System.out.print("Choose an option: ");

            // Handle user input for navigation
            String input = sc.nextLine().trim().toLowerCase();
            if ("n".equals(input) && currentPage < totalPages) {
                currentPage++;
            } else if ("p".equals(input) && currentPage > 1) {
                currentPage--;
            } else if ("e".equals(input)) {
                break;
            }
        }
    }
//    public void Display(List<StaffMember> staffMember){
//        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
//        Table t = new Table(9, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);
//
//        t.setColumnWidth(0, 30, 30);
//        t.setColumnWidth(1, 5, 5);
//        t.setColumnWidth(2, 20, 20);
//        t.setColumnWidth(3, 10, 10);
//        t.setColumnWidth(4, 20, 20);
//        t.setColumnWidth(5, 15, 15);
//        t.setColumnWidth(6, 10, 10);
//        t.setColumnWidth(7, 10, 10);
//        t.setColumnWidth(8, 20, 20);
//
//        t.addCell("TYPE"  ,  cellStyle);
//        t.addCell( "ID"  , cellStyle);
//        t.addCell( "NAME"  , cellStyle);
//        t.addCell( "ADDRESS" , cellStyle);
//        t.addCell( "SALARY"  , cellStyle);
//        t.addCell( "BONUS"  , cellStyle);
//        t.addCell( "HOUR"  , cellStyle);
//        t.addCell( "RATE" , cellStyle);
//        t.addCell( "PAY"  , cellStyle);
//
//        staffMember.forEach(staffData-> {
//            if(staffData instanceof SalariedEmployee salariedEmployee){
//                t.addCell("Salaried Employee",cellStyle);
//                t.addCell(String.valueOf(salariedEmployee.id), cellStyle);
//                t.addCell(String.valueOf(salariedEmployee.name),cellStyle);
//                t.addCell(String.valueOf(salariedEmployee.address), cellStyle);
//                t.addCell(String.valueOf(salariedEmployee.getSalary()),cellStyle);
//                t.addCell(String.valueOf(salariedEmployee.getBonus()), cellStyle);
//                t.addCell("---",cellStyle);
//                t.addCell("---", cellStyle);
//                t.addCell(String.valueOf(salariedEmployee.pay()),cellStyle);
//            }else if(staffData instanceof Volunteer volunteer){
//                t.addCell("Volunteer",cellStyle);
//                t.addCell(String.valueOf(volunteer.id), cellStyle);
//                t.addCell(String.valueOf(volunteer.name),cellStyle);
//                t.addCell(String.valueOf(volunteer.address), cellStyle);
//                t.addCell(String.valueOf(volunteer.getSalary()),cellStyle);
//                t.addCell("---", cellStyle);
//                t.addCell("---",cellStyle);
//                t.addCell("---", cellStyle);
//                t.addCell(String.valueOf(volunteer.pay()),cellStyle);
//            }else if(staffData instanceof HourlySalaryEmployee hourlyEmployee){
//                t.addCell("Hourly Employee",cellStyle);
//                t.addCell(String.valueOf(hourlyEmployee.id), cellStyle);
//                t.addCell(String.valueOf(hourlyEmployee.name),cellStyle);
//                t.addCell(String.valueOf(hourlyEmployee.address), cellStyle);
//                t.addCell("---",cellStyle);
//                t.addCell("---", cellStyle);
//                t.addCell(String.valueOf(hourlyEmployee.getHourWorked()),cellStyle);
//                t.addCell(String.valueOf(hourlyEmployee.getRate()), cellStyle);
//                t.addCell(String.valueOf(hourlyEmployee.pay()),cellStyle);
//            }
//        });
//        System.out.println(t.render());
//        System.out.print("Press Enter to continue...");
//        sc.nextLine();
//    }
    public void Remove(List<StaffMember> staffMember){
        System.out.println("Enter ID to remove : ");
        String idString = sc.nextLine();
        try{
            int id = Integer.parseInt(idString);
            boolean removed = staffMember.removeIf(item -> item.id == id);
            if(removed){
                System.out.println("ID "+id+" removed");
            }else {
                System.out.println("ID is not valid");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }
    public void InputData(List <StaffMember> staffMembers, String type){
        try {
            System.out.println("ID: "+(++id));
            System.out.println("Enter Name : ");
            String name = sc.nextLine();
            System.out.println("Enter Address : ");
            String address = sc.nextLine();
            StaffMember data = null;

            switch (type) {
                case "Volunteer" ->{
                    System.out.println("Enter Salary : ");
                    String salaryString = sc.nextLine();
                    double salary = Double.parseDouble(salaryString);
                    data = new Volunteer(id, name, address, salary);
                }
                case "Salaried Employee" -> {
                    System.out.println("Enter Salary : ");
                    String salaryString = sc.nextLine();
                    double salary = Double.parseDouble(salaryString);
                    System.out.println("Enter Bonus : ");
                    String bonusString = sc.nextLine();
                    double bonus = Double.parseDouble(bonusString);
                    data = new SalariedEmployee(id, name, address, salary, bonus);
                }
                case "Hourly Employee" -> {
                    System.out.println("Enter HourWorked : ");
                    String hourString = sc.nextLine();
                    System.out.println("Enter Rate : ");
                    String rateString = sc.nextLine();
                    int hourWorked = Integer.parseInt(hourString);
                    double rate = Double.parseDouble(rateString);
                    if(rate<1 || hourWorked<1){
                        System.out.println("Hour Worked and Rate cannot be less than 1 or negative");
                        return;
                    }
                    data = new HourlySalaryEmployee(id, name, address, hourWorked, rate);
                }
            }
            staffMembers.add(data);
            System.out.println("*You added "+name+" of type "+type+" Successfully*");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
