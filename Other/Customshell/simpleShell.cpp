// Nabeel Ahmed
// 18024

#include <windows.h>
#include <sstream>
#include <iostream>
#include <iterator>
#include <string>
using namespace std;
#include <list>
#include <stdio.h>
#include <ctime>
#include <process.h>
#include <Tlhelp32.h>
#include <winbase.h>
#include <string.h>
#include <algorithm>
#define TEXTTABLE_ENCODE_MULTIBYTE_STRINGS
#define TEXTTABLE_USE_EN_US_UTF8
#include <time.h>
#include <unistd.h>
#include <signal.h>
#include <vector>
#include <functional>
#include <cctype>
#include <locale>

#define TEXTTABLE_ENCODE_MULTIBYTE_STRINGS
#define TEXTTABLE_USE_EN_US_UTF8
#define HOUR 3600
#define MIN 60

#include <table.h>

struct Process
{
    int num;
    PROCESS_INFORMATION pi;
    string procName;
    bool isActive;
    time_t start;
    mutable time_t endt;
    mutable time_t elapsedt;
};

list<Process> procList;

int sno = 0;

string getFilename(string filename)
{
    const size_t last_slash_idx = filename.find_last_of("\\/");

    if (std::string::npos != last_slash_idx)
    {
        filename.erase(0, last_slash_idx + 1);
    }
    return filename;
}

void createProc(string name)
{
    HANDLE hProcess;
    HANDLE hThread;
    STARTUPINFO si;
    PROCESS_INFORMATION pi;
    DWORD dwProcesId = 0;
    DWORD dwThreadId = 0;
    ZeroMemory(&si, sizeof(si));
    ZeroMemory(&pi, sizeof(pi));

    LPCSTR path = name.c_str();

    BOOL bCreateProcess = NULL;
    bCreateProcess = CreateProcess(
        path,
        NULL,
        NULL,
        NULL,
        FALSE,
        0,
        NULL,
        NULL,
        &si,
        &pi);

    if (bCreateProcess == FALSE)
    {
        cout << "Create Process Failed & Error No -" << GetLastError() << endl;
    }
    else
    {
        cout << "Process Created Successfully!" << endl;

        sno++;
        //time (&start);
        struct Process p_struct = {sno, pi, getFilename(name), true, time(0), '-'};
        procList.push_back(p_struct);

        CloseHandle(pi.hThread);
        CloseHandle(pi.hProcess);
    }

    return;
}

void printAllProcesses(list<Process> const &list)
{
    if (procList.empty())
    {
        cout << "\t\t ----- No Processes Created yet! ----- \t\t" << endl;
    }
    else
    {
        cout << "\t\t --- All Proccesses --- \t\t" << endl;
        TextTable t('-', '|', '+');

        char my_time[50];

        t.add("SNO");
        t.add("PID");
        t.add("Name of Process");
        t.add("Start Time");
        t.add("End Time");
        t.add("Elapsed Time");
        t.add("Status");
        t.endOfRow();
        for (auto it = procList.cbegin(); it != procList.cend(); it++)
        {
            t.add(to_string(it->num));
            t.add(to_string(it->pi.dwProcessId));
            t.add(it->procName);
            strftime(my_time, sizeof(my_time), "%I:%M%p", localtime(&it->start));
            t.add(my_time);
            if (!it->isActive)
            {
                strftime(my_time, sizeof(my_time), "%I:%M%p", localtime(&it->endt));
                t.add(my_time);

                int final_time = difftime(it->endt, it->start);
                int hour = final_time / HOUR;
                int second = final_time % HOUR;
                int minutes = second / MIN;
                second = second % MIN;

                string h = hour < 10 ? "0" + to_string(hour) : to_string(hour);
                string m = minutes < 10 ? "0" + to_string(minutes) : to_string(minutes);
                string s = second < 10 ? "0" + to_string(second) : to_string(second);
                t.add(it->endt != NULL ? h + ":" + m + ":" + s : "-");
                t.add("False");
                t.endOfRow();
            }
            else
            {
                t.add("-");
                t.add("-");
                t.add("True");
                t.endOfRow();
            }
        }
        t.setAlignment(3, TextTable::Alignment::RIGHT);
        std::cout << t;
    }
    //}
}

