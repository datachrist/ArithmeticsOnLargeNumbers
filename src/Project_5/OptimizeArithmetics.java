/**
 * 
 */
package Project_5;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hp
 *
 */
public class OptimizeArithmetics {


	/**
	 * 
	 * This method does base Conversion from base 10 to base 2^32
	 * 
	 * 
	 * 
	 * */
	LinkedList<Long> BaseConversion(LinkedList<Long> listBase10){
		LinkedList<Long> listBase232 = new LinkedList<Long>();
		
		
		
		
		
		return listBase232;
	}
	

		/**
		 * this method takes a linked list as parameter and returns the number that
		 * it represents as a string (in decimal) *
		 * 
		 * @param list1
		 * @param digitSize
		 * @return
		 */
		public static String NumToStr(LinkedList<Long> list1, int digitSize) {
			String number = null;
			ListIterator<Long> iter1 = list1.listIterator();
			while (iter1.hasNext()) {

				number = number.concat(iter1.next().toString());
			}

			return number;
		}

		/**
		 * Takes a string as parameter, that stores a number in decimal, and returns
		 * the list corresponding that number. The string can have arbitrary length.
		 * 
		 * @param number
		 * @param digitSize
		 * @return
		 */
		public static LinkedList<Long> StrToNum(String number, int digitSize) {
			LinkedList<Long> list = new LinkedList<Long>();
			int part = number.length(); // parts are created
			while (part > 0) {
				if (part - digitSize < 0)
					list.add(Long.parseLong(number.substring(0, part)));
				else
					list.add(Long.parseLong(number
							.substring(part - digitSize, part)));
				part -= digitSize;
			}
			return list;
		}

		/**
		 * 
		 * this method adds 2 numbers in different base
		 * */
		public static int addInOtherBase(int n1,int n2){
			
			// add only single digit number using bit wise operator
			
			return 0;
		}
		
		
		
		/**
		 * this method is used to add two large numbers using linked list
		 * 
		 * @param l1
		 * @param l2
		 * @return
		 */
		public static LinkedList<Long> add(LinkedList<Long> l1, LinkedList<Long> l2) {
			long base = 10;
			long carry = 0l;
			LinkedList<Long> l3 = new LinkedList<Long>();
			ListIterator<Long> iter1 = l1.listIterator();
			ListIterator<Long> iter2 = l2.listIterator();
			long sum = 0l, value1 = 0l, value2 = 0l;
			while ((iter1.hasNext()) && (iter2.hasNext())) {
				value1 = iter1.next();
				value2 = iter2.next();
				sum = value1 + value2 + carry;
				l3.add(sum % base);
				carry = sum / base;
			}
			while (iter1.hasNext()) {
				value1 = iter1.next();
				sum = value1 + carry;
				l3.add(sum % base);
				carry = sum / base;
			}
			while (iter2.hasNext()) {
				value2 = iter2.next();
				sum = value2 + carry;
				l3.add(sum % base);
				carry = sum / base;
			}
			if (carry > 0) {
				l3.add(carry);
			}
			return l3;
		}

		/**
		 * This method is used to return Returns the value of the first argument l1
		 * raised to the power of the second argument l2 i.e. l1^l2.
		 * 
		 * @param l1
		 * @param l2
		 * @return l3
		 */
		public static LinkedList<Long> power(LinkedList<Long> l1,
				LinkedList<Long> l2,boolean powerLimitFlag) {
			LinkedList<Long> l3 = new LinkedList<Long>();
			l3.add(1l);
			LinkedList<Long> dummyList = new LinkedList<Long>();
			dummyList.add(1l);
			long start = System.currentTimeMillis();
			long end = start + 15*1000;
			while (l2.getLast() > 0) { // Used subtract and multiple function to
										// generate power function
				
				if ((powerLimitFlag)&&(System.currentTimeMillis() > end)){
					break;
				}
				l2 = subtract(l2, dummyList);
				l3 = multiple(l1, l3);
			}

			return l3;
		}

