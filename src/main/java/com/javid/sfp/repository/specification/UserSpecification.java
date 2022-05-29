package com.javid.sfp.repository.specification;

import com.javid.sfp.model.base.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

/**
 * @author javid
 * Created on 5/10/2022
 */
public class UserSpecification<T extends User> implements Specification<T> {

    protected static final char PER = '%';
    protected final SearchCriteria criteria;

    public UserSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        ArrayList<Predicate> predicates = toUserPredicates(root, builder);

        return builder.and(predicates.toArray(Predicate[]::new));
    }

    protected ArrayList<Predicate> toUserPredicates(Root<T> root, CriteriaBuilder builder) {
        var firstname = criteria.getFirstname();
        var lastname = criteria.getLastname();
        var email = criteria.getEmail();

        var first = "firstname";
        var last = "lastname";
        var emailAddress = "email";
        var predicates = new ArrayList<Predicate>();

        predicates.add(builder.and());

        if (firstname == null) {
            predicates.add(root.get(first).isNull());
        } else {
            predicates.add(builder.like(root.get(first), PER + firstname + PER));
        }

        if (lastname == null) {
            predicates.add(root.get(last).isNull());
        } else {
            predicates.add(builder.like(root.get(last), PER + lastname + PER));
        }

        if (email != null) {
            predicates.add(builder.like(root.get(emailAddress), PER + email + PER));
        }
        return predicates;
    }
}
