package vn.edu.fpt.dao;

import java.util.List;

public interface SynthesisDao {
    List<Object[]> getStaffSynthesis();

    Object[] getStaffSynthesisByCode(String staffCode);
}
