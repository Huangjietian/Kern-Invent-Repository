package cn.kerninventory.tools.excel.anno.constants;

/**
 * <p>
 *     Excel document type enums.
 * </p>
 *
 * @author Kern
 */
public enum  DocumentType {

    XLS( "The 1997 to 2003 edition."),
    XLSX("The edition since 2003."),
    ;

    private String description;

    DocumentType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "DocumentType{" +
                "description='" + description + '\'' +
                '}';
    }
}
