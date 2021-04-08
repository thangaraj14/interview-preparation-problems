package java8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CustomSpliterator {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/Users/vignesh_rajarajan/Documents/learning/Problems/src/java8/Books.txt");
        try(Stream<String> lines = Files.lines(path)){
            Spliterator<String> baseSpliterator = lines.spliterator();
            Spliterator<Book> spliterator= new BookSpliterator(baseSpliterator);
            Stream<Book> stream=StreamSupport.stream(spliterator,false);
            stream.forEach(System.out::println);

        }

        Path path1 = Paths.get("/Users/vignesh_rajarajan/Documents/learning/Problems/src/java8/EmployeeData.txt");

        try(Stream<String> lines = Files.lines(path1)){
            Stream<String> words= lines.flatMap(line-> Arrays.stream(line.split(",")));
            Spliterator<String> baseSpliterator = words.spliterator();
            Spliterator<Employee> spliterator= new EmployeeSpliterator(baseSpliterator);
            Stream<Employee> empStream=StreamSupport.stream(spliterator,false);

            List<Employee> employeeList= empStream.collect(Collectors.toList());

            System.out.println(employeeList.stream().map(Employee::getName).collect(Collectors.joining(","+"\n")));

            TreeSet<Employee> tree=employeeList.stream().collect(Collectors.toCollection(TreeSet::new));

            Map<Integer,String> map=employeeList.stream().collect(Collectors.toMap(Employee::getId,Employee::getName));

            Map<Boolean,List<Employee>> partitionedList=employeeList.stream().collect(Collectors.partitioningBy(e->e.getGender()=='M'));

            Map<String,List<Employee>> groupByDesignation= employeeList.stream().collect(Collectors.groupingBy(Employee::getDesignation));
        }
    }
}

class Book{
    private String name;
    private String author;
    private String genre;
    private Double rating;

    public Book(String name, String author, String genre, Double rating) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                '}';
    }
}

class BookSpliterator implements Spliterator<Book>{

    private String name;
    private String author;
    private String genre;
    private Double rating;
    private Spliterator<String> baseSpliterator;

    public BookSpliterator(Spliterator<String> baseSpliterator) {
        this.baseSpliterator = baseSpliterator;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Book> action) {
        if(this.baseSpliterator.tryAdvance(name ->this.name=name ) &&
            this.baseSpliterator.tryAdvance(author->this.author=author) &&
           this.baseSpliterator.tryAdvance(genre->this.genre=genre) &&
           this.baseSpliterator.tryAdvance(rating->this.rating=Double.valueOf(rating))
        ){
            action.accept(new Book(this.name, this.author,this.genre,this.rating));
            return true;
        }
        return false;
    }

    @Override
    public Spliterator<Book> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return baseSpliterator.estimateSize()/4; // because the book txt has 4 lines for each book
    }

    @Override
    public int characteristics() {
        return this.baseSpliterator.characteristics();
    }
}

 class Employee implements Comparable<Employee>{

    private int id;
    private String name;
    private char gender;
    private Date dob;
    private String city;
    private String designation;
    private Date joiningDate;
    private double salary;

    public Employee(int id, String name, char gender, Date dob, String city, String designation,
                    Date joiningDate, double salary) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.city = city;
        this.designation = designation;
        this.joiningDate = joiningDate;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", gender=" + gender + ", dob=" + dob + ", city=" + city
                + ", designation=" + designation + ", joiningDate=" + joiningDate + ", salary=" + salary + "]";
    }

    @Override
    public int compareTo(Employee o) {

        if(this.id < o.id)
            return -1;
        else if(this.id > o.id)
            return 1;
        else
            return 0;
    }

}

class EmployeeSpliterator implements Spliterator<Employee> {

    private Spliterator<String> wordSpliterator;
    private int id;
    private String name;
    private char gender;
    private Date dob;
    private String city;
    private String designation;
    private Date joiningDate;
    private double salary;




    public EmployeeSpliterator(Spliterator<String> wordSpliterator) {
        this.wordSpliterator = wordSpliterator;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Employee> action) {

        if(this.wordSpliterator.tryAdvance(word -> this.id = Integer.valueOf(word))
                && this.wordSpliterator.tryAdvance(word -> this.name = word)
                && this.wordSpliterator.tryAdvance(word -> this.gender = word.charAt(0))
                && this.wordSpliterator.tryAdvance(word -> this.dob = Date.valueOf(word))
                && this.wordSpliterator.tryAdvance(word -> this.city = word)
                && this.wordSpliterator.tryAdvance(word -> this.designation = word)
                && this.wordSpliterator.tryAdvance(word -> this.joiningDate = Date.valueOf(word))
                && this.wordSpliterator.tryAdvance(word -> this.salary = Double.valueOf(word))
        ) {
            action.accept(new Employee(this.id,
                    this.name,
                    this.gender,
                    this.dob,
                    this.city,
                    this.designation,
                    this.joiningDate,
                    this.salary
            ));
            return true;
        }

        return false;
    }

    @Override
    public Spliterator<Employee> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return wordSpliterator.estimateSize()/8;
    }

    @Override
    public int characteristics() {
        return wordSpliterator.characteristics();
    }

}


