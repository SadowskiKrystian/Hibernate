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
        carDao.find(CarFilter.builder().startDate(LocalDate.of(2022, 06, 10)).finishDate(LocalDate.of(2024, 06, 12)).build()).forEach(System.out::println);
        System.out.println("-------------------------------------------------------------------------------");
        carDao.findUsingCriteriaBuilder(
                        CarFilter.builder()
                                .model("leon")
                                .build())
                .forEach(System.out::println);
    }
}