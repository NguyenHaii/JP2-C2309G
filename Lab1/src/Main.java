import Entity.Department;
import Entity.Employee;
import Entity.Gender;
import Service.DepartmentService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Department dep1 = new Department(1, "IT");
        Department dep2 = new Department(2, "HR");

        Employee emp1 = new Employee(1, "Alice", dep1, LocalDateTime.of(1990, 1, 1, 0, 0), Gender.F, "CityA", "ProvinceA", "123456789");
        Employee emp2 = new Employee(2, "Bob", dep2, LocalDateTime.of(1991, 2, 2, 0, 0), Gender.M, "CityB", "ProvinceB", "987654321");
        Employee emp3 = new Employee(3, "Charlie", dep1, LocalDateTime.of(1992, 3, 3, 0, 0), Gender.O, "CityC", "ProvinceC", "555555555");

        Set<Department> departments = new HashSet<>();
        departments.add(dep1);
        departments.add(dep2);

        Set<Employee> employees = new HashSet<>();
        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);

        DepartmentService manager = new DepartmentService(departments, employees);
        Scanner scanner = new Scanner(System.in);
        String searchName = scanner.next();
        manager.displayDepartmentsWithEmployees(searchName);
    }
}
