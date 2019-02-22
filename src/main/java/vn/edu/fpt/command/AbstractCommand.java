package vn.edu.fpt.command;

import vn.edu.fpt.dto.PNotifyDto;

import java.util.List;
import java.util.Locale;

public class AbstractCommand<T> {
    protected T pojo;
    private List<T> listResult;
    private int maxPageItems = 5;
    private int totalItems = 0;
    private int firstItem = 0;
    private int page = 1;
    private int totalPages;
    private String sortExpression;
    private String sortDirection;
    private String[] checkList;
    private PNotifyDto pNotifyDto;
    private Locale locale;

    public T getPojo() {
        return pojo;
    }

    public void setPojo(T pojo) {
        this.pojo = pojo;
    }

    public List<T> getListResult() {
        return listResult;
    }

    public void setListResult(List<T> listResult) {
        this.listResult = listResult;
    }

    public int getMaxPageItems() {
        return maxPageItems;
    }

    public void setMaxPageItems(int maxPageItems) {
        this.maxPageItems = maxPageItems;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getFirstItem() {
        return firstItem;
    }

    public void setFirstItem(int firstItem) {
        this.firstItem = firstItem;
    }

    public String getSortExpression() {
        return sortExpression;
    }

    public void setSortExpression(String sortExpression) {
        this.sortExpression = sortExpression;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public String[] getCheckList() {
        return checkList;
    }

    public void setCheckList(String[] checkList) {
        this.checkList = checkList;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public PNotifyDto getpNotifyDto() {
        return pNotifyDto;
    }

    public void setpNotifyDto(PNotifyDto pNotifyDto) {
        this.pNotifyDto = pNotifyDto;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
