public class ExpensesCategories {
    private String categoryName;
    private int id;

    public ExpensesCategories(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
    public ExpensesCategories(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName(){
        return this.categoryName;
    }
}