//TODO: List all Inactive Processes
void printAllTerminatedProcesses(list<Process> const &list)
{
    char my_time[50];
    int counter = 0;
    for (auto it = procList.cbegin(); it != procList.cend(); it++)
    {
        if (!it->isActive)
        {
            counter++;
        }
    }

    if (procList.empty())
    {
        cout << "\t\t ----- No Processes Created yet! ----- \t\t" << endl;
    }
    else if (counter == 0)
    {
        cout << "\t\t ----- No Terminated Processes ----- \t\t\n"
             << endl;
    }
    else
    {
        TextTable t('-', '|', '+');

        t.add("SNO");
        t.add("PID");
        t.add("Name of Process");
        t.add("Start Time");
        t.add("End Time");
        t.add("Elapsed Time");
        t.endOfRow();
        for (auto it = procList.cbegin(); it != procList.cend(); it++)
        {
            if (!it->isActive)
            {
                t.add(to_string(it->num));
                t.add(to_string(it->pi.dwProcessId));
                t.add(it->procName);
                strftime(my_time, sizeof(my_time), "%I:%M%p", localtime(&it->start));
                t.add(my_time);

                strftime(my_time, sizeof(my_time), "%I:%M%p", localtime(&it->endt));
                t.add(my_time);

                int final_time = difftime(it->endt, it->start);
                int hour = final_time / HOUR;
                int second = final_time % HOUR;
                int minutes = second / MIN;
                second = second % MIN;

                string h = hour < 10 ? "0" + to_string(hour) : to_string(hour);
                string m = minutes < 10 ? "0" + to_string(minutes) : to_string(minutes);
                string s = second < 10 ? "0" + to_string(second) : to_string(second);
                t.add(it->endt != NULL ? h + ":" + m + ":" + s : "-");
                t.endOfRow();
            }
        }
        t.setAlignment(3, TextTable::Alignment::RIGHT);
        std::cout << t;
    }
    //
}

//TODO: List all Active Processes
void printAllActiveProcesses(list<Process> const &list)
{
    int counter = 0;
    for (auto it = procList.cbegin(); it != procList.cend(); it++)
    {
        if (it->isActive)
        {
            counter++;
        }
    }

    if (procList.empty())
    {
        cout << "\t\t ----- No Processes Created yet! ----- \t\t" << endl;
    }
    else if (counter == 0)
    {
        cout << "\t\t----- No Active processes -----\t\t\n";
    }
    else
    {
        char my_time[50];
        TextTable t('-', '|', '+');

        t.add("SNO");
        t.add("PID");
        t.add("Name of Process");
        t.add("Start Time");
        t.add("End Time");
        t.add("Elapsed Time");
        t.endOfRow();

        for (auto it = procList.cbegin(); it != procList.cend(); it++)
        {
            if (it->isActive)
            {
                t.add(to_string(it->num));
                t.add(to_string(it->pi.dwProcessId));
                t.add(it->procName);
                strftime(my_time, sizeof(my_time), "%I:%M%p", localtime(&it->start));
                t.add(my_time);
                t.add("-");
                t.add("-");
                t.endOfRow();
            }
        }
        t.setAlignment(3, TextTable::Alignment::RIGHT);
        std::cout << t;
    }
}

void killProcessByName(const char *filename)
{
    bool isPresent = false;
    int counter = 0;
    for (auto it = procList.cbegin(); it != procList.cend(); it++)
    {
        if (it->procName == filename)
        {
            isPresent = true;
        }
        if (it->procName == filename && !it->isActive)
        {
            counter++;
        }
    }
    if (!isPresent)
    {
        cout << "\t\t --- The Process has not been created yet --- \t\t\n";
    }
    else if (counter > 0)
    {
        cout << "\t\t --- The Process is not active --- \t\t\n";
    }
    else
    {
        //TODO: Check if the prcoess is even in the list or not
        HANDLE hSnapShot = CreateToolhelp32Snapshot(TH32CS_SNAPALL, NULL);
        PROCESSENTRY32 pEntry;
        pEntry.dwSize = sizeof(pEntry);
        BOOL hRes = Process32First(hSnapShot, &pEntry);
        while (hRes)
        {
            if (strcmp(pEntry.szExeFile, filename) == 0)
            {
                HANDLE hProcess = OpenProcess(PROCESS_TERMINATE, 0,
                                              (DWORD)pEntry.th32ProcessID);
                if (hProcess != NULL)
                {
                    TerminateProcess(hProcess, 9);
                    CloseHandle(hProcess);
                }
            }
            hRes = Process32Next(hSnapShot, &pEntry);
        }
        std::list<Process>::iterator it;
        for (it = procList.begin(); it != procList.end(); ++it)
        {
            if (it->procName == filename)
            {
                it->isActive = false;
                it->endt = time(0);
                it->elapsedt = difftime(it->endt, it->start);
            }
        }
        CloseHandle(hSnapShot);
    }
}

