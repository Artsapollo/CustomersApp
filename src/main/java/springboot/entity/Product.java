package springboot.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PRODUCTS", schema = "MA_STUDENT")
public class Product implements java.io.Serializable {
    private String productId;
    private String mfrId;
    private String description;
    private BigDecimal price;
    private BigDecimal qtyOnHand;
    private Set<Order> orders = new HashSet<>();

    public Product() {
    }

    public Product(String productId) {
        this.productId = productId;
    }

    public Product(String productId, String mfrId, String description, BigDecimal price, BigDecimal qtyOnHand) {
        this.productId = productId;
        this.mfrId = mfrId;
        this.description = description;
        this.price = price;
        this.qtyOnHand = qtyOnHand;
    }

    @Id
    @Column(name = "PRODUCT_ID", unique = true, nullable = false, length = 5)
    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Column(name = "MFR_ID", length = 3)
    public String getMfrId() {
        return this.mfrId;
    }

    public void setMfrId(String mfrId) {
        this.mfrId = mfrId;
    }

    @Column(name = "DESCRIPTION", length = 50)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "PRICE", precision = 22, scale = 0)
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "QTY_ON_HAND", precision = 22, scale = 0)
    public BigDecimal getQtyOnHand() {
        return this.qtyOnHand;
    }

    public void setQtyOnHand(BigDecimal qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId) &&
                Objects.equals(mfrId, product.mfrId) &&
                Objects.equals(description, product.description) &&
                Objects.equals(price, product.price) &&
                Objects.equals(qtyOnHand, product.qtyOnHand) &&
                Objects.equals(orders, product.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, mfrId, description, price, qtyOnHand, orders);
    }

    @Override
    public String toString() {
        return "springboot.entity.Product{" +
                "productId='" + productId + '\'' +
                ", mfrId='" + mfrId + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", qtyOnHand=" + qtyOnHand +
                '}';
    }
}