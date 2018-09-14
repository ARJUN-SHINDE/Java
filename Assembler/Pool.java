import java.io.*;
import java.util.*;
import java.lang.*;

class Pool
{
	int arr[];
	int itr1=1;
	public Pool()
	{
		arr = new int[20];
		arr[0] = 0;
	}

	void insert(int no)
	{
	 	arr[itr1] = no;
	
		itr1++;	
	}

	void display()
	{
		
		System.out.println("---------LITERAL POOL TABLE---------");
		System.out.println();
		for(int i=0;i<itr1-1;i++)
		{
			System.out.println(arr[i]);
		}	

	}
}
