package springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "CUSTOMERS", schema = "MA_STUDENT")
public class Customer implements java.io.Serializable {

    @Id
    @Column(name = "CUST_NUM", unique = true, nullable = false, precision = 22, scale = 0)
    private BigDecimal custNum;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUST_REP")
    private Salesrep salesrep;

    @Column(name = "COMPANY", length = 40)
    private String company;

    @Column(name = "CREDIT_LIMIT", precision = 22, scale = 0)
    private BigDecimal creditLimit;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<Order> orders = new HashSet<>();

    public Customer() {
    }

    public Customer(BigDecimal custNum) {
        this.custNum = custNum;
    }

    public Customer(BigDecimal custNum, Salesrep salesrep, String company, BigDecimal creditLimit,
                    Set<Order> orders) {
        this.custNum = custNum;
        this.salesrep = salesrep;
        this.company = company;
        this.creditLimit = creditLimit;
        this.orders = orders;
    }

    public BigDecimal getCustNum() {
        return this.custNum;
    }

    public void setCustNum(BigDecimal custNum) {
        this.custNum = custNum;
    }

    public Salesrep getSalesrep() {
        return this.salesrep;
    }

    public void setSalesrep(Salesrep salesrep) {
        this.salesrep = salesrep;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public BigDecimal getCreditLimit() {
        return this.creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Set<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(custNum, customer.custNum) &&
                Objects.equals(company, customer.company) &&
                Objects.equals(creditLimit, customer.creditLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(custNum, company, creditLimit);
    }

    @Override
    public String toString() {
        return "springboot.entity.Customer{" +
                "custNum=" + custNum +
                ", company='" + company + '\'' +
                ", creditLimit=" + creditLimit +
                '}';
    }
}