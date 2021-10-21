package studentcircularlist;

import java.util.Scanner;

public class StudentCircularList 
{
    Student head;
    Student tail;
    
    class Student
    {
        int RegNo;
        Student next;

        public Student(int RegNo)
        {
            this.RegNo = RegNo;
            this.next = null;
        }
    }
    
    public StudentCircularList Insert (StudentCircularList list, int RegNo)
    {
        Student s = new Student(RegNo);
        if (head == null)
        {
            head = s;
            tail = s;
            tail.next = head;
        }
        
        else
        {
            tail.next = s;
            tail = s;
            tail.next = head;
        }
        return list;
    }
    
    public StudentCircularList Search (StudentCircularList list, int RegNo)
    {
        Student s1 = head; 
        do
        {
            if (s1.RegNo == RegNo)
            {
                System.out.println("Record Found");
                return list;
            }
            
            if (s1.next == head)
            {
                System.out.println("Record Not Found");
            }
            s1 = s1.next;
        }
        while (s1 != head);
        return list;
    }
    
    public StudentCircularList Delete (StudentCircularList list, int RegNo)
    {
        Student current = head, prev = null;  
        if (current.RegNo == RegNo)
        {
            System.out.println("RegNo " +current.RegNo+ " Deleted\n--------------");
            head = head.next;
            tail.next = head;
            return list;
        }
        
        do
        {
            if (current.next == head)
            {
                return list;
            }
            
            prev = current; 
            current = current.next;
            
            if (current.RegNo == RegNo)
            {
                prev.next = current.next; 
                System.out.println("RegNo " +current.RegNo+ " Deleted\n--------------");
                return list;
            }
        }
        while (current.RegNo != RegNo);
        return list;
    }
    
    public void Reverse (StudentCircularList list)
    {
        Student current = head;
        Student prev = null;
        Student next = null;
        
        do
        {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        while (current != head);
        head.next = prev;
        tail = current;
        head = prev;
    }
    
    public void show (StudentCircularList list)
    {
        Student temp = head;
        do
        {
            System.out.println("RegNo : " +temp.RegNo);
            temp = temp.next;
        }
        while (temp != head);
        System.out.println("--------------");
    }
    
    public static void main(String[] args)
    {
        Scanner s = new Scanner (System.in);
        
        StudentCircularList list = new StudentCircularList();
        
        list = list.Insert(list, 1);
        list = list.Insert(list, 2);
        list = list.Insert(list, 3);
        list = list.Insert(list, 4);
        list = list.Insert(list, 5);    
        list.show(list);
        
    /*    System.out.print("Enter RegNo You Want to Search & Delete: ");
        int RegNo = s.nextInt();
        
        list = list.Search(list, RegNo);      
        list = list.Delete(list, RegNo);
        list.show(list);
        */
        list.Reverse(list);
        list.show(list);
    }
}