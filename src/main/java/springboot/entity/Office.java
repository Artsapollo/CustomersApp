package springboot.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "OFFICES", schema = "MA_STUDENT")
public class Office implements java.io.Serializable {

    @Id
    @Column(name = "OFFICE", unique = true, nullable = false, precision = 22, scale = 0)
    private BigDecimal office;

    @Column(name = "CITY", nullable = false, length = 40)
    private String city;

    @Column(name = "REGION", nullable = false, length = 30)
    private String region;

    @Column(name = "TARGET", precision = 22, scale = 0)
    private BigDecimal target;

    @Column(name = "SALES", nullable = false, precision = 22, scale = 0)
    private BigDecimal sales;

    public Office() {
    }

    public Office(BigDecimal office, String city, String region, BigDecimal sales) {
        this.office = office;
        this.city = city;
        this.region = region;
        this.sales = sales;
    }

    public Office(BigDecimal office, String city, String region, BigDecimal target,
                  BigDecimal sales) {
        this.office = office;
        this.city = city;
        this.region = region;
        this.target = target;
        this.sales = sales;
    }


    public BigDecimal getOffice() {
        return office;
    }

    public void setOffice(BigDecimal office) {
        this.office = office;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public BigDecimal getTarget() {
        return this.target;
    }

    public void setTarget(BigDecimal target) {
        this.target = target;
    }

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
        Office office1 = (Office) o;
        return Objects.equals(office, office1.office) &&
                Objects.equals(city, office1.city) &&
                Objects.equals(region, office1.region) &&
                Objects.equals(target, office1.target) &&
                Objects.equals(sales, office1.sales);
    }

    @Override
    public int hashCode() {
        return Objects.hash(office, city, region, target, sales);
    }

    @Override
    public String toString() {
        return "springboot.entity.Office{" +
                "office=" + office +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", target=" + target +
                ", sales=" + sales +
                '}';
    }
}