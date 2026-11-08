package kec.allocator;
import java.util.*;
public class StartupProposal {
 public int id; public String code,name,category; public double business,prototype,pitch,impact,feasibility;
 public final List<Founder> founders=new ArrayList<>();
 public record Founder(String name,double sgpa,boolean backlogCleared) {}
}
