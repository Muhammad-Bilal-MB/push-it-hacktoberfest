Project Statement:
You have to make a bank account systems using c++ language.
This project is divided in to section for better understanding. Following are the Sections:
Section 01:
Design a generic class to hold the following information about a bank account:
•	Balance of the account
•	Number of deposits this month
•	Number of withdrawals this month
•	Annual interest rate for the account
•	Monthly service charges for the account
The class should have the following member functions:
•	Constructor: Accepts arguments for the balance and annual interest rate.
•	Deposit: A function that accepts an argument for the amount of the deposit. The function should add the argument to the account balance. It should also increment the variable holding the number of deposits.

•	Withdraw: A function that accepts an argument for the amount of the withdrawal. The function should subtract the argument from the balance. It should also increment the variable holding the number of withdrawals.

•	CalcInt: A function that updates the balance by calculating the monthly interest earned by the account, and adding this interest to the balance. This is performed by the following formulas:
Monthly Interest Rate = (Annual Interest Rate / 12)
Monthly Interest = Balance * Monthly Interest Rate
Balance = Balance + Monthly Interest

•	MonthlyProc: A function that subtracts the monthly service charges from the balance, calls the calcInt function, and then sets the variables that hold the number of withdrawals, number of deposits, and monthly service charges to zero.
Section 02:
Next, design a savings account class, derived from the generic account class. The savings account class should have the following additional member:
•	status (to represent an active or inactive account)
If the balance of a savings account falls below $45, it becomes inactive. (The status member could be a flag variable.) No more withdrawals may be made until the balance is raised above $45, at which time the account becomes active again. The savings account class should have the following member functions:
•	Withdraw: A function that checks to see if the account is inactive before a withdrawal is made. (No withdrawal will be allowed if the account is not active.) A withdrawal is then made by calling the base class version of the function.

•	Deposit: A function that checks to see if the account is inactive before a deposit is made. If the account is inactive and the deposit brings the balance above $45, the account becomes active again. The deposit is then made by calling the base class version of the function.

•	MonthlyProc: Before the base class function is called, this function checks the number of withdrawals. If the number of withdrawals for the month is more than 3, a service charge of $2 for each withdrawal above 3 is added to the base class variable that holds the monthly service charges. (Don’t forget to check the account balance after the service charge is taken. If the balance falls below $45, the account becomes inactive.)

Section 03:
Next, design a checking account class, also derived from the generic account class. It should have the following member functions:
•	Withdraw: Before the base class function is called, this function will determine if a withdrawal (a check written) will cause the balance to go below $0. If the balance goes below $0, a service charge of $12 will be taken from the account. (The withdrawal will not be made.) If there isn’t enough in the account to pay the service charge, the balance will become negative and the customer will owe the negative amount to the bank.

•	MonthlyProc: Before the base class function is called, this function adds the monthly fee of $3, plus $0.25 per withdrawal (check written) to the base class variable that holds the monthly service charges.

Section 04:
Write a complete program that demonstrates these classes by asking the user to enter the amounts of deposits and withdrawals for a savings account and checking account. The program should display statistics for the month, including beginning balance, total amount of deposits, total amount of withdrawals, service charges, and ending balance.

Note: You can create more variables and functions other than listed above if needed.
