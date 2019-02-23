package vn.edu.fpt.dao;

import java.util.List;

public interface SynthesisDao {
    Object[] getStaffSynthesisByCode(String staffCode);

    List<Object[]> getTopStaffSynthesis(Integer limit);

    List<Object[]> getDepartSynthesis();
}
