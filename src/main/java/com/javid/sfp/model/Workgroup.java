package com.javid.sfp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.javid.sfp.model.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.javid.sfp.util.validators.Constant.Message.BLANK_NAME;
import static com.javid.sfp.util.validators.Constant.Message.NAME_SIZE;

/**
 * @author javid
 * Created on 5/1/2022
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "WORKGROUP")
public class Workgroup extends BaseEntity<Long> {

    @Size(min = 1, max = 200, message = NAME_SIZE)
    @NotBlank(message = BLANK_NAME)
    @Column(name = "WORKGROUP_NAME", length = 200, nullable = false, unique = true)
    private String name;

    @JsonManagedReference(value = "Work_Group")
    @OneToMany(mappedBy = "workgroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Work> works = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Workgroup workgroup = (Workgroup) o;
        return Objects.equals(name, workgroup.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
