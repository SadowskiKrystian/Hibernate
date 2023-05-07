package org.example;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();
        studentDao.save(new Student("Ramesh", "Fadatare", "rameshfadatare@javaguides.com"));
        studentDao.save(new Student("Adam", "Kulinski", "wp@wp.pl"));
        studentDao.save(new Student("Piotr", "Wozniak", "torrenty@o2.pl"));
        CarDao carDao = new CarDao();
        carDao.save(new Car("BMW", "7", "Black", LocalDate.now()));
        carDao.save(new Car("Porsche", "Panamera", "Red", LocalDate.now()));
        carDao.save(new Car("Seat", "Leon", "Green", LocalDate.now()));
//        carDao.find(new CarFilter()).stream()
//                .forEach(System.out::println);
        carDao.find(new CarFilter("seat", "leon")).forEach(System.out::println);

        List< Student > students = studentDao.find();
        students.forEach(s -> System.out.println(s.getFirstName()));
    }
}