#include <iostream>
using namespace std;

int main()

{
    int rows=7,cols=7;
    int arr[rows][cols];

    for(int i=0;i<rows;i++)
    {
        for(int j=0;j<=i;j++)
        {
            if(j==0)
                arr[i][j]=1;
            else if (j==i) 
                arr[i][j]=1;
            else     
                arr[i][j]=arr[i-1][j]+arr[i-1][j-1];
        }
    }
    for(int i=0;i<rows;i++)
    {
        for(int j=0;j<=i;j++)
        { 
            cout << arr[i][j] << " ";
        }
        cout << endl;
    }
    return 0;
}

/*

1       
1 1     
1 2 1   
1 3 3 1 
1 4 6 4 1
1 5 10 10 5 1
1 6 15 20 15 6 1


*/