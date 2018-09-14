import java.io.*;
import java.util.*;
import java.lang.*;

class Symbol
{

	String sym[][];
	int itr1=0;

	public Symbol()
	{
		sym = new String[25][2];
	for(int i=0;i<25;i++)
	{
		for(int j=0;j<2;j++)
		{
			sym[i][j] = "-1";
		}
	
	}
	}

	void insert(String s,int l)
	{
		sym[itr1][0] = 	s;

		sym[itr1][1] = Integer.toString(l);

		itr1++;	
			
	} 

	void display()
	{

		System.out.println("---------SYMBOL TABLE---------");
	for(int i=0;i<itr1;i++)
	{
		System.out.println();
		
		for(int j=0;j<2;j++)
		{
			System.out.print(sym[i][j]);
			System.out.print("   ");
								
		}
	
	}
		
	

	}

}