		/**
		 * 
		 * This method is used to subtract two large numbers using linked list
		 * 
		 * @param l1
		 * @param l2
		 * @return
		 */
		public static LinkedList<Long> subtract(LinkedList<Long> l1,
				LinkedList<Long> l2) {
			long base = 10;
			long carry = 0l;
			LinkedList<Long> l3 = new LinkedList<Long>();
			ListIterator<Long> iter1 = l1.listIterator();
			ListIterator<Long> iter2 = l2.listIterator();
			long difference = 0l, value1 = 0l, value2 = 0l;
			while ((iter1.hasNext()) && (iter2.hasNext())) {
				value1 = iter1.next();
				value2 = iter2.next();
				difference = value1 - value2 + carry;
				if (difference < 0) {
					value1 += base;
					difference = value1 - value2 + carry;
					carry = -1;
				} else
					carry = 0;
				l3.add(difference);
			}
			while (iter1.hasNext()) {
				value1 = iter1.next();
				difference = value1 + carry;
				if (difference > 0) // if difference is zero then dont insert
					l3.add(difference);
				carry = 0;
			}
			while ((iter2.hasNext()) || (carry < 0)) { // if second variable is
														// bigger than first,
														// i.e. result is negative
														// then return zero
				l3 = new LinkedList<Long>();
				l3.add(0l);
				break;
			}
			return l3;

		}

		/**
		 * This method is used to multiple two large numbers using linked list
		 * 
		 * @param l1
		 * @param l2
		 * @return
		 */
		public static LinkedList<Long> multiple(LinkedList<Long> l1,
				LinkedList<Long> l2) {

			LinkedList<Long> l3 = new LinkedList<Long>();
			l3.add(0l);
			long base = 10;
			long product = 1l;
			for (int j = 0; j < l2.size(); j++) {

				for (int i = 0; i < l1.size(); i++) {

					product = l2.get(j) * l1.get(i);
					int shift = i + j;
					int outputlistLength = l3.size();
					for (int k = shift; product != 0; k++) {
						long digit = product % base;
						product /= base;

						int nodes = k - outputlistLength + 1;

						// Expand the width of the answer linked list if necessary
						for (int m = 0; m < nodes; m++) {
							l3.addFirst((0l));
						}

						outputlistLength = l3.size();

						// Add the current digit of the product to the corresponding
						// node in the answer
						int outputIdx = outputlistLength - k - 1;
						long curVal = l3.get(outputIdx);
						long newVal = curVal + digit;

						// Carry if necessary
						if (newVal >= base) {
							newVal -= base;
							product++;
						}

						l3.set(outputIdx, newVal);
					}
				}
			}
			ListIterator<Long> iter3 = l3.listIterator(l3.size());
			LinkedList<Long> l4 = new LinkedList<Long>();
			while (iter3.hasPrevious()) {
				l4.add(iter3.previous());
			}
			return l4;
		}
		
	/**
	 * this method is used to find division and mod of large numbers and return
	 * a linked list
	 * 
	 * */
	public static LinkedList<Long> divisionNMod(LinkedList<Long> n1,
			LinkedList<Long> n2, boolean modFlag) {
		LinkedList<Long> n3 = new LinkedList<Long>();

		long quotient = 0;
		int reminder = 0;
		LinkedList<Long> temp = new LinkedList<Long>();
		temp = n1;
		LinkedList<Long> qlist = new LinkedList<Long>();
		while (true) {

			if ((temp.size() == n2.size()) && (temp.getLast() < n2.getLast())) {
				break;
			}
			temp = subtract(temp, n2);
			// quotient++;
			LinkedList<Long> One = new LinkedList<Long>();
			One.add(1l);
			qlist = add(qlist, One);

		}

		if (modFlag) {
			return temp;
		}

		return qlist;
	}
		
