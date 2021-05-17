package com.criteriaandquerydsl.CriteriaApiAndQueryDsl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Transactional
@Service
public class EmployeeService {

    private final EntityManager em;

    public EmployeeService(EntityManager em) {
        this.em = em;
    }

    public Employee save(Employee employee) {
        em.persist(employee);
        return employee;
    }

    /*public List<Employee> getALlEmp(){
        Query query = em.createQuery("select e from Employee e");
        List<Employee> employees = query.getResultList();
        return employees;
    }
*/
    public Employee getEmp(Long id) {
        return em.find(Employee.class, id);
    }

    public List<Employee> getAllEmpByCriteriaApi() {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> employeeCriteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = employeeCriteriaQuery.from(Employee.class);
        employeeCriteriaQuery.select(employeeRoot);
        TypedQuery<Employee> query = em.createQuery(employeeCriteriaQuery);

        return query.getResultList();
    }

    public List<Employee> getEmpByName(String empName){

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> employeeRoot = cq.from(Employee.class);
        Predicate name = cb.equal(employeeRoot.get("empName"), empName);
        cq.where(name);
        TypedQuery<Employee> query = em.createQuery(cq);

        return query.getResultList();
    }
}
