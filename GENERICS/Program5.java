import java.util.*;
abstract class JobRole {
private String candidateName;
public JobRole(String candidateName) {
this.candidateName = candidateName;
}
public String getCandidateName() {
return candidateName;
}
public abstract String getRoleName();
public abstract List<String> getKeySkills();
}
class SoftwareEngineer extends JobRole {
public SoftwareEngineer(String candidateName) {
super(candidateName);
}
public String getRoleName() {
return "Software Engineer";
}
public List<String> getKeySkills() {
return Arrays.asList("Java", "Spring", "Git", "System Design");
}
}
class DataScientist extends JobRole {
public DataScientist(String candidateName) {
super(candidateName);
}
public String getRoleName() {
return "Data Scientist";
}
public List<String> getKeySkills() {
return Arrays.asList("Python", "Machine Learning", "Pandas",
"Statistics");
}
}
class ProductManager extends JobRole {
public ProductManager(String candidateName) {
super(candidateName);
}
public String getRoleName() {
return "Product Manager";
}
public List<String> getKeySkills() {
return Arrays.asList("Agile", "Roadmapping", "User Research","Communication");
}
}
class Resume<T extends JobRole> {
private T jobRole;
public Resume(T jobRole) {
this.jobRole = jobRole;
}
public T getJobRole() {
return jobRole;
}
}
public class Program5 {
public static void screenResumes(List<? extends JobRole> roles) {
    for (JobRole role : roles) {
System.out.println("Candidate: " + role.getCandidateName());
System.out.println("Applying for: " + role.getRoleName());
System.out.println("Skills: " + String.join(", ",
role.getKeySkills()));
System.out.println();
}
}
public static void main(String[] args) {
List<JobRole> resumes = new ArrayList<>();
resumes.add(new SoftwareEngineer("Alice"));
resumes.add(new DataScientist("Bob"));
resumes.add(new ProductManager("Charlie"));
screenResumes(resumes);
}
}
