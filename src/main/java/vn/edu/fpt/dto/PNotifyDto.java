package vn.edu.fpt.dto;

public class PNotifyDto {
    private String title;
    private String text;
    private String type;

    public PNotifyDto() {
    }

    public PNotifyDto(String title, String text, String type) {
        this.title = title;
        this.text = text;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
