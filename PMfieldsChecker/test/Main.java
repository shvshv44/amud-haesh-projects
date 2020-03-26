import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AmitModel amitModel = new AmitModel("Amit", null);
        List<InnerModel> list = new ArrayList<>();
        InnerInnerModel innerInnerModel = new InnerInnerModel(null);
        InnerModel innerModel = new InnerModel(null, innerInnerModel);
        amitModel.setInnerModel(innerModel);
        list.add(null);
        list.add(innerModel);
        amitModel.setList(list);
        NullFieldHandler handleNullField = (String path)-> {
            System.out.println(path);
        };
        NullFieldsFinder nullFieldsFinder = new NullFieldsFinder(handleNullField);
        try {
            nullFieldsFinder.checkNullFields(amitModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
