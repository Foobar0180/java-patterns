package com.patterns.gamma.creational;

class Employee {
    public String streetAddress, postcode, city;
    public String companyName, position;
    public int annualIncome;

    @Override
    public String toString() {
        return "com.patterns.gamma.creational.Employee{" +
                "streetAddress='" + streetAddress + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", annualIncome=" + annualIncome +
                '}';
    }
}

class EmployeeBuilder {
    protected Employee employee = new Employee();

    public EmployeeAddressBuilder lives() {
        return new EmployeeAddressBuilder(employee);
    }

    public EmployeeJobBuilder works() {
        return new EmployeeJobBuilder(employee);
    }

    public Employee build() {
        return employee;
    }
}

class EmployeeAddressBuilder extends EmployeeBuilder {
    public EmployeeAddressBuilder(Employee employee) {
        this.employee = employee;
    }

    public EmployeeAddressBuilder at(String streetAddress) {
        employee.streetAddress = streetAddress;
        return this;
    }

    public EmployeeAddressBuilder withPostcode(String postcode) {
        employee.postcode = postcode;
        return this;
    }

    public EmployeeAddressBuilder in(String city) {
        employee.city = city;
        return this;
    }
}

class EmployeeJobBuilder extends EmployeeBuilder {
    public EmployeeJobBuilder(Employee employee) {
        this.employee = employee;
    }

    public EmployeeJobBuilder at(String companyName) {
        employee.companyName = companyName;
        return this;
    }

    public EmployeeJobBuilder asA(String position) {
        employee.position = position;
        return this;
    }

    public EmployeeJobBuilder earning(int annualIncome) {
        employee.annualIncome = annualIncome;
        return this;
    }
}

class FacetedBuilderDemo {
    public static void main(String[] args) {
        EmployeeBuilder builder = new EmployeeBuilder();
        Employee e = builder
                .lives()
                    .at("110 Bishopsgate")
                    .withPostcode("EC2N 4AY")
                    .in("London")
                .works()
                    .at("Salesforce")
                    .asA("Software Architect")
                    .earning(123000)
                .build();
        System.out.println(e);
    }
}
