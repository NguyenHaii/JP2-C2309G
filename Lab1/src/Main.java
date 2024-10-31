import Entity.Department;
import Entity.Employee;
import Entity.Gender;
import Service.DepartmentService;

import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<Department> departments = new HashSet<>();
        Set<Employee> employees = new HashSet<>();

        Department dep1 = new Department(1, "IT");
        Department dep2 = new Department(2, "HR");

        Employee emp1 = new Employee(1, "Alice", dep1, LocalDateTime.of(1990, 1, 1, 0, 0), Gender.F, "CityA", "ProvinceA", "123456789");
        Employee emp2 = new Employee(2, "Bob", dep2, LocalDateTime.of(1991, 2, 2, 0, 0), Gender.M, "CityB", "ProvinceB", "987654321");
        Employee emp3 = new Employee(3, "Charlie", dep1, LocalDateTime.of(1992, 3, 3, 0, 0), Gender.O, "CityC", "ProvinceC", "555555555");

        departments.add(dep1);
        departments.add(dep2);

        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);

        DepartmentService manager = new DepartmentService(departments, employees);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter department name to display employees:");
        String searchName = scanner.next();
        manager.displayDepartmentsWithEmployees(searchName);

        System.out.println("\nGrouping employees by department:");
        Map<String, Set<Employee>> groupedEmployees = manager.groupEmployeesByDepartment();
        groupedEmployees.forEach((departmentName, employeeSet) -> {
            System.out.println("Department: " + departmentName);
            employeeSet.forEach(System.out::println);
        });

        System.out.println("\nCounting total employees per department:");
        Map<String, Long> employeeCount = manager.countEmployeesByDepartment();
        employeeCount.forEach((departmentName, count) -> {
            System.out.println("Department: " + departmentName + ", Employee Count: " + count);
        });

        System.out.println("\nCounting total employees per Gender ");
        Map<Gender , Long > countByGender = manager.countEmployeesByGender();
        countByGender.forEach((departmentName,count)-> {
            System.out.println("Gender Male " + "| Total :" + count);
        });

        System.out.println("\nCounting employees with DoB in the current month:");
        Map<Integer, Set<Employee>> employeesDoB = manager.countEmployeeByDoB();
        employeesDoB.forEach((month, empSet) -> {
            System.out.println("Month: " + month + " -> Employee Count: " + empSet.size());
            empSet.forEach(employee -> System.out.println("Deparment: " + employee.getDepartment().getName_Dep() + " | Employee: " + employee.getName()));
        });


    }
}
