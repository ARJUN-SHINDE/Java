import java.io.*;
import java.util.*;
import java.lang.*;


public class CodeConversion 
{
	public static void main(String args[]) throws Exception
	{
		
		int count=0;
		int iccount=0;
		
		int lpools =0;
		int lpoole = 1;
		
		int pools =0;
		int poole = 1;
		int flaglc=2;
		Symbol ss = new Symbol();
		Literal lt = new Literal();
		Pool p = new Pool();
		
		String ins[] = new String[100];
		
		for(int k=0;k<100;k++)
		{
			ins[k] = "";
			
		}

			FileReader fr = new FileReader("asmcode.txt");
			int i=0;
			int j=0;
	
		while((i=fr.read())!=-1)
		{
			ins[j] = ins[j]+(char)i;
			if((char)i=='\n')
			{
				count++;
				j++;
			}
		
			//System.out.println((char)i);

		}
			
		String sins[][]=new String[count][3];	
		int lc[]= new int[count+1];
		int ll[] = new int[count];
		String mc[] = new String[count];

		for(int k=0;k<count;k++)
		{
			lc[k] = -1;
		}

		for(int k=0;k<count;k++)
		{
			mc[k] = "";
		}
		for(int k=0;k<count;k++)
		{
			for(int l=0;l<3;l++)
			{
				sins[k][l]="-1";
			}

		}

	

		for(int k=0;k < count;k++)
		{
			
		//System.out.println("ins :" + ins[k]);
		
		String w[] = ins[k].split("\\s");
					
			if(w.length>3)
				{
					ss.insert(w[0],-1);
					ll[k]=k;
					
				}
			for(int m=0;m<w.length;m++)
			{
				if(w.length>3)
				{
					//ss.insert(w[0],-1);
					sins[k][0]=w[1];
					
					sins[k][1]=w[2];
					sins[k][2]=w[3];
										
					
				}
				else
				{
					sins[k][m]=w[m];
				}	

			}

							
		}
	/*		
		for(int k=0;k<count;k++)
		{
			for(int l=0;l<3;l++)
			{
			System.out.println("value : "+sins[k][l]);
			}

		}
	*/		
		lc[1] = Integer.parseInt(sins[0][1]); 

		
		System.out.println();
		System.out.println("TOTAL INSTRUCTION`S ARE :" + count);
		


		Mot obj = new Mot();
		String ic[] = new String[count];		
	
	for(int k=0;k<count;k++)
		{
			ic[k] = "";
			
		}
	
		
//..................finding itermediate code...................................................................................................
		for(int k=0;k<count;k++)
		{
			for(int l=0;l<3;l++)
			{
					
				//............IMPREATIVE S...................
						
				if((sins[k][l].equals("STOP"))||(sins[k][l].equals("ADD"))||(sins[k][l].equals("SUB"))||(sins[k][l].equals("MULT"))||(sins[k][l].equals("MOVER"))||(sins[k][l].equals("MOVEM"))||(sins[k][l].equals("COMP"))||(sins[k][l].equals("BC"))||(sins[k][l].equals("DIV"))||(sins[k][l].equals("READ"))||(sins[k][l].equals("PRINT")))
				{
					
					for(int m=0;m<11;m++)
					{
						if(sins[k][l].equals(obj.arr[m]))
						{
							ic[k] = ic[k] + "(IS,0"+m+")";
							mc[k] = mc[k] + "0"+m+"   ";
						}		
					}
					
					if(sins[k][l].equals("STOP"))
					{
						mc[k] = mc[k] + "00   000";
					}
					if(sins[k][l].equals("READ"))
					{
						mc[k] = mc[k] + "00   ";
					}
					if(sins[k][l].equals("PRINT"))
					{
						mc[k] = mc[k] + "00   000";
					}
											
				}

				//...........ASSEMBLYDIRECTIVES................				
				if(sins[k][l].equals("START")||sins[k][l].equals("END")||sins[k][l].equals("ORIGIN")||sins[k][l].equals("EQU")||sins[k][l].equals("LTORG")||sins[k][l].equals("LTROG"))
				{
					if(sins[k][l].equals("START"))
						ic[k] = ic[k] + "(AD,01)";
					if(sins[k][l].equals("END"))
						ic[k] = ic[k] + "(AD,02)";
					if(sins[k][l].equals("ORIGIN"))
					{
						ic[k] = ic[k] + "(AD,03)";
						mc[k] = mc[k] + "00   00   000";
					}
					if(sins[k][l].equals("EQU"))
					{	ic[k] = ic[k] + "(AD,04)";
						mc[k] = mc[k] + "00   00   000";					
					}
					if(sins[k][l].equals("LTORG"))
						ic[k] = ic[k] + "(AD,05)";
					if(sins[k][l].equals("LTROG"))
						ic[k] = ic[k] + "(AD,05)";
					
				}
					
				//..............DECLARATIVE S..................
				
				if(sins[k][l].equals("DS")||sins[k][l].equals("DC"))
				{
	
					if(sins[k][l].equals("DS"))
						ic[k] = ic[k] + "(DL,01)";
					if(sins[k][l].equals("DC"))
						ic[k] = ic[k] + "(DL,02)";
					
					ss.insert(sins[k][l-1],-1);										      				

				}	

				//.............REGISTER HANDLE...........
				
				if(sins[k][l].equals("AREG")||sins[k][l].equals("BREG")||sins[k][l].equals("CREG"))
				{
	
					if(sins[k][l].equals("AREG"))
						{
							ic[k] = ic[k] + "(RG,01)";
							mc[k] = mc[k] + "01   ";
						}
					if(sins[k][l].equals("BREG"))
					{						
						ic[k] = ic[k] + "(RG,02)";
						mc[k] = mc[k] + "02   ";
						
					}
					if(sins[k][l].equals("CREG"))
					{
						ic[k] = ic[k] + "(RG,03)";
						mc[k] = mc[k] + "03   ";
											
					}
				}	
					
				if(sins[k][l].equals("EQU"))
				{
					ss.insert(sins[k][l-1],-1);
							
				}
				



			}

		}
			
			ic[0] = ic[0]+"(C,"+sins[0][1]+")";
	

//.............for location countr.....................................................................................................

		for(int k=1;k<count;k++)
		{
			for(int l=0;l<3;l++)
			{
					


				if(sins[k][l].contains("'")&&!(sins[k][l-1].equals("DC")))
				{

					lt.insert(sins[k][l],-1);
				}




				//............IMPREATIVE S...................
						
				if((sins[k][l].equals("STOP"))||(sins[k][l].equals("ADD"))||(sins[k][l].equals("SUB"))||(sins[k][l].equals("MULT"))||(sins[k][l].equals("MOVER"))||(sins[k][l].equals("MOVEM"))||(sins[k][l].equals("COMP"))||(sins[k][l].equals("BC"))||(sins[k][l].equals("DIV"))||(sins[k][l].equals("READ"))||(sins[k][l].equals("PRINT")))
				{
				
					lc[k+1] = lc[k]+1;	
						
				}

				//...........ASSEMBLY DIRECTIVES................				
				if(sins[k][l].equals("ORIGIN")||sins[k][l].equals("EQU")||sins[k][l].equals("LTORG")||sins[k][l].equals("LTROG"))
				{
						if(sins[k][l].equals("LTORG")||sins[k][l].equals("LTROG"))
						{
							lc[k+1] = lc[k]+lt.itr1;
							//System.out.println(lt.itr1);
							p.insert(lt.itr1);

						}
						if(sins[k][l].equals("ORIGIN"))
						{
							lc[k+1] = lc[k]+1;
						}
					
				}
					
				//..............DECLARATIVE S..................
				
				if(sins[k][l].equals("DS")||sins[k][l].equals("DC"))
				{
					if(sins[k][l].equals("DS"))
					{
						int xyz = Integer.parseInt(sins[k][l+1]);
						lc[k+1] = lc[k]+xyz;
					}
					if(sins[k][l].equals("DC"))
					{
						lc[k+1] = lc[k]+1;
					}
	
				}

				//.............REGISTER HANDLE...........
				
				if(sins[k][l].equals("EQU")||sins[k][l].equals("END"))
				{
					lc[k+1] = lc[k]+1;
				}	
					




			}

		}


//-------------------FILLED LOCATION ATTRIBUTE IN ALL TABLES-----------------------	
int h=0;
for(int c=0;c<count;c++)
	{
		if(ll[c]!=0)
		{
		  ss.sym[h][1] = Integer.toString(lc[c]);
			h++;	
		}
	
	}

int g=0;

	for(int k=1;k<count;k++)
		{
			for(int l=0;l<3;l++)
			{
				if(sins[k][l].equals("LTORG")||sins[k][l].equals("LTROG"))
				{
					g = lc[k];
							
					
						for(int c=p.arr[lpools];c<p.arr[lpoole];c++)
						{
			
							lt.lit[c][1] = (Integer.toString(g));
							g++;
						}
						lpools++;
						lpoole++;
				}
			
				if(sins[k][l].equals("DS")||sins[k][l].equals("DC"))
				{

					for(int f=0;f<ss.itr1;f++)
					{						
						if(ss.sym[f][0].equals(sins[k][l-1]))
						{
							ss.sym[f][1] = Integer.toString(lc[k]);
						}
			
								
					}
	
				}
				
				if(sins[k][l].equals("EQU"))
				{
						String uy="";
					for(int f=0;f<ss.itr1;f++)
					{						
						if(sins[k][l+1].contains(ss.sym[f][0]))
						{
								uy = ss.sym[f][1];						
							for(int m=0;m<ss.itr1;m++)
							{
								if(ss.sym[m][0].equals(sins[k][l-1]))
								{
									ss.sym[m][1] = uy;		
								}
							
							}

						}
			
								
					}
				}	
					
	
				

	
			}
		}
	




//.............for intermediate code third parameter.....................................................................................................

		for(int k=1;k<count;k++)
		{
			for(int l=0;l<3;l++)
			{
					

				//...........(L,0)...............
				if(sins[k][l].contains("'")&&!(sins[k][l-1].equals("DC")))
				{

					int flag = lt.find(sins[k][l]);
					String o = Integer.toString(flag);
					ic[k] = ic[k] + "(L,"+o+")";

					for(int xx=0;xx<lt.itr1;xx++)
					{
						if(lt.lit[xx][0].equals(sins[k][l]))
						{
							mc[k] = mc[k] + lt.lit[xx][1];
						}
					}	
				}

				//..........(C,0)................
				if(sins[k][l].equals("DS")||sins[k][l].equals("DC"))
				{
					
					ic[k] = ic[k] + "(C,"+sins[k][l+1]+")";



				}				




				//............(S,0)..................
				for(int x = 0;x<ss.itr1;x++)
				{		
					if(sins[k][l].equals(ss.sym[x][0]))
					{
						
						ic[k] = ic[k] + "(S,"+x+")"; 
						mc[k] = mc[k] + ss.sym[x][1];
					}	

			
				}

				/*for(int x = 0;x<ss.itr1;x++)
				{		
					if(sins[k][l].equals(ss.sym[x][0])&&!(sins[k][l].equals("DC"))&&!(sins[k][l].equals("DS")))
					{
						
					}
				}*/
				if(sins[k][l].equals("LTORG")||sins[k][l].equals("LTROG"))
				{
				
					for(int b=p.arr[pools];b<p.arr[poole];b++)
					{
						ic[k] = ic[k] + "(C,"+lt.lit[b][0]+")";	
						mc[k] = mc[k] + "00   00  "+lt.lit[b][0]+"    ";		
					}	
					pools++;
					poole++;
					 
					
				
				}
				

			}

		}

		System.out.println();
		System.out.println(".............................................................................");
		System.out.println("                                  PASS-I                                     ");
		System.out.println(".............................................................................");	
System.out.println();	
	System.out.println("------------INTERMEDIATE CODE---------------");
	System.out.println();
	for(int k=0;k<count;k++)
		{
			System.out.println(ic[k]);
		}



System.out.println();

System.out.println();
ss.display();
System.out.println();

System.out.println();
lt.display();


System.out.println();

System.out.println();
p.display();


System.out.println("------------LOCATION COUNTER---------------");
System.out.println();


for(int k=0;k<count;k++)
		{
			System.out.print("LC OF INSTRUCTION "+(k+1)+" :  ");
			System.out.println(lc[k]);
		}


System.out.println();
		System.out.println(".............................................................................");
		System.out.println("                                 PASS-II                                     ");
		System.out.println(".............................................................................");	
System.out.println();	
System.out.println("------------MACHINE CODE---------------");
		for(int k=0;k<count;k++)
		{
				System.out.println(mc[k]);
		}
/*
	Symbol ss = new Symbol();
	ss.insert("a",11);
	ss.insert("b",21);
	ss.display();	
*/		
	}


}
