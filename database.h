
struct Transaction{
	char* vendor;
	float amount;

	Transaction();
};

struct User{
	int user_id;
	int num_transactions;
	char* user_name;
	char* password;
	Transaction ** _transaction;

	User();
	void retrieveUser(int id);
	void saveUser();
};
