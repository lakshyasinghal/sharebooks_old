var statusCodes = {

	ADD_USER_SUCCESSFUL : 1,
	GET_USER_SUCCESSFUL : 2,
	USER_UPDATE_SUCCESSFUL : 3,
	USER_UPDATE_FAILED : 4,
	USERNAME_ALREADY_EXISTS : 5,
	USERINFO_INCOMPLETE : 6,
	LOGOUT_SUCCESSFUL : 7,
	SESSION_ALREADY_EXPIRED : 8,
	SESSION_TIMEOUT : 9,
	LOGIN_FAILED : 10,
	LOGIN_SUCCESSFUL : 11,
	ADD_BOOK_FAILED : 12,
	ADD_BOOK_SUCCESSFUL : 13,
	GET_BOOKS_SUCCESSFUL : 14,
	GET_BOOKS_FAILED : 15,
	GET_BOOKS_BY_CATEGORY_SUCCESSFUL : 16,
	GET_BOOKS_BY_CATEGORY_FAILED : 17,
	GET_BOOKS_BY_SEARCH_SUCCESSFUL : 18,
	GET_BOOKS_BY_SEARCH_FAILED : 19
};




var messages = [
	"Registered successfully",                                               //1
	"User retrieved successfully",                                           //2
	"User updated successfully",                                             //3
	"User update failed",                                                    //4
	"Username already exists. Please choose a different username",           //5
	"Please enter all the details for the user",                             //6
	"You have been logged out",                                              //7
	"Your session is already expired",                                       //8
	"Your session has timed out",                                            //9
	"Login failed. Please check login credentials",                          //10
	"Logged in successfully",                                                //11
	"Book could not be added. Some error occurred",                          //12
	"Book has been added successfully to the account",                       //13
	"Books retrieved successfully",                                          //14
	"Books could not be fetched. Some error occurred",                       //15
	"Books fetched successfully by category",                                //16
	"Books request by category failed",                                      //17
	"Books request by search string successful",                             //18
	"Books request by search string failed"                                  //19
];