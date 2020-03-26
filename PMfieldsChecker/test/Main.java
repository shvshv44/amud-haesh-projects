import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TestModel testModel = new TestModel("Amit", null);
        List<InnerModel> list = new ArrayList<>();
        InnerInnerModel innerInnerModel = new InnerInnerModel(null);
        InnerModel innerModel = new InnerModel(null, innerInnerModel);
        testModel.setInnerModel(innerModel);
        list.add(null);
        list.add(innerModel);
        testModel.setList(list);
        Integer[] numbers = {1, 2 , null};
        testModel.setNumbers(numbers);
        NullFieldHandler handleNullField = (String path)-> {
            System.out.println(path + " is null");
        };
        NullFieldsFinder nullFieldsFinder = new NullFieldsFinder(handleNullField);
        try {
            nullFieldsFinder.checkNullFields(testModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
