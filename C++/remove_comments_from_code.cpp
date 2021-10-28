#include <iostream>
#include <fstream>
#include <string.h>
#include <sstream>
using namespace std;
bool staric_com = false;
string removeComments(string c)
{
    string str = "";
    for (int i = 0; i < c.length(); i++)
    {
        if (c[i] == '/' and c[i + 1] == '*')
            staric_com = true;
        if (c[i] == '*' and c[i + 1] == '/')
        {
            staric_com = false;
            return str;
        }
        if (c[i] == '/' and c[i + 1] == '/')
        {
            return str;
        }
        if (!staric_com)
            str.push_back(c[i]);
    }
    return str;
}
int main()
{
    cout << "\n\n......................H_A_M_S_O_F..................\n\n";
    cout << "Welcome to remove comment program\n\n";
    cout << "Please enter file name with file type like hamsof.cpp , hafiz.js from current directory\n\n";
    string file_name;
    cin >> file_name;
    ifstream out(file_name);//read mode
    ofstream in("input.txt");//write mode
    string s;
    while (!out.eof())
    {
        getline(out, s);
        s = removeComments(s);
        if (s == "") //removing enter spaces
            continue;
        in << s << "\n";
    }
    in.close();
    out.close();
    const char *c = file_name.c_str();
    remove(c);
    rename("input.txt", c);

    cout << "\n\nyour file  : " << c << " has been changed successfully check it from your repository\n\n";
    cout << ".....................Thanks for using us ...................\n";
    cout << "\n\n......................H_A_M_S_O_F..................\n\n";

    return 0;
}