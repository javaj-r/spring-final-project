package com.javid.sfp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.javid.sfp.model.base.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * @author javid
 * Created on 4/30/2022
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("2")
public class Customer extends User {

    @Digits(integer = 15, fraction = 2)
    @Column(name = "USER_CREDIT")
    private BigDecimal credit;

    @JsonManagedReference(value = "Customer_Order")
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CustomerOrder> orders = new HashSet<>();
}