		/**
		 * This method is used to find square root of large number using linked list
		 * */
	public static LinkedList<Long> sqrtOperation(LinkedList<Long> number) {
		LinkedList<Long> Quotient = new LinkedList<Long>();

		ListIterator<Long> iterNumb = number.listIterator(number.size());

		int numbSize = number.size();
		long digits = 0;
		int fdigit = 0;
		long divisor = 0l;
		LinkedList<Long> digitList = new LinkedList<Long>();
		LinkedList<Long> carryList = null;
		while (iterNumb.hasPrevious()) {

			if ((fdigit == 0) && (numbSize % 2 != 0)) {
				digits = iterNumb.previous();
				divisor = (int) Math.sqrt(digits);
				fdigit = 1;
				carryList = subtract(StrToNum(String.valueOf(digits), 10), StrToNum(String.valueOf(divisor*divisor), 10));
				Quotient.add(divisor);
			} 
			else if ((fdigit == 0) && (numbSize % 2 == 0)) {
				
				digits = iterNumb.previous() * 10 + iterNumb.previous();
				divisor = (int) Math.sqrt(digits);				
				carryList = subtract(StrToNum(String.valueOf(digits), 10), StrToNum(String.valueOf(divisor*divisor), 10));
				Quotient.add(divisor);
				fdigit = 1;
			}
			else{
				digits = iterNumb.previous() * 10 + iterNumb.previous();
				digitList = StrToNum(String.valueOf(digits), 10);

			if (carryList != null) {
				carryList = multiple(carryList, StrToNum("100", 10));
				digitList = add(digitList, carryList);
			}

			divisor *= 2;
			LinkedList<Long> divisorList = StrToNum(String.valueOf(divisor), 10);
			LinkedList<Long> iList = null;
			LinkedList<Long> intermediateList = new LinkedList<Long>();
			long i;
			for (i = 9; i > 0; i--) {
				divisorList.addFirst(i);
				iList = new LinkedList<Long>();
				iList.add(i);
				intermediateList = multiple(divisorList, iList);
				if ((intermediateList.size() == digitList.size())
						&& (subtract(digitList, intermediateList).get(0)>0)) {
					divisorList.add(i);
					break;
				}
				divisorList.removeFirst();
			}
			carryList = subtract(digitList, intermediateList);
			Quotient.add(i);
		}

		}
		
		ListIterator<Long> reverseIter = Quotient.listIterator(Quotient.size());		
		LinkedList<Long> l4 = new LinkedList<Long>();
		while (reverseIter.hasPrevious()) {
			l4.add(reverseIter.previous());
		}
		
		return l4;
	}
	
