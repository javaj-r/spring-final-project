package com.javid.spring.last.project.phase3.model;

import com.javid.spring.last.project.phase3.model.base.User;
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
