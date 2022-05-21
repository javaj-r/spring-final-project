package com.javid.spring.last.project.phase3.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.javid.spring.last.project.phase3.model.base.BaseEntity;
import com.javid.spring.last.project.phase3.model.enums.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import static com.javid.spring.last.project.phase3.util.Validator.Message.*;

/**
 * @author javid
 * Created on 5/1/2022
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CUSTOMER_ORDER")
public class CustomerOrder extends BaseEntity<Long> {

    @Digits(integer = 15, fraction = 2)
    @DecimalMin(value = "0.0", message = NEGATIVE_PRICE)
    @Column(name = "CUSTOMER_PROPOSED_PRICE")
    private BigDecimal proposedPrice;

    @Size(max = 10000, message = DESCRIPTION_SIZE)
    @Column(name = "ORDER_DESCRIPTION", length = 10000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_STATUS", length = 32)
    private OrderStatus orderStatus;

    @Column(name = "WORK_DATE")
    private Date workDate;

    @Column(name = "WORK_TIME")
    private Time workTime;

    @Size(max = 500, message = ADDRESS_SIZE)
    @Column(name = "ORDER_ADDRESS", length = 500)
    private String address;

    @Size(max = 10000, message = COMMENT_SIZE)
    @Column(name = "CUSTOMER_COMMENT", length = 10000)
    private String customerComment;

    @Min(value = -10, message = SCORE_MIN)
    @Max(value = 10, message = SCORE_MAX)
    private Byte workScore;

    @JsonBackReference(value = "Customer_Order")
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    @JsonBackReference(value = "Order_Work")
    @ManyToOne
    @JoinColumn(name = "WORK_ID", nullable = false)
    private Work work;

    @JsonBackReference(value = "Order_Expert")
    @ManyToOne
    @JoinColumn(name = "SELECTED_EXPERT_ID")
    private Expert selectedExpert;

    @JsonManagedReference(value = "Offer_Order")
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<ExpertOffer> expertOffers = new HashSet<>();
}
