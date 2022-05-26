package com.javid.spring.last.project.phase3.repository.specification;

import com.javid.spring.last.project.phase3.model.Expert;
import com.javid.spring.last.project.phase3.model.Work;

import javax.persistence.criteria.*;

/**
 * @author javid
 * Created on 5/11/2022
 */
public class ExpertSpecification extends UserSpecification<Expert> {

    public ExpertSpecification(SearchCriteria criteria) {
        super(criteria);
    }

    @Override
    public Predicate toPredicate(Root<Expert> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getWorkName() == null || criteria.getWorkName().isBlank())
            return super.toPredicate(root, query, builder);

        var workName = criteria.getWorkName();

        Join<Expert, Work> join = root.join("enrolledWorks", JoinType.INNER);

        var predicates = toUserPredicates(root, builder);
        predicates.add(builder.like(join.get("name").as(String.class), PER + workName + PER));

        return builder.and(predicates.toArray(Predicate[]::new));
    }
}
