package com.recepkabakci;

import java.time.LocalDate;

public class PersonFactory {
	
	public static Person create(String type) throws UnknownPersonTypeException {
		if (type.equalsIgnoreCase("öğrenci")) 
			return new Student("ali", "veli");
		
		if (type.equalsIgnoreCase("öğretmen")) {
			Teacher teacher = new Teacher.TeacherBuilder("babür", "somer", LocalDate.of(2021, 6, 15), 12000)
					.birthDate(LocalDate.of(1964, 11, 10))
					.married(true)
					.build();
			return teacher;
		}
			 
		if (type.equalsIgnoreCase("memur")) {
			Officer officer = new Officer.OfficerBuilder("mahmut", "ciner", LocalDate.of(2021, 6, 15), 8000)
					.birthDate(LocalDate.of(1964, 11, 10))
					.married(true)
					.build();
			return officer;
		}
		
		if (type.equalsIgnoreCase("çalışan")) {
			Janitor janitor = new Janitor.JanitorBuilder("deniz", "mutlu", LocalDate.of(2021, 6, 15), 5000)
					.birthDate(LocalDate.of(1964, 6, 10))
					.married(true)
					.gender("kadın")
					.build();
			return janitor;
		}
		
		throw new UnknownPersonTypeException("Unsupported person type <<< " + type + " >>>");
	}
}
