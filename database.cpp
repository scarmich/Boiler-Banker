#include <stdio.h>
#include <stdlib.h>
#include "database.h"

/*******************
Server Side Database
*******************/

User::User(){
	user_name = (char*)malloc(50 * sizeof(char));
	password = (char*)malloc(50 * sizeof(char));
	_transaction = (Transaction**)malloc(50 * sizeof(Transaction*));
	for(int i = 0; i < 50; i++){
		_transaction[i] = new Transaction();
	}
}

void
User::retrieveUser(int id){
	user_id = id;

	char fileName [50];
	sprintf(fileName, "%d.txt", user_id);

	FILE * userFile;
	userFile = fopen(fileName, "r");
	if(userFile!=NULL){
		fscanf (userFile, "%s\n", user_name);
		fscanf (userFile, "%s\n", password);
		fscanf (userFile, "%d\n", &num_transactions);
		printf("user_name: %s\n", user_name);
		printf("password: %s\n", password);
		printf("num_transactions: %d\n", num_transactions);

		int i = 0;
		while(i < num_transactions){
			fscanf (userFile, "%f\n", &_transaction[i]->amount);
			fscanf (userFile, "%s\n", _transaction[i]->vendor);
			printf("amount: %f\t", _transaction[i]->amount);
			printf("vendor: %s\n", _transaction[i]->vendor);
			i++;
		}
	}
	fclose(userFile);
}

void
User::saveUser(){
	char fileName [50];
	sprintf(fileName, "%d.txt", user_id);

	
}

Transaction::Transaction(){
	vendor = (char*)malloc(50 * sizeof(char));
	amount = 0;
}


main(){
	printf("0\n");
	User* testUser = new User();
	testUser->retrieveUser(1234);
}