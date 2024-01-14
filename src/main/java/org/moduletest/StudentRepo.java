package org.moduletest;

/*создаем класс интерфейс для будущей заглушке*/

public interface StudentRepo {
   int getRatingForGradeSum(int sum);
   long count();
   void delete(Student entity);
   void deleteAll(Iterable<Student> entities);
   Iterable<Student> findAll();
   Student save(Student entity);
   Iterable<Student> saveAll(Iterable<Student> entities);

}
