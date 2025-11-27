import java.text.SimpleDateFormat;
import java.util.*;

public class FoodSupplierA {

	private Map<Date, LinkedList<Order>> supply;

	public Map<Date, LinkedList<Order>> getSupply() {
		return supply;
	}

	public void setSupply(Map<Date, LinkedList<Order>> supply) {
		this.supply = supply;

	}

	public FoodSupplierA() {
		supply = new TreeMap<Date, LinkedList<Order>>();
	}

	public void add(String s) {
		// m d y
		// y m d
		String[] x = s.split(" ");
		String[] d = x[0].split("-");
		Date b = new Date(Integer.parseInt(d[2]) , Integer.parseInt(d[0]) - 1, Integer.parseInt(d[1]));
//		b.setMonth(Integer.parseInt(d[0]) - 1);
//		b.setDate(Integer.parseInt(d[1]));
//		b.setYear(Integer.parseInt(d[2]) - 1900);
//		
		
		
		
		
//		String pattern = "MM-dd-yyyy";
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//				

		Order o = new Order(s);
		if (supply.containsKey(b)) {
			// System.out.println("hi");
			LinkedList<Order> n = new LinkedList<Order>();
			n = supply.get(b);
			// System.out.println("bye");
			supply.put(b, n);
			// System.out.println(supply.get(b));
		} else {
			LinkedList n = new LinkedList<Order>();
			n.add(o);
			supply.put(b, n);
			o.addToLink(n, o);

		}
		// list.add(o);

	}

	public Date hasMost(String type) {
		int most = 0;
		Date d = new Date();
		for (Date x : supply.keySet()) {
			for (Order z : supply.get(x))
				if (z.getType(type) > most) {
					most = z.getType(type);
					
					Date y = new Date(x.getYear()+3,x.getMonth() - 1,x.getDate());
					d=y;
				}
		}
		return d;
		
	}
	public String hasMostCompany(String type) {
		int most =0;
		String company = "";
		for(Date x:supply.keySet()) {
			for(Order z : supply.get(x)) {
				if(z.getType(type) > most) {
					most = z.getType(type);
					company = z.getCompany();
				}
			}
		}
		return company;
	}
	
	//fix this later 
	public String longestWaitTime() {
	    int longestWait = 0;
	    String companyWithLongestWait = null;

	    for (Date date : supply.keySet()) {
	        LinkedList<Order> orders = supply.get(date);
	        if (!orders.isEmpty()) {
	            Map<String, Date> lastOrderDates = new HashMap<>();
	            for (Order order : orders) {
	                String company = order.getCompany();
	                Date lastOrderDate = lastOrderDates.get(company);
	                if (lastOrderDate != null) {
	                    long difference = date.getTime() - lastOrderDate.getTime();
	                    int daysBetweenOrders = (int) (difference / (1000 * 60 * 60 * 24));
	                    if (daysBetweenOrders > longestWait) {
	                        longestWait = daysBetweenOrders;
	                        companyWithLongestWait = company;
	                    }
	                }
	                lastOrderDates.put(company, date);
	            }
	        }
	    }

	    return companyWithLongestWait;
	}
	
	public String companyThatOrderedTheMost(String type, int year) {
		int most = 0;
		String company = "null";
		for(Date date : supply.keySet()) {
			if( date.getYear() == year) {
				for(Order order : supply.get(date)) {
					if(order.getType(type) > most) {
						company = order.getCompany() + "#" +  order.getRestraunt() ;
					}
				}
			}
		}
		return company;
	}
	public Set allOrdersDay(int day) {
		Set<Order> all = new HashSet();
		for(Date date : supply.keySet()) {
			if(date.getDay()==day) {
				for(Order order : supply.get(date)) {
					all.add(order);
				}
			}
		}
		return all;
	}
	public Set allOrdersMonth(int month) {
		Set<Order> all = new HashSet();
		for(Date date : supply.keySet()) {
			if(date.getMonth() == month) {
				for(Order order : supply.get(date)) {
					all.add(order);
				}
			}
		}
		return all;
	}
	
