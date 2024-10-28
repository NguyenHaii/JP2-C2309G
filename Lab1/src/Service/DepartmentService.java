package Service;

import Entity.Department;
import Entity.Employee;
import IGeneric.IGeneral;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DepartmentService implements IGeneral<Department> {
    private Set<Department> departments;
    private Set<Employee> employees;

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


    @Override
    public Set<Department> getByName(String name) {
        return departments.stream()
                .filter(department -> department.getName_Dep().equalsIgnoreCase(name))
                .collect(Collectors.toSet());
    }
}
