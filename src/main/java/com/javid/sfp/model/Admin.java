package com.javid.sfp.model;

import com.javid.sfp.model.base.User;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author javid
 * Created on 5/1/2022
 */
@NoArgsConstructor
@Entity
@DiscriminatorValue("1")
public class Admin extends User {
}
