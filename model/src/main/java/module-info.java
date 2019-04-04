module model {
    requires gson;
    opens com.model to gson;

    exports com.model;
    exports com.model.enums;
}