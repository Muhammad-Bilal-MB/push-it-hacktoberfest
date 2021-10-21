#include<iostream>
using namespace std;
class bankaccount
{
protected:
    float balance;
    float s_balance;
    int ND=0; //number of deposits
    int NW=0; //number of withdrawls
    float annual_rate; //interest rate
    float month_rate;
    float month_interest;
    float charges; //service charges
    float amountOfDeposit=0.00;  //total
    float amountOfwithdraw=0.00; //total
    float totalmonthycharges=0.00;
    public:
        bankaccount()
    {
        cout<<"Enter account balance:";
        cin>>balance;
        s_balance=balance;
        cout<<"Enter annual interest rate:";
        cin>>annual_rate;
        cout<<"Monthly service charges for this account:"<<endl;
    cin>>charges;
    totalmonthycharges+=charges;
    }
    bankaccount(float b, float r):balance(b),annual_rate(r)
    { }

    void deposit(float amount_dep)
    {
        balance=balance+amount_dep;
        cout<<"Now your balance after deposit is:"<<balance;
        ND++;
        amountOfDeposit+=amount_dep;
    }
    void withdraw(float amount_wdraw)
    {
        balance=balance-amount_wdraw;
        cout<<"Now your balance after withdraw is:"<<balance<<endl;
        NW++;
        amountOfwithdraw+=amount_wdraw;
    }
    void CalcInt()
    {
        month_rate=annual_rate/12;
        month_interest=balance*month_rate;
        cout<<"The monthly interest is:"<<month_interest<<endl;
        balance=balance+month_interest;

    }
    void MonthlyProc()
    {
        balance=balance-charges;
        CalcInt();
        ND=0;
        NW=0;
        charges=0;
    }
};
class savings_account:public bankaccount
{
private:
    bool status; //0=inactive, 1=active
public:
    savings_account():bankaccount()
    {

    }
    void check() //to check account status
    {
        if(balance<45)
        {
            status=false;
            cout<<"Account is inactive"<<endl;
        }
        else
        {
              status=true;
            cout<<"Account is active"<<endl;
        }
    }
    void withdraw(float a_wdraw)
    {
        check();
        if(status==true)
        {
            bankaccount::withdraw(a_wdraw);
        }
        else
            cout<<"Your account is inactive"<<endl;
    }
void deposit(float a_dep)
{
    check();
    if(status== false && (balance=balance+a_dep>45))
    {
        status=true;
        bankaccount::deposit(a_dep);
    }
    else
    {
        bankaccount::deposit(a_dep);
    }
}
void MonthlyProc()
{
    if(NW>3)
    {
        totalmonthycharges+=2;
    }
    bankaccount::MonthlyProc();
    check();
}

 void statistics_show()
    {
        cout<<"Your starting account balance for this month is:"<<s_balance<<endl;
        cout<<"Total number of deposits for this month is:"<<ND<<endl;
        cout<<"Total amount of deposits for this month is:"<<amountOfDeposit<<endl;
        cout<<"Total number of withdraws for this month is:"<<NW<<endl;
        cout<<"Total amount of withdraws for this month is:"<<amountOfwithdraw<<endl;

        MonthlyProc();
        cout<<"Total amount of monthly charges for this month is:"<<totalmonthycharges<<endl;
        cout<<"Your ending account balance for this month is:"<<balance<<endl;
    }
};


class checking_account:public bankaccount
{
private:
    int month_fee=3;
public:
    checking_account():bankaccount()
    {

    }
    void withdraw(float am_wdraw)
    {
        float x=balance-am_wdraw;
        if((x)<=0)
        {
            cout<<"your balance will be less than 0 if withdrawl made so 12$ charges added"<<endl;
            balance-=12;
        }
        else
        {
            bankaccount::withdraw(am_wdraw);
        }

    }
    void MonthlyProc()
    {
        charges=month_fee+(0.25*NW);
        totalmonthycharges+=charges;
        bankaccount::MonthlyProc();

    }
    void statistics_show()
    {
        cout<<"Your starting account balance for this month is:"<<s_balance<<endl;
        cout<<"Total number of deposits for this month is:"<<ND<<endl;
        cout<<"Total amount of deposits for this month is:"<<amountOfDeposit<<endl;
        cout<<"Total number of withdraws for this month is:"<<NW<<endl;
        cout<<"Total amount of withdraws for this month is:"<<amountOfwithdraw<<endl;

        MonthlyProc();
        cout<<"Total amount of monthly charges for this month is:"<<totalmonthycharges<<endl;
        cout<<"Your ending account balance for this month is:"<<balance<<endl;
    }
};
int main()
{

char ch;
int choice;
float amnt_dep;
float amnt_wdraw;
do
{
    cout<<"Would you like to operate on savings account or checking account?"<<endl;
    cout<<"(Note: You can only make a withdraw for checking account!)"<<endl;
    cout<<"Please enter your choice ( S for savings or C for checking):"<<endl;
    cout<<"If you want to quit press 'E' "<<endl;
    cin>>ch;
    if (ch=='S')
    {

        cout<<"You have entered in Savings Account Menu"<<endl;
        savings_account S1;
        do
        {
        cout<<"Please select the option below for desired operation:"<<endl;
        cout<<"1. Deposit"<<endl;
        cout<<"2. Withdraw"<<endl;
        cout<<"3. Monthly Statistics"<<endl;
        cout<<"4. Exit"<<endl;
        cin>>choice;
        switch(choice)
        {
        case 1:
            cout<<"Enter the amount of Deposit"<<endl;
            cin>>amnt_dep;
            S1.deposit(amnt_dep);
            break;
        case 2:
            cout<<"Enter the amount of Withdraw"<<endl;
            cin>>amnt_wdraw;
            S1.withdraw(amnt_wdraw);
            break;
        case 3:
            cout<<"Monthly Statistics for this savings account are:"<<endl;
            S1.statistics_show();
            break;
        default:
            cout<<"You have exited savings account menu."<<endl;
        }
        }while(choice<=3);
    }
    else if(ch=='C')
    {
        cout<<"You have entered in Checking Account Menu"<<endl;
        checking_account C1;
        do
        {
        cout<<"Please select the option below for desired operation:"<<endl;
        cout<<"1. Withdraw"<<endl;
        cout<<"2. Monthly Statistics"<<endl;
        cout<<"3. Exit"<<endl;
        cin>>choice;
        switch(choice)
        {
        case 1:
            cout<<"Enter the amount of Withdraw"<<endl;
            cin>>amnt_wdraw;
            C1.withdraw(amnt_wdraw);
            break;
        case 2:
            cout<<"Monthly Statistics for this savings account are:"<<endl;
            C1.statistics_show();
            break;
        default:
            cout<<"You have exited checking account menu."<<endl;
        }
        }while(choice<=2);
    }
}while((ch=='S') || (ch=='C'));

}
