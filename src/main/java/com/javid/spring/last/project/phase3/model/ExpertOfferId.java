package com.javid.spring.last.project.phase3.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author javid
 * Created on 5/2/2022
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class ExpertOfferId implements Serializable {

    @Column(name = "EXPERT_ID")
    private Long expertId;

    @Column(name = "CUSTOMER_ORDER_ID")
    private Long customerOrderId;
}
