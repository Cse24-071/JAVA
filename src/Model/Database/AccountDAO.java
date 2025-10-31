package Model.Database;
import Model.Account;
import java.util.List;

public interface AccountDAO {
        void create(Account account);
        Account read(String id);
        void update(Account account);
        void delete(String id);
        List<Account> getAccountsByCustomer(String customerId);
    }

