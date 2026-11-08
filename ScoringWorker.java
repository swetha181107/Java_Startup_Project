package kec.allocator;
import java.util.concurrent.Callable;
public class ScoringWorker implements Callable<EvaluationResult> {
 private final StartupProposal p; public ScoringWorker(StartupProposal p){this.p=p;}
 public EvaluationResult call(){String c=p.category.trim().toUpperCase(); double score; boolean threshold;
  switch(c){case "TECH HARDWARE" -> {score=.30*p.business+.50*p.prototype+.20*p.pitch; threshold=p.prototype>=60;}
   case "SOFTWARE", "SOFTWARE / SAAS", "SAAS" -> {score=.40*p.business+.40*p.prototype+.20*p.pitch; threshold=p.business>=50;}
   case "SOCIAL INNOVATION" -> {score=.50*p.impact+.30*p.feasibility+.20*p.pitch; threshold=p.impact>=50;}
   default -> throw new IllegalArgumentException("Unsupported category: "+c);}
  boolean academic=!p.founders.isEmpty() && p.founders.stream().allMatch(f->f.sgpa()>=6.0 && f.backlogCleared());
  double grant=0; String tier="REJECTED"; if(score>=90){grant=2_000_000;tier="TIER-1";} else if(score>=75){grant=1_000_000;tier="TIER-2";} else if(score>=60){grant=500_000;tier="TIER-3";}
  String status,disbursement; if(score<60||!threshold){status="REJECTED";grant=0;tier="REJECTED";disbursement="NOT_APPLICABLE";} else if(!academic){status="CONDITIONAL_APPROVAL";disbursement="ON_HOLD";} else {status="APPROVED";disbursement="READY";}
  return new EvaluationResult(p.id,p.code,score,threshold,academic,grant,tier,status,disbursement);
 }
}
