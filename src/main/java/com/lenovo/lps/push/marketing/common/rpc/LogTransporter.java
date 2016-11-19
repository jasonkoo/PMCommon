package com.lenovo.lps.push.marketing.common.rpc;

import java.util.Collection;

import com.lenovo.lps.push.marketing.common.vo.Feedback;
import com.lenovo.lps.push.marketing.common.vo.FeedbackError;
import com.lenovo.lps.push.marketing.common.vo.HitResult;
import com.lenovo.lps.push.marketing.common.vo.dataentry.AppDataEntry;
import com.lenovo.lps.push.marketing.common.vo.dataentry.AppFeedbackDataEntry;

public interface LogTransporter {
	public String transportFeedback(Feedback feedBack);
	public String transportFeedback(Collection<Feedback> feedBacks);
	public String transportFeedbackError(FeedbackError error);
	public String transportFeedbackError(Collection<FeedbackError> errors);
	public String transportHitresult(HitResult hitResult);
	public String transportHitresult(Collection<HitResult> hitResults);
	public String transportAppInfo(AppDataEntry appDataEntry);
	public String transportAppInfo(Collection<AppDataEntry> arg0);
	public String transportAppFeedback(AppFeedbackDataEntry appFeedbackDataEntry);
	public String transportAppFeedback(Collection<AppFeedbackDataEntry> arg0);
}