	//wtf this one even mean koby bryant
	public Set duplicateOrders(int day, int month, int year) {
		Set<Order> all = new HashSet();
		for(Date date : supply.keySet()) {
			if(date.getDay() == day &&date.getMonth() == month && date.getYear() == year ) {
				Order[] x = (Order[]) supply.get(date).toArray();
				for(int i=0; i <x.length; i++) {
					
				}
			}
			
		}
		return all;
	}
	public void summaryDateInterval(String company, Date one, Date two) {
		Order sum = new  Order();
		for(Date date : supply.keySet()) {
			
			if(one.getYear() <= date.getYear() && one.getMonth() <= date.getMonth() && one.getDay() <= date.getDay() &&  date.getYear() <= two.getYear() && date.getMonth() <= two.getMonth() && date.getDay() <= two.getDay()) {
				
				for(Order order :  supply.get(date)) {
					if(order.getCompany() ==company) {
						int burgers = sum.getBurger() + order.getBurger();
						int buns = sum.getBuns() + order.getBuns();
						int straws = sum.getStraw() + order.getStraw();
						int syrup = sum.getSyrup() + order.getSyrup();
						int fries = sum.getFries() + order.getFries();
						int cups = sum.getCup() + order.getCup();
						int napkins = sum.getNapkin() + order.getNapkin();
						sum.setBuns(buns);
						sum.setBuns(burgers);
						sum.setBuns(straws);
						sum.setBuns(syrup);
						sum.setBuns(fries);
						sum.setBuns(cups);
						sum.setBuns(napkins);
					}
					}
					
			}
			
			
		}
		
		System.out.println(sum.toString());
	
	
	}
	public void summaryFoodInterval(String type, Date one, Date two) {
		int sum = 0;
		for(Date date : supply.keySet()) {
			
			if(one.getYear() <= date.getYear() && one.getMonth() <= date.getMonth() && one.getDay() <= date.getDay() &&  date.getYear() <= two.getYear() && date.getMonth() <= two.getMonth() && date.getDay() <= two.getDay()) {
				
				for(Order order :  supply.get(date)) {
						
					sum += order.getType(type);
					}
					
			}
			
			
		}
		
		System.out.println(sum);
	
	
	}
	
	
	
	
	
	

	// get number of burgers for UNO DAY
	public int getTotalFood(Date date, String food) {

		int sum = 0;
		LinkedList<Order> list = new LinkedList<Order>();
		list = supply.get(date);

		for (Object x : list.toArray()) {

			sum += ((Order) x).getType(food);
		}
		return sum;
	}

	public boolean notEmpty(Date date) {
		if (supply.get(date) == null) {
			return true;
		} else
			return false;
	}

	// complete burger :)
	public String wholeBurger(Date date) {
		int burgers = 0;
		
		System.out.println(supply.get(date).toString());
		
		for (Order x : supply.get(date)) {

			burgers += x.getBurger();
		}
		
		int buns = 0;
		
		for (Order x : supply.get(date)) {
			buns += x.getBuns();
		}
		System.out.println(burgers);
		System.out.println(buns);
		if (buns > burgers) {
			if (buns / 2 == burgers) {
				return "you have " + buns / 2 + " total burgers";
			} else {
				return "you have " + buns / 2 + " total burgers and " + buns % 2 + " extra buns";
			}
		} else {
			return "you have " + buns / 2 + " total burgers and " + burgers % 2 + " extra burgers";
		}

	}

//	public String hasMostComp(String type) {
//		int most = 0;
//		String comp = "";
//		// Date y = new Date();
//
//		for (Date x : supply.keySet()) {
//			if (supply.get(x).getType(type) > most) {
//				most = supply.get(x).getType(type);
//				comp = supply.get(x).getCompany();
//			}
//		}
//		return comp;
//	}

	public Set allOrdersDay(Date date) {
		Set<Order> orders = new TreeSet<Order>();

		for (Date x : supply.keySet()) {
			if (x.compareTo(date) == 0) {
				for (Order y : supply.get(x)) {

					orders.add(y);
				}
			}
		}

		return orders;
	}

	public Set allOrdersMonth(Date date) {

		Set<Order> orders = new TreeSet<Order>();

		for (Date x : supply.keySet()) {
			if (date.getYear() == x.getYear()) {
				if (date.getMonth() == x.getMonth()) {
					orders.add(supply.get(x));
				}
			}
		}

		return orders;
	}

	public String toString() {
		String pattern = "MM-dd-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String out = "";
		for (Date x : supply.keySet()) {
			out += simpleDateFormat.format(x) + "\n";

			for (Order y : supply.get(x)) {
				out += y.toString() + " \n";
			}

			out += "\n";
		}

		return out;
	}

}
