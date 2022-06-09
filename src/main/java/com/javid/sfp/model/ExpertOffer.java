package com.javid.sfp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.javid.sfp.model.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Time;

import static com.javid.sfp.util.validators.Constant.Message.*;

/**
 * @author javid
 * Created on 5/2/2022
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "EXPERT_OFFER")
public class ExpertOffer extends BaseEntity<ExpertOfferId> {

    @EmbeddedId
    @AttributeOverride(name = "ID", column = @Column(name = "id"))
    private ExpertOfferId id;

    @JsonBackReference(value = "Expert_Offer")
    @NotNull(message = NULL_EXPERT)
    @ManyToOne
    @JoinColumn(name = "EXPERT_ID", updatable = false, insertable = false)
    private Expert expert;

    @JsonBackReference(value = "Offer_Order")
    @NotNull(message = NULL_ORDER)
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ORDER_ID", updatable = false, insertable = false)
    private CustomerOrder order;

    @Digits(integer = 15, fraction = 2)
    @DecimalMin(value = "0.0", message = NEGATIVE_PRICE)
    @Column(name = "EXPERT_OFFERED_PRICE")
    private BigDecimal offeredPrice;

    @Min(value = 0, message = NEGATIVE_WORK_DURATION)
    @Column(name = "WORK_DURATION_HOUR")
    private Long workDurationHour;

    @Column(name = "WORK_START_HOUR")
    private Time startTime;
}
