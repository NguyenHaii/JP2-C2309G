package Service;

import Entity.Department;
import Entity.Employee;
import IGeneric.IGeneral;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DepartmentService implements IGeneral<Department> {
    private final Set<Department> departments;
    private final Set<Employee> employees;

    public DepartmentService(Set<Department> departments, Set<Employee> employees) {
        this.departments = departments;
        this.employees = employees;
    }

    public void displayDepartmentsWithEmployees(String departmentName) {
        System.out.println("Employees in Department: " + departmentName);
        employees.stream()
                .filter(employee -> employee.getDepartment().getName_Dep().equalsIgnoreCase(departmentName))
                .forEach(System.out::println);
    }


    public Map<String, Set<Employee>> groupEmployeesByDepartment() {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        employee -> employee.getDepartment().getName_Dep(),
                        Collectors.toSet()
                ));
    }

    public Map<String, Long> countEmployeesByDepartment() {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        employee -> employee.getDepartment().getName_Dep(),
                        Collectors.counting()
                ));
    }
    @Override
    public Set<Department> getByName(String name) {
        return Set.of();
    }
}
