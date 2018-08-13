package org.activiti.model;

import java.io.Serializable;

public class AlertData implements Serializable {

    private Long id;

    private String description;

    private String status;

    public AlertData() {

    }

    public AlertData(Long id, String description, String status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlertData alertData = (AlertData) o;

        if (id != null ? !id.equals(alertData.id) : alertData.id != null) return false;
        if (description != null ? !description.equals(alertData.description) : alertData.description != null)
            return false;
        return status != null ? status.equals(alertData.status) : alertData.status == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
