package vn.edu.fpt.common.paging;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class SearchProperty {
    private String propertyName;
    private Object value;
    private MatchType matchMode;

    public enum MatchType {
        LIKE_EXACT, LIKE_START, LIKE_END, LIKE_ANYWHERE, EQUAL;

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
            case LIKE_ANYWHERE:
                criterion = Restrictions.like(propertyName, value.toString(), MatchMode.ANYWHERE);
                break;
            case LIKE_END:
                criterion = Restrictions.like(propertyName, value.toString(), MatchMode.END);
                break;
            case LIKE_START:
                criterion = Restrictions.like(propertyName, value.toString(), MatchMode.START);
                break;
            case LIKE_EXACT:
                criterion = Restrictions.like(propertyName, value);
                break;
            case EQUAL:
                criterion = Restrictions.eq(propertyName, value);
                break;
            default:
                criterion = Restrictions.eq(propertyName, value);
        }

        return criterion;
    }
}
