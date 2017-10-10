package com.simpletour.query;

import com.simpletour.baseQuery.BaseQuery;
import com.simpletour.baseQuery.MatchType;
import com.simpletour.baseQuery.QueryWord;
import com.simpletour.domain.Item;
import com.simpletour.domain.Item_;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuliyuan
 * @date 2017/10/10 10:10
 * @Description: item查询类
 */
@Data
public class ItemQuery extends BaseQuery<Item> {

    @QueryWord(column = "item_id", func = MatchType.equal)
    private Integer itemId;

    @QueryWord(column = "item_name",func = MatchType.like)
    private String itemName;

    private Integer itemPriceMin;

    private Integer itemPriceMax;

    @Override
    public Specification<Item> toSpec() {
        Specification<Item> spec = super.toSpecWithAnd();
        return ((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicatesList = new ArrayList<>();
            predicatesList.add(spec.toPredicate(root, criteriaQuery, criteriaBuilder));
            if (itemPriceMin != null) {
                predicatesList.add(
                        criteriaBuilder.and(
                                criteriaBuilder.ge(
                                        root.get(Item_.itemPrice), itemPriceMin)));
            }
            if (itemPriceMax != null) {
                predicatesList.add(
                        criteriaBuilder.and(
                                criteriaBuilder.le(
                                        root.get(Item_.itemPrice), itemPriceMax)));
            }
            return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
        });
    }
}
