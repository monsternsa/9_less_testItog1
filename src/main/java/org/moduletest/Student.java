package org.moduletest;

import lombok.*;

import java.util.List;
import java.util.ArrayList;



@ToString @EqualsAndHashCode
public class Student {
    @Setter
    private StudentRepo repo; // добавили

    @Setter
    @Getter
    private String name;
    private List<Integer> marks=new ArrayList<>();
    public Student(String name)
    {
        this.name=name;
    }
    public void addMark(int mark)
    {
        if (mark <2||mark>5)       throw new IllegalArgumentException("Error mask:"+mark);

        marks.add(mark);
    }

    public List<Integer> getMarks() {
        return marks;
    }
@SneakyThrows
    public int rating()
    {
        return repo.getRatingForGradeSum(
                marks.stream()
                        .mapToInt(x->x)
                        .sum()
        );
    }
}
