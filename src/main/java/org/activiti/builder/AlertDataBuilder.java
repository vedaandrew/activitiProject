package org.activiti.builder;

import org.activiti.model.AlertData;

import java.util.function.Consumer;

public class AlertDataBuilder {

    public Long id;

    public String description;

    public String status;

    public AlertDataBuilder with(Consumer<AlertDataBuilder> builderFunction) {
        builderFunction.accept(this);
        return this;
    }

    public AlertData createAlertData() {
        return new AlertData(id, description, status);
    }
}
