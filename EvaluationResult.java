package kec.allocator;
public record EvaluationResult(int startupId,String code,double score,boolean thresholdMet,boolean academicEligible,double grant,String tier,String status,String disbursement) {}
