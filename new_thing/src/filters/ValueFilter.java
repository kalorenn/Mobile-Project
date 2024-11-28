package filters;

public class ValueFilter extends Filter<String>{
    private String value;

    public ValueFilter(String name){
        super(name);
    }

    public void setValue(String value){
        this.value = value;
    }

    @Override
    public String getFilterValue() {
        return value;
    }
}
