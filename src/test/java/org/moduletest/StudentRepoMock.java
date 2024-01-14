package org.moduletest;
/*создаем клас заглушку у него не рализуем методы поскольку это методы работы с БД
 реализуем один метод  и его будем проверять */
public class StudentRepoMock implements StudentRepo {
    public int getRatingForGradeSum(int sum) {
        return 10;
    }

    public long count() {   return 0;   }
    public void delete(Student entity) {    }
    public void deleteAll(Iterable<Student> entities) {   }
    public Iterable<Student> findAll() {   return null;    }
    public Student save(Student entity) {   return null;   }
    public Iterable<Student> saveAll(Iterable<Student> entities) {      return null;
    }
}
