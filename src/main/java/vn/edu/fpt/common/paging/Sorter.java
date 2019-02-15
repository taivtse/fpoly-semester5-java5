package vn.edu.fpt.common.paging;

import org.hibernate.criterion.Order;
import vn.edu.fpt.constant.SystemConstant;

public class Sorter {
    private String propertyName;
    private String direction;

    public Sorter(String sortExpression, String sortDirection) {
        this.propertyName = sortExpression;
        this.direction = sortDirection;
    }

    public Order getOrder(){
        if (direction.equals(SystemConstant.SORT_ASC)){
            return Order.asc(propertyName);
        }else {
            return Order.desc(propertyName);
        }
    }
}
