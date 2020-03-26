import java.util.List;

public class TestModel {
    private String name;
    private Integer age;
    private InnerModel innerModel;
    private List<InnerModel> list;
    private Integer[] numbers;

    public TestModel(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<InnerModel> getList() {
        return list;
    }

    public void setList(List<InnerModel> list) {
        this.list = list;
    }

    public InnerModel getInnerModel() {
        return innerModel;
    }

    public void setInnerModel(InnerModel innerModel) {
        this.innerModel = innerModel;
    }

    public Integer[] getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer[] numbers) {
        this.numbers = numbers;
    }
}
