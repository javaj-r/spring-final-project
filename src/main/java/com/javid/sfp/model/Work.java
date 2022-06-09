package com.javid.sfp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.javid.sfp.model.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

import static com.javid.sfp.util.validators.Constant.Message.*;

/**
 * @author javid
 * Created on 5/1/2022
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "WORK")
public class Work extends BaseEntity<Long> {

    @Size(min = 1, max = 200, message = NAME_SIZE)
    @NotBlank(message = BLANK_NAME)
    @Column(name = "WORK_NAME", length = 200, nullable = false, unique = true)
    private String name;

    @Digits(integer = 15, fraction = 2)
    @DecimalMin(value = "0.0", message = NEGATIVE_PRICE)
    @NotNull(message = NULL_PRICE)
    @Column(name = "BASE_PRICE", nullable = false)
    private BigDecimal basePrice;

    @Size(max = 1000, message = DESCRIPTION_SIZE)
    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

    @JsonBackReference(value = "Work_Group")
    @NotNull(message = NULL_WORKGROUP)
    @ManyToOne
    @JoinColumn(name = "WORKGROUP_ID", nullable = false)
    private Workgroup workgroup;

    @JsonManagedReference(value = "Order_Work")
    @OneToMany(mappedBy = "work", cascade = CascadeType.ALL)
    private Set<CustomerOrder> orders;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Work work = (Work) o;
        return Objects.equals(name, work.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
