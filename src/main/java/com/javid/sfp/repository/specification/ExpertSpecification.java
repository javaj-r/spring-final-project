package com.javid.sfp.repository.specification;

import com.javid.sfp.model.Expert;
import com.javid.sfp.model.Work;

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
        var workName = criteria.getWorkName();
        var score = criteria.getScore();

        Join<Expert, Work> join = null;

        if (workName != null && workName.isBlank()) {
             join = root.join("enrolledWorks", JoinType.INNER);
        }

        var predicates = toUserPredicates(root, builder);

        if (join != null) {
            predicates.add(builder.like(join.get("name").as(String.class), PER + workName + PER));
        }

        if (score != null) {
            predicates.add(builder.between(root.get("score"), score - 0.5, score + 0.5));
        }

        return builder.and(predicates.toArray(Predicate[]::new));
    }
}