		/** The program takes input from stdin 
		 * @param args
		 */
		public static void main(String[] args) {

			Map<Integer, String> lineMap = new HashMap<Integer, String>();
			Scanner in = new Scanner(System.in);
			String line = null;
			String[] strArray = null;
			
			// reading each line from standard input and storing into hashmap(line number, string)
			while ((line = in.nextLine()).length() > 0) {
				strArray = line.split(" ");
				lineMap.put(Integer.parseInt(strArray[0]), strArray[1]);
			}

			Map<String, LinkedList<Long>> varMap = new HashMap<String, LinkedList<Long>>();
			Map<String,Boolean> varSignMap = new HashMap<String, Boolean>();
			
								
			// processing each row line by line
			for (int k = 0; k < lineMap.size(); k++) {
				
				String expr = lineMap.get(k + 1);
				
//				if (expr==null)
//					continue;
				Pattern p = Pattern.compile("[0-9]");
				Matcher m = p.matcher(expr);
				
				// checking for printing the value of the variable to stdout
				if (expr.length() == 1) {
					LinkedList<Long> list1 = varMap.get(expr);
					ListIterator<Long> iter3 = list1.listIterator(list1.size());
					StringBuffer outputStr = new StringBuffer();
					if(varSignMap.get(expr)){
					outputStr.append("-");	
					}
					
					while (iter3.hasPrevious()) {
						outputStr.append(iter3.previous().toString());
					}
					
					System.out.println(outputStr);
					
				} else if (expr.charAt(1) == '=') {
					
					// check for digits and sets x to be that number
					if (m.find()) {
						 Boolean negative = false;	
						 LinkedList<Long> list1 = new LinkedList<Long>();
					      if (expr.charAt(2) == '-'){
					    	  negative = true;
					    	  list1 = StrToNum(expr.substring(3), 1);
					      }
					      else	    
					    	  list1 = StrToNum(expr.substring(2), 1);
						varMap.put(expr.substring(0, 1), list1);
						varSignMap.put(expr, negative);
					} else {
						String var1 = expr.substring(2, 3);
						String var2="";
						if(expr.length()>4)
							var2 = expr.substring(4, 5);
						String var3 = expr.substring(0, 1);
						
						// check for addition of two numbers and assign to var on left
						if (expr.charAt(3) == '+') {
							if(varSignMap.get(var1)&&(varSignMap.get(var2))){
								varSignMap.put(var3, true);							
							varMap.put(var3,
									add(varMap.get(var1), varMap.get(var2)));
							}
							else if(varSignMap.get(var1)){
								LinkedList<Long> result = subtract(varMap.get(var2), varMap.get(var1));
								if (result.get(0)==0){
									result= subtract(varMap.get(var1), varMap.get(var2));
									varSignMap.put(var3, true);									
								}
								varMap.put(var3, result);
							}
							else if(varSignMap.get(var2)){
								LinkedList<Long> result = subtract(varMap.get(var1), varMap.get(var2));
								if (result.get(0)==0){
									result= subtract(varMap.get(var2), varMap.get(var1));
									varSignMap.put(var3, true);									
								}
								varMap.put(var3, result);
							}
							else{
								varMap.put(var3,
										add(varMap.get(var1), varMap.get(var2)));
								varSignMap.put(var3, false);		
							}
									
							
						// check for multiplication of two numbers
						} else if (expr.charAt(3) == '*') {

						if (varSignMap.get(var1) && (varSignMap.get(var2))) {
							varSignMap.put(var3, false);

						} else if ((varSignMap.get(var1))
								|| varSignMap.get(var2)) {

							varSignMap.put(var3, true);
						}

						else {
							varSignMap.put(var3, false);
						}
									
							
						// check for multiplication of two numbers
						
							
							varMap.put(var3,
									multiple(varMap.get(var1), varMap.get(var2)));
							
						// check for power 
						} else if (expr.charAt(3) == '^') {
							if (varMap.get(var2).getFirst()%2==0)
								varSignMap.put(var3, false);
							else
								varSignMap.put(var3, true);
							
							varMap.put(var3,
									power(varMap.get(var1), varMap.get(var2),false));
							
						// check for difference of two numbers
						} else if (expr.charAt(3) == '-') {
							if(varSignMap.get(var2))
								varSignMap.put(var3, false);
							else
								varSignMap.put(var3, true);
							varMap.put(var3,
									subtract(varMap.get(var1), varMap.get(var2)));
						}
						else if(expr.charAt(3) == '/') {
							if (varSignMap.get(var1) && (varSignMap.get(var2))) {
								varSignMap.put(var3, false);

							} else if ((varSignMap.get(var1))
									|| varSignMap.get(var2)) {

								varSignMap.put(var3, true);
							}

							else {
								varSignMap.put(var3, false);
							}									
							
							varMap.put(var3, divisionNMod(varMap.get(var1), varMap.get(var2), false));
						}
						else if(expr.charAt(3) == '%') {
							if (varSignMap.get(var1) && (varSignMap.get(var2))) {
								varSignMap.put(var3, false);

							} else if ((varSignMap.get(var1))
									|| varSignMap.get(var2)) {

								varSignMap.put(var3, true);
							}

							else {
								varSignMap.put(var3, false);
							}
							
							varMap.put(var3, divisionNMod(varMap.get(var1), varMap.get(var2), true));
						}
						else if(expr.charAt(3) == '~'){
							if(varSignMap.get(var1))
									varSignMap.put(var3, true);
							else
								varSignMap.put(var3, false);
							varMap.put(var3, sqrtOperation(varMap.get(var1)));
						}
						else if(expr.charAt(3) == ')'){
							
							varMap.put(var3,
									power(varMap.get(var1), varMap.get(var1),true));
						}
					}

					// if var value is not 0, then go to Line number
				} else if (expr.charAt(1) == '?') {
					LinkedList<Long> list1 = varMap.get(expr.substring(0, 1));
					
					//  check condition if var value is not 0. 
					if ((list1.size() == 1) && (list1.get(0) == 0))
						continue;
					else
						k = Integer.parseInt(expr.substring(2, 3)) - 2;
				}

			}
			 
		}
	}

