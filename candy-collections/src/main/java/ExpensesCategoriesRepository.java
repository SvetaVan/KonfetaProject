import com.mysql.jdbc.Driver;

import java.sql.*;

public class ExpensesCategoriesRepository {
    static final String INSERT_EXPENSE_CATEGORY = "INSERT INTO ExpensesCategories (CategoryName) VALUES (?)" ;

    public static ExpensesCategories save (ExpensesCategories expensesCategories){

        try {
            DriverManager.registerDriver(new Driver());
            try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/CandyDB?useSSL=false", "root", "1");
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EXPENSE_CATEGORY, PreparedStatement.RETURN_GENERATED_KEYS);
            ){
                preparedStatement.setString(1, expensesCategories.getName());
                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next()){
                    int key = resultSet.getInt(1);
                    return new ExpensesCategories(key, expensesCategories.getName());
                }else{
                    throw new CandyRuntimeException("данные не были внесены в БД по категории "+expensesCategories.getName());
                }
            }
        } catch (SQLException exception) {
            throw new CandyRuntimeException("Can't register driver!",exception);
        }
    }
}
