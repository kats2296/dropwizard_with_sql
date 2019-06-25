package com.kuliza.dropwizard_with_sql.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.kuliza.dropwizard_with_sql.domain.Employee;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class EmployeeMapper implements ResultSetMapper<Employee> {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DEPARTMENT = "department";
    private static final String SALARY = "salary";

    public Employee map(int i, ResultSet resultSet, StatementContext statementContext)
            throws SQLException {
        Employee employee = new Employee(resultSet.getString(NAME), resultSet.getString(DEPARTMENT),resultSet.getInt(SALARY));
        employee.setId(resultSet.getInt(ID));
        return employee;
    }
}