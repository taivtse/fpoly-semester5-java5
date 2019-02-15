package vn.edu.fpt.common.paging;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class SearchProperty {
    private String propertyName;
    private Object value;
    private MatchType matchMode;

    public enum MatchType {
        EXACT, START, END, ANYWHERE;

        private MatchType() {
        }
    }

    public SearchProperty(String propertyName, Object value, MatchType matchMode) {
        this.propertyName = propertyName;
        this.value = value;
        this.matchMode = matchMode;
    }

    public Criterion getCriterion() {
        Criterion criterion;
        switch (matchMode) {
            case ANYWHERE:
                criterion = Restrictions.like(propertyName, value.toString(), MatchMode.ANYWHERE);
                break;
            case END:
                criterion = Restrictions.like(propertyName, value.toString(), MatchMode.END);
                break;
            case START:
                criterion = Restrictions.like(propertyName, value.toString(), MatchMode.START);
                break;
            default:
                criterion = Restrictions.like(propertyName, value.toString());
        }

        return criterion;
    }
}
