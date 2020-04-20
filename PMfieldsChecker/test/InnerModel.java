import annotations.NullCheckableObject;

@NullCheckableObject
public class InnerModel {
    private String description;
    private InnerInnerModel innerInnerModel;

    public InnerModel(String description, InnerInnerModel innerInnerModel) {
        this.description = description;
        this.innerInnerModel = innerInnerModel;
    }
}
