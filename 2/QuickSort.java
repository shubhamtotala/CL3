/*
Assignment No.:

Title:Using Divide and Conquer Strategies to design an efficient class for Concurrent Quick Sort and the input data is stored using XML. Use object oriented software design method and Modelio/ StarUML2.x Tool. Perform the efficiency comparison with any two software design methods. Use necessary USE-CASE diagrams and justify its use with the help of mathematical modeling. Implement the design using Scala/Python/Java/C++.
*/

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class QuickSort {

private static int arr[];
	static int partition(int left,int right){
		int p,i,j;
		p=arr[left];
		i=left;
		j=right+1;
			while(true){
				while(arr[++i]<p){
					if(i>=right)
					break;
				}
				while(arr[--j]>p){
					if(j<=left)
					break;
				}
				if(i>=j)
				break;
				else{
				int temp=arr[i];
				arr[i]=arr[j];
				arr[j]=temp;
				}
			}

		int temp=arr[left];
		arr[left]=arr[j];
		arr[j]=temp;
		return j;
	}

	static void quicksort(final int left,final int right){
		final int q;
		if(right>left)
		{
			q=partition(left,right);
			new Thread(){
				public void run(){
					quicksort(left,q-1);
				}
			}.start();
	
			new Thread(){
				public void run(){
					quicksort(q+1,right);
				}
			}.start();
		}
	}

	public static void main(String []args)throws Exception{

		File xmlFile = new File("/home/ubuntu/final/2/input.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("input");
		arr=new int[nList.getLength()];
		
		for (int temp = 0; temp < nList.getLength(); temp++)
		arr[temp]=Integer.parseInt(((Element)nList.item(temp)).getElementsByTagName("no").
		item(0).getTextContent());
		quicksort(0,arr.length-1);
	
		System.out.println("\nSorted Array is\n");
		for(int no:arr){
		System.out.print(no+" ");
		}
	}
}


/*
xml input file:


<?xml version="1.0"?>
<arrInput>
	<input>
		<no>12</no>
	</input>
	<input>
		<no>41</no>
	</input>
	<input>
		<no>30</no>
	</input>
	<input>
		<no>10</no>
	</input>
	<input>
		<no>19</no>
	</input>
	<input>
		<no>1</no>
	</input>
	<input>
		<no>18</no>
	</input>
</arrInput>



Output:
projadmin@proj-8:~/Desktop$ javac QuickSort.java
projadmin@proj-8:~/Desktop$ java QuickSort

Sorted Array is

1 10 12 18 19 30 41
projadmin@proj-8:~/Desktop$ 
*/
