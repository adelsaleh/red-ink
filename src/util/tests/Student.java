import java.io.*;
import java.util.*;

public class Student extends Person {
	private String email;
	private int grade;

	public Student(String email, int grade) { super(); } 

	public int getGrade() { return 0; }

	public boolean hasGrade() { return false; }

	public void setGrade(int newgrade) { return ; }
}