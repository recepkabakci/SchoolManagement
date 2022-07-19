package com.bilgeadam.marathon02;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.bilgeadam.util.BAUtil;

public class SchoolManagementSystem {
	private static final int         EXIT = 99;
	private HashMap<Integer, String> menuItems;
	private List<Employee>           employees;
	private List<Student>            students;
	private List<Class>              classes;

	public SchoolManagementSystem() {
		super();
		this.menuItems = new HashMap<>();
		this.employees = new LinkedList<>();
		this.students  = new LinkedList<>();
		this.classes   = new LinkedList<>();
	}

	public static void main(String[] args) {
		BAUtil.header("Okul Yönetim Sistemi");
		SchoolManagementSystem school = new SchoolManagementSystem();
		school.menu();
		BAUtil.footer();
	}

	private void menu() {
		this.initMenuItems();

		boolean exit = false;
		while (!exit) {
			int sel = BAUtil.menu(menuItems);
			if (sel == SchoolManagementSystem.EXIT) {
				exit = BAUtil.wantToEnd("Gerçekten mi?", "Hayır");
				continue;
			}
			try {
				this.processSelection(sel);
			}
			catch (UnknownPersonTypeException ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	private void processSelection(int sel) throws UnknownPersonTypeException {
		switch (sel) {
			case 1:
				this.employees.add((Employee) PersonFactory.create("öğretmen"));
				this.employees.add((Employee) PersonFactory.create("çalışan"));
				this.employees.add((Employee) PersonFactory.create("memur"));
				this.employees.add((Employee) PersonFactory.create("öğretmen"));
				Teacher teacher = (Teacher) PersonFactory.create("öğretmen");
				teacher.setEndDate(LocalDate.of(2022, 5, 25));
				this.employees.add(teacher);
				this.employees.add((Employee) PersonFactory.create("çalışan"));
				this.employees.add((Employee) PersonFactory.create("memur"));
				this.employees.add((Employee) PersonFactory.create("öğretmen"));
				this.employees.add((Employee) PersonFactory.create("öğretmen"));
				this.employees.add((Employee) PersonFactory.create("çalışan"));
				this.employees.add((Employee) PersonFactory.create("memur"));
				this.employees.add((Employee) PersonFactory.create("öğretmen"));
				this.employees.add((Employee) PersonFactory.create("öğretmen"));
				this.employees.add((Employee) PersonFactory.create("çalışan"));
				this.employees.add((Employee) PersonFactory.create("memur"));
				teacher = (Teacher) PersonFactory.create("öğretmen");
				teacher.setStartDate(LocalDate.of(2019, 5, 25));
				this.employees.add(teacher);
				break;
			case 2:
				for (Employee employee : employees) {
					if (employee.getEndDate() == null)
						System.out.println(employee);
				}
				break;
			case 3:
				for (Employee employee : employees) {
					if (employee.getEndDate() != null)
						System.out.println(employee);
				}
				break;
			case 4:
				Class c1 = new Class("Java 1", 15);
				c1.setAssistant((Teacher) this.employees.get(0));
				c1.setMaster((Teacher) this.employees.get(3));
				Class c2 = new Class("Java 2", 10);
				this.classes.add(c1);
				// c1.setMaster((Teacher)this.employees.get(6)); ==> hata verir
				this.classes.add(c2);
				break;
			case 5:
				Class clazz = this.classes.get(0);
				Student student = new Student("Eralp", "Nitelik");
				student.addClass(clazz);
				clazz.addStudent(student);
				this.students.add(student);
				
				student = new Student("Berna Baykan", "Dere");
				student.addClass(clazz);
				clazz.addStudent(student);
				this.students.add(student);
				student = new Student("Çağatay", "Ünal");
				student.addClass(clazz);
				clazz.addStudent(student);
				this.students.add(student);
				student = new Student("Can", "Demirhan");
				student.addClass(clazz);
				clazz.addStudent(student);
				this.students.add(student);
				student = new Student("Cebrail", "Kaplan");
				student.addClass(clazz);
				clazz.addStudent(student);
				this.students.add(student);
				

				clazz = this.classes.get(1);
				student.addClass(clazz);
				clazz.addStudent(student);
				
				student = new Student("Elif", "Yıldırım");
				student.addClass(clazz);
				clazz.addStudent(student);
				this.students.add(student);
				student = new Student("Gözde", "Saygılı");
				student.addClass(clazz);
				clazz.addStudent(student);
				this.students.add(student);
				student = new Student("Hande", "Hüdayioğlu");
				student.addClass(clazz);
				clazz.addStudent(student);
				this.students.add(student);


				break;
			case 6:
				for (Class clas : classes) {
					System.out.println(clas);
				}
				break;
				
			case 7:
				for (Student stud : students) {
					System.out.println(stud);
					System.out.println(stud.listClasses());
				}
				break;
			case 8:
				LocalDate today = LocalDate.now();
				int actMonth = today.getMonthValue();
				System.out.println("Bu ay işe başlayan çalışanlar:");
				for (Employee employee : employees) {
					if (employee.getEndDate() == null) {
						if (employee.getStartDate().getMonthValue() == actMonth) {
							System.out.println("\t" + employee.getFirstName() + " " + employee.getLastName());
						}
					}
				}

				System.out.println("Bu ay doğum günü olan kadın çalışanlar listesi:");
				for (Employee employee : employees) {
					if (employee.getEndDate() == null) {
						if (employee.getBirthDate().getMonthValue() == actMonth
								&& employee.getGender().equalsIgnoreCase("kadın")) {
							System.out.println("\t" + employee.getFirstName() + " " + employee.getLastName());
						}
					}
				}

				break;
			case 9:
				for (Employee employee : employees) {
					if (employee.getEndDate() == null)
						System.out.println(employee.salaryHistory());
				}
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + sel);
		}
	}

	private void initMenuItems() {
		this.menuItems.put(1, "Yeni çalışan ekle");
		this.menuItems.put(2, "Çalışan listesi");
		this.menuItems.put(3, "Eski çalışanlar");
		this.menuItems.put(5, "Öğrenci atama");
		this.menuItems.put(4, "Yeni sınıf");
		this.menuItems.put(6, "Sınıf listesi");
		this.menuItems.put(7, "Öğrencinin sınıfları");
		this.menuItems.put(8, "Hediye Listesi");
		this.menuItems.put(9, "Maaş Listesi");
		this.menuItems.put(SchoolManagementSystem.EXIT, "Programı bitir");
	}
}