std::vector<std::string> quotedSplit(std::string str, char delimiter, char quoter)
{
    std::vector<std::string> res;
    std::string cur;
    for (size_t i = 0; i < str.size(); ++i)
    {
        if (str[i] == delimiter)
        {
            res.push_back(cur);
            cur.clear();
        }
        else if (str[i] == quoter)
        {
            ++i;
            for (; i < str.size(); ++i)
            {
                if (str[i] == quoter)
                {
                    if (i + 1 == str.size() || str[i + 1] != quoter)
                        break;
                    ++i;
                    cur.push_back(quoter);
                }
                else
                {
                    cur.push_back(str[i]);
                } // end if
            }     // end for
        }
        else
        {
            cur.push_back(str[i]);
        } // end if
    }     // end for
    if (!cur.empty())
        res.push_back(cur);
    return res;
}
static inline std::string &ltrim(std::string &s)
{
    s.erase(s.begin(), std::find_if(s.begin(), s.end(),
                                    std::not1(std::ptr_fun<int, int>(std::isspace))));
    return s;
}

// trim from end
static inline std::string &rtrim(std::string &s)
{
    s.erase(std::find_if(s.rbegin(), s.rend(),
                         std::not1(std::ptr_fun<int, int>(std::isspace)))
                .base(),
            s.end());
    return s;
}

// trim from both ends
static inline std::string &trim(std::string &s)
{
    return ltrim(rtrim(s));
}
void print(string)
{
}

int main()
{
    string line;

    cout << "> ";
    while (true)
    {
        cout << "\n\t\t\t ----- Simple Command Line Shell ----- \t\t\t\n"
             << endl;
        cout << "\tCommand to list all Processes: `list all processes`" << endl;
        cout << "\tCommand to list all Active Processes `list active processes`" << endl;
        cout << "\tCommand to list all Terminated Processes `list terminated processes`" << endl;
        cout << "\tCommand to run a process(write path in qoutation marks) `run [exe path]`" << endl;
        cout << "\tCommand to run notepad directly: `run notepad.exe`" << endl;
        cout << "\tCommand to run wmplayer directly: `run wmplayer.exe`" << endl;
        cout << "\tCommand to Kill a process(w/o qoutation marks) `kill [exe filename]`" << endl;
        cout << "\tCommand to print the message prompted by user `print [userInput]`" << endl;
        cout << "\tCommand to exit the program `exit`\n\n"
             << endl;

        getline(cin, line);
        line = trim(line);
        vector<string> key_list = quotedSplit(line, ' ', '"');

        if ((strcmp(key_list[0].c_str(), "run") == 0))
        {
            if (key_list.size() > 2)
            {
                cout << "Too many Arguments. Follow Manual!";
            }
            else if ((strcmp(key_list[1].c_str(), "notepad.exe") == 0))
            {
                createProc("C:\\Windows\\system32\\" + key_list[1]);
            }
            else if ((strcmp(key_list[1].c_str(), "wmplayer.exe") == 0))
            {
                createProc("C:\\Program Files (x86)\\Windows Media Player\\" + key_list[1]);
            }
            else
            {
                createProc(key_list[1]);
            }
        }
        else if ((strcmp(key_list[0].c_str(), "list") == 0))
        {
            if (key_list.size() > 3)
            {
                cout << "Too many Arguments. Follow Manual!";
            }
            else if ((strcmp(key_list[1].c_str(), "all") == 0) && (strcmp(key_list[2].c_str(), "processes") == 0))
            {
                printAllProcesses(procList);
            }
            else if ((strcmp(key_list[1].c_str(), "active") == 0) && (strcmp(key_list[2].c_str(), "processes") == 0))
            {
                printAllActiveProcesses(procList);
            }
            else if ((strcmp(key_list[1].c_str(), "terminated") == 0) && (strcmp(key_list[2].c_str(), "processes") == 0))
            {
                printAllTerminatedProcesses(procList);
            }
        }
        else if ((strcmp(key_list[0].c_str(), "kill") == 0))
        {
            if (key_list.size() > 2)
            {
                cout << "Too many Arguments. Follow Manual!";
            }
            else
            {
                killProcessByName(key_list[1].c_str());
            }
        }
        else if ((strcmp(key_list[0].c_str(), "print") == 0))
        {
            int i = 1;
            while (key_list[i] != "")
            {
                cout << key_list[i] << " ";
                i++;
            }
            cout << endl;
        }
        else if ((strcmp(key_list[0].c_str(), "exit") == 0))
        {
            break;
        }
        else
        {
            cout << "Invalid Argument! Refer to the manual.";
        }
    }
}
