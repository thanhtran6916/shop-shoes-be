package com.adidas.shopshoes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "SHOES")
public class Shoes {

    @Id
    @GeneratedValue(generator = "system_uuid")
    @GenericGenerator(name = "system_uuid", strategy = "uuid")
    private String id;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_TYPE_ID")
    private CustomerType customerType;

    @OneToMany
    @JoinTable(
            name = "IMAGE_SHOES",
            joinColumns = @JoinColumn(name = "SHOES_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "IMAGE_ID", referencedColumnName = "ID")
    )
    private List<ImageProduct> imageProduct;

    private Integer size;

    private String color;

    @Column(name = "QUANTITY_SALE")
    private Integer quantitySale;

    private Integer reviews;

}
