package Service;

import Entity.Department;
import Entity.Employee;
import Entity.Gender;
import IGeneric.IGeneral;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
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
        Map<String, Set<Employee>> groupedEmployees = new HashMap<>();
        departments.forEach(department -> {
            Set<Employee> employeesInDept = employees.stream()
                    .filter(emp -> department.getName_Dep() == emp.getDepartment().getName_Dep())
                    .collect(Collectors.toSet());
            groupedEmployees.put(department.getName_Dep(), employeesInDept);
        });

        return groupedEmployees;
    }

    public Map<String, Long> countEmployeesByDepartment() {
        Map<String, Long> employeeCountByDepartment = new HashMap<>();
        departments.forEach(department -> {
            long count = employees.stream()
                    .filter(emp -> department.getName_Dep() == emp.getDepartment().getName_Dep())
                    .count();
            employeeCountByDepartment.put(department.getName_Dep(), count);
        });
        return employeeCountByDepartment;
    }

    public Map<Gender, Long> countEmployeesByGender() {
        Map<Gender, Long> employeeCountByGender = new HashMap<>();
            long count = employees.stream()
                    .filter(emp -> emp.getGender()==Gender.M)
                    .count();
            employeeCountByGender.put(Gender.M, count);
        return employeeCountByGender;
    }

    public Map<Integer, Set<Employee>> countEmployeeByDoB() {
        Map<Integer, Set<Employee>> employeeDoB = new HashMap<>();
        int currentMonth = LocalDate.now().getMonthValue();
        employeeDoB.put(currentMonth, new HashSet<>());
        employees.forEach(emp -> {
                employeeDoB.get(currentMonth).add(emp);
        });
        return employeeDoB;
    }


    @Override
    public Set<Department> getByName(String name) {
        return Set.of();
    }
}
