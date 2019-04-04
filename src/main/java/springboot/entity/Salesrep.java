package springboot.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "SALESREPS", schema = "MA_STUDENT")
public class Salesrep implements java.io.Serializable {

    private BigDecimal emplNum;
    private Office office;
    private String name;
    private java.math.BigDecimal age;
    private String title;
    private Date hireDate;
    private java.math.BigDecimal quota;
    private java.math.BigDecimal sales;


    public Salesrep() {
    }

    public Salesrep(BigDecimal emplNum, Date hireDate) {
        this.emplNum = emplNum;
        this.hireDate = hireDate;
    }

    public Salesrep(BigDecimal emplNum, Office office, String name, java.math.BigDecimal age,
                    String title, Date hireDate, java.math.BigDecimal quota, java.math.BigDecimal sales) {
        this.emplNum = emplNum;
        this.office = office;
        this.name = name;
        this.age = age;
        this.title = title;
        this.hireDate = hireDate;
        this.quota = quota;
        this.sales = sales;
    }

    @Id
    @Column(name = "EMPL_NUM", unique = true, nullable = false, precision = 22, scale = 0)
    public BigDecimal getEmplNum() {
        return this.emplNum;
    }

    public void setEmplNum(BigDecimal emplNum) {
        this.emplNum = emplNum;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REP_OFFICE")
    public Office getOffice() {
        return this.office;
    }

    public void setOffice(Office offices) {
        this.office = office;
    }

    @Column(name = "NAME", length = 30)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "AGE", precision = 22, scale = 0)
    public BigDecimal getAge() {
        return this.age;
    }

    public void setAge(BigDecimal age) {
        this.age = age;
    }

    @Column(name = "TITLE", length = 40)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "HIRE_DATE", nullable = false, length = 7)
    public Date getHireDate() {
        return this.hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @Column(name = "QUOTA", precision = 22, scale = 0)
    public BigDecimal getQuota() {
        return this.quota;
    }

    public void setQuota(BigDecimal quota) {
        this.quota = quota;
    }

    @Column(name = "SALES", precision = 22, scale = 0)
    public BigDecimal getSales() {
        return this.sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salesrep salesrep = (Salesrep) o;
        return Objects.equals(emplNum, salesrep.emplNum) &&
                Objects.equals(office, salesrep.office) &&
                Objects.equals(name, salesrep.name) &&
                Objects.equals(age, salesrep.age) &&
                Objects.equals(title, salesrep.title) &&
                Objects.equals(hireDate, salesrep.hireDate) &&
                Objects.equals(quota, salesrep.quota) &&
                Objects.equals(sales, salesrep.sales);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emplNum, office, name, age, title, hireDate, quota, sales);
    }

    @Override
    public String toString() {
        return "springboot.entity.Salesrep{" +
                "emplNum=" + emplNum +
                ", office=" + office +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", title='" + title + '\'' +
                ", hireDate=" + hireDate +
                ", quota=" + quota +
                ", sales=" + sales +
                '}';
    }
}