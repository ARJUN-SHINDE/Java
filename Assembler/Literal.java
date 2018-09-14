import java.io.*;
import java.util.*;
import java.lang.*;

class Literal
{

	String lit[][];
	int itr1=0;

	public Literal()
	{
			lit = new String[15][2];
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<2;j++)
			{
				lit[i][j] = "-1";
			}
	
		}
	}

	void insert(String s,int l)
	{
		lit[itr1][0] = 	s;

		lit[itr1][1] = Integer.toString(l);

		itr1++;	
			
	} 

	void display()
	{
		System.out.println("---------LITERAL TABLE---------");
		for(int i=0;i<itr1;i++)
		{
			System.out.println();
			for(int j=0;j<2;j++)
			{
				System.out.print(lit[i][j]);
				System.out.print("   ");
			}
		}
	}
		
	

	int find(String f)
	{
		int flag=-1;
		for(int i=0;i<itr1;i++)
		{
		
			if(lit[i][0].equals(f))
			{
				flag =i;
				return flag;
			}		
		}
		if(flag!=-1)
		{
			return flag;
		}
		else
		{
			return -1;
		}
	}
}
