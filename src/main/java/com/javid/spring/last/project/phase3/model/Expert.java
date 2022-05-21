package com.javid.spring.last.project.phase3.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.javid.spring.last.project.phase3.model.base.User;
import com.javid.spring.last.project.phase3.model.enums.UserStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static com.javid.spring.last.project.phase3.util.Validator.Message.IMAGE_MAX_KB;
import static com.javid.spring.last.project.phase3.util.Validator.Message.NULL_IMAGE;

/**
 * @author javid
 * Created on 4/30/2022
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("3")
public class Expert extends User {

    @NotNull(message = NULL_IMAGE)
    @Size(max = 300 * 1024, message = IMAGE_MAX_KB)
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "EXPERT_IMAGE")
    private Byte[] expertImage;

    @Enumerated(EnumType.STRING)
    @Column(name = "EXPERT_STATUS", length = 32)
    private UserStatus expertStatus;

    @Digits(integer = 15, fraction = 2)
    @Column(name = "USER_CREDIT")
    private BigDecimal credit;

    @JsonManagedReference(value = "Expert_Offer")
    @OneToMany(mappedBy = "expert", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ExpertOffer> expertOffers = new HashSet<>();

    @JsonManagedReference(value = "Order_Expert")
    @OneToMany(mappedBy = "selectedExpert", cascade = CascadeType.PERSIST)
    private Set<CustomerOrder> orders = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "EXPERT_WORK",
            joinColumns = @JoinColumn(name = "EXPERT_ID"),
            inverseJoinColumns = @JoinColumn(name = "WORK_ID"))
    private Set<Work> enrolledWorks = new HashSet<>();

    @PreRemove
    private void onRemove() {
        orders.forEach(order -> order.setSelectedExpert(null));
    }
}
