package com.javid.sfp.repository.specification;

import lombok.*;

/**
 * @author javid
 * Created on 5/10/2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SearchCriteria {
    private String email;
    private String firstname;
    private String lastname;
    private String workName;
}
